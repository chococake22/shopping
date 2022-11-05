<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/static/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <script src="/resources/static/bootstrap-5.1.3-dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<jsp:include page="common/header.jsp"></jsp:include>

<div class="container justify-content-center my-4">
    <div>
        <h1>test</h1>
    </div>
</div>
<a href="https://kauth.kakao.com/oauth/logout?client_id=5496be38ca50070af71eab2dcbc6ec26&logout_redirect_uri=http://localhost:8080/logout/kakao">로그아웃</a>

<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>
