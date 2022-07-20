package com.atguigu.gmall.seckill.rpc;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.activity.SeckillGoods;
import com.atguigu.gmall.model.order.OrderInfo;
import com.atguigu.gmall.seckill.service.SeckillBizService;
import com.atguigu.gmall.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rpc/inner/seckill")
@RestController
public class SeckillRpcController {


    @Autowired
    SeckillGoodsService seckillGoodsService;

    @Autowired
    SeckillBizService seckillBizService;
    /**
     * 获取当天参与秒杀的所有商品
     * @return
     */
    @GetMapping("/goods/currentDay")
    public Result<List<SeckillGoods>> getCurrentDaySeckillGoods(){

        List<SeckillGoods> seckillGoods = seckillGoodsService.getCurrentDaySeckillGoods();
        return Result.ok(seckillGoods);
    }

    /**
     * 获取某个秒杀商品详情
     * @param skuId
     * @return
     */
    @GetMapping("/goods/detail/{skuId}")
    public Result<SeckillGoods> getGoodsDetail(@PathVariable("skuId")Long skuId){

        SeckillGoods goods = seckillGoodsService.getSeckillGood(skuId);
        return Result.ok(goods);
    }


    /**
     * 获取某个秒杀单详情
     */
    @GetMapping("/order/{skuId}/{code}")
    public Result<OrderInfo> getSeckillOrder(@PathVariable("code") String code,
                                             @PathVariable("skuId") Long skuId){
        //防止越权操作（）？把商品id和秒杀码都带来。  date+userid+skuid == code
        //越权：
        //1、横向越权： 已经得到授权的用户，利用身份拿到别人的资源进行CRUD。 由业务保证。
        //2、纵向越权： 已经得到授权的用户，利用身份拿到自己需要更多权限才能操作的资源。 @PreAuthorize。 由框架


        OrderInfo orderInfo = seckillBizService.getSeckillOrder(code,skuId);
        return Result.ok(orderInfo);
    }
}