<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4" style="width: 300px">
    <form action="/user/signup" method="post">
        <div class="mb-3" style="text-align: center">
            <h1>회원가입</h1>
        </div>
        <div class="mb-3">
            <label for="userId" class="form-label">아이디(이메일)<span style="color: red">*</span></label>
            <input type="email" class="form-control" id="userId" name="userId" placeholder="아이디(이메일)" required>
        </div>
        <input type="hidden" id="chkUserId" name="chkUserId">
        <div class="mb-3">
            <h6 id="userIdMsg"></h6>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">비밀번호<span style="color: red">*</span></label>
            <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
        </div>
        <div class="mb-3">
            <label for="chkPwd" class="form-label">비밀번호 확인<span style="color: red">*</span></label>
            <input type="password" class="form-control" id="chkPwd" name="chkPwd" placeholder="비밀번호 확인" required>
        </div>
        <div class="mb-3">
            <h6 id="chkPwdMsg"></h6>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">이름<span style="color: red">*</span></label>
            <input type="text" class="form-control" id="name" name="name" placeholder="이름" required>
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">전화번호</label>
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="전화번호">
        </div>
        <div class="mb-3">
            <label for="addr" class="form-label">주소<span style="color: red">*</span></label>
            <input type="text" class="form-control" id="addr" name="addr" placeholder="주소">
        </div>
        <div class="mb-3">
            <label for="addrDetail" class="form-label">상세주소<span style="color: red">*</span></label>
            <input type="text" class="form-control" id="addrDetail" name="addrDetail" placeholder="상세주소" required>
        </div>
        <div class="mb-3" style="text-align: center">
            <label for="emailYn" class="form-label">이메일 수신여부</label>
            <input class="form-check-input" type="checkbox" value="Y" id="emailYn" name="emailYn">
        </div>
        <input type="hidden" id="authority" name="authority" value="User">
        <div class="mb-3" style="text-align: center">
            <button type="button" id="fn_reg" class="btn btn-primary">등록</button>
            <button type="button" class="btn btn-light">취소</button>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">

    $(document).ready(function () {

        $("#userId").change(function () {

            var data = {
                "userId" : $('#userId').val()
            };

            var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

            $.ajax({
                type: 'post',
                url: "/user/idcheck",
                data: data,
                success: function (res) {

                    if (reg_email.test($('#userId').val())) {
                        $('#userIdMsg').html("사용가능한 아이디입니다.");
                        document.getElementById('userIdMsg').style.color='blue';
                        $('input[name=chkUserId]').attr('value', 'checked');
                    }
                    else {
                        $('#userIdMsg').html("");
                        $('#userIdMsg').html("이메일 형식이 아니거나 존재하는 아이디입니다.");
                        document.getElementById('userIdMsg').style.color='red';
                        $('input[name=chkUserId]').attr('value', null);
                    }
                },
                error: function (err) {
                    $('#userIdMsg').html("");
                    $('#userIdMsg').html("이메일 형식이 아니거나 존재하는 아이디입니다.");
                    document.getElementById('userIdMsg').style.color='red';
                    $('input[name=chkUserId]').attr('value', null);

                }
            })
        })

        $('#chkPwd, #password').on("propertychange change paste input", function () {

            var password1 = $('#password').val();
            var password2 = $('#chkPwd').val();

            if ((password1 == password2) && (password1 != "" && password2 != "")) {
                $('#chkPwdMsg').html("동일한 비밀번호입니다.");
                document.getElementById('chkPwdMsg').style.color='blue';
            } else if(password1 == "" && password2 == "") {
                $('#chkPwdMsg').html("비밀번호를 입력하세요");
                document.getElementById('chkPwdMsg').style.color='red';
            } else {
                $('#chkPwdMsg').html("두 비밀번호가 다릅니다.");
                document.getElementById('chkPwdMsg').style.color='red';
            }
        })


        $("#fn_reg").click(function () {

            var password1 = $('#password').val();
            var password2 = $('#chkPwd').val();
            var name = $('#name').val();

            var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

            if ($('input[name=chkUserId]').val() != 'checked' ||
                    reg_email.test($('input[name=chkUserId]').val())) {
                alert("사용 가능한 이메일로 변경하세요.");
            }

            if (password1 !== password2) {
                alert("두 비밀번호가 다릅니다.");
            }

            if (name == "") {
                alert("이름을 입력하세요.");
                return;
            }

            var dto = {
                "userId" : $('#userId').val(),
                "password" : $('#password').val(),
                "name" : $('#name').val(),
                "addr" : $('#addr').val(),
                "addrDetail" : $('#addrDetail').val(),
                "emailYn" : $('#emailYn').val(),
                "authority" : $('#authority').val()
            }

            $.ajax({
                type: 'post',
                url: "/user/signup",
                data: dto,
                success : function (res) {
                    alert("가입되었습니다.")
                    window.location = "/"
                },
                error : function (err) {
                    console.log("실패함 ㅠ")
                }
            })

        })

    })

</script>

<%--카카오 주소 API--%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    window.onload = function(){
        document.getElementById("addr").addEventListener("click", function(){ //주소입력칸을 클릭하면
            //카카오 지도 발생
            new daum.Postcode({
                oncomplete: function(data) { //선택시 입력값 세팅
                    document.getElementById("addr").value = data.address; // 주소 넣기
                    document.querySelector("input[name=addrDetail]").focus(); //상세입력 포커싱
                }
            }).open();
        });
    }
</script>

</body>
</html>
