package com.mooc.sell.service.impl;

import com.mooc.sell.dto.OrderDto;
import com.mooc.sell.enums.ResultEnum;
import com.mooc.sell.exception.SellException;
import com.mooc.sell.service.BuyerService;
import com.mooc.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
       return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrderOne(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid,orderId);
        if(orderDto==null){
            throw  new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid,String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto==null){
            return null;
        }
        //判断是否是自己的订单
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }

}
