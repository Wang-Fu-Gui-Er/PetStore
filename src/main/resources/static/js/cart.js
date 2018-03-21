var ADD_CART_URL = "http://localhost:8080/cart/addRecord";
var QUERY_CART_URL = "http://localhost:8080/cart/queryRecords";
var DELETE_CART_URL = "http://localhost:8080/cart/deleteRecord";
var UPFDATE_CART_URL = "http://localhost:8080/cart/updateQuantity";
var COMMIT_CART_URL = "http://localhost:8080/cart/updateOrderRecord";
$(function () {
    $("#cartMsg").html("购物车");
    //var userid = 13;
    var userid = $.cookie("PET_STORE_USERID");
    if (userid == undefined){
        window.location = "../shop/login.html";
        return;
    }
    var itemId = getItemId();
    if (itemId != -1){
        /**
         * userid : cookie里取
         * itemid : 地址栏
         * quantity : 默认是1
         */
        var cartData = {"userid":userid,"itemId":itemId,"quantity":1};
        var json = JSON.stringify(cartData);
        addCartRecord(json);
    }
    var orderId = getOrderId();
    queryCartRecordsByOrderId(orderId);
});

function checkOut(userid,orderid,orderTotle) {
    if (orderTotle == 0){
        alert("未选择任何商品，不能结算！");
        return;
    }
    //alert(userid+","+orderid+","+orderTotle);
    var cartData = {"userid":userid,"orderId":orderid,"totleprice":orderTotle};
    var json = JSON.stringify(cartData);
    $.ajax({
        url : COMMIT_CART_URL,
        data : json,
        contentType : "application/json",
        type : "POST",
        //dataType : "text json",
        statusCode : {
            200 : function () {
                alert("完成付款");
                window.location = "../shop/main.html";
                // window.event.returnValue = false;
                // return;
            }
        }
    });
}
function addCartRecord(json) {
    //console.log(json);
    $.ajax({
        url : ADD_CART_URL,
        data : json,
        type : "POST",
        contentType : "application/json",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                //console.log(data);
                window.location = "../shop/cart.html?orderId="+data;
            }
        }
    });
}
function updateCartQuantity(userid,orderid,itemid){
    //alert(userid+orderid+itemid);
    //alert($("#item_quantity_"+itemid).val());
    var newQuantity = $("#item_quantity_"+itemid).val();
    // var cartData = {"userid":userid,"orderId":orderid,"itemId":itemid,"quantity":newQuantity};
    // var json = JSON.stringify(cartData);
    //console.log(json);
    $.ajax({
        url : UPFDATE_CART_URL+"/"+userid+"/"+orderid+"/"+itemid+"/"+newQuantity,
        //data : json,
        contentType : "application/json",
        Type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                window.location = "../shop/cart.html?orderId="+data;
            }
        }
    });
}
function delCartRecord(userid,orderid,itemid){
    //alert(123);
    //alert(userid+","+orderid+","+itemid);
    var cartData = {"userid":userid,"orderId":orderid,"itemId":itemid};
    var json = JSON.stringify(cartData);
    //var data = "/"+userid+"/"+orderid+"/"+itemid;
    //alert(json);
    $.ajax({
        url : DELETE_CART_URL,
        data : json,
        type : "DELETE",
        dataType : "text json",
        contentType : "application/json",
        statusCode : {
            200 : function (data) {//orderid
                //console.log(200);
                //queryCartRecordsByOrderId(data)
                window .location = "../shop/cart.html?orderId="+data;
            }
        }
    });
}
function queryCartRecordsByOrderId(orderid) {
    //var userid = 13;
    var userid = $.cookie("PET_STORE_USERID");
    if (userid == undefined){
        window.location = "../shop/login.html";
        return;
    }
    $.ajax({
        url : QUERY_CART_URL+"/"+userid+"/"+orderid,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                var cartTotlePrice = 0;
                $("#ORDERID").html(orderid);
                $(data).each(function (i) {
                    //console.log(data[i]);
                    var itemsTotlePrice = data[i].item.price*data[i].quantity;
                    cartTotlePrice += itemsTotlePrice;
                    var s =
                        "<tr bgcolor='#FFFF88'>"+
                            "<td>"+data[i].item.productWithBLOBs.prodName+"</td>"+
                            "<td>"+data[i].item.itemDesc+"</td>"+
                            "<td>"+data[i].item.price+"</td>"+
                            "<td><input id='item_quantity_"+data[i].itemId+"' type='number' min='1' value='"+data[i].quantity+"' "+
                                "onblur='updateCartQuantity("+userid+","+orderid+","+data[i].itemId+")'>"+
                            "</td>"+
                            "<td>"+itemsTotlePrice+"</td>"+
                            "<td>"+
                            "<a href='' onclick='delCartRecord("+
                                    userid+","+
                                    orderid+","+
                                    data[i].itemId+
                                    ")'>"+
                            "<img border='0' src='../images/button_remove.gif'/>"+
                            "</a>"+
                            "</td>"+
                        "</tr>";
                    $("#carts").append(s);
                });
                var s =
                    "<tr bgcolor='#FFFF88'>"+
                        "<td colspan='5' align='right'><b>总计</b></td>"+
                        "<td id='order_totle'>"+cartTotlePrice+"</td>"+
                    "</tr>";
                $("#carts").append(s);
                $("#checkOut").click(function () {
                    var userid = $.cookie("PET_STORE_USERID");
                    if (userid == undefined){
                        window.location = "../shop/login.html";
                    }
                    alert("准备转往付款界面");
                    checkOut(userid,orderid,cartTotlePrice);
                    //alert("待发货");
                });
            }
        }
    });
}

function getItemId() {
    var href = location.href;
    var start = href.lastIndexOf("?");
    var params = href.substring(start+1,href.length);
    var param = params.split("=");
    var itemId = -1;
    if (param[0] == 'itemId'){
        itemId = param[1];
    }
    return itemId;
}
function getOrderId() {
    var href = location.href;
    var start = href.lastIndexOf("?");
    var params = href.substring(start+1,href.length);
    var param = params.split("=");
    var orderId = -1;
    if (param[0] == 'orderId'){
        orderId = param[1];
    }
    return orderId;
}