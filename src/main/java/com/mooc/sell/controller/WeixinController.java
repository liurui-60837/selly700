package com.mooc.sell.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
    @GetMapping("/auth")
    public void auth(@RequestParam("code")String code){
        System.out.println("123123");
        System.out.println(code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx87cd4690555154b1&secret=dc27a5a567c7f9ca024b80e680d80f9d&code="+code+"&grant_type=authorization_code";
      //  return "程丹阳最美丽";
        RestTemplate restTemplate = new RestTemplate();
        String reponse = restTemplate.getForObject(url,String.class);
        System.out.println(reponse);
    }
}
