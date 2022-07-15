package com.atguigu.gmall.order.service;

import com.atguigu.gmall.model.enums.OrderStatus;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.model.vo.order.OrderSubmitVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface OrderInfoService extends IService<OrderInfo> {

    /**
     * 保存订单明细
     * @param orderInfo
     * @param orderSubmitVo
     */
    void saveDetail(OrderInfo orderInfo, OrderSubmitVo orderSubmitVo);

    /**
     * 修改订单状态
     * @param orderId
     * @param userId
     * @param orderStatus
     * @param processStatus
     */
    void updateOrderStatus(Long orderId, Long userId, String orderStatus, String processStatus,String expectStatus);

    /**
     *  获取某个用户的指定订单信息
     * @return
     */
    OrderInfo getOrderInfoByIdAndUserId(Long id);

    /**
     * 修改订单为已支付状态
     * @param map
     */
    void orderPayedStatusChange(Map<String, String> map);

    /**
     * 先查出当前订单信息和详情
     * @param orderId
     * @param userId
     * @return
     */
    OrderInfo getOrderInfoAndDetails(long orderId, long userId);
}
