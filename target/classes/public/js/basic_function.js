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

function get_my_follows(body, id) {
    // 加载关注者列表
    fetch("/get_follows?user_id=" + id)
        .then(Response => Response.json())
        .then(data => {
            let times = 1
            data.forEach(item => {
                if (times > 5) {
                    return
                }
                var uid = item.user_id
                var name = item.user_name
                var profile = item.user_profile
                var li = document.createElement("li")
                li.dataset.id = uid
                li.onclick = function () { window.location.href = "/accounts/index.html?id=" + uid }
                li.style.transform = "translateX(50%)"
                li.style.opacity = "0"
                li.style.transition = "transform .3s, opacity .3s"
                li.className = "flex flex-col items-center space-y-2"
                li.innerHTML = `<a class="block bg-white p-1 rounded-full" href="#">
                            <img class="w-16 rounded-full" src="${profile}">
                        </a>
                        <span class="text-xs text-gray-500">${name}</span>`
                // 触发动画
                setTimeout(() => {
                    body.appendChild(li)
                    setTimeout(() => {
                        li.style.transform = "translateX(0)"
                        li.style.opacity = "1"
                    }, 100)
                }, 250 * times)
                times++
            })
        })
}

function createPostBody(post, num, full) {
    if (window.loginInfo == undefined) {
        window.loginInfo = {}
    }
    const div = document.createElement("div")
    div.className = "bg-white shadow rounded-lg mb-6"
    div.style.transition = "transform .3s, opacity .3s"
    div.style.transform = "translateY(30%)"
    div.style.opacity = "0"
    div.id = "post-" + post.post_id
    const delbtn = id == post.post_author ? `<svg onclick="del_post(${post.post_id})" class="w-6 h-6 close-btn" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>` : ""
    // 用户顶栏
    div.innerHTML = `<div class="flex flex-row px-2 py-3 mx-3">
                        <div class="w-auto h-auto rounded-full border-2 border-green-500">
                            <img class="w-12 h-12 object-cover rounded-full shadow cursor-pointer" alt="User avatar" src="">
                        </div>
                        <div class="flex flex-col mb-2 ml-4 mt-1">
                            <div class="text-gray-600 text-sm font-semibold"></div>
                            <div class="flex w-full mt-1">
                                <div class="text-blue-700 font-base text-xs mr-1 cursor-pointer">
                                    
                                </div>
                                <div class="text-gray-400 font-thin text-xs">
                                    • ${post.post_date}
                                </div>
                            </div>
                        </div>
                        <div style="flex:1"></div>
                        ${delbtn}
                    </div>`
    // 正文
    const img_style = post.post_image_url === "undefined" ? "display:none;" : ""
    div.innerHTML = div.innerHTML + `
    <div class="border-b border-gray-100"></div>
    <div class="text-gray-400 font-medium text-sm mb-7 mt-6 mx-3 px-2">
        <img class="rounded w-full" src="${post.post_image_url}" style="max-height: 50vh;width: auto;${img_style}">
    </div>
    <div class="text-gray-500 text-sm mb-6 mx-3 px-2" style="cursor: pointer;" onclick="window.location.href = '/post/index.html?id=' + ${post.post_id}">
        ${post.post_content}
    </div>`
    // 互动部分
    let url = "https://dotchat.chuhelan.com" + "/post/index.html?id=" + post.post_id
    div.innerHTML = div.innerHTML + `
        <div class="flex justify-start mb-4 border-t border-gray-100">
            <div class="flex w-full mt-1 pt-2 pl-5">
                <span onclick="like_post(${post.post_id}, this.dataset.liked)" data-liked="false" class="transition ease-out duration-300 hover:bg-gray-50 bg-gray-100 h-8 px-2 py-2 mr-2
                    text-center rounded-full text-gray-100 cursor-pointer">
                    <svg class="h-4 w-4 text-red-500" stroke="currentColor" fill="none" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clip-rule="evenodd"></path></svg>
                </span>
                <!--
                <img class="inline-block object-cover w-8 h-8 text-white border-2 border-white rounded-full shadow-sm cursor-pointer" src="https://image.chuhelan.com/i/2022/05/02/626fd89bac55f.png" alt="">
                <img class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer" src="https://image.chuhelan.com/i/2022/05/02/626fd89bac55f.png" alt="">
                <img class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer" src="https://image.chuhelan.com/i/2022/05/02/626fd89bac55f.png" alt="">
                <img class="inline-block object-cover w-8 h-8 -ml-2 text-white border-2 border-white rounded-full shadow-sm cursor-pointer" src="https://image.chuhelan.com/i/2022/05/02/626fd89bac55f.png" alt="">
                -->
            </div>
            <div class="flex justify-end w-full mt-1 pt-2 pr-5">
                <span onclick="copy('post-comment-share-${post.post_id}')" id="post-comment-share-${post.post_id}" data-clipboard-text="${url}" class="transition ease-out duration-300 hover:bg-blue-50 bg-blue-100 w-8 h-8 px-2 py-2 text-center rounded-full text-blue-400 cursor-pointer">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" width="14px" viewBox="0 0 24 24" stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z">
                        </path>
                    </svg>
                </span>
            </div>
        </div>`
    // 文章数据
    div.innerHTML = div.innerHTML + `
        <div class="flex w-full border-t border-gray-100">
            <div class="mt-3 mx-5 flex w-full text-xs">
                <div class="flex text-gray-700 font-normal rounded-md mb-2 mr-4 items-center">喜欢:
                    <div class="ml-1 text-gray-400 text-ms"> ${post.post_likes_count}</div>
                </div>
            </div>
            <div class="mt-3 mx-5 w-full flex justify-end text-xs">
                <div class="flex text-gray-700  rounded-md mb-2 mr-4 items-center">评论:
                    <div class="ml-1 text-gray-400  text-ms"> ${post.post_comments_count}</div>
                </div>
            </div>
        </div>`

    // 加载更多
    if (full != undefined && full == true) {
        // 评论
        div.innerHTML = div.innerHTML + `
             <div class="text-black p-4 antialiased flex" style="display: none;">
                <img class="rounded-full h-8 w-8 mr-2 mt-1 ">
                <div>
                    <div class="bg-gray-100 rounded-lg px-4 pt-2 pb-2.5">
                        <div class="font-semibold text-sm leading-relaxed"></div>
                        <div class="text-xs leading-snug md:leading-normal"></div>
                    </div>
                </div>
                <div style="flex:1"></div>
                <svg class="w-6 h-6 close-btn" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>
            </div>`
        // 回复控件
        div.innerHTML = div.innerHTML + `
            <div class="relative flex items-center self-center w-full p-4 overflow-hidden text-gray-600 focus-within:text-gray-400">
                <img class="w-10 h-10 object-cover rounded-full shadow mr-2 cursor-pointer" alt="User avatar" src="${window.loginInfo.user_profile}">
                <span class="absolute inset-y-0 right-0 flex items-center pr-6">
                    <button type="submit" class="p-1 focus:outline-none focus:shadow-none hover:text-blue-500" onclick="sendComment(${post.post_id})">
                        <svg class="w-6 h-6 transition ease-out duration-300 hover:text-blue-500 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                            <path strokelinecap="round" strokelinejoin="round" strokewidth="{2}" d="M3 10h10a8 8 0 018 8v2M3 10l6 6m-6-6l6-6"></path></svg>
                    </button>
                </span>
                <input id="comment-send-${post.post_id}" type="search" class="w-full py-2 pl-4 pr-10 text-sm bg-gray-100 border border-transparent appearance-none rounded-tg placeholder-gray-400" style="border-radius: 25px" placeholder="评论点啥吧..." autocomplete="off">
            </div>`
    }

    // --------------------------------------------------------------------

    setTimeout(() => {
        // 插入内容
        document.getElementById("post-list").appendChild(div)

        // 触发动画
        setTimeout(() => {
            div.style.transform = "translateY(0)"
            div.style.opacity = "1"
        }, 100)
    }, 300 * num)

    // 填充用户信息
    fetch("/info?id=" + post.post_author)
        .then(Response => Response.json())
        .then(data_user => {
            const post_head = document.getElementById("post-" + post.post_id).children[0]
            post_head.children[0].children[0].onclick = function () { window.location.href = "/accounts/index.html?id=" + post.post_author }
            post_head.children[0].children[0].src = data_user.user_profile
            post_head.children[1].children[0].innerText = data_user.user_name
            post_head.children[1].children[1].children[0].innerText = data_user.user_location
        })

    // 填充评论
    fetch("/get_fist_comment/" + post.post_id)
        .then(Response => Response.json())
        .then(data_comment => {
            const comment_body = document.getElementById("post-" + post.post_id).children[6]
            if (data_comment != null && comment_body != undefined) {
                comment_body.style.display = "flex"
                comment_body.id = data_comment.comment_id
                comment_body.children[0].src = data_comment.comment_author_profileurl
                comment_body.children[0].style.cursor = "pointer"
                comment_body.children[0].onclick = function() { window.location.href = "/accounts/index.html?id=" + data_comment.comment_post_id }
                comment_body.children[1].children[0].children[0].innerText = data_comment.comment_author_name
                comment_body.children[1].children[0].children[1].innerText = data_comment.comment_content
                fetch("/can_i_delete?user_id=" + id + "&comment_id=" + data_comment.comment_id)
                    .then(Response => Response.json())
                    .then(data_can => {
                        if (data_can.code == 403 && data_can.message == "false") {
                            comment_body.children[3].style.display = "none"
                        } else {
                            comment_body.children[3].onclick = function() {
                                fetch("/delete_comment?user_id=" + id + "&comment_id=" + data_comment.comment_id)
                                    .then(Response => Response.json())
                                    .then(() => {
                                        window.location.reload()
                                    })
                            }
                        }
                    })
            }
        })

    // 刷新喜欢状态
    fetch("/is_liked?user_id=" + id + "&post_id=" + post.post_id)
        .then(Response => Response.json())
        .then(data_like => {
            const like_body = document.getElementById("post-" + post.post_id).children[4]
            if (data_like.code == 200 && data_like.message == "true") {
                like_body.children[0].children[0].children[0].style.fill = "rgb(239 68 68 / var(--tw-text-opacity))"
                like_body.children[0].children[0].dataset.liked = "true"
            }
        })
}

