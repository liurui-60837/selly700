package com.mooc.sell.controller;

import com.mooc.sell.Utls.ResultVOUtil;
import com.mooc.sell.VO.ProductInfoVo;
import com.mooc.sell.VO.ProductVo;
import com.mooc.sell.VO.ResultVO;
import com.mooc.sell.dataobject.ProductCategory;
import com.mooc.sell.dataobject.ProductInfo;
import com.mooc.sell.service.CategoryService;
import com.mooc.sell.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //.查询所有的商家商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //查询类目（一次查询）
        //lambda
        List<Integer> categoryTypeList = productInfoList.stream()
                    .map(e -> e.getCategoryType())
                    .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //数据拼接
        List<ProductVo> productVoList = new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVo productVo = new ProductVo();
            productVo.setCategoryType(productCategory.getCategoryType());
            productVo.setCategoryName(productCategory.getCategoryName());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for(ProductInfo productInfo : productInfoList){
                ProductInfoVo productInfoVo = new ProductInfoVo();
                BeanUtils.copyProperties(productInfo,productInfoVo);
                productInfoVoList.add(productInfoVo);
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        return ResultVOUtil.success(productVoList);
    }

}
