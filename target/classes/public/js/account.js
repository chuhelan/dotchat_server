let id = -1
let cookie = ''
const cookies = document.cookie
const cookie_list = cookies.split(";")
let save = ""
cookie_list.forEach(function (element) {
    if (element.trim().substring(0, 2) === 'id' || element.trim().substring(0, 5) === 'token') {
        save += element + "&"
    }
})
save = save.substring(0, save.length - 1)
save = save.split(" ").join("")     // 去除空格
console.log('save：' + save)
if (save.indexOf('&') > 0) {
    if (save.split('&')[0].split('=')[0].trim() === 'id') {
        id = save.split('&')[0].split('=')[1]
        cookie = save.split('&')[1].split('=')[1]
    } else {
        id = save.split('&')[1].split('=')[1]
        cookie = save.split('&')[0].split('=')[1]
    }
}
console.log("id=" + id)


let current_user_name
let current_user_id
set_userinfo()
set_user_count()

// 该页面需要获取的用户信息有
// 顶层image的url 资料页面的imageurl 用户昵称 用户未知信息
function set_userinfo() {
    fetch("/info?id=" + id)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            console.log("username=" + data.user_name + "usergender=" + data.user_gender + "userlocation=" + data.user_location)

            var nav_image = document.getElementById("nav_image")
            var profile = document.getElementById("user_image")
            nav_image.src = data.user_profile
            profile.src = data.user_profile

            var name = document.getElementById("user_name")
            name.value = data.user_name
            name.innerHTML = data.user_name

            var location = document.getElementById("user_location")
            location.innerHTML = data.user_location

            console.log("设置成功！")

            set_follow_or_edit()
        }).catch(console.error)
}

function set_user_count() {
    fetch("/show_user_count?user_id=" + id)
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

// 判断该页面cookie的id是否和用户昵称id是否一致
// 通过用户昵称查找用户id 如相等 隐藏关注按钮 显示修改资料， 如果不相等 隐藏修改资料 显示关注按钮
function set_follow_or_edit() {
    current_user_name = document.getElementById("user_name").value
    console.log("获取到的current_name为：" + current_user_name)
    fetch("/find_id?user_name=" + current_user_name)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            current_user_id = data.user_id
            console.log("当前浏览页面上的id为：" + current_user_id)
            console.log("读取到cookie中的id为：" + id)
            if (id == current_user_id) {
                // 隐藏关注按钮
                var follow_button = document.getElementById("follow_button")
                follow_button.remove()
            }
            else if (id != current_user_id) {
                // 隐藏修改资料按钮
                var edit_button = document.getElementById("edit_button")
                edit_button.remove()
            } else {
                alert("你为什么会看到这个消息？你把我整不会了，你不应该看到的啊？？？请速度联系我！关键字：current_user_id error")
            }
        }).catch(console.error)
}


// 获取帖子粉丝关注列表数量 并且设置



// 获取用户关注列表