package com.atguigu.gmall.pay.notify;

import com.alipay.api.AlipayApiException;
import com.atguigu.gmall.common.constant.MqConst;
import com.atguigu.gmall.common.util.JSONs;
import com.atguigu.gmall.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 接受支付宝异步通知
 */
@Slf4j
@RestController
@RequestMapping("/rpcapi/payment")
public class AlipayNotifyController {

    @Autowired
    PayService payService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 支付宝只要付发现了有订单成功支付,就会往商家指定的地址发送
     *      异步通知: 1.分布式事务。 (最大努力方案) [消息服务]
     *              如果消息发送失败,支付宝会在25小时内 总试 8次
     * @return
     */
    @RequestMapping("/notify/success")
    public String paySuccessNotify(@RequestParam Map<String ,String> params) throws AlipayApiException {
        log.info("支付宝异步通知抵达：{}", JSONs.toStr(params));
        //1.签名验证
        boolean checked = payService.checkSign(params);
        if(checked){
            //2.验签通过
            //收到支付宝消息，就把成功支付的这个订单发消息给订单交换机
            //MQ保证所有业务的稳定运行与交互。分布式全异步全响应式系统。
            rabbitTemplate.convertAndSend(
                    MqConst.EXCHANGE_ORDER_EVENT,
                    MqConst.RK_ORDER_PAYED,
                    JSONs.toStr(params)
            );
            return "success";
        }
        return "error";
    }
}