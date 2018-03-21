var QUERY_ORDER_URL = "http://localhost:8080/order/queryOrderId";
$(function () {
    var userid = $.cookie("PET_STORE_USERID");
    //alert("外"+userid);
    if (userid == undefined){
        //alert("内"+userid);
        // $("#index_to_cart").click(function () {
        //     window.location = "../shop/login.html";
        // });
        $("#loginUser").html("未登录");
        $("#top_to_cart").attr("href","../shop/login.html");
        $("#index_to_cart").attr("href","../shop/login.html");
        return;
    }
    var username = $.cookie("PET_STORE_USERNAME");
    var deusername = decodeURI(username);
    $("#loginUser").html(deusername);
    queryOrderid();
});

function queryOrderid(){
    //var userid = 13;
    var userid = $.cookie("PET_STORE_USERID");
    //alert(userid);
    if (userid == undefined){
        window.location = "../shop/login.html";
        return;
    }
    $.ajax({
        url : QUERY_ORDER_URL+"/"+userid,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {//orderid
                $("#top_to_cart").attr("href","cart.html?orderId="+data);
                $("#index_to_cart").attr("href","shop/cart.html?orderId="+data);
            }
        }
    });
}
