package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台属性控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin/product")
public class BaseAttrController {

    @Autowired
    BaseAttrInfoService baseAttrInfoService;

    @Autowired
    BaseAttrValueService baseAttrValueService;

    /**
     * 查询分类下的所有属性名和值
     * @param c1Id : 一级分类id
     * @param c2Id : 二级分类id,不传是0
     * @param c3Id : 三级分类id,不传是0
     * @return
     */
    @GetMapping("/attrInfoList/{c1Id}/{c2Id}/{c3Id}")
    public Result attrInfoList(@PathVariable("c1Id") Long c1Id,
                               @PathVariable("c2Id") Long c2Id,
                               @PathVariable("c3Id") Long c3Id){
        List<BaseAttrInfo> infos = baseAttrInfoService.getAttrInfoListWithValue(c1Id,c2Id,c3Id);
        return Result.ok(infos);
    }

    /**
     * 保存/修改 平台属性, 二合一
     * @param baseAttrInfo : 前端传来的数据 与 BaseAttrInfo对应
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        log.info("保存/修改属性:{}",baseAttrInfo);
        if(baseAttrInfo.getId()!=null){
            //修改
            baseAttrInfoService.updateAttrAndValue(baseAttrInfo);
        }else{
            //新增
            //原本的save() 不行,应为有属性名和属性值
            baseAttrInfoService.saveAttrAndValue(baseAttrInfo);
        }
        return Result.ok();
    }

    /**
     * 查询某个属性的所有属性值
     * @param attrId
     * @return
     */
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId") Long attrId){
        //原本的save() 不行,应为有属性名和属性值
        List<BaseAttrValue> values = baseAttrValueService.getAttrValueList(attrId);
        return Result.ok(values);
    }
}
