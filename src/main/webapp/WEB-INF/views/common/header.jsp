<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light" aria-label="Eighth navbar example" style="background-color: #eeb59f;">
    <div class="container-fluid" style="color: white">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-center" id="navbarsExample07">
            <ul class="nav">
                <li class="nav-item">
                    <a class="nav-link active" style="color: white" aria-current="page" href="/">HOME</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" style="color: white" style="color: white"  aria-current="page" href="#">물건사기</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="/board/list">게시판</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Action</a></li>
                        <li><a class="dropdown-item" href="#">Another action</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="/user/signup">회원가입</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" style="color: white" href="/login">로그인</a>
                </li>
            </ul>
        </div>
    </div>
</nav>



