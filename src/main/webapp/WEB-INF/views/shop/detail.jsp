<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/static/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/resources/static/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/static/css/common.css">
    <style>
        #imgfile {
            width: 500px;
            height: 400px;
            border: #dccece 1px solid;
        }
        .gubun {
            color: #e5e4e4;
        }
    </style>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container  justify-content-center">
    <div class="d-flex justify-content-between">
        <div class="d-inline">
            <h1>구매하기</h1>
        </div>
        <div class="d-inline d-flex justify-content-end">
            <c:if test="${!user.isEmpty() && boardVo.writer == user}">
                <a id="update" style="margin-left: 20px; color: gray; cursor: pointer; text-decoration-line: underline" href="/shop/update/${item.regItemIdx}">수정</a>
                <a id="delete" style="margin-left: 20px; color: gray; cursor: pointer; text-decoration-line: underline" onclick="fn_delete()">삭제</a>
            </c:if>
        </div>
    </div>
    <div class="card" style="margin-bottom: 30px;">
        <div class="card-body">
            <div class="mb-3">
                <div class="d-flex justify-content-between">
                    <div class="d-inline d-flex">
                        <img src="/summernoteImage/${item.uuidExt}"  id="imgfile" class="card-img-top">
                        <div class="d-inline" style="margin-left: 50px; margin-top: 20px;">
                            <div style="width: 450px;">
                                <h4>${item.itemName}</h4>
                            </div>
                            <div style="width: 450px; text-align: end;">
                                <h3><fmt:formatNumber value="${item.itemPrice}" pattern="#,###"/> 원</h3>
                            </div>
                            <hr>
                            <p>판매자 <span class="gubun">|</span> <span id="writer" style="margin-right: 30px;">${item.writer}</span></p>
                            <p>등록일 <span class="gubun">|</span> <span id="regDt">${item.regDt}</span></p>
                            <p>수량 <span class="gubun">|</span> <span id="itemCount">${item.itemCount}</span></p>
                            <p>제조사 <span class="gubun">|</span> <span id="madeCorp">${item.madeCorp}</span></p>
                            <hr>
                            <p>배송 <span class="gubun">|</span> <span id="delivery">${item.delivery}</span></p>
                            <p style="color: gray; font-size: 12px;">도서, 산간 지역은 3,000원의 배송료가 추가됩니다.</p>
                            <div>
                                <div class="d-inline d-flex" style=""><input type="number"></div>
                            </div>
                            <a href="/shop/reg"><button type="button" class="btn btn-success">구매하기</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <h1>상세정보</h1>
    <div class="card">
        <div class="card-body">
            <div class="mb-3">
                <p id="content">${item.itemInfo}</p>
            </div>
        </div>
    </div>
    <input type="hidden" id="regItemIdx" name="regItemIdx" value="${item.regItemIdx}">
    <jsp:include page="../board/comment.jsp"></jsp:include>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/static/js/shop/detail.js"></script>

</body>
</html>