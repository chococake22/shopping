<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>로그인</title>
    <link rel="stylesheet" href="/resources/static/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/resources/static/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"/>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    <link rel="stylesheet" href="/resources/static/css/common.css">
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container-fluid justify-content-center">
    <div class="container-fluid justify-content-center my-4 w-25 h-50" style="padding-top: 100px">
        <div>
            <form action="/login" method="post">
                <div class="mb-3" style="text-align: center">
                    <h1>로그인</h1>
                </div>
                <div class="mb-3">
                    <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디(이메일)">
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호">
                </div>
                <div class="mb-3" style="text-align: center">
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <button type="button" class="btn btn-light">돌아가기</button>
                </div>
                <div class="mb-3">
                    <span ><img src="/resources/images/kakao_login_large_wide.png"  onclick="kakaoLogin()" style="width: 300px; margin-left: 5px; cursor: pointer;"/></span>

                    <a href="/oauth2/authorization/kakao">카카오용</a>

                    <a href="https://kauth.kakao.com/oauth/logout?client_id=5496be38ca50070af71eab2dcbc6ec26&logout_redirect_uri=http://localhost:8080/logout/kakao">로그아웃</a>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="/resources/static/js/user/login.js"></script>

</body>
</html>
