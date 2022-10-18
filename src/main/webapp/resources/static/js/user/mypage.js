$(document).ready(function () {

    $('#fn_change_pwd_check').click(function () {

        var userId = $('#userId').val();
        var beforePwd = $('#beforePwd').val();
        var newPwd = $('#newPwd').val();
        var newPwdChk = $('#newPwdChk').val();

        var data = {
            "userId" : userId,
            "beforePwd" : beforePwd,
            "newPwd" : newPwd,
            "newPwdChk" : newPwdChk,
        }

        if(newPwd != newPwdChk) {
            alert("두 비밀번호가 다릅니다.")
        }

        $.ajax({
            type : 'post',
            url : "change/password",
            data : data,
            success : function (res) {
                alert(res.msg)
                $('#staticBackdrop').closed;
            },
            error : function (err) {
                alert(err.msg)
            }
        })
    })
})