package com.atguigu.gmall.seckill.mapper;

import com.atguigu.gmall.model.activity.SeckillGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.atguigu.gmall.seckill.domain.SeckillGoods
 */
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    /**
     * 查询某天的所有秒杀商品
     * @param day
     * @return
     */
    List<SeckillGoods> getDaySeckillGoods(@Param("day") String day);

    /**
     * 扣秒杀库存
     * @param skuId
     * @param num
     * @return
     */
    long updateSeckillStockCount(@Param("skuId") Long skuId, @Param("num") int num);
}



