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

    <script>
        $.datepicker.setDefaults({
            dateFormat: 'yy-mm-dd',
            prevText: '이전 달',
            nextText: '다음 달',
            monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
            dayNames: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
            showMonthAfterYear: true,
            yearSuffix: '년'
        });

        $(function() {
            $("#startDate").datepicker();
        });

        $(function() {
            $("#endDate").datepicker();
        });

    </script>

</head>
<body>

<jsp:include page="../common/header.jsp"></jsp:include>
<div class="container justify-content-center my-4">
    <div class="card">
        <div class="card-body">
            <form action="/board/list/search" method="post" id="searchForm" class="row g-4">
                <h3>검색</h3>
                <div class="col-md-2">
                    <select class="form-select mb-2" id="boardType" name="boardType"  aria-label=".form-select-lg example">
                        <option value="" selected>글 분류</option>
                        <option value="notice" <c:if test="${searchDto.boardType == 'notice'}">selected</c:if>>공지사항</option>
                        <option value="normal" <c:if test="${searchDto.boardType == 'normal'}">selected</c:if>>일반글</option>
                        <option value="etc" <c:if test="${searchDto.boardType == 'etc'}">selected</c:if>>기타</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select class="form-select mb-2" id="searchType" name="searchType"  aria-label=".form-select-lg example">
                        <option value="" selected>검색조건</option>
                        <option value="title" <c:if test="${searchDto.searchType == 'title'}">selected</c:if>>제목</option>
                        <option value="content" <c:if test="${searchDto.searchType == 'content'}">selected</c:if>>내용</option>
                        <option value="writer" <c:if test="${searchDto.searchType == 'writer'}">selected</c:if>>작성자</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <input type="text" id="startDate" name="startDate" value="${searchDto.startDate}" class="form-control"  placeholder="시작일">
                </div>
                <div class="col-md-2">
                    <input type="text" id="endDate" name="endDate" value="${searchDto.endDate}" class="form-control" placeholder="종료일">
                </div>
                <div class="col-md-3">
                    <input type="text" class="form-control" id="keyword" name="keyword" value="${searchDto.keyword}" placeholder="검색어를 입력하세요.">
                </div>
                <div class="col-md-1">
                    <button type="submit" class="btn btn-primary justify-content-end">검색</button>
                </div>
            </form>
        </div>
    </div>

    <div class="d-flex flex-row justify-content-between my-4">
        <h4>검색결과 <span id="totalCount">${totalCount}</span>건</h4>
        <c:if test="${user != null}">
            <a href="/board/reg"><button type="button" class="btn btn-primary justify-content-end">등록</button></a>
        </c:if>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">번호</th>
                <th scope="col">구분</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">작성일자</th>
                <th scope="col">조회수</th>
                <th scope="col">좋아요</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${boards}" var="board">
            <tr>
                <td>${board.boardIdx}</td>
                <td>${board.boardType}</td>
                <td><a href="/board/detail/${board.boardIdx}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.regDt}</td>
                <td>${board.click}</td>
                <td>${board.likeCount}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="../common/pagination.jsp"></jsp:include>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

<script type="text/javascript">

    $('#Btn_search').click(function () {

        $.ajax({
            type: "post",
            url: "/board/list/search",
            contentType: "application/json",
            data: dto,
            success: function (res) {

            },
            error: function (err) {
                alert("실패");
            }
        })

    })

</script>



</body>
</html>
