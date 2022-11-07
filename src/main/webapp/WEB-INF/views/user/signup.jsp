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
    <link rel="stylesheet" href="/resources/static/css/common.css">
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center" style="width: 300px">
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
            <label for="nickname" class="form-label">이름<span style="color: red">*</span></label>
            <input type="text" class="form-control" id="nickname" name="nickname" placeholder="이름" required>
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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="/resources/static/js/user/signup.js"></script>

</body>
</html>
