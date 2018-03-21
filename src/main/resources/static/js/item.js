var QUERY_ITEM_URL = "http://localhost:8080/item/queryItemByItemId";
$(function () {
        var params = getParams();
        var itemId = params[0];
        var prodId = params[1];
        if (itemId != -1 && prodId != -1){
            //  console.log(itemId+","+prodId);
            $("#cart").attr("href","../shop/cart.html?itemId="+itemId);
            getItem(itemId);
        }
});
function getItem(itemId) {
    $.ajax({
        url : QUERY_ITEM_URL+"/"+itemId,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                console.log(data);
                $("#itemDesc").html(data.itemDesc);
                $("#price").html("价格：￥"+data.price+"元");
                $("#prodName").html(data.productWithBLOBs.prodName);
                $("#prodDesc").html(data.productWithBLOBs.prodDesc);
                $("#cateName").html(data.productWithBLOBs.category.cateName);
                $("#pic").attr("src","../dataImages/item/"+data.itemPicPath);
            }
        }
    });
}
function getParams() {
    var href = location.href;
    //http://localhost:8080/shop/product.html?cateId=4&cateId=4
    var start = href.lastIndexOf("?");
    var params = href.substring(start+1,href.length);//cateId=4&cateId=4
    //  console.log(params);
    var array_params = params.split("&");
    var itemid_param = array_params[0].split("=");
    var prodid_param = array_params[1].split("=");
    var itemId = -1;
    if (itemid_param[0] == 'itemId'){
        itemId = itemid_param[1];
    }
    var prodId = -1;
    if (prodid_param[0] == 'prodId'){
        prodId = prodid_param[1];
    }
    var result = [itemId,prodId];
    return result;
}