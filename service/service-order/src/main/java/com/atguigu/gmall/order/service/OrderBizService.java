package com.atguigu.gmall.order.service;

import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.model.vo.order.OrderConfirmVo;
import com.atguigu.gmall.model.vo.order.OrderSubmitVo;
import com.atguigu.gmall.model.vo.ware.OrderSpiltVo;
import com.atguigu.gmall.model.vo.ware.OrderSplitRespVo;
import com.atguigu.gmall.model.vo.ware.WareFenBuVo;

import java.util.List;


public interface OrderBizService {

    /**
     * 获取订单确认信息
     * @return
     */
    OrderConfirmVo getOrderConfirmData();

    /**
     * 生成一个交易令牌
     * @return
     */
    String generateTradeToken();

    /**
     * 校验令牌 前端传来的令牌
     * @param token
     * @return
     */
    boolean checkTradeToken(String token);

    /**
     * 提交订单
     * @param tradeNo
     * @param orderSubmitVo
     * @return
     */
    Long submitOrder(String tradeNo, OrderSubmitVo orderSubmitVo);

    /**
     * 保存订单
     * @param orderSubmitVo
     */
    OrderInfo saveOrder(String tradeNo,OrderSubmitVo orderSubmitVo);

    /**
     * 关闭订单
     * @param orderId
     * @param userId
     */
    void closeOrder(Long orderId, Long userId);

    /**
     * 拆单
     * @param vo
     * @return
     */
    List<OrderSplitRespVo> splitOrder(OrderSpiltVo vo);

    /**
     * 保存子订单
     * @param orderInfo
     * @param item
     */
    OrderSplitRespVo saveChildOrder(OrderInfo orderInfo, WareFenBuVo item);
}
