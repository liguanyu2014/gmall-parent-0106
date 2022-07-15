package com.atguigu.gmall.order.service;

import com.atguigu.gmall.model.order.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface OrderDetailService extends IService<OrderDetail> {

    /**
     * 根据订单id和用户id查询订单详情
     * @param orderId
     * @param userId
     * @return
     */
    List<OrderDetail> getOrderDetailsByOrderIdAndUserId(Long orderId, Long userId);
}
