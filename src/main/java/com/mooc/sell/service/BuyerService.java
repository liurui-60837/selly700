package com.mooc.sell.service;

import com.mooc.sell.dto.OrderDto;

/**
 * 买家
 */
public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openid,String orderId);
    //取消订单
    OrderDto cancelOrderOne(String openid,String orderId);
}
