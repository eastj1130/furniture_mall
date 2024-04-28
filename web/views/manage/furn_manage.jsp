<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <title>韩顺平教育-家居网购</title>
    <base href="http://localhost:8080/mall/">
    <!-- 移动端适配 -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <link rel="stylesheet" href="assets/css/vendor/vendor.min.css"/>
    <link rel="stylesheet" href="assets/css/plugins/plugins.min.css"/>
    <link rel="stylesheet" href="assets/css/style.min.css">
    <script type="text/javascript">
        document.addEventListener("DOMContentLoaded", function () {
            let deleteArr = document.getElementsByClassName("deleteFurniture");
            for (const deleteSingle of deleteArr) {
                deleteSingle.addEventListener("click", function (evnet) {
                    if (confirm("您确定要删除吗?")) {
                        alert(deleteSingle.href);
                    } else {
                        evnet.preventDefault();
                    }

                })
            }
        })
    </script>
</head>

<body>
<!-- Header Area start  -->
<div class="header section">
    <!-- Header Top  End -->
    <!-- Header Bottom  Start -->
    <div class="header-bottom d-none d-lg-block">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->

                <!-- Header Action Start -->
                <div class="col align-self-center">
                    <div class="header-actions">
                        <div class="header_account_list">
                            <a href="javascript:void(0)" class="header-action-btn search-btn"><i
                                    class="icon-magnifier"></i></a>
                            <div class="dropdown_search">
                                <form class="action-form" action="#">
                                    <input class="form-control" placeholder="Enter your search key" type="text">
                                    <button class="submit" type="submit"><i class="icon-magnifier"></i></button>
                                </form>
                            </div>
                        </div>
                        <!-- Single Wedge Start -->
                        <div class="header-bottom-set dropdown">
                            <a href="#">后台管理</a>
                        </div>
                        <div class="header-bottom-set dropdown">
                            <a href="views/manage/furn_add.jsp?pageNo=${furniturePage.pageNo}">添加家具</a>
                        </div>
                    </div>
                </div>
                <!-- Header Action End -->
            </div>
        </div>
    </div>
    <!-- Header Bottom  End -->
    <!-- Header Bottom  Start 手机端的header -->
    <div class="header-bottom d-lg-none sticky-nav bg-white">
        <div class="container position-relative">
            <div class="row align-self-center">
                <!-- Header Logo Start -->
                <div class="col-auto align-self-center">
                    <div class="header-logo">
                        <a href="index.html"><img width="280px" src="assets/images/logo/logo.png" alt="Site Logo"/></a>
                    </div>
                </div>
                <!-- Header Logo End -->
            </div>
        </div>
    </div>
    <!-- Main Menu Start -->
    <div style="width: 100%;height: 50px;background-color: black"></div>
    <!-- Main Menu End -->
