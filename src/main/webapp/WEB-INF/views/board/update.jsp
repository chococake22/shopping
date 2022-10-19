<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/static/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/resources/static/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/static/css/common.css">
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4">
    <h1>수정하기</h1>
    <form action="/board/update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="boardIdx" id="boardIdx" value="${boardVo.boardIdx}">
        <label for="boardType" class="form-label">주제</label>
        <div class="mb-3">
            <select class="form-select mb-3" id="boardType" name="boardType" aria-label=".form-select-lg example">
                <option value="" selected>선택하세요</option>
                <option value="notice" <c:if test="${boardVo.boardType == 'notice'}">selected</c:if>>공지사항</option>
                <option value="normal" <c:if test="${boardVo.boardType == 'normal'}">selected</c:if>>일반글</option>
                <option value="etc" <c:if test="${boardVo.boardType == 'etc'}">selected</c:if>>기타</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${boardVo.title}" placeholder="제목">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" name="content" rows="8" style="resize: none" placeholder="내용을 입력하세요.">${boardVo.content}</textarea>
        </div>
        <div class="mb-3">
            <p style="display: inline-block">파일첨부(최대 3개, 개당 1MB)</p>
            <input type="button" value="추가" onclick="fn_file()">
            <div id="fileDiv">
                <div id="fileBox">
                </div>
            </div>
        </div>
        <div class="mb-3">
            <button type="submit"  class="btn btn-primary">수정</button>
<%--            <button type="button" onclick="Btn_submit()" class="btn btn-light">취소</button>--%>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script src="/resources/static/js/board/registration.js"></script>

</body>
</html>
