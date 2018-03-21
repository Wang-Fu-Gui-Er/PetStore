var USER_LOGIN_URL = "http://localhost:8080/account/login";
$(function () {
    $("#login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var flag = loginCheck(username,password);//登录合法性检查
        if (flag == 0){
            return;
        } else {
            login();    //登录
        }
    });
});
function login() {
    var json = JSON.stringify($("#form1").serializeObject());
    //console.log(json);
    $.ajax({
        url : USER_LOGIN_URL,
        data : json,
        type : "POST",
        contentType : "application/json",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                //alert(data.username);
                var userid = data.userid;
                var username = data.username;
                var encodeUsername = encodeURI(username);
                //console.log(encodeUsername+","+userid);
                var today = new Date();
                var expiresValue = new Date(today);//把日期转化为一个值
                expiresValue.setMinutes(today.getMinutes()+60*24*7);
                $.cookie("PET_STORE_USERID",userid,{"expires":expiresValue});
                $.cookie("PET_STORE_USERNAME",encodeUsername,{"expires":expiresValue});
                //alert("cookie:"+$.cookie('myusername'));
                window.location = "main.html";
            },
            409 : function () {
                $("#loginError").html("用户名或密码错误");
            }
        }
    });
}
function loginCheck(username,password) {
    if (username == ''){
        $("#uError").html("用户名不能为空！");
        return 0;
    }else if (password == ''){
        $("#uError").html("");
        $("#pError").html("密码不能为空！");
        return 0;
    } else {
        $("#uError").html("");
        $("#pError").html("");
        return 1;
    }
}