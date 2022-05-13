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

