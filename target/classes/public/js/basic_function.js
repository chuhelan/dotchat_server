function close_top_banner() {
    console.log("asd")
    var full_banner = document.getElementById("full_banner")
    full_banner.remove()
}

// 由于不可抗拒的原因 该功能已经被弃用
// 鼠标经过显示菜单
// var down_div = document.getElementById("down_div")
// down_div.onmouseover
// function hold_down(){

// }

// 退出账号
function signout_account() {
    console.log("test")
    document.cookie = "token=; expires=Sun, 31 Dec 2017 12:00:00 UTC; path=/";
    window.location.replace("/login.html")
}

//发送邮件

function send_mail_button() {
    var mail_address = document.getElementById("send_mail_input").value

    // 先发邮件
    fetch("/mail/sendTextMail?to=" + mail_address + "&subject=感谢订阅!&content=感谢您的订阅！您的信息已经提交至服务器，您将会收到我们的更新提醒！该邮件由系统自动发送！")
        .then(Response => Response.json())
    // 再写数据库
    fetch("/send_mail?mail_address=" + mail_address)
        .then(Response => Response.json())
        .then(data => {
            if (data.code == 200) {
                alert("订阅成功！感谢订阅！")
            }
        }).catch(console.error)

}

