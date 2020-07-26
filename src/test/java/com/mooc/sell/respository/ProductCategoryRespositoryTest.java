package com.mooc.sell.respository;

import com.alibaba.fastjson.JSON;
import com.mooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {
    @Autowired
    private ProductCategoryRespository respository;
    @Test
    public void find(){
        List<ProductCategory> productCategory =  respository.findAll();
        Optional<ProductCategory> optional =  respository.findById(1);
        System.out.println(optional.get());
    }
    @Test
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("他sji");
        productCategory.setCategoryType(1);
        respository.save(productCategory);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> result =  respository.findByCategoryTypeIn(list);
        System.out.println(JSON.toJSONString(result,true));
    }
}