<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4">
    <h1>상세보기</h1>
    <div class="card">
        <div class="card-body">
            <div class="mb-3">
                <h3 id="title">${boardVo.title}</h3>

                <div class="d-flex justify-content-between">
                    <div class="d-inline">
                        <span id="writer">${boardVo.writer}</span>
                    </div>
                    <div class="d-inline d-flex justify-content-start">
                        <c:if test="${!user.isEmpty() && boardVo.writer == user}">
                            <a id="update" style="margin-left: 20px; color: gray; cursor: pointer; text-decoration-line: underline" onclick="fn_update()">수정</a>
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
    <div class="mt-3">

    </div>
    <input type="hidden" id="boardIdx" name="boardIdx" value="${boardVo.boardIdx}">
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">

    function fn_delete() {

        var boardIdx = $('#boardIdx').val();

        if (confirm('정말 삭제하시겠습니까?') == false) {
            return;
        }

        $.ajax({
            type: "post",
            url: "/board/delete/" + boardIdx,
            success: function (res) {
                alert(boardIdx + "번 글이 삭제되었습니다.")
                window.location = "/board/list"
            },
            error: function (res) {
                alert("실패")
            }
        })


    }

</script>

</body>
</html>
