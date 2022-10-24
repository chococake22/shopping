 var i = 1;

function fn_file() {

    if (i >= 4) {
        alert("파일 첨부는 최대 3개까지 가능합니다.")
        return;
    }

    var str = '<div style="margin-bottom: 10px; id="fileBox' + i + '">' +
        '<input type="file" style="display: inline; width: 500px; margin-right: 10px;" class="form-control" name="files" id="files' + i + '">' +
        '<input type="button" style="display: inline" value="삭제" onclick="fn_delete_file(this.id)" id="delete' + i + '">' +
        '</div>';
    $('#fileDiv').append(str);
    i++;
}

function fn_delete_file(id) {
    const div = document.getElementById(id);
    console.log(id)
    div.parentElement.remove();
    if (i > 1) {
        i--;
    }
}

function Btn_submit() {
    var boardType = $("#boardType").val();
    var title = $("#title").val();
    var content = $('#content').val().replace(/(?:\r\n|\r|\n)/g, '<br>');

    if (boardType === "") {
        alert("주제를 선택하세요.");
    }

    const maxSize = 1024 * 1024;

    for (let a = 1; a < i; a++) {
        let id = "files" + String(a);

        const ext = String(document.getElementById(id).files[0].type).split("/");
        if($.inArray(ext[1], ['bmp' , 'hwp', 'jpg', 'pdf', 'png', 'xls', 'zip', 'pptx', 'xlsx', 'jpeg', 'doc', 'gif']) == -1) {
            alert(ext[1] + "는 허용되지 않는 확장자입니다.")
            return;
        }

        const size = document.getElementById(id).files[0].size;
        if (size > maxSize) {
            alert(a + "번째 파일이 1MB를 초과합니다.");
            return;
        }
    }

    var form = $('#form_submit')[0];
    var formData = new FormData(form);

    $.ajax({
        type: "POST",
        url: "save",
        enctype: "multipart/form-data",
        processData : false,
        contentType : false,
        cache: false,
        data: formData,
        success: function (res) {
            console.log(res.boardIdx);
            alert("등록되었습니다.")
            window.location = "/board/list"
        },
        error: function (err) {

        }
    })
}