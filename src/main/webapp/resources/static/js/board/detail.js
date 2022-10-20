function fn_delete() {

    var boardIdx = $('#boardIdx').val();

    if (confirm('정말 삭제하시겠습니까?') == false) {
        return;
    }

    $.ajax({
        type: "post",
        url: "/board/delete/" + boardIdx,
        success: function (res) {
            alert(boardIdx + "번 글이 삭제되었습니다.")
            window.location = "/board/list"
        },
        error: function (res) {
            alert("실패")
        }
    })
}


// $('#fn_comment_save').click(function () {
//
//     if( $('#comment').val() == "" ) {
//         alert("내용을 입력하세요.")
//         return;
//     }
//
//     var dto = {
//         "commentContent" : $('#comment').val(),
//         "boardIdx" : $('#boardIdx').val()
//     }
//
//     $.ajax({
//         url: '/comment/save',
//         method: 'POST',
//         data: dto,
//         success: function (res) {
//
//             var writer = res.comment.writer
//             var regDt = res.comment.regDt
//             var commentContent = res.comment.commentContent
//
//             console.log(res.comments);
//
//             var list = res.comments
//
// //             $.each(list, function(idx, val) {
// //                 $('#commentDiv').append($('<div class="card mt-3" >'))
// //                                 .append($('<div class="card-body">'))
// //                                 .append($('<div class="d-inline">'))
// //                                 .append($('<span style="margin-right: 30px;">' + val.writer + '</span>'))
// //                                 .append($('<span style="font-size: 12px;">' + val.regDt + '</span><br>'))
// //                                 .append($('<p style="font-size: 12px;">' + val.commentContent + '</p><br>'))
// //                                 .append($('</div>'))
// //                                 .append($('</div>'))
// //                                 .append($('</div>'))
// //             });
//
//
//             var str = '<div class="card mt-3" >' +
//                             '<div class="card-body">' +
//                                 '<div class="d-inline">' +
//                                     '<span style="margin-right: 30px;">' + writer + '</span>' +
//                                     '<span style="font-size: 12px;">' + regDt + '</span><br>' +
//                                     '<p style="font-size: 12px;">' + commentContent + '</p><br>' +
//                                 '</div>' +
//                             '</div>' +
//                       '</div>';
//
//             $('#commentDiv').append(str);
//
//             $('#commentDiv').load("/comment/list/load/" + $('#boardIdx').val() + " #commentBox");
//
//         },
//         error: function (err) {
//             console.log("댓글 작성 실패")
//         }
//
//     })
// })
