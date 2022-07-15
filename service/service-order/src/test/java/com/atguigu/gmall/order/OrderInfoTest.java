package com.atguigu.gmall.order;

import com.atguigu.gmall.model.activity.CouponInfo;
import com.atguigu.gmall.model.order.OrderDetail;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.model.order.OrderStatusLog;
import com.atguigu.gmall.model.payment.PaymentInfo;
import com.atguigu.gmall.order.service.OrderDetailService;
import com.atguigu.gmall.order.service.OrderInfoService;
import com.atguigu.gmall.order.service.OrderStatusLogService;
import com.atguigu.gmall.order.service.PaymentInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
public class OrderInfoTest {
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    OrderDetailService orderDetailServicel;

    @Autowired
    PaymentInfoService paymentInfoService;

    @Autowired
    OrderStatusLogService orderStatusLog;

    @Test
    void testStatusLog(){
        OrderStatusLog log = new OrderStatusLog();
        log.setOrderId(3322L);
        log.setUserId(2233L);
        log.setOrderStatus("sdfg");
        log.setOperateTime(new Date());

        orderStatusLog.save(log);
        System.out.println("log = " + log);
    }

    @Test
    void testPayment(){
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOutTradeNo("234234234");
        paymentInfo.setUserId(333L);
        paymentInfo.setOrderId(333L);
        paymentInfo.setPaymentType("asdfasdf");
        paymentInfo.setTradeNo("asdfgsadf");
        paymentInfo.setTotalAmount(new BigDecimal("333"));
        paymentInfo.setSubject("333");
        paymentInfo.setPaymentStatus("333");
        paymentInfo.setCreateTime(new Date());
        paymentInfo.setCallbackTime(new Date());
        paymentInfo.setCallbackContent("333");

        paymentInfoService.save(paymentInfo);
        System.out.println("paymentInfo = " + paymentInfo);
    }

    @Test
    void testOrderDetaile(){
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(21345L);
        detail.setUserId(16L);
        detail.setSkuId(2345L);
        detail.setSkuName("asdf");
        detail.setImgUrl("asdf");
        detail.setOrderPrice(new BigDecimal("43125432"));
        detail.setSkuNum(3);
        detail.setHasStock("1");
        detail.setCreateTime(new Date());
        detail.setSplitTotalAmount(new BigDecimal("234234"));
        detail.setSplitActivityAmount(new BigDecimal("234234"));
        detail.setSplitCouponAmount(new BigDecimal("234234"));

        orderDetailServicel.save(detail);
        System.out.println("detail = " + detail);
    }

    @Test
    void testQuery(){
        QueryWrapper<OrderInfo>  info = new QueryWrapper<>();
        info.eq("user_id",16L);
        //info.eq("id",249);
        orderInfoService.list(info).stream().forEach(item->{
            System.out.println("查到："+item);
        });

        //雪花算法

    }

    @Test
    void testsharding(){
        OrderInfo info = new OrderInfo();
        info.setConsignee("adadasd");
        info.setConsigneeTel("231231");
        info.setTotalAmount(new BigDecimal("777"));
        info.setOrderStatus("1");
        info.setUserId(16L);
        info.setPaymentWay("aa");
        info.setDeliveryAddress("aaa");
        info.setOrderComment("ddd");
        info.setOutTradeNo("fdfsafdsa");
        info.setTradeBody("fdasfdasfdsa");
        info.setCreateTime(new Date());
        info.setExpireTime(new Date());
        info.setProcessStatus("ddd");
        info.setTrackingNo("fffff");
        info.setParentOrderId(0L);
        info.setImgUrl("ggggg");
        info.setOrderDetailList(Lists.newArrayList());
        info.setWareId("11");
        info.setProvinceId(0L);
        info.setActivityReduceAmount(new BigDecimal("0"));
        info.setCouponAmount(new BigDecimal("0"));
        info.setOriginalTotalAmount(new BigDecimal("0"));
        info.setRefundableTime(new Date());
        info.setFeightFee(new BigDecimal("0"));
        info.setOperateTime(new Date());
        info.setOrderDetailVoList(Lists.newArrayList());
        info.setCouponInfo(new CouponInfo());

        orderInfoService.save(info);

        System.out.println("保存成功：订单id："+info.getId());
    }

    @Test
    void  testreadwritesplite(){

    }
}
