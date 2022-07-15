package com.atguigu.gmall.order.mapper;

import com.atguigu.gmall.model.order.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.atguigu.gmall.order.domain.OrderInfo
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    /**
     * 修改订单状态
     * @param orderId
     * @param userId
     * @param orderStatus
     * @param processStatus
     */
    long updateOrderStatus(@Param("orderId") Long orderId,
                           @Param("userId") Long userId,
                           @Param("orderStatus") String orderStatus,
                           @Param("processStatus") String processStatus,
                            @Param("expectStatus") String expectStatus);

    /**
     *
     * @param id
     * @param userId
     * @param orderStatus
     * @param processStatus
     * @param expectStatus : 如果订单状态在其中,则修改为 orderStatus指定的状态
     * @return
     */
    long updateOrderStatusInExpects(@Param("id") Long id,
                                    @Param("userId") long userId,
                                    @Param("orderStatus") String orderStatus,
                                    @Param("processStatus") String processStatus,
                                    @Param("expectStatus") List<String> expectStatus);

    /**
     * 查询订单详情
     * @param orderId
     * @param userId
     * @return
     */
    OrderInfo getOrderInfoAndDetails(@Param("orderId") long orderId, @Param("userId") long userId);
}