// 发送评论
function sendComment(post_id) {
    const txt = document.getElementById("comment-send-" + post_id).value
    if (txt != "") {
        // 提交 API
        fetch("/comment?post_id=" + post_id + "&user_id=" + id + "&comment=" + txt)
            .then(Response => Response.json())
            .then(data => {
                if (data.code == 200) {
                    window.location.reload()
                }
            })
    } else {
        alert("请输入内容！")
    }
}

// 删除帖子
function del_post(id) {
    fetch("/delete_post?post_id=" + id)
        .then(Response => Response.json())
        .then(() => {
            window.location.reload()
        })
}

// 喜欢帖子
function like_post(pid, tag) {
    if (tag != "true") {
        fetch("/like_post?user_id=" + id + "&post_id=" + pid)
            .then(Response => Response.json())
            .then(() => {
                window.location.reload()
            })
    } else {
        fetch("/dislike_post?user_id=" + id + "&post_id=" + pid)
            .then(Response => Response.json())
            .then(() => {
                window.location.reload()
            })
    }
}

// 显示弹窗
function show_pop(status) {
    const pop = document.getElementById("pop-main")
    if (status == true) {
        pop.style.display = "block"
        setTimeout(() => {
            pop.style.opacity = "1"
        }, 100)
    } else {
        pop.style.opacity = "0"
        setTimeout(() => {
            pop.style.display = "none"
        }, 300)
    }
}

