package com.atguigu.gmall.seckill.service.impl;

import com.atguigu.gmall.common.constant.RedisConst;
import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.common.util.JSONs;
import com.atguigu.gmall.model.activity.SeckillGoods;
import com.atguigu.gmall.seckill.service.SeckillGoodsCacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SeckillGoodsCacheServiceImpl implements SeckillGoodsCacheService {

    @Autowired
    StringRedisTemplate redisTemplate;

    //当天参与秒杀的所有商品都会在这个Map中
    Map<Long, SeckillGoods> localCache = new ConcurrentHashMap<>();

    /**
     * 多级缓存操作
     *
     * @param day
     * @param goods
     */
    @Override
    public void saveToCache(String day, List<SeckillGoods> goods) {
        String cacheKey = RedisConst.SECKILL_GOODS_CACHE_PREFIX + "" + day;

        //1.双缓存写入
        goods.stream().forEach(item -> {
            //幂等性的放缓存,只会覆盖
            redisTemplate.opsForHash().put(cacheKey,item.getSkuId().toString(), JSONs.toStr(item));
            //2.给本地缓存一份
            localCache.put(item.getSkuId(),item);
        });

        //最好设置一个过期时间,不过每天秒杀结束后,定时任务盘点当天账单数据时也会删除秒杀缓存
        redisTemplate.expire(cacheKey,2, TimeUnit.DAYS);

    }

    @Override
    public List<SeckillGoods> getCachedSeckillGoods(String day) {
        //1、先查询本地缓存中的数据
        List<SeckillGoods> seckillGoodsList = localCache.values().stream()
                .sorted(((o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime())))
                .collect(Collectors.toList());

        log.info("本地秒杀缓存命中数据：{}", seckillGoodsList.size());

        if(seckillGoodsList == null || seckillGoodsList.size() == 0){
            //本地缓存没有,可能上次发生了宕机
            //则查询redis里缓存的当天上架的商品
            HashOperations<String, String, String> ops = redisTemplate.opsForHash();
            List<String> goodsJson = ops.values(RedisConst.SECKILL_GOODS_CACHE_PREFIX + "" + day);

            seckillGoodsList = goodsJson.stream()
                    .map(item -> JSONs.toObj(item, SeckillGoods.class))
                    .collect(Collectors.toList());

            log.info("分布式秒杀缓存命中数据");

            //3、放回本地
            seckillGoodsList.stream().forEach(item -> localCache.put(item.getSkuId(),item));
        }
        return seckillGoodsList;
    }

    @Override
    public SeckillGoods getSeckillGood(Long skuId) {
        SeckillGoods goods = localCache.get(skuId);
        if (goods == null) {
            //1、看下是否是宕机导致的？ 宕机导致的就同步下远程缓存。
            if(localCache.size() == 0){
                //本地缓存没数据，就可能是宕机了。调方法使 本地此时就同步上了远程数据
                getCachedSeckillGoods(DateUtil.formatDate(new Date()));
                goods = localCache.get(skuId);
                return goods;
            }
        }
        return goods;
    }
}
