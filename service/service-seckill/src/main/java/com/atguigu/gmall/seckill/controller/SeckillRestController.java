package com.atguigu.gmall.seckill.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.result.ResultCodeEnum;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.seckill.service.SeckillBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/activity/seckill")
@RestController
public class SeckillRestController {

    @Autowired
    SeckillBizService seckillBizService;

    /**
     * 生成秒杀码
     * 这个方法需要用户登录
     * @return
     */
    @GetMapping("/auth/getSeckillSkuIdStr/{skuId}")
    public Result generateSeckillCode(@PathVariable("skuId") Long skuId){
        String code = seckillBizService.generateSeckillCode(skuId);
        return Result.ok(code);
    }

    /**
     * 秒杀下单
     * @param skuId
     * @param skuIdStr
     * @return
     */
    @PostMapping("/auth/seckillOrder/{skuId}")
    public Result seckillCreateOrder(@PathVariable("skuId") Long skuId,
                                     @RequestParam("skuIdStr") String skuIdStr){

        //秒杀异步下单
        seckillBizService.seckillOrderSubmit(skuId,skuIdStr);

        return Result.ok();
    }

    /**
     * 检查秒杀单状态
     * @param skuId
     * @return
     */
    @GetMapping("/auth/checkOrder/{skuId}")
    public Result checkSeckillOrder(@PathVariable("skuId") Long skuId){
        //1、SECKILL_RUN(211, "正在排队中"),
        //2、SECKILL_SUCCESS(215, "抢单成功"),
        //3、SECKILL_ORDER_SUCCESS(218, "下单成功"),
        //4、其他
        ResultCodeEnum status = seckillBizService.checkOrderStatus(skuId);
        return Result.build("",status);
    }

    /**
     * 提交秒杀单
     * @param orderInfo
     * @return
     */
    @PostMapping("/auth/submitOrder")
    public Result submitOrder(@RequestBody OrderInfo orderInfo){

        //把整个订单信息发消息出去。前端。
        Long orderId = seckillBizService.saveSeckillOrder(orderInfo);

        //给前端返回订单id即可，自动跳到支付页
        return Result.ok(orderId+"");
    }

}
