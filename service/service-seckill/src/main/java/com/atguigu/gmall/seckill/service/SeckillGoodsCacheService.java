package com.atguigu.gmall.seckill.service;

import com.atguigu.gmall.model.activity.SeckillGoods;

import java.util.List;

public interface SeckillGoodsCacheService {

    /**
     * 把查到的秒杀商品放到缓存
     * @param day
     * @param goods
     */
    void saveToCache(String day, List<SeckillGoods> goods);

    /**
     * 查询指定某天参与秒杀的所有商品(从缓存中获取)
     * @param day
     * @return
     */
    List<SeckillGoods> getCachedSeckillGoods(String day);

    /**
     * 获取秒杀商品详情,可以防止宕机。如果宕机后第一次调用,会自动同步远程 缓存
     * @param skuId
     * @return
     */
    SeckillGoods getSeckillGood(Long skuId);
}
