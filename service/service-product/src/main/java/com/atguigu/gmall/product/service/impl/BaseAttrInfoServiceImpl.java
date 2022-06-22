package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
        implements BaseAttrInfoService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Autowired
    BaseAttrValueService baseAttrValueService;

    @Transactional
    @Override
    public List<BaseAttrInfo> getAttrInfoListWithValue(Long c1Id, Long c2Id, Long c3Id) {
        return baseAttrInfoMapper.getAttrInfoListWithValue(c1Id, c2Id, c3Id);
    }

    @Override
    public void saveAttrAndValue(BaseAttrInfo baseAttrInfo) {
        //1.保存属性名信息
        baseAttrInfoMapper.insert(baseAttrInfo);
        //mybatis-plus自动回填自增id到原来的JavaBean中
        Long id = baseAttrInfo.getId();
        //2.保存属性值信息
        List<BaseAttrValue> valueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue value : valueList) {
            //给保存的信息找到对应的attrInfo
            value.setAttrId(id);
        }
        //批量保存 mapper里没有 service里有
        baseAttrValueService.saveBatch(valueList);
    }

    /**
     * 根据 ID 修改
     * //@param entity 实体对象-->只修改 BaseAttrInfo 这个JavaBean
     * int updateById(@Param(Constants.ENTITY) T entity);
     *
     * @param baseAttrInfo
     */
    @Override
    public void updateAttrAndValue(BaseAttrInfo baseAttrInfo) {
        List<Long> ids = new ArrayList<>();
        //1.修改属性名(这些一一对应的属性直接删了重新添加)
        baseAttrInfoMapper.updateById(baseAttrInfo);
        //2.修改属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue value : attrValueList) {
            //2.1新增(没带id)
            if (value.getId() == null) {
                //回填属性id,避免添加数据不在对应的attrInfo下面
                value.setAttrId(baseAttrInfo.getId());
                baseAttrValueService.save(value);
            }
            //2.2修改(带了id但值变了)
            if (value.getId() != null) {
                baseAttrValueService.updateById(value);
                //收集前端带了的id
                ids.add(value.getId());
            }
        }

        //非空判断,不然sql语句炸了
        if (ids.size() > 0) {
            //2.3删除(原来有现在没有的id)
            //delete * from base_attr_value where attrr_id=12 and id not in(现在有的id)
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", baseAttrInfo.getId());
            wrapper.notIn("id", ids);//不在前端给的id范围内的都删
            baseAttrValueService.remove(wrapper);
        } else {
            //全删
            QueryWrapper<BaseAttrValue> wrapper = new QueryWrapper<>();
            wrapper.eq("attr_id", baseAttrInfo.getId());
            baseAttrValueService.remove(wrapper);
        }

    }

}