</div>
<!-- Cart Area Start -->
<div class="cart-main-area pt-100px pb-100px">
    <div class="container">
        <h3 class="cart-page-title">家居后台管理</h3>
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-12">
                <form action="#">
                    <div class="table-content table-responsive cart-table-content">
                        <table>
                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>家居名</th>
                                <th>商家</th>
                                <th>价格</th>
                                <th>销量</th>
                                <th>库存</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach items="${furniturePage.items}" var="furniture">


                                <tr>
                                    <input type="hidden" name="furnitureId" value="${furniture.id}">
                                    <td class="product-thumbnail">
                                        <a href="#"><img class="img-responsive ml-3" src="${furniture.imgPath}"
                                                         alt=""/></a>
                                    </td>
                                    <td class="product-name"><a href="#">${furniture.name}</a></td>
                                    <td class="product-name"><a href="#">${furniture.maker}</a></td>
                                    <td class="product-price-cart"><span class="amount">${furniture.price}</span></td>
                                    <td class="product-quantity">
                                            ${furniture.sales}
                                    </td>
                                    <td class="product-quantity">
                                            ${furniture.stock}
                                    </td>
                                    <td class="product-remove">
                                        <a href="manager/furnitureServlet?action=updateFurniturePage&id=${furniture.id}&pageNo=${furniturePage.pageNo}"><i
                                                class="icon-pencil"></i></a>
                                        <a href="manager/furnitureServlet?action=deleteFurniture&id=${furniture.id}&pageNo=${furniturePage.pageNo}"
                                           class="deleteFurniture"><i class="icon-close"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </form>
            </div>
        </div>
        <%--分页导航条--%>
        <div class="pro-pagination-style text-center mb-md-30px mb-lm-30px mt-6" data-aos="fade-up">
            <ul>
                <li><a href="manager/furnitureServlet?action=singlePage&pageNo=1&pageSize=3">首页</a></li>
                <c:if test="${furniturePage.pageNo!=1}">
                    <li>
                        <a href="manager/furnitureServlet?action=singlePage&pageNo=${furniturePage.pageNo-1}&pageSize=3">上页</a>
                    </li>
                </c:if>
                <c:set var="pageNo" value="${furniturePage.pageNo}"/>
                <c:set var="totalPage" value="${furniturePage.totalPage}"/>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <c:if test="${furniturePage.pageNo==i}">
                        <li><a class="active"
                               href="manager/furnitureServlet?action=singlePage&pageNo=${i}&pageSize=3">${i}</a></li>
                    </c:if>
                    <c:if test="${furniturePage.pageNo!=i}">
                        <li><a href="manager/furnitureServlet?action=singlePage&pageNo=${i}&pageSize=3">${i}</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${furniturePage.pageNo!=furniturePage.totalPage}">
                    <li>
                        <a href="manager/furnitureServlet?action=singlePage&pageNo=${furniturePage.pageNo+1}&pageSize=3">下页</a>
                    </li>
                </c:if>

                <li><a href="manager/furnitureServlet?action=singlePage&pageNo=${totalPage}&pageSize=3">末页</a></li>
                <li><a>共${furniturePage.totalPage}页</a></li>
                <li><a>共${furniturePage.totalItems}记录</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- Cart Area End -->

<!-- Footer Area Start -->
<div class="footer-area">
    <div class="footer-container">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <!-- Start single blog -->
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-sm-6 col-lg-3 mb-md-30px mb-lm-30px" data-aos="fade-up"
                         data-aos-delay="400">
                        <div class="single-wedge">
                            <h4 class="footer-herading">信息</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="about.html">关于我们</a></li>
                                        <li class="li"><a class="single-link" href="#">交货信息</a></li>
                                        <li class="li"><a class="single-link" href="privacy-policy.html">隐私与政策</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="#">条款和条件</a></li>
                                        <li class="li"><a class="single-link" href="#">制造</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-2 col-sm-6 mb-lm-30px" data-aos="fade-up" data-aos-delay="600">
                        <div class="single-wedge">
                            <h4 class="footer-herading">我的账号</h4>
                            <div class="footer-links">
                                <div class="footer-row">
                                    <ul class="align-items-center">
                                        <li class="li"><a class="single-link" href="my-account.html">我的账号</a>
                                        </li>
                                        <li class="li"><a class="single-link" href="cart.html">我的购物车</a></li>
                                        <li class="li"><a class="single-link" href="login.html">登录</a></li>
                                        <li class="li"><a class="single-link" href="wishlist.html">感兴趣的</a></li>
                                        <li class="li"><a class="single-link" href="checkout.html">结账</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End single blog -->
                    <!-- Start single blog -->
                    <div class="col-md-6 col-lg-3" data-aos="fade-up" data-aos-delay="800">

                    </div>
                    <!-- End single blog -->
                </div>
            </div>
        </div>
        <div class="footer-bottom">
            <div class="container">
                <div class="row flex-sm-row-reverse">
                    <div class="col-md-6 text-right">
                        <div class="payment-link">
                            <img src="#" alt="">
                        </div>
                    </div>
                    <div class="col-md-6 text-left">
                        <p class="copy-text">Copyright &copy; 2021 韩顺平教育~</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Footer Area End -->
<script src="assets/js/vendor/vendor.min.js"></script>
<script src="assets/js/plugins/plugins.min.js"></script>
<!-- Main Js -->
<script src="assets/js/main.js"></script>
</body>
</html>