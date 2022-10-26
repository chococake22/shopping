<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css"/>
<style>
    #photoBox {
        width: 100%;
        text-align: center;
    }
    .autoplay {
        display: inline-block;
    }
</style>
    <div id="commentBox">
        <h3 style="margin-top: 100px;" >상품후기 <span id="count">${count}</span>개</h3>
        <c:if test="${user != null}">
            <div class="card mt-3" >
                <div class="mt-3">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요.">
                    </div>
                    <div class="form-floating d-flex justify-content-center" style="margin-top: 10px; width: 95%; height: 120px;">
                        <textarea class="form-control" id="comment" name="comment" style="height: 100px; width: 95%; resize: none;"></textarea>
                    </div>
                    <div class="mb-3">
                        <form:form id="frm" method="POST">
                            <input type="file" class="form-control" id="files" name="files" placeholder="제목을 입력하세요." multiple>
                        </form:form>
                    </div>
                    <div>
                        <button type="button" id="fn_comment_save" class="btn btn-primary" style="margin-left: 90%;">등록</button>
                    </div>
                </div>
            </div>
        </c:if>


        <h3 style="margin-top: 50px;">사진 후기</h3>
        <p>마우스를 대면 사진이 일시정지합니다.</p>
        <div id="photoBox" style="align-items: center">

            <div class="autoplay" data-slick='{"slidesToShow": 4, "slidesToScroll": 4}' style="border: #ecd5d5 solid 1px; width: 800px; height: 200px;">
                <div><img src="/resources/images/mb1.webp" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb2.jpeg" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb3.jpeg" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb4.jpeg" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb5.jpeg" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb6.jpeg" width="200px;" height="200px;" ></div>
                <div><img src="/resources/images/mb7.jpeg" width="200px;" height="200px;" ></div>
            </div>
        </div>

        <div class="card mt-3" id="commentDiv" style="border: none;">
            <c:forEach items="${buyNotes}" var="buyNote">
                <div class="card mt-3">
                    <div class="card-body">
                        <div class="d-flex d-inline">
                            <p style="margin-right: 30px;">${buyNote.title}</p>
                            <c:if test="${user == buyNote.writer}">
                                <a href=""><img src="/resources/images/cancel.png" width="20px;" height="20px;"></a>
                            </c:if>
                        </div>
                        <div class="d-inline">
                            <span style="margin-right: 30px; color: darkgray; font-size: 13px;">${buyNote.writer}</span>
                        </div>
                        <span style="font-size: 12px; color: darkgray;">${buyNote.regDt}</span>
                        <div style="margin-top: 20px;">
                            <p style="font-size: 12px;">${buyNote.content}</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


<script type="text/javascript">

    $(document).ready(function(){
        $('.autoplay').slick({
            slidesToShow: 3,
            slidesToScroll: 1,
            autoplay: true,
            autoplaySpeed: 2000,
        });
    });

    $('#fn_comment_save').click(function () {

        if($('#comment').val() == "" ) {
            alert("내용을 입력하세요.")
            return;
        }

        var formData = new FormData($("#frm")[0]);
        formData.append("title", $('#title').val());
        formData.append("content", $('#comment').val().replace(/(?:\r\n|\r|\n)/g, '<br>'));
        formData.append("regItemIdx", $('#regItemIdx').val());

        $.ajax({
            url: '/shop/save/buynote',
            method: 'POST',
            data : formData,
            processData: false,
            contentType: false,
            success: function (res) {

            document.getElementById('commentDiv').replaceChildren();

            var list = res.buyNotes;


                    var userValue = '<c:out value="${user}"/>';
                    <%--<c:forEach items="${buyNotes}" var="buyNote">--%>
                    <%--console.log("${buyNote.writer}");--%>
                    <%--console.log("${buyNote.writer}" == userValue);--%>
                    <%--</c:forEach>--%>


        $('#commentDiv')
            .append($(
                <c:forEach items="${buyNotes}" var="buyNote">
                    '<div class="card mt-3">' +
                        '<div class="card-body">' +
                            '<div class="d-flex d-inline">' +
                                '<p style="margin-right: 30px;">' + ${buyNote.title} + '</p>' +
                                <c:if test="${user == buyNote.writer}">
                                    '<a href=""><img src="/resources/images/cancel.png" width="20px;" height="20px;"></a>' +
                                </c:if>
                            '</div>' +
                            '<div class="d-inline">' +
                            '<span style="margin-right: 30px; color: darkgray; font-size: 13px;">' + ${buyNote.writer} + '</span>' +
                            '</div>' +
                            '<span style="font-size: 12px; color: darkgray;">' + ${buyNote.regDt} + '</span>' +
                            '<div style="margin-top: 20px;">' +
                                '<p style="font-size: 12px;">' + ${buyNote.content} + '</p>' +
                            '</div>' +
                        '</div>' +
                    '</div>'
                </c:forEach>
            ));




            <%--$.each(list, function(idx, val) {--%>

            <%--    var userValue = '<c:out value="${user}"/>';--%>
            <%--    <c:forEach items="${buyNotes}" var="buyNote">--%>
            <%--    console.log("${buyNote.writer}");--%>
            <%--    console.log("${buyNote.writer}" == userValue);--%>
            <%--    </c:forEach>--%>
            <%--    --%>
            <%--    $('#commentDiv')--%>
            <%--        .append($('<div class="card mt-3" >' +--%>
            <%--                '<div class="card-body">' +--%>
            <%--                    '<div class="d-flex d-inline">' +--%>
            <%--                        '<p style="margin-right: 30px;">' + val.title + '</p>' +--%>
            <%--                        <c:if test="${buyNote.writer == userValue}">--%>
            <%--                        '<a href=""><img src="/resources/images/cancel.png" width="20px;" height="20px;"></a>' +--%>
            <%--                        </c:if>--%>
            <%--                    '</div>' +--%>
            <%--                    '<div class="d-inline">' +--%>
            <%--                        '<span style="margin-right: 30px; color: darkgray; font-size: 13px;">' + val.writer + '</span>' +--%>
            <%--                    '</div>' +--%>
            <%--                    '<span style="font-size: 12px; color: darkgray;">' + val.regDt + '</span><br>' +--%>
            <%--                    '<div style="margin-top: 20px;">' +--%>
            <%--                        '<p style="font-size: 12px;">' + val.content + '</p>' +--%>
            <%--                    '</div>' +--%>
            <%--                '</div>' +--%>
            <%--            '</div>'));--%>
            <%--});--%>



            document.getElementById("count").innerHTML = res.count;
            var title = document.getElementById("title");
            var comment = document.getElementById("comment");
            title.value = null;
            comment.value = null;

            },
            error: function (err) {
                console.log("댓글 작성 실패")
            }
        })
    });


</script>