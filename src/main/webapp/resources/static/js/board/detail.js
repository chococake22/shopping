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
