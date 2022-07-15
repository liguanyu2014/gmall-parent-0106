package com.atguigu.gmall.order.service.impl;
import java.math.BigDecimal;
import java.util.Date;

import com.atguigu.gmall.common.util.JSONs;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.model.payment.PaymentInfo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.order.service.PaymentInfoService;
import com.atguigu.gmall.order.mapper.PaymentInfoMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 *
 */
@Service
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoMapper, PaymentInfo>
    implements PaymentInfoService{

    @Override
    public void savePayment(Map<String, String> map, OrderInfo orderInfo) {
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOutTradeNo(map.get("out_trade_no"));
        paymentInfo.setOrderId(orderInfo.getId());
        paymentInfo.setUserId(orderInfo.getUserId());
        paymentInfo.setPaymentType("alipay");
        paymentInfo.setTradeNo(map.get("trade_no"));
        paymentInfo.setTotalAmount(new BigDecimal(map.get("invoice_amount")));
        paymentInfo.setSubject(map.get("subject"));
        paymentInfo.setPaymentStatus(map.get("trade_status"));
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setCallbackTime(new Date());

        paymentInfo.setCallbackContent(JSONs.toStr(map));

        //保存支付信息
        save(paymentInfo);

    }
}




