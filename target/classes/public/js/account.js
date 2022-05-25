let id = -1
let pageid = -1
let token = ""
let id_from_url = false     // 用于后续判定是否为他人页面

// 获取登录信息
id = getCookie("id")
token = getCookie("token")
console.log("id=" + id)
// 尝试从 url 中获取 id
const get_id = getQueryVariable("id")
if(get_id != false) {
    pageid = get_id
    console.log("pageid(url)=" + pageid)
    id_from_url = true
} else {
    // 尝试从 cookie 内获取 id
    pageid = getCookie("id")
    console.log("pageid(cookie)=" + pageid)
}

// 处理显示关注按钮还是修改资料按钮
if(id !== pageid) {
    // 隐藏修改资料按钮
    var edit_button = document.getElementById("edit_button")
    edit_button.remove()
} else {
    // 隐藏关注按钮
    var follow_button = document.getElementById("follow_button")
    follow_button.remove()
}
// 处理此人是否已经被关注
fetch("/get_follows?user_id=" + id)
.then(Response => Response.json())
.then(data => {
    console.log(data)
    data.forEach(item => {
        console.log(item.user_id + "/" + pageid)
        if(Number(item.user_id) == Number(pageid)) {
            // 禁用关注按钮
            document.getElementById("follow_button").onclick = function() { cancelFollow() }
            document.getElementById("follow_button_text").innerText = "取消关注"
        }
    })
})

let current_user_name
let current_user_id
set_userinfo()
set_user_count()

// 生成二维码
new QRCode(document.getElementById("qrcode"), id_from_url ? document.URL : (document.URL + "?id=" + id));
// 显示二维码的鼠标悬停操作
function showQRCode(isshow) {
    const div = document.getElementById("qrcode")
    if(isshow) {
        div.style.display = "block"
        setTimeout(() => {
            div.style.opacity = "1"
        }, 100)
    } else {
        div.style.opacity = "0"
        setTimeout(() => {
            div.style.display = "none"
        }, 3000)
    }
 }
// 该页面需要获取的用户信息有
// 顶层image的url 资料页面的imageurl 用户昵称 用户未知信息
function set_userinfo() {
    fetch("/info?id=" + pageid)
        .then(Response => Response.json())
        .then(data => {
            if(data.user_name === undefined) {
                window.location.href = "/404.html"
            }
            console.log(data)
            console.log("username=" + data.user_name + "usergender=" + data.user_gender + "userlocation=" + data.user_location)

            var profile = document.getElementById("user_image")
            profile.src = data.user_profile

            var name = document.getElementById("user_name")
            name.value = data.user_name
            name.innerHTML = data.user_name

            var location = document.getElementById("user_location")
            location.innerHTML = data.user_location

            console.log("设置成功！")
        })
        .catch(err => {
            console.log(err)
            window.location.href = "/404.html"
        })
        fetch("/info?id=" + id)
        .then(Response => Response.json())
        .then(data => {
            var nav_image = document.getElementById("nav_image")
            nav_image.src = data.user_profile
        })
        .catch(err => {
            console.log(err)
            window.location.href = "/404.html"
        })
}

function set_user_count() {
    fetch("/show_user_count?user_id=" + pageid)
        .then(Response => Response.json())
        .then(data => {
            var user_post_count = document.getElementById("post_count")
            var user_followers_count = document.getElementById("followers_count")
            var user_follows_count = document.getElementById("follows_count")

            user_post_count.innerHTML = data.user_post_count
            user_followers_count.innerHTML = data.user_followers_count
            user_follows_count.innerHTML = data.user_follows_count

            console.log("count信息设置成功！")
        }).catch(console.error)
} 

// 关注
function followUser() {
    if(id !== pageid) {
        fetch("/follow?user_id=" + id + "&follow_id=" + pageid)
        .finally(() => {
            // 刷新页面
            window.location.reload()
        })
    }
}

// 取消关注
function cancelFollow() {
    if(id !== pageid) {
        fetch("/cancel_follow?mine_id=" + id + "&its_id=" + pageid)
        .finally(() => {
            // 刷新页面
            window.location.reload()
        })
    }
}

// 加载关注者列表
get_my_follows(document.getElementById("follows_list"), pageid)

// 加载第一部分文章
window.nowPage = 0
fetch("/show_post/0?post_author=" + pageid)
    .then(Response => Response.json())
    .then(data => {
        console.log("get_post: ")
        console.log(data)
        if (data.length > 0) {
            for(let i=0; i<data.length; i++) {
                let times = 0
                createPostBody(data[i], times)
                times ++
            }
        } else {
            const div = document.createElement("div")
            div.classList = "shadow bg-white mt-6 p-6 rounded-lg"
            div.innerText = "什么都没有"
            div.style.textAlign = "center"
            document.getElementById("post-list").appendChild(div)
        }
        window.fistLoding = true
    })

// 页面滚动事件
window.onscroll = function () {
    if (getScrollTop() + getWindowHeight() == getScrollHeight() && window.nowPage != -1 && window.fistLoding == true) {
        // 页面滚到里底部，加载下一页
        window.nowPage ++
        console.log("现在加载的分页：" + nowPage)
        // 显示加载提醒
        const div = document.createElement("div")
        div.id = "post-loader"
        div.classList = "shadow bg-white mt-6 p-6 rounded-lg"
        div.innerText = "加载中"
        div.style.textAlign = "center"
        div.style.transition = "opacity .3s"
        document.getElementById("post-list").appendChild(div)
        fetch("/show_post/" + window.nowPage + "?post_author=" + pageid)
        .then(Response => Response.json())
        .then(data => {
           console.log("get_post: ")
            console.log(data)
            if(data.length > 0) {
                for(let i=0; i<data.length; i++) {
                    let times = 0
                    createPostBody(data[i], times)
                    times ++
                }
                // 不足五条说明是最后一页了，下次就不用加载了
                if(data.length < 5) {
                    window.nowPage = -1
                }
            } else {
                window.nowPage = -1
            }
        })
        .finally(() => {
            // 移除加载提醒
            document.getElementById("post-loader").opacity = "0"
            setTimeout(() => {
                document.getElementById("post-loader").parentNode.removeChild(document.getElementById("post-loader"))
            }, 300)
        })
    }
}

// ---------------------------------------

// 获取 URL 内的参数
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}

// 获取 Cookie
function getCookie(name) {
    let cookieValue = null;
    if (document.cookie && document.cookie !== '') {
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim();
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}

function getScrollTop() {
    var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
    if (document.body) {
        bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
    return scrollTop;
}

//文档的总高度
function getScrollHeight() {
    var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
    if (document.body) {
        bodyScrollHeight = document.body.scrollHeight;
    }
    if (document.documentElement) {
        documentScrollHeight = document.documentElement.scrollHeight;
    }
    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
    return scrollHeight;
}

function getWindowHeight() {
    var windowHeight = 0;
    if (document.compatMode == "CSS1Compat") {
        windowHeight = document.documentElement.clientHeight;
    } else {
        windowHeight = document.body.clientHeight;
    }
    return windowHeight;
}
