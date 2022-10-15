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
<div class="container-fluid justify-content-center my-4">
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
            </form>
        </div>
    </div>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
