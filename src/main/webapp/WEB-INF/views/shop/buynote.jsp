<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

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
                    <div>
                        <button type="button" id="fn_comment_save" class="btn btn-primary" style="margin-left: 90%;">등록</button>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="card mt-3" id="commentDiv" style="border: none;">
            <c:forEach items="${buyNotes}" var="buyNote">
                <div class="card mt-3">
                    <div class="card-body">
                        <div>
                            <p style="margin-right: 30px;">${buyNote.title}</p>
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

    $('#fn_comment_save').click(function () {

        if($('#comment').val() == "" ) {
            alert("내용을 입력하세요.")
            return;
        }

        var dto = {
            "title" : $('#title').val(),
            "content" : $('#comment').val(),
            "regItemIdx" : $('#regItemIdx').val()
        }

        $.ajax({
            url: '/shop/save/buynote',
            method: 'POST',
            data: dto,
            success: function (res) {

            document.getElementById('commentDiv').replaceChildren();

            var list = res.buyNotes

            $.each(list, function(idx, val) {
                $('#commentDiv')
                    .append($('<div class="card mt-3" ><div class="card-body">' +
                        '<div><p style="margin-right: 30px;">' + val.title + '</p></div>' +
                        '<div class="d-inline"><span style="margin-right: 30px; color: darkgray; font-size: 13px;">' + val.writer + '</span></div>' +
                        '<span style="font-size: 12px; color: darkgray;">' + val.regDt + '</span><br>' +
                        '<div style="margin-top: 20px;"><p style="font-size: 12px;">' + val.content + '</p></div></div></div>'));
              });

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