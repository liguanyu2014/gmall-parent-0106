package com.atguigu.gmall.order.controller;

import com.atguigu.gmall.model.vo.ware.OrderSpiltVo;
import com.atguigu.gmall.model.vo.ware.OrderSplitRespVo;
import com.atguigu.gmall.order.service.OrderBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderSplitController {

    @Autowired
    OrderBizService orderBizService;

    /**
     * 拆单
     * @param vo
     * @return
     */
    @PostMapping("/orderSplit")
    public List<OrderSplitRespVo> splitOrder(OrderSpiltVo vo){
        //订单服务需要按照库存系统说的商品分布，重新把一个大订单，拆分成几个小订单独立存储
        List<OrderSplitRespVo> vos = orderBizService.splitOrder(vo);
        return vos;
    }

}
