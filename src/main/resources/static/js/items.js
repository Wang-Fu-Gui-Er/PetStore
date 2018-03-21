var QUERY_ITEMS_URL = "http://localhost:8080/item/queryItemsByProdId";
$(function () {
    var prodId = getprodId();
    //console.log(prodId);
    if (prodId != -1){
        getItems(prodId);
    }
});
function getItems(prodId) {
    $.ajax({
        url : QUERY_ITEMS_URL+"/"+prodId,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) { //List<Item>
                var catename = data[0].productWithBLOBs.category.cateName;
                console.log(catename);
                $("#cateName").html(catename);
                $(data).each(function (i) {
                    console.log(data[i]);
                    var s =
                    "<tr bgcolor='#FFFF88'>"+
                        "<td hidden id='itemId'>"+data[i].itemId+"</td>"+
                        "<td hidden id='prodId'>"+data[i].prodId+"</td>"+
                        "<td>"+data[i].productWithBLOBs.prodName+"</td>"+
                        "<td>"+data[i].price+"</td>"+
                        "<td><a href='../shop/item.html?itemId="+data[i].itemId+"&prodId="+data[i].prodId+"'>"+
                            data[i].itemDesc+"</a></td>"+
                        "<td>"+
                        "<a href='cart.html?itemId="+data[i].itemId+"'>"+
                        "<img border='0' src='../images/button_add_to_cart.gif'/>"+
                        "</a>"+
                        "</td>"+
                     "</tr>";
                    $("#items").append(s);
                });
            }
        }
    });
}
function getprodId() {
    var href = location.href;//http://localhost:8080/shop/product.html?cateId=4
    var start = href.lastIndexOf("?");
    var prodIdExp = href.substring(start+1,href.length);
    var array = prodIdExp.split("=");
    var prodid = -1;
    if (array[0] == "prodId"){
        prodid = array[1];
    }
    return prodid;
}