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
    <style>

        .input-number-group {
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-justify-content: center;
            -ms-flex-pack: center;
            justify-content: center;
        }

        .input-number-group input[type=number]::-webkit-inner-spin-button,
        .input-number-group input[type=number]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            appearance: none;
        }

        .input-number-group .input-group-button {
            line-height: calc(80px/2 - 5px);
        }

        .input-number-group .input-number {
            width: 80px;
            padding: 0 12px;
            vertical-align: top;
            text-align: center;
            outline: none;
            display: block;
            margin: 0;
        }

        .input-number-group .input-number,
        .input-number-group .input-number-decrement,
        .input-number-group .input-number-increment {
            border: 1px solid #cacaca;
            height: 40px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            border-radius: 0;
        }

        .input-number-group .input-number-decrement,
        .input-number-group .input-number-increment {
            display: inline-block;
            width: 40px;
            background: #e6e6e6;
            color: #0a0a0a;
            text-align: center;
            font-weight: bold;
            cursor: pointer;
            font-size: 2rem;
            font-weight: 400;
        }

        .input-number-group .input-number-decrement {
            margin-right: 0.3rem;
        }

        .input-number-group .input-number-increment {
            margin-left: 0.3rem;
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
                                <h1><fmt:formatNumber value="${item.itemPrice}" pattern="#,###"/> 원</h1>
                            </div>
                            <hr>
                            <p>판매자 <span class="gubun">|</span> <span id="writer" style="margin-right: 30px;">${item.writer}</span></p>
                            <p>등록일 <span class="gubun">|</span> <span id="regDt">${item.regDt}</span></p>
                            <p>수량 <span class="gubun">|</span> <span id="itemCount">${item.itemCount}</span></p>
                            <p>제조사 <span class="gubun">|</span> <span id="madeCorp">${item.madeCorp}</span></p>
                            <hr>
                            <p>배송 <span class="gubun">|</span> <span id="delivery">${item.delivery}</span></p>
                            <p style="color: gray; font-size: 12px;">도서, 산간 지역은 3,000원의 배송료가 추가됩니다.</p>
                            <div class="d-inline d-flex justify-content-between">
                                <div class="d-inline d-flex">
                                    <div class="input-group input-number-group">
                                        <div class="input-group-button">
                                            <span class="input-number-decrement">-</span>
                                        </div>
                                        <input class="input-number" type="number" id="buyCount" value="1" min="0" max="1000">
                                        <div class="input-group-button">
                                            <span class="input-number-increment">+</span>
                                        </div>
                                    </div>
                                </div>
                                <h3 id="totalPrice"></h3><span>원</span>
                                <div class="d-inline">
                                    <a href="/shop/reg"><button type="button" class="btn btn-success">구매하기</button></a>
                                </div>
                            </div>
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

<script>
    $('.input-number-increment').click(function() {
        var $input = $(this).parents('.input-number-group').find('.input-number');
        var val = parseInt($input.val(), 10);
        $input.val(val + 1);

        var buyCount = $('#buyCount').val();
        var itemPrice = ${item.itemPrice};
        var result = itemPrice * buyCount
        document.getElementById('totalPrice').innerText = result.toLocaleString('ko-KR')

    });

    $('.input-number-decrement').click(function() {
        var $input = $(this).parents('.input-number-group').find('.input-number');
        var val = parseInt($input.val(), 10);
        $input.val(val - 1);

        var buyCount = $('#buyCount').val();
        var itemPrice = ${item.itemPrice};
        var result = itemPrice * buyCount
        document.getElementById('totalPrice').innerText = result.toLocaleString('ko-KR')

    })
</script>

</body>
</html>