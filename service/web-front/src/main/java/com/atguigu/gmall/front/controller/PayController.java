package com.atguigu.gmall.front.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.order.OrderFeignClient;
import com.atguigu.gmall.model.enums.OrderStatus;
import com.atguigu.gmall.model.order.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayController {

    @Autowired
    OrderFeignClient orderFeignClient;

    /**
     * 跳转到支付确认页
     *
     * @param orderId
     * @return
     */
    @GetMapping("/pay.html")
    public String payPage(@RequestParam("orderId") Long orderId, Model model) {
        Result<OrderInfo> result = orderFeignClient.getOrderInfoByIdAndUserId(orderId);
        if (OrderStatus.UNPAID.name().equals(result.getData().getOrderStatus())) {
            //横向越权。 如果只是订单id查询，可以查到别人的订单， 应该 orderId + userId
            model.addAttribute("orderInfo", result.getData());
            return "payment/pay";
        }
        //订单列表页
        return "redirect:/myOrder.html";
    }

    /**
     * 支付成功后跳转的页面
     * @return
     */
    @GetMapping("/payment/success.html")
    public String paySuccessPage(){
        //永远不要再这里拿到订单信息，修改为已支付;

        //1、浏览器故障没跳过来？（用户关了浏览器）
        //2、非法直接请求这个页面（如果忘记验证签名）。出现安全问题
        return "payment/success";
    }
}
