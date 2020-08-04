package com.mooc.sell.controller;


import com.mooc.sell.dataobject.ProductCategory;
import com.mooc.sell.dataobject.ProductInfo;
import com.mooc.sell.exception.SellException;
import com.mooc.sell.form.CategoryForm;
import com.mooc.sell.service.CategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.management.MalformedObjectNameException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView List(Map<String,Object>map){
        List<ProductCategory> productCategories = categoryService.findAll();
        map.put("productCategories",productCategories);
        return new ModelAndView("category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value =  "categoryId",required = false) Integer categoryId, Map<String,Object> map){
        if(categoryId!=null){
            ProductCategory productCategory = categoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
        }
        return new ModelAndView("category/index",map);
    }

    /**
     * 保存更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save1(@Valid CategoryForm form,
                             BindingResult bindingResult,
                             Map<String,Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("common/error",map);
        }
        ProductCategory productCategory  = new ProductCategory();
        try{
            if(form.getCategoryId()!=null){
                productCategory =  categoryService.findOne(form.getCategoryId());
            }
            BeanUtils.copyProperties(form,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return  new ModelAndView("common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return  new ModelAndView("common/success",map);


    }


}
