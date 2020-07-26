package com.mooc.sell.respository;

import com.mooc.sell.dataobject.OrderMaster;
import com.mooc.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal("3.2"));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);
        repository.save(productInfo);

    }
    @Autowired
    private OrderMasterRepository repository1;
    @Test
    public void savetest(){
        OrderMaster orderMaster = new OrderMaster();

        orderMaster.setOrerId("123");
        orderMaster.setBuyerPhone("12331244");
        orderMaster.setBuyerName("杰森");
        orderMaster.setOrderAmount(new BigDecimal("2.9"));
        orderMaster.setBuyerOpenid("liurui");
        orderMaster.setBuyerAddress("地区");

        OrderMaster result = repository1.save(orderMaster);

        Assert.assertNotNull(result);


    }
}