// 加载所有关注者
function loadFollowers(uid) {
    fetch("/fower?user_id=" + uid)
            .then(Response => Response.json())
            .then(data => {
                const body = document.getElementById("pop-main").children[0]
                body.innerHTML = ""
                // 创建标题
                const title = document.createElement("h3")
                title.className = "text-gray-600 font-semibold mb-4"
                title.innerHTML = `粉丝<svg style="display: inline-block;float: right;" onclick="show_pop(false)" class="w-6 h-6 close-btn" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>`
                body.appendChild(title)
                data.forEach(item => {
                    // 创建内容
                    const div = document.createElement("div")
                    div.className = "flex justify-between px-2 py-3 mx-3"
                    div.innerHTML = `
                        <div class="flex">
                            <div class="w-auto h-auto rounded-full border-2 border-green-500" onclick="window.location.href = '/accounts/index.html?id=${item.user_id}'">
                            <img class="w-12 h-12 object-cover rounded-full shadow cursor-pointer" alt="User avatar" src="${item.user_profile}">
                        </div>
                        <div class="flex flex-col  mb-2 ml-4 mt-1">
                            <div class="text-gray-600 text-sm font-semibold">${item.user_name}</div>
                            <div class="flex w-full mt-1">
                                <div class="text-blue-700 font-base text-xs mr-1 cursor-pointer">
                                    ${item.user_location}
                                </div>
                            </div>
                        </div>
                    </div>`
                    body.appendChild(div)
                })
                // 显示弹窗
                show_pop(true)
            })
}

