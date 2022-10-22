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

    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center">
    <h1>상품 등록하기</h1>
    <form id="form_submit" action="/board/save" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <select class="form-select mb-3" id="boardType" name="boardType" aria-label=".form-select-lg example">
                <option value="" selected>상품분류</option>
                <option value="notice">여성패션</option>
                <option value="normal">남성패션</option>
                <option value="etc">액세서리</option>
                <option value="etc">화장품/미용</option>
                <option value="etc">가구/인테리어</option>
                <option value="etc">식품</option>
                <option value="etc">출산/유아동</option>
                <option value="etc">반려동물용품</option>
                <option value="etc">생활/주방용품</option>
                <option value="etc">가전</option>
                <option value="etc">디지털</option>
                <option value="etc">컴퓨터</option>
                <option value="etc">스포츠/레저</option>
                <option value="etc">건강/의료용품</option>
                <option value="etc">자동차/공구</option>
                <option value="etc">취미/문구/악기</option>
                <option value="etc">여행</option>
                <option value="etc">E쿠폰/티켓</option>
                <option value="etc">정기구독</option>
            </select>
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" id="title" name="title" placeholder="상품명을 입력하세요.">
        </div>
        <div class="mb-3">
            <input type="text" class="form-control" id="itemCount" name="itemCount" placeholder="판매 수량을 입력하세요.">
        </div>
        <div class="mb-3">
            <textarea class="summernote" id="summernote" name="summernote"></textarea>

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
            <button type="button" id="test" onclick="saveContent()" class="btn btn-primary">test</button>
<%--            <button type="button" onclick="Btn_submit()" class="btn btn-light">취소</button>--%>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script src="/resources/static/js/board/registration.js"></script>
<script>
$(document).ready(function () {
    $('#summernote').summernote({
        height: 450,
        lang: "ko-KR",
        minHeight: null,
        maxHeight: null,
        focus: true,
        callbacks: {
            onImageUpload: function (files, editor, welEditable) {
                for (var i = files.length - 1; i >= 0; i--) {
                    summernoteImageUpload(files[i], this);
                }
            }
        }

    });
})

function summernoteImageUpload(file, el) {
    let form_data = new FormData();
    form_data.append('file', file);

    $.ajax({
        data: form_data,
        type: "POST",
        url: '/shop/summernote',
        cache: false,
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
        success: function (json) {

            console.log("summernoteContent : " + json.url);
            console.log(json.filename);

            setTimeout(function () {
                $('#summernote').summernote('insertImage', json.url, json.filename);
            }, 4000);
            // $(el).summernote('el.insertImage', json.url, json.filename);


            // html 가져오기
            var markupStr = $('#summernote').summernote('code');
        }

    });
}
</script>
</body>
</html>
