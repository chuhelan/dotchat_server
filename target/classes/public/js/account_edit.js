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

set_pageinfo();
set_email_name();

let online_image

function set_pageinfo() {
    fetch("/info?id=" + id)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            console.log("username=" + data.user_name + "usergender=" + data.user_gender + "userlocation=" + data.user_location)
            var name = document.getElementById("user_name")
            var gender = document.getElementById("user_gender")
            var profile = document.getElementById("user_image")
            var location = document.getElementById("user_location")
            var nav_image = document.getElementById("nav_image")
            name.value = data.user_name
            gender.value = data.user_gender
            profile.src = data.user_profile
            nav_image.src = data.user_profile
            location.value = data.user_location
            online_image = data.user_profile
            console.log("设置成功！")
        }).catch(console.error)
    fetch("/basic?id=" + id)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            console.log("userphonenumber=" + data.user_phonenumber + "useremail=" + data.user_email)
            var phone = document.getElementById("user_phone")
            var email = document.getElementById("user_email")
            phone.value = data.user_phonenumber
            email.value = data.user_email
            console.log("设置成功！")
        }).catch(console.error)
}


let out_sendname
let out_sendto
let out_subject = "密码已被修改!"
let out_content = "尊敬的" + out_sendname + "您好:您已为登录邮箱为" + out_sendto + "的账户重置密码。本邮件由系统自动发送，请勿回复。"

function set_email_name(){
    fetch("/email_name?user_id=" + id)
    .then(Response => Response.json())
    .then(data => {
        console.log(data)
        console.log("这是email信息")
        out_sendname = data.user_name
        out_sendto = data.user_email
        out_content = "尊敬的" + out_sendname + "您好:您已为登录邮箱为" + out_sendto + "的账户重置密码。本邮件由系统自动发送，请勿回复。"
    }).catch(console.error)
}


function send_mail() {
    fetch("/mail/sendTextMail?to=" + out_sendto + "&subject=" + out_subject + "&content=" + out_content)
        .then(Response => Response.json())
        .then(data => {
            console.log(data)
            alert("邮件正常发送！")
        }).catch(console.error)

}

function update_password() {
    var password_radio_state = document.getElementById("password_radio_state").checked
    console.log(password_radio_state)
    var password = document.getElementById("password").value
    var confirm_password = document.getElementById("confirm_password").value
    if (password_radio_state == true) {
        if (password === confirm_password) {
            fetch("/update_password?user_password=" + password + "&user_id=" + id)
                .then(Response => Response.json())
                .then(data => {
                    if (data.code === 200) {
                        send_mail()
                        window.location.replace("/login.html")
                    }
                })
        } else {
            alert("两次密码输入不一致，请确认后重新输入！")
        }
    } else {
        alert("请选中\"我同意\"！")
    }
}



// https://image.chuhelan.com/api/v1/upload 接口返回url填入user_gender
// 

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
    let file = document.getElementById("user_profile").files[0];
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
                document.getElementById("user_image").src = upload_address
                console.log(upload_address)

            }
            else {
                alert(data.message)
            }
        }).catch(console.error)
}

function update_userinfo() {
    var user_id = id;
    var user_name = document.getElementById("user_name").value
    var user_gender = document.getElementById("user_gender").value
    var user_profile = document.getElementById("user_image").src
    var user_location = document.getElementById("user_location").value
    var edit_radio_state = document.getElementById("edit_confirm").checked
    console.log(edit_radio_state)
    console.log("需要上传的值为" + user_id, user_name, user_gender, user_profile, user_location)

    if (edit_radio_state == true) {
        fetch("/update?user_id=" + user_id + "&user_name=" + user_name + "&user_gender=" + user_gender + "&user_profile=" + user_profile + "&user_location=" + user_location)
            .then(Response => Response.json())
            .then(data => {
                console.log(data)
                if (data.code === 200) {
                    alert("信息更新成功！")
                } else {
                    alert(data.message)
                }
            }).catch(console.error)
    } else {
        alert("请选中\"我同意\"！")
    }
}


