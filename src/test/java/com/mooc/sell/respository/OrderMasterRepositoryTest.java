package com.mooc.sell.respository;

import com.mooc.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    @Test
    public void savetest(){
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrerId("124");
        orderMaster.setBuyerPhone("12331244");
        orderMaster.setBuyerName("杰森");
        orderMaster.setOrderAmount(new BigDecimal("2.4"));
        orderMaster.setBuyerOpenid("liurui");
        orderMaster.setBuyerAddress("地区");

        OrderMaster result = repository.save(orderMaster);

        Assert.assertNotNull(result);


    }
    @Test
    public void findByOpenId()throws  Exception{
        Pageable request =  PageRequest.of(0,1);
        Page<OrderMaster> result =  repository.findByBuyerOpenid("liurui",request);
        System.out.println(result.getTotalElements());
    }
}