// 加载所有关注的人
// 加载所有关注者
function loadFollows(uid) {
    fetch("/fow?user_id=" + uid)
            .then(Response => Response.json())
            .then(data => {
                const body = document.getElementById("pop-main").children[0]
                body.innerHTML = ""
                // 创建标题
                const title = document.createElement("h3")
                title.className = "text-gray-600 font-semibold mb-4"
                title.innerHTML = `关注的人<svg style="display: inline-block;float: right;" onclick="show_pop(false)" class="w-6 h-6 close-btn" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path></svg>`
                body.appendChild(title)
                data.forEach(item => {
                    // 创建内容
                    const div = document.createElement("div")
                    div.className = "flex justify-between px-2 py-3 mx-3"
                    div.innerHTML = `
                        <div class="flex">
                            <div class="w-auto h-auto rounded-full border-2 border-green-500" onclick="window.location.href = '/accounts/index.html?id=${item.user_id}'">
                            <img class="w-12 h-12 object-cover rounded-full shadow cursor-pointer" alt="User avatar" src="${item.user_profile}">
                        </div>
                        <div class="flex flex-col  mb-2 ml-4 mt-1">
                            <div class="text-gray-600 text-sm font-semibold">${item.user_name}</div>
                            <div class="flex w-full mt-1">
                                <div class="text-blue-700 font-base text-xs mr-1 cursor-pointer">
                                    ${item.user_location}
                                </div>
                            </div>
                        </div>
                    </div>`
                    body.appendChild(div)
                })
                // 显示弹窗
                show_pop(true)
            })
}

// 加载搜索结果
function loadSearch(who) {
    const box = who.parentNode.children[0]
    box.innerHTML = ""
    box.style.transform = "scaleY(1)"
    if(who.value == "") {
        box.style.transform = "scaleY(0)"
        return
    }
    fetch("/s?wd=" + who.value)
            .then(Response => Response.json())
            .then(data => {
                data.forEach(item => {
                    // 创建内容
                    const div = document.createElement("div")
                    div.className = "flex justify-between px-2 py-3 mx-3"
                    div.innerHTML = `
                        <div class="flex">
                            <div class="w-auto h-auto rounded-full border-2 border-green-500" onclick="window.location.href = '/accounts/index.html?id=${item.user_id}'">
                            <img class="w-12 h-12 object-cover rounded-full shadow cursor-pointer" alt="User avatar" src="${item.user_profile}">
                        </div>
                        <div class="flex flex-col  mb-2 ml-4 mt-1">
                            <div class="text-gray-600 text-sm font-semibold">${item.user_name}</div>
                            <div class="flex w-full mt-1">
                                <div class="text-blue-700 font-base text-xs mr-1 cursor-pointer">
                                    ${item.user_location}
                                </div>
                            </div>
                        </div>
                    </div>`
                    box.appendChild(div)
                })
            })
}

// 关闭搜索框
function hiddenSearch(who) {
    who.parentNode.children[0].style.transform = "scaleY(0)"
}

// 复制
function copy(sender) {
    var clipboard = new ClipboardJS('#' + sender);

    clipboard.on('success', function(e) {
        alert("复制成功！")
    });
}