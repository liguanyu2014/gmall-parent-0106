package com.atguigu.gmall.order.mapper;


import com.atguigu.gmall.model.order.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.atguigu.gmall.order.domain.OrderDetail
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {


    /**
     * 根据订单id和用户id查询订单详情
     * @param orderId
     * @param userId
     * @return
     */
    List<OrderDetail> getOrderDetailsByOrderIdAndUserId(@Param("orderId") Long orderId, @Param("userId") Long userId);
}




