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
    <form id="form_submit" action="/shop/reg" method="post" enctype="multipart/form-data">
        <div class="card mb-3">
            <div class="card-body">
                <div class="mb-3" style="text-align : center;">
                    <img id="preview" width="300px" height="300px">
                    <p>대표 이미지를 선택하세요.</p>
                </div>
                <div class="mb-3">
                    <div>
                        <input type="file" class="form-control"  id="file" name="file" placeholder="대표이미지를 고르세요.">
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-body">
                <div class="mb-3">
                    <select class="form-select mb-3" id="itemType" name="itemType" aria-label=".form-select-lg example">
                        <option value="" selected>상품분류</option>
                        <option value="1">여성패션</option>
                        <option value="2">남성패션</option>
                        <option value="3">액세서리</option>
                        <option value="4">화장품/미용</option>
                        <option value="5">가구/인테리어</option>
                        <option value="6">식품</option>
                        <option value="7">출산/유아동</option>
                        <option value="8">반려동물용품</option>
                        <option value="9">생활/주방용품</option>
                        <option value="10">가전</option>
                        <option value="11">디지털</option>
                        <option value="12">컴퓨터</option>
                        <option value="13">스포츠/레저</option>
                        <option value="14">건강/의료용품</option>
                        <option value="15">자동차/공구</option>
                        <option value="16">취미/문구/악기</option>
                        <option value="17">여행</option>
                        <option value="18">E쿠폰/티켓</option>
                        <option value="19">정기구독</option>
                    </select>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="itemName" name="itemName" placeholder="상품명을 입력하세요.">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="madeCorp" name="madeCorp" placeholder="제조사를 입력하세요.">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="itemPrice" name="itemPrice" placeholder="가격을 입력하세요.">
                </div>
                <script>




                </script>
                <div class="mb-3">
                    <input type="text" class="form-control" id="itemCount" name="itemCount" placeholder="판매 수량을 입력하세요.">
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="delivery" name="delivery" placeholder="배송정보를 입력하세요.">
                </div>
            </div>
        </div>


        <div class="mb-3">
            <textarea class="summernote" id="itemInfo" name="itemInfo"></textarea>

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

// textarea에 이미지 올리기
$(document).ready(function () {
    $('.summernote').summernote({
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


            $(el).summernote('insertImage', json.url, json.filename);
            $(el).append('<li><img src="' + json.url + '"width="480" height="auto"/></li>');

            // $(el).summernote('el.insertImage', json.url, json.filename);

            // html 가져오기
            var markupStr = $('.summernote').summernote('code');
            console.log(markupStr)
        }

    });
}

// 썸네일 올리기
$(function() {
    $("#file").on('change', function(){
        readURL(this);
    });
});


function readURL(input) {

    if(input.files && input.files[0]) {

        console.log("썸네일 추가")
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
};

function Btn_submit() {
    var itemType = $("#itemType").val();
    var itemName = $("#itemName").val();
    var itemCount = $("#itemCount").val();
    var itemInfo = $('#itemInfo').val().replace(/(?:\r\n|\r|\n)/g, '<br>');

    if (itemType === "") {
        alert("카테고리를 선택하세요.");
        return;
    }

    const maxSize = 1024 * 1024;

    const ext = String(document.getElementById('file').files[0].type).split("/");
    if($.inArray(ext[1], ['bmp' , 'jpg', 'png', 'jpeg', 'gif']) == -1) {
        alert(ext[1] + "는 허용되지 않는 확장자입니다.")
        return;
    }

    const size = document.getElementById('file').files[0].size;
    if (size > maxSize) {
        alert("파일이 1MB를 초과합니다.");
        return;
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
            alert("등록되었습니다.")
            window.location = "/shop/list"
        },
        error: function (err) {

        }
    })
}



</script>
</body>
</html>
