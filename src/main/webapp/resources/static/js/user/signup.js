$(document).ready(function () {

    $('#chkPwdMsg').html("비밀번호는 8~15자리의 문자, 숫자, 특수문자 조합");
        document.getElementById('chkPwdMsg').style.color='red';



    $("#userId").change(function () {

        var data = {
            "userId" : $('#userId').val()
        };

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        $.ajax({
            type: 'post',
            url: "/user/idcheck",
            data: data,
            success: function (res) {

                if (reg_email.test($('#userId').val())) {
                    $('#userIdMsg').html("사용가능한 아이디입니다.");
                    document.getElementById('userIdMsg').style.color='blue';
                    $('input[name=chkUserId]').attr('value', 'checked');
                }
                else {
                    $('#userIdMsg').html("");
                    $('#userIdMsg').html("이메일 형식이 아니거나 존재하는 아이디입니다.");
                    document.getElementById('userIdMsg').style.color='red';
                    $('input[name=chkUserId]').attr('value', null);
                }
            },
            error: function (err) {
                $('#userIdMsg').html("");
                $('#userIdMsg').html("이메일 형식이 아니거나 존재하는 아이디입니다.");
                document.getElementById('userIdMsg').style.color='red';
                $('input[name=chkUserId]').attr('value', null);

            }
        })
    })

    $('#chkPwd, #password').on("propertychange change paste input", function () {

        var password1 = $('#password').val();
        var password2 = $('#chkPwd').val();
        var regExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

        if ((password1 == password2) && (password1 != "" && password2 != "") && (regExp.test(password1) && regExp.test(password2))) {

            console.log("된다")
            $('#chkPwdMsg').html("사용 가능한 비밀번호입니다.");
            document.getElementById('chkPwdMsg').style.color='blue';

        } else if(password1 == "" && password2 == "") {
            $('#chkPwdMsg').html("비밀번호는 8~15자리의 문자, 숫자, 특수문자 조합");
            document.getElementById('chkPwdMsg').style.color='red';
        } else {
            $('#chkPwdMsg').html("두 비밀번호가 다르거나 조건에 맞지 않습니다.");
            document.getElementById('chkPwdMsg').style.color='red';
        }
    })


    $("#fn_reg").click(function () {

        var password1 = $('#password').val();
        var password2 = $('#chkPwd').val();
        var nickname = $('#nickname').val();

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if ($('input[name=chkUserId]').val() != 'checked' ||
                reg_email.test($('input[name=chkUserId]').val())) {
            alert("사용 가능한 이메일로 변경하세요.");
        }

        if (password1 !== password2) {
            alert("비밀번호가 올바르지 않습니다.");
            return;
        }

        if (nickname == "") {
            alert("이름을 입력하세요.");
            return;
        }

        var dto = {
            "userId" : $('#userId').val(),
            "password" : $('#password').val(),
            "nickname" : $('#nickname').val(),
            "addr" : $('#addr').val(),
            "addrDetail" : $('#addrDetail').val(),
            "emailYn" : $("input:checkbox[id='emailYn']").is("checked") == true ? "Y": "N",
            "authority" : $('#authority').val()
        }

        $.ajax({
            type: 'post',
            url: "/user/signup",
            data: dto,
            success : function (res) {
                alert("가입되었습니다.")
                window.location = "/"
            },
            error : function (err) {
                console.log("실패함 ㅠ")
            }
        })

    })

})


window.onload = function(){
    document.getElementById("addr").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
                document.getElementById("addr").value = data.address; // 주소 넣기
                document.querySelector("input[name=addrDetail]").focus(); //상세입력 포커싱
            }
        }).open();
    });
}
