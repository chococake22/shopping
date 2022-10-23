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

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/resources/static/css/common.css">
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center">
    <h1>글쓰기</h1>
    <form id="form_submit" action="/board/save" method="post" enctype="multipart/form-data">
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
            <textarea class="form-control" style="white-space:pre-wrap; resize: none;" id="content" name="content" rows="8" placeholder="내용을 입력하세요."></textarea>
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
            <button type="button" onclick="Btn_submit()"  class="btn btn-primary">등록</button>
<%--            <button type="button" onclick="Btn_submit()" class="btn btn-light">취소</button>--%>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script src="/resources/static/js/board/registration.js"></script>

<script>
    function Btn_submit() {
        var boardType = $("#boardType").val();
        var title = $("#title").val();
        var content = $('#content').val().replace(/(?:\r\n|\r|\n)/g, '<br>');

        if (boardType === "") {
            alert("주제를 선택하세요.");
        }

        const maxSize = 1024 * 1024;

        for (let a = 1; a < i; a++) {
            let id = "files" + String(a);

            const ext = String(document.getElementById(id).files[0].type).split("/");
            if($.inArray(ext[1], ['bmp' , 'jpg',  'png', 'jpeg', 'gif']) == -1) {
                alert(ext[1] + "는 허용되지 않는 확장자입니다.")
                return;
            }

            const size = document.getElementById(id).files[0].size;
            if (size > maxSize) {
                alert(a + "번째 파일이 1MB를 초과합니다.");
                return;
            }
        }

        var form = $('#form_submit')[0];
        var formData = new FormData(form);

        $.ajax({
            type: "POST",
            url: "save",
            enctype: "multipart/form-data",
            processData : false,
            contentType : false,
            cache: false,
            data: formData,
            success: function (res) {
                console.log(res.boardIdx);
                alert("등록되었습니다.")
                window.location = "/board/list"
            },
            error: function (err) {

            }
        })
    }
</script>

</body>
</html>
