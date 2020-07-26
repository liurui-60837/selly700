package com.mooc.sell.respository;

import com.mooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private  OrderDetailRepository repository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1233");
        orderDetail.setOrderId("123");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("123");
        orderDetail.setProductName("水煮鱼");
        orderDetail.setProductPrice(new BigDecimal("1.8"));
        orderDetail.setProductQuantity(2);
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void find(){
        List<OrderDetail> list = repository.findByOrderId("123");

        System.out.println(list);
    }
}