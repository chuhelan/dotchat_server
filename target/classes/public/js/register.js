function replace_after_register() {
    console.log("test")

    var user_name = document.getElementById('user_name').value
    var user_phonenumber = document.getElementById('user_phonenumber').value
    var user_email = document.getElementById('user_email').value
    var user_password = document.getElementById('user_password').value
    var password_confirm = document.getElementById('password_confirm').value

    console.log(user_name)
    console.log(user_phonenumber)
    console.log(user_email)
    console.log(user_password)
    console.log(password_confirm)

    if (user_password === password_confirm) {
        fetch("/register?user_name=" + user_name + "&user_phonenumber=" + user_phonenumber + "&user_email=" + user_email + "&user_password=" + user_password)
            .then(Response => Response.json())
            .then(data => {
                console.log(data)
                if (data.code === 200) {
                    window.location.replace("/");
                    alert("注册成功！")
                }
                else if (data.code === 302) {
                    alert("错误！")
                } else {
                    alert("未知错误")
                }
            }).catch(console.error)
    } else {
        alert("两次密码输入不正确")
    }


}

