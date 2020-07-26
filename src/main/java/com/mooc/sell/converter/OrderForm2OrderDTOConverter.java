package com.mooc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mooc.sell.dataobject.OrderDetail;
import com.mooc.sell.dto.OrderDto;
import com.mooc.sell.enums.ResultEnum;
import com.mooc.sell.exception.SellException;
import com.mooc.sell.form.OrderForm;

import java.util.ArrayList;
import java.util.List;

public class OrderForm2OrderDTOConverter {
        public   static OrderDto convert(OrderForm orderForm){
            Gson gson = new Gson();
            OrderDto orderDto = new OrderDto();

            orderDto.setBuyerName(orderForm.getName());
            orderDto.setBuyerPhone(orderForm.getPhone());
            orderDto.setBuyerAddress(orderForm.getAddress());
            orderDto.setBuyerOpenid(orderForm.getOpenid());
            List<OrderDetail> orderDetailList = new ArrayList<>();
            try {
                orderDetailList =   gson.fromJson(
                        orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
                        }.getType()
                );
            } catch (Exception e){
                throw  new SellException(ResultEnum.PARAM_ERROR);
            }
            orderDto.setOrderDetailList(orderDetailList);
            return  orderDto;
        }

}
