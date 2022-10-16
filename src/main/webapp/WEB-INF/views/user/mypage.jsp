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
    <form action="" method="post">
        <div class="mb-3" style="text-align: center">
            <h1>마이페이지</h1>
        </div>
        <div class="mb-3">
            <label for="userId" class="form-label">아이디(이메일)</label>
            <input type="email" class="form-control" id="userId" name="userId" value="${user.userId}" placeholder="아이디(이메일)" readonly>
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="이름" >
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">전화번호</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="${user.phone}" placeholder="전화번호" readonly>
        </div>
        <div class="mb-3">
            <label for="addr" class="form-label">주소</label>
            <input type="text" class="form-control" id="addr" name="addr" value="${user.addrTotal}" placeholder="주소" readonly>
        </div>
<%--        <div class="mb-3" style="text-align: center">--%>
<%--            <label for="emailYn" class="form-label">이메일 수신여부</label>--%>
<%--            <input class="form-check-input" type="checkbox" value="Y" id="emailYn" name="emailYn">--%>
<%--        </div>--%>
        <div class="mb-3" style="text-align: center">
            <button type="button" class="btn btn-primary" id="fn_change_pwd" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                비밀번호 변경
            </button>
            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="staticBackdropLabel">비밀번호 변경하기</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <div class="mb-3">
                                <input type="password" class="form-control" id="beforePwd" name="beforePwd" placeholder="이전 비밀번호">
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="newPwd" name="newPwd" placeholder="새로운 비밀번호">
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="newPwdChk" name="newPwdChk" placeholder="확인 비밀번호">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                            <button type="button" class="btn btn-primary" id="fn_change_pwd_check">확인</button>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-light">뒤로</button>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">

    $(document).ready(function () {

        $('#fn_change_pwd_check').click(function () {

            var userId = $('#userId').val();
            var beforePwd = $('#beforePwd').val();
            var newPwd = $('#newPwd').val();
            var newPwdChk = $('#newPwdChk').val();

            var data = {
                "userId" : userId,
                "beforePwd" : beforePwd,
                "newPwd" : newPwd,
                "newPwdChk" : newPwdChk,
            }

            if(newPwd != newPwdChk) {
                alert("두 비밀번호가 다릅니다.")
            }

            $.ajax({
                type : 'post',
                url : "change/password",
                data : data,
                success : function (res) {
                    alert(res.msg)
                    $('#staticBackdrop').closed;
                },
                error : function (err) {
                    alert(err.msg)
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
