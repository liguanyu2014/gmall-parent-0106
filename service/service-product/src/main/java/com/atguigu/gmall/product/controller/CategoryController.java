package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 三级分类业务控制器
 */
@RestController
@RequestMapping("/admin/product")
public class CategoryController {
    @Autowired
    private BaseCategory1Service baseCategory1Service;
    @Autowired
    private BaseCategory2Service baseCategory2Service;

    /**
     * 获取所有的一级分类
     * 1.control,service,dao
     * @return
     */
    @GetMapping("/getCategory1")
    public Result getCategory1(){
        //查询所有一级分类
        List<BaseCategory1> category1s = baseCategory1Service.list();
        //封装到Result里面
        return Result.ok(category1s);
    }

    /**
     * 获取某个一级分类下的所有二级分类
     * @param category1Id
     * @return
     */
    @GetMapping("/getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable("category1Id") Long category1Id){
        //查询某个一级分类下的所有二级分类
        List<BaseCategory2> category2s = baseCategory1Service.getCategory2(category1Id);
        return Result.ok(category2s);
    }

    /**
     * 获取某个二级分类下的所有三级分类
     * @param category2Id
     * @return
     */
    @GetMapping("/getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable("category2Id") Long category2Id){
        //查询某个二级分类下的所有三级分类
        List<BaseCategory3> category3s = baseCategory2Service.getCategory3(category2Id);
        return Result.ok(category3s);
    }

}
