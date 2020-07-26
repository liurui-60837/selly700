package com.mooc.sell.service.impl;


import com.alibaba.fastjson.JSON;
import com.mooc.sell.dataobject.OrderDetail;
import com.mooc.sell.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private  OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("杰森");
        orderDto.setBuyerAddress("寂静村");
        orderDto.setBuyerPhone("2312312312");
        orderDto.setBuyerOpenid("110110");

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);

    }

    @Test
    public void findOne() {
        OrderDto orderDto =  orderService.findOne("1581859458758803600");
        System.out.println(JSON.toJSONString(orderDto,true));
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0,4);
        Page<OrderDto> orderDtoPage =  orderService.findList(" ew3euwhd7sjw9diwkq",request);
        System.out.println(JSON.toJSONString(orderDtoPage.getContent(),true));
    }

    @Test
    public void cancel() {
        OrderDto orderDto =  orderService.findOne("1581859458758803600");
        OrderDto result =  orderService.cancel(orderDto);
        System.out.println(JSON.toJSONString(result,true));
    }

    @Test
    public void finish() {
        OrderDto orderDto =  orderService.findOne("123");
        OrderDto result =  orderService.finish(orderDto);
        System.out.println(JSON.toJSONString(result,true));
    }

    @Test
    public void paid() {
        OrderDto orderDto =  orderService.findOne("123");
        OrderDto result =  orderService.paid(orderDto);
        System.out.println(JSON.toJSONString(result,true));

    }

    @Test
    public void findList1() {
        PageRequest request = PageRequest.of(0,4);
        Page<OrderDto> orderDtoPage =  orderService.findListAll(request);
        System.out.println(JSON.toJSONString(orderDtoPage.getContent(),true));
    }
}