// 验证登录
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
save = save.replace("id", "user_id")
console.log("新save：" + save)
if (save.indexOf("&") > 0) {
    // 请求 API
    const httpRequest = new XMLHttpRequest()
    httpRequest.open('POST', '/verify', true)
    httpRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    httpRequest.send(save)
    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === 4 && httpRequest.status === 200) {
            console.log("登录验证：" + httpRequest.responseText)
            if (httpRequest.responseText.indexOf("200") <= 0) {
                // 跳回主页登录区域
                // window.location.replace("/") 
            }
        }
    };
} else {
    // 跳回主页登录区域
    window.location.replace("/login.html")
}

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
            console.log("|userprofile=" + data.user_profile + "|username=" + data.user_name + "|usergender=" + data.user_gender + "|userlocation=" + data.user_location)

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

//发送帖子
function post_by_post_author() {
    var post_author = id
    var post_content = document.getElementById("post_content").value
    var post_image_url = upload_address

    fetch("/post?post_author=" + post_author + "&post_content=" + post_content + "&post_image_url=" + post_image_url)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            if (data.code === 200) {
                console.log("上传成功！")
                post_plus_by_user_id()
                location.reload()
            } else {
                alert(data.message)
            }
        }).catch(console.error)
}

//发送帖子的同时呢 把该用户下的 count 帖子 +1 
function post_plus_by_user_id() {
    var user_id = id
    fetch("/post_plus?user_id=" + id)
        .then(Response => Response.json())
        .then(data => {
            if (data.code == 200) {
                console.log("发帖子成功！帖子数量+1")
            } else {
                alert("帖子发送成功！但计数api出错！")
            }
        }).catch(console.error)
}

let upload_file
let upload_address

function onChange(event) {
    upload_file = event.target.files[0];
    var reader = new FileReader();
    reader.onload = function (event) {
        console.log("输出上传图片文件结果")
        console.log(event.target.result)
    };
    reader.readAsText(upload_file);
    get_upload_image_url()
}

function get_upload_image_url() {
    let file = upload_file
    //   表单数据对象
    let formatData = new FormData()
    // 第一个 key           fileInfo
    // 第二个value是对应的值 file
    // 把上传的内容添加到表单 数据对象里面
    formatData.append("file", file)
    fetch("https://image.chuhelan.com/api/v1/upload", {
        method: "POST",
        headers: {
            // 'Content-Type': 'multipart/form-data',
            'Accept': 'application/json',
            // 'Access-Control-Allow-Origin': '*',
            'Accept-Encoding': 'gzip, deflate, br',
            // 'Access-Control-Allow-Headers': 'Content-Type,X-Custom-Header',
            // 'Access-Control-Allow-Methods': 'POST,GET,OPTIONS,DELETE',
            'Connection': 'keep-alive'
        },
        body: formatData
    }).then(Response => Response.json())
        .then(data => {
            console.log(data)
            if (data.status === true) {
                console.log("上传成功！")
                upload_address = data.data.links.url
                document.getElementById("image_image").src = upload_address
                document.getElementById("image_div").style.display = "flex"
                console.log(upload_address)

            }
            else {
                alert(data.message)
            }
        }).catch(console.error)
}



//设置一个完整的帖子 我将它分成几个部分 先用注释打出来
//最外层 一个div  <div class="bg-white shadow rounded-lg mb-6">
//顶部信息状态
//分界线
//帖子正文加帖子图像 
//帖子互动部分
//帖子数据部分
//评论部分 *评论不止一个 需要链接数据库 搜寻到所有该帖子下的所有评论
//自己发送帖子部分

function set_a_full_post_by_post_id_order_by_desc() {
    // what i need
    set_outter_div()


}

// 创建最外层div
function set_outter_div() {
    const outter_div = document.createElement("div")
    outter_div.className = "bg-white shadow rounded-lg mb-6"
}



// 获取帖子粉丝关注列表数量 并且设置



// 获取用户关注列表 获取所有用户信息 但是我只设置前面六个
function show_all_follows() {

    fetch("/get_follows?user_id=" + id)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            // data.forEach(item => {
            //     console.log(item.user_id)
            //     console.log(item.user_name)
            //     console.log(item.user_profile)
            // }

            for (let i = 0; i < 6; i++) {
                console.log(data[i].user_id)
                console.log(data[i].user_name)
                console.log(data[i].user_profile)
            }
        })

}