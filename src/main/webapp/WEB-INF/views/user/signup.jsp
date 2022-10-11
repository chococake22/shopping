<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4">
    <form>
        <h1>회원가입</h1>
        <div class="mb-3">
            <label for="userId" class="form-label">아이디(이메일)</label>
            <input type="text" class="form-control" id="userId" placeholder="아이디(이메일)">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" class="form-control" id="password" placeholder="비밀번호">
        </div>
        <div class="mb-3">
            <label for="chkPwd" class="form-label">비밀번호 확인</label>
            <input type="password" class="form-control" id="chkPwd" placeholder="비밀번호 확인">
        </div>
        <div class="mb-3">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control" id="name" placeholder="이름">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">전화번호</label>
            <input type="text" class="form-control" id="phone" placeholder="전화번호">
        </div>
        <div class="mb-3">
            <button type="button" class="btn btn-primary">등록</button>
            <button type="button" class="btn btn-light">취소</button>
        </div>
    </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
