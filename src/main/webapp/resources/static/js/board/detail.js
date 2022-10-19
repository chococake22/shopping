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


$('#fn_comment_save').click(function () {

        console.log($('#boardIdx').val());

        var dto = {
            "commentContent" : $('#comment').val(),
            "boardIdx" : $('#boardIdx').val()
        }

        $.ajax({
            url: '/comment/save',
            method: 'POST',
            data: dto,
            success: function (res) {
                alert(res.msg)

                var content = $('#comment').val();
                var str = '<h1>' + content + '</h1>';
                $('#commentDiv').append(str);
            },
            error: function (err) {
                console.log("댓글 작성 실패")
            }

        })
})
