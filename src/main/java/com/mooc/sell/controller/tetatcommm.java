package com.mooc.sell.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class tetatcommm {

    @RequestMapping(value = "/aaa",method = RequestMethod.POST)
    public Twert ttet(String inputInfo){

        Twert twert = new Twert();

        System.out.println(inputInfo);
        twert.setCbuCar(true);
        twert.setCkdCar(true);
        return twert;
    }
}
