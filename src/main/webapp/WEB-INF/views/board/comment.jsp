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
    <h3 style="margin-top: 100px;" >댓글 <span id="count">${count}</span>개</h3>
    <div class="card mt-3">
        <div class="mt-3">
            <div class="form-floating d-flex justify-content-center" style="margin-top: 10px; width: 95%; height: 120px;">
                <textarea class="form-control" id="comment" name="comment" style="height: 100px; width: 95%; resize: none;"></textarea>
            </div>
            <div>
                <button type="button" id="fn_comment_save" class="btn btn-primary" style="margin-left: 90%;">등록</button>
            </div>

        </div>
    </div>
    <div class="card mt-3" id="commentDiv" style="border: none;">
        <c:forEach items="${comments}" var="comment">
            <div class="card mt-3" >
                <div class="card-body">
                    <div class="d-inline">
                        <span style="margin-right: 30px;">${comment.writer}</span>
                        <span style="font-size: 12px;">${comment.regDt}</span>
                        <p style="font-size: 12px;">${comment.commentContent}</p>
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
            "commentContent" : $('#comment').val(),
            "boardIdx" : $('#boardIdx').val()
        }

        $.ajax({
            url: '/comment/save',
            method: 'POST',
            data: dto,
            success: function (res) {

            document.getElementById('commentDiv').replaceChildren();

            var list = res.comments

            // $.each(list, function(idx, val) {
            //     $('#commentDiv').append($('<div class="card mt-3" >'))
            //                     .append($('<div class="card-body">'))
            //                     .append($('<div class="d-inline">'))
            //                     .append($('<span style="margin-right: 30px;">' + val.writer + '</span>'))
            //                     .append($('<span style="font-size: 12px;">' + val.regDt + '</span><br>'))
            //                     .append($('<p style="font-size: 12px;">' + val.commentContent + '</p><br>'))
            //                     .append($('</div>'))
            //                     .append($('</div>'))
            //                     .append($('</div>'));
            // });


            $.each(list, function(idx, val) {
                $('#commentDiv')
                    .append($('<div class="card mt-3" ><div class="card-body">' +
                        '<div class="d-inline"><span style="margin-right: 30px;">' + val.writer + '</span>' +
                        '<span style="font-size: 12px;">' + val.regDt + '</span><br>' +
                        '<p style="font-size: 12px;">' + val.commentContent + '</p>'));
            });



            console.log(res.count)

            document.getElementById("count").innerHTML = res.count;


            },
            error: function (err) {
                console.log("댓글 작성 실패")
            }


        })
    });


</script>