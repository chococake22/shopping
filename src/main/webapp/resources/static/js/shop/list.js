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