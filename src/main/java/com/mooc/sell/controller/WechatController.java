package com.mooc.sell.controller;

import com.mooc.sell.config.WecharMpConfig;
import com.mooc.sell.enums.ResultEnum;
import com.mooc.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;


    @GetMapping("/authorize")
    public String   authorize(@RequestParam("returnUrl") String returnUrl ){

        //1.配置
        //2.调用方法
        String url = "http://tudou007.natapp1.cc/wechat/userInfo";
         String  result=   wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        return "redirect:"+result;
    }

    @GetMapping("/userInfo")
    public String  userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String result){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken =   wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            throw new SellException(ResultEnum.WX_MP_ERROR);
        }
        String openId = wxMpOAuth2AccessToken
                .getOpenId();

        return "redirect:"+result+"?openid=" +openId;
    }
}
