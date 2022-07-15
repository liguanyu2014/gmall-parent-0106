package com.atguigu.gmall.model.vo.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderSubmitVo {
    //收件人
    private String consignee;
    //收件人手机号
    private String consigneeTel;
    //收货地址
    private String deliveryAddress;
    //订单备注
    private String orderComment;
    //以上数据要保存到 order_info 表

    //以下数据要保存到 order_detail表
    private List<OrderSubmitDetailVo> orderDetailList;
}
