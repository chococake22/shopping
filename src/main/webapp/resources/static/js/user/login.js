function kakaoLogin() {

    $.ajax({
        url: '/login/kakao',
        type: 'get',
        data: 'text',
        success: function (res) {
            console.log("로그인 성공")
            location.href = res;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

