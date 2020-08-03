package com.mooc.sell.controller;


import com.alibaba.fastjson.JSON;
import com.mooc.sell.Utls.ResultVOUtil;
import com.mooc.sell.VO.ResultVO;
import com.mooc.sell.converter.OrderForm2OrderDTOConverter;
import com.mooc.sell.dto.OrderDto;
import com.mooc.sell.enums.ResultEnum;
import com.mooc.sell.exception.SellException;
import com.mooc.sell.form.OrderForm;
import com.mooc.sell.service.BuyerService;
import com.mooc.sell.service.OrderService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/buyer/order")
public class BuyOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
       OrderDto orderDto = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDto creatResult =  orderService.create(orderDto);
        Map<String,String> map = new HashMap<>();

        map.put("orderId",creatResult.getOrderId());
        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)) {
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = PageRequest.of(page,size);
        Page<OrderDto> orderDtoPage = orderService.findList(openid,request);

        return  ResultVOUtil.success(orderDtoPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId ){
        //TODO不安全做法
        OrderDto orderDto = new OrderDto();
        orderDto = buyerService.findOrderOne(openid,orderId);
        return ResultVOUtil.success(orderDto);

    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId ){
        buyerService.cancelOrderOne(openid, orderId);
        return ResultVOUtil.success();
    }


}
