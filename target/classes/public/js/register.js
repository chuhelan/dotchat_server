function btn_register() {
    var user_name = document.getElementsByName("user_name").value;
    var user_phonenumber = document.getElementsByName("user_phonenumber").value;
    var user_email = document.getElementsByName("user_email").value;
    var user_password = document.getElementsByName("user_password").value;
    fetch("127.0.0.1:8888/register?user_name=" + user_name + "&user_phonenumber=" + user_phonenumber + "&user_email=" + user_email + "&user_password=" + user_password)
        .then(Response => Response.json()).catch(console.error)
}