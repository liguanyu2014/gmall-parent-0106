package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

    /**
     * 查询分类下的所有属性名和值
     * @param c1Id : 一级分类id
     * @param c2Id : 二级分类id,不传是0
     * @param c3Id : 三级分类id,不传是0
     * @return
     */
    List<BaseAttrInfo> getAttrInfoListWithValue(Long c1Id, Long c2Id, Long c3Id);

    /**
     * 保存平台属性
     * @param baseAttrInfo : 前端传来的数据 与 BaseAttrInfo对应
     * @return
     */
    void saveAttrAndValue(BaseAttrInfo baseAttrInfo);

    /**
     * 修改平台属性
     * @param baseAttrInfo
     */
    void updateAttrAndValue(BaseAttrInfo baseAttrInfo);
}
