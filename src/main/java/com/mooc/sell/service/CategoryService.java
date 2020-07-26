package com.mooc.sell.service;

import com.mooc.sell.dataobject.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
