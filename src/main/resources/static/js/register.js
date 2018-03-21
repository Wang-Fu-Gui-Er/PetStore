var QUERY_CATEGORY_URL = "http://localhost:8080/category/queryAll";
var LANG = [{"id":"Chinese","value":"汉语"},{"id":"English","value":"英语"}];
var REGX=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
var REGISTER_URL = "http://localhost:8080/account/register";
$(function () {
    getLanguage();
    getCategory();
    $("#save").click(function () {
        var flag = registerCheck();
        console.log(flag);
        if (flag == 0){
            return;
        } else {
            register();
        }
    });
});
function register() {
    var json = JSON.stringify($("#form2").serializeObject());
    $.ajax({
        url : REGISTER_URL,
        data : json,
        type : "POST",
        contentType : "application/json",
        dataType : "text json",
        statusCode : {
            201 : function () { //注册成功
                alert("注册成功，请登录");
                window.location = "../shop/login.html";
            },
            200 : function () { //注册失败
                alert("注册失败，请重试");
                window.location = "../shop/register.html";
            },
            409 : function () { //重名
                alert("用户名已存在，请更改");
            }
        }
    });
}
function registerCheck() {
    var username = $("#username").val();
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    var email = $("#email").val();
    var realname = $("#realname").val();
    var address = $("#address").val();
    var language = $("#lang option").val();//Chinese
    var category = $("#category option").val();//1

    if(username == ''){
        $("#userError").html("用户名不能为空");
        return 0;
    } else if(password == ''){
        $("#userError").html("");
        $("#passError").html("密码不能为空");
        return 0;
    } else if(repassword == ''){
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("确认密码不能为空");
        return 0;
    } else if(email == ''){
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("");
        $("#emailError").html("邮箱不能为空");
        return 0;
    } else if(realname == ''){
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("");
        $("#emailError").html("");
        $("#realError").html("姓名不能为空");
        return 0;
    } else if(address == ''){
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("");
        $("#emailError").html("");
        $("#realError").html("");
        $("#addError").html("地址不能为空");
        return 0;
    } else if (password != repassword){
        $("#userError").html("");
        $("#passError").html("");
        $("#emailError").html("");
        $("#realError").html("");
        $("#addError").html("");
        $("#repassError").html("两次密码不一致");
        return 0;
    } else if (!REGX.test(email)){
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("");
        $("#realError").html("");
        $("#addError").html("");
        $("#emailError").html("邮箱格式不正确");
        return 0;
    } else {
        $("#userError").html("");
        $("#passError").html("");
        $("#repassError").html("");
        $("#realError").html("");
        $("#addError").html("");
        $("#emailError").html("");
        return 1;
    }
}
function getCategory() {
    $.ajax({
        url : QUERY_CATEGORY_URL,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                //console.log(data);
                var s = "";
                $(data).each(function (i) { //jquery的数据使用，必须用$()包上
                    //console.log(data[i].cateName);
                    s = s+"<option value='"+data[i].cateId+"'>"+data[i].cateName+"</option>";
                });
                $("#category").append(s);
            }
        }
    });
}

function getLanguage() {
    var s = "";
    for(var i in LANG) {
        s = s+"<option value='"+LANG[i].id+"'>"+LANG[i].value+"</option>";
    }
    $("#lang").append(s);
}
