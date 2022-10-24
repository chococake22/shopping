<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/static/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/resources/static/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

    <script src="/resources/static/js/common/datapicker.js"></script>
    <link rel="stylesheet" href="/resources/static/css/common.css">
    <style>
        .itemName {
            width: 100%;
            height: 70px;
            overflow: hidden;
            display: -webkit-box;
            -webkit-line-clamp: 3;
            -webkit-box-orient: vertical;
            word-wrap: break-word;
            margin-bottom: 10px;
        }
        /*#listContainer {*/
        /*    height: 100%;*/
        /*}*/
    </style>
</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center">
    <div class="container d-flex justify-content-between">
        <div class="d-inline justify-content-start">
            <h1>물건사기</h1>
        </div>
        <div class="justify-content-end">
            <c:if test="${user != null}">
                <a href="/shop/reg"><button type="button" class="btn btn-primary justify-content-end">상품등록</button></a>
            </c:if>
        </div>
    </div>
    <div id="contentBox">
        <c:forEach items="${list}" var="item">
        <div class="card">
            <a href="/shop/detail/${item.regItemIdx}"><img src="/summernoteImage/${item.uuidExt}" width="300px" height="200px" class="card-img-top"></a>
            <div class="card-body">
                <a href="/shop/detail/${item.regItemIdx}"><div class="itemName">${item.itemName}</div></a>
                <div class="d-inline d-flex justify-content-between">
                    <p style="color: gray; font-size: 12px;">${item.writer}</p>
                    <p style="color: gray; font-size: 12px;">${item.regDt}</p>
                </div>
            </div>
        </div>
        </c:forEach>

    </div>
    <jsp:include page="../common/pagination.jsp"></jsp:include>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

<script src="/resources/static/js/board/list.js"></script>

<link rel="stylesheet" href="/resources/static/css/contentbox.css" >

</body>
</html>
