<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <h1>글쓰기</h1>
    <form action="/board/save" method="post" enctype="multipart/form-data">
        <label for="boardType" class="form-label">주제</label>
        <div class="mb-3">
            <select class="form-select mb-3" id="boardType" name="boardType" aria-label=".form-select-lg example">
                <option value="" selected>선택하세요</option>
                <option value="notice">공지사항</option>
                <option value="normal">일반글</option>
                <option value="etc">기타</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="title" class="form-label">제목</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="제목">
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">내용</label>
            <textarea class="form-control" id="content" name="content" rows="8" style="resize: none" placeholder="내용을 입력하세요."></textarea>
        </div>
        <div class="mb-3">
            <label for="files" class="form-label">파일첨부(최대 3개, 개당 1MB)</label>
            <input class="form-control" type="file" name="files" id="files" multiple>
        </div>
        <div class="mb-3">
            <button type="button" onclick="Btn_submit()"  class="btn btn-primary">등록</button>
<%--            <button type="button" onclick="Btn_submit()" class="btn btn-light">취소</button>--%>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">

    function Btn_submit() {
        var boardType = $("#boardType").val();

        if (boardType === "") {
            alert("주제를 선택하세요.");
        }

        let dto = {
            "boardType" : $('#boardType').val(),
            "title" : $('#title').val(),
            "content" : $('#content').val()
        };

        console.log($('#boardType').val());
        console.log($('#title').val());
        console.log($('#content').val());


        $.ajax({
            type: "POST",
            url: "save",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(dto),
            success: function (res) {
                console.log(res.boardIdx);
                alert("등록되었습니다.")
                window.location = "/board/list"
            },
            error: function (err) {


            }
        })
    };





</script>

</body>
</html>
