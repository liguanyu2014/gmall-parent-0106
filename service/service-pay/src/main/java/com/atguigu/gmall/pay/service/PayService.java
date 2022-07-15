package com.atguigu.gmall.pay.service;

import com.alipay.api.AlipayApiException;

import java.util.Map;

public interface PayService {
    /**
     * 为订单生成支付页
     * @param orderId
     * @return
     */
    String generatePaypage(Long orderId) throws AlipayApiException;

    /**
     * 支付宝验签方法
     * @param params
     * @return
     */
    boolean checkSign(Map<String, String> params) throws AlipayApiException;
}
