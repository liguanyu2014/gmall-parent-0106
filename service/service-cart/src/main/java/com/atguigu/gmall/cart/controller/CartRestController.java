package com.atguigu.gmall.cart.controller;

import com.atguigu.gmall.cart.service.CartService;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.cart.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    CartService cartService;

    /**
     * 查询购物车列表
     * @return
     */
    @GetMapping("/cartList")
    public Result cartList(){
        //1.查出购物车中所有数据
        List<CartInfo> items = cartService.getCartAllItem();
        return Result.ok(items);
    }

    /**
     * 修改购物车数量
     * @param skuId
     * @param num
     * @return
     */
    @PostMapping("/addToCart/{skuId}/{num}")
    public Result addToCart(@PathVariable Long skuId,
                            @PathVariable Integer num){
        //调用之前的方法就行了
        cartService.addToCart(skuId,num);
        return Result.ok();
    }

    /**
     * 修改购物车中商品状态
     * @param skuId
     * @param status
     * @return
     */
    @GetMapping("/checkCart/{skuId}/{status}")
    public Result checkCart(@PathVariable("skuId")Long skuId,
                            @PathVariable("status")Integer status){

        cartService.updateCartItemStatus(skuId,status);
        return Result.ok();
    }

    /**
     * 删除购物车中指定商品
     * @param skuId
     * @return
     */
    @DeleteMapping("/deleteCart/{skuId}")
    public Result deleteCartItem(@PathVariable("skuId")Long skuId){

        cartService.deleteCartItem(skuId);
        return Result.ok();
    }
}
