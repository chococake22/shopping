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
            <textarea class="form-control" style="white-space:pre-wrap;" id="content" name="content" rows="8" style="resize: none" placeholder="내용을 입력하세요."></textarea>
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

<script type="text/javascript">

    var i = 1;

    function fn_file() {

        if (i >= 4) {
            alert("파일 첨부는 최대 3개까지 가능합니다.")
            return;
        }

        var str = '<div style="margin-bottom: 10px; id="fileBox' + i + '">' +
            '<input type="file" style="display: inline; width: 500px; margin-right: 10px;" class="form-control" name="files" id="files' + i + '">' +
            '<input type="button" style="display: inline" value="삭제" onclick="fn_delete_file(this.id)" id="delete' + i + '">' +
            '</div>';
        $('#fileDiv').append(str);
        i++;
    }

    function fn_delete_file(id) {
        const div = document.getElementById(id);
        console.log(id)
        div.parentElement.remove();
        if (i > 1) {
            i--;
        }
    }

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
            if($.inArray(ext[1], ['bmp' , 'hwp', 'jpg', 'pdf', 'png', 'xls', 'zip', 'pptx', 'xlsx', 'jpeg', 'doc', 'gif']) == -1) {
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
