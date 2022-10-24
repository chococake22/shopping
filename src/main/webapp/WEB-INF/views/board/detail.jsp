<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center">
    <h1>상세보기</h1>
    <div class="card">
        <div class="card-body">
            <div class="mb-3">
                <h3 id="title">${boardVo.title}</h3>

                <div class="d-flex justify-content-between">
                    <div class="d-inline">
                        <span id="writer" style="margin-right: 30px;">${boardVo.writer}</span>
                        <span id="regDt">${boardVo.regDt}</span>
                    </div>
                    <div class="d-inline d-flex justify-content-start">
                        <c:if test="${!user.isEmpty() && boardVo.writer == user}">
                            <a id="update" style="margin-left: 20px; color: gray; cursor: pointer; text-decoration-line: underline" href="/board/update/${boardVo.boardIdx}">수정</a>
                            <a id="delete" style="margin-left: 20px; color: gray; cursor: pointer; text-decoration-line: underline" onclick="fn_delete()">삭제</a>
                        </c:if>
                        <p id="likeCount" style="margin-left: 20px">좋아요 <span>${boardVo.likeCount}</span></p>
                        <p id="click" style="margin-left: 20px">조회수 <span>${boardVo.click}</span></p>
                    </div>
                </div>
            </div>
            <hr style="margin-top: -20px;">
            <div class="mb-3">
                <p id="content">${fn:replace(boardVo.content, cn, br)}</p>
            </div>
        </div>
    </div>
    <c:if test="${!files.isEmpty()}">
    <div class="card mt-3">
        <div class="card-body">
            <div class="mb-3">
                <h5>첨부파일</h5>
                <c:forEach items="${files}" var="file">
                    <a href="/board/file/download?boardFileIdx=${file.boardFileIdx}">${file.fileName}</a><br>
                </c:forEach>
            </div>
        </div>
    </div>
    </c:if>
    <input type="hidden" id="boardIdx" name="boardIdx" value="${boardVo.boardIdx}">
    <jsp:include page="../board/comment.jsp"></jsp:include>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script type="text/javascript" src="/resources/static/js/board/detail.js"></script>

</body>
</html>