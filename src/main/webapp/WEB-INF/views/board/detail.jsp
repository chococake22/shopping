<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4">
    <h1>상세보기</h1>
    <div class="mb-3">
        <label for="title" class="form-label">제목</label>
        <input type="text" class="form-control" id="title" value="${boardVo.title}" placeholder="제목">
    </div>
    <div class="mb-3">
        <label for="writer" class="form-label">작성자</label>
        <input type="text" class="form-control" id="writer" value="${boardVo.writer}" placeholder="제목">
    </div>
    <div class="mb-3">
        <label for="content" class="form-label">내용</label>
        <input type="text" class="form-control" id="content" value="${boardVo.content}" placeholder="제목">
    </div>
    <div class="mb-3">
        <label for="likeCount" class="form-label">좋아요</label>
        <input type="text" class="form-control" id="likeCount" value="${boardVo.likeCount}" placeholder="제목">
    </div>
    <div class="mb-3">
        <label for="click" class="form-label">조회수</label>
        <input type="text" class="form-control" id="click" value="${boardVo.click}" placeholder="제목">
    </div>
    <div class="mb-3">
        <label for="files" class="form-label">파일첨부(최대 3개, 개당 1MB)</label>
        <input class="form-control" type="file" id="files" multiple>
    </div>
    <div class="mb-3">
        <button type="button" class="btn btn-primary">등록</button>
        <button type="button" class="btn btn-light">취소</button>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
