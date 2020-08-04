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
                                类目id
                            </th>
                            <th>
                                类目名称
                            </th>
                            <th>
                                type
                            </th>
                            <th>
                               创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th colspan ="1">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productCategories as cateGories>
                            <tr>
                                <td>${cateGories.categoryId}</td>
                                <td>${cateGories.categoryName}</td>
                                <td>${cateGories.categoryType}</td>
                                <td>${cateGories.createTime}</td>
                                <td>${cateGories.updateTime}</td>
                                <td><a href="/sell/seller/category/index?categoryId=${cateGories.categoryId}" >修改</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>