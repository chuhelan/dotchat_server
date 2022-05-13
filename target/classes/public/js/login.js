function verify_with_email_and_password() {
    var user_email = document.getElementById('user_email').value
    var user_password = document.getElementById('user_password').value
    console.log(user_email)
    console.log(user_password)
    fetch("/login?user_email=" + user_email + "&user_password=" + user_password)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            if (data.code === 200) {
                var dateTime = new Date()
                dateTime = dateTime.setDate(dateTime.getDate() + 5)
                dateTime = new Date(dateTime)
                document.cookie = "token=" + data.message.split('|')[0] + "; expires=" + dateTime.toString() + "; path=/";
                document.cookie = "id=" + data.message.split('|')[1] + "; expires=" + dateTime.toString() + "; path=/";
                window.location.replace("/home/index.html");
            }
            else if (data.code === 302) {
                alert("用户名或密码错误！")
            }
            else{
                alert("未知错误！")
            }
        }
        ).catch(console.error)
}