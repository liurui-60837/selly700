<html>
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
   <#-- 边栏-->
    <#include "../common/nav.ftl">
   <#-- 主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped table-condensed ">
                        <thead>
                        <tr>
                            <th>
                                订单id
                            </th>
                            <th>
                                姓名
                            </th>
                            <th>
                                手机号
                            </th>
                            <th>
                                地址
                            </th>
                            <th>
                                金额
                            </th>
                            <th>
                                订单状态
                            </th>
                            <th>
                                支付状态
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th colspan ="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.content as OrderDto>
                            <tr>
                                <td>${OrderDto.orderId}</td>
                                <td>${OrderDto.buyerName}</td>
                                <td>${OrderDto.buyerPhone}</td>
                                <td>${OrderDto.buyerAddress}</td>
                                <td>${OrderDto.orderAmount}</td>
                                <td>${OrderDto.getOrderStatusEnum().message}</td>
                                <td>${OrderDto.getPayStatusEnum().message}</td>
                                <td>${OrderDto.createTime}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${OrderDto.orderId}" >详情</a></td>
                                <td>
                                    <#if OrderDto.getOrderStatusEnum().message="新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${OrderDto.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage -1}&size=5">上一页</a></li>
                        </#if>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage ==index >
                                <li class="disabled" ><a href="#">${index}<br></a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${index}&size=5">${index}<br></a></li>
                            </#if>
                        </#list>

                        <#if currentPage= orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?page=${currentPage +1}&size=5">下一页</a></li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>