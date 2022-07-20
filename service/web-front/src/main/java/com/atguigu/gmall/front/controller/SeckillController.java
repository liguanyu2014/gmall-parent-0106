package com.atguigu.gmall.front.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.feign.seckill.SeckillFeignClient;
import com.atguigu.gmall.feign.user.UserFeignClient;
import com.atguigu.gmall.model.activity.SeckillGoods;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.model.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SeckillController {

    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    SeckillFeignClient seckillFeignClient;

    /**
     * 秒杀商品列表页
     * @param model
     * @return
     */
    @GetMapping("/seckill.html")
    public String seckillPage(Model model){
        Result<List<SeckillGoods>> seckillGoods = seckillFeignClient.getCurrentDaySeckillGoods();
        model.addAttribute("list",seckillGoods.getData());
        return "seckill/index";
    }

    /**
     * 秒杀商品详情页面
     * @param skuId
     * @return
     */
    @GetMapping("/seckill/{skuId}.html")
    public String seckillGoodsDetail(@PathVariable("skuId") Long skuId,
                                     Model model){
        Result<SeckillGoods> result = seckillFeignClient.getGoodsDetail(skuId);
        model.addAttribute("item",result.getData());
        return "seckill/item";
    }

    @GetMapping("/seckill/queue.html")
    public String queue(@RequestParam("skuId") Long skuId,
                        @RequestParam("skuIdStr") String code,
                        Model model){

        model.addAttribute("skuId",skuId);
        model.addAttribute("skuIdStr",code);
        return "seckill/queue";
    }

    /**
     * 秒杀订单确认页
     * @return
     */
    @GetMapping("/seckill/trade.html")
    public String seckillTrade(Model model,
                               @PathVariable("code") String code,
                               @PathVariable("skuId") Long skuId) {
//        //秒杀服务要到刚才的临时单
        Result<OrderInfo> order = seckillFeignClient.getSeckillOrder(code,skuId);
        OrderInfo data = order.getData();
        model.addAttribute("detailArrayList",data.getOrderDetailList());
        model.addAttribute("totalNum","1");
        model.addAttribute("totalAmount",data.getTotalAmount());
        Result<List<UserAddress>> address = userFeignClient.getUserAddress();
        model.addAttribute("userAddressList",address.getData());
        return "seckill/trade";
    }

}
