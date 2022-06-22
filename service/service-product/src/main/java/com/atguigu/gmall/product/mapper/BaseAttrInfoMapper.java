package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.atguigu.gmall.product.domain.BaseAttrInfo
 */
public interface BaseAttrInfoMapper extends BaseMapper<BaseAttrInfo> {

    /**
     * 查询分类下的所有属性名和值
     * @param c1Id : 一级分类id
     * @param c2Id : 二级分类id,不传是0
     * @param c3Id : 三级分类id,不传是0
     * @return
     */
    List<BaseAttrInfo> getAttrInfoListWithValue(@Param("c1Id") Long c1Id,
                                                @Param("c2Id") Long c2Id,
                                                @Param("c3Id") Long c3Id);
}




