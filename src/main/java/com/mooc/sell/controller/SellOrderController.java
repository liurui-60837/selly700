package com.mooc.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.mooc.sell.dto.OrderDto;
import com.mooc.sell.enums.ResultEnum;
import com.mooc.sell.exception.SellException;
import com.mooc.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家段订单
 */
@Controller
@RequestMapping("/sell/order")
@Slf4j
public class SellOrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView List(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value =  "size",defaultValue = "5") Integer size,
                             Map<String,Object> map){
        PageRequest request = PageRequest.of(page-1,size);
        Page<OrderDto> orderDtoPage = orderService.findListAll(request);
        //orderDtoPage.getTotalPages();
        map.put("orderDTOPage",orderDtoPage);
        map.put("currentPage",page);
    return new ModelAndView("order/list",map);
    }
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDto orderDto = new OrderDto();
        try {
            orderDto = orderService.findOne(orderId);
            orderService.cancel(orderDto);
        } catch (SellException e){
            System.out.println("卖家订单查询不到1");
            map.put("msg", e.getMessage());
            map.put("url","/sell/sell/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS_FUL);
        map.put("url","/sell/sell/order/list");
        return new ModelAndView("common/success");
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,Map<String,Object> map){
        OrderDto orderDto = new OrderDto();
        try {
            orderDto =  orderService.findOne(orderId);
        } catch (SellException e){
            System.out.println("卖家订单查询不到2");
            map.put("msg", e.getMessage());
            map.put("url","/sell/sell/order/list");
            return new ModelAndView("common/error",map);
        }
            map.put("orderDTO",orderDto);
            return  new ModelAndView("order/detail",map);
    }
    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,Map<String,Object> map){
        try {
            OrderDto orderDto = orderService.findOne(orderId);
            orderService.finish(orderDto);
        } catch (SellException e){
            System.out.println("卖家订单查询不到3");
            map.put("msg", e.getMessage());
            map.put("url","/sell/sell/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.SUCCESS_FUL_END);
        map.put("url","/sell/sell/order/list");
        return new ModelAndView("common/success");
    }

}
