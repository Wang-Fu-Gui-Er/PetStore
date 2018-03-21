var QUERY_CATEGORY_URL = "http://localhost:8080/category/queryAll";
var QUERY_PRODUCT_URL = "http://localhost:8080/product/queryProductByCateid";
$(function () {
    var cateId = getCateId();
    if (cateId != -1){
        getCateName(cateId);
        getProdect(cateId);
    }
});
function getProdect(cateId) {
    $.ajax({
        url : QUERY_PRODUCT_URL+"/"+cateId,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) { //某一种类所有product
                var s = "";
                $(data).each(function (i) {
                    // console.log(data[i]);
                    // console.log(data[i].prodId);
                    s = "<tr bgcolor='#FFFF88'>"+
                        "<td><img width='200' height='150' src='../dataImages/product/"+data[i].procPicPath+"' alt='加载失败'></td>"+
                        "<td width='200' height='150'><b><a style='text-decoration: none' href='items.html?prodId="+data[i].prodId+"'>"+data[i].prodName+"</a></b></td>"+
                    "</tr>";
                    $("#products").append(s);
                });
            }
        }
    });
}
function getCateName(cateId) {
    $.ajax({
        url : QUERY_CATEGORY_URL,
        type : "GET",
        dataType : "text json",
        statusCode : {
            200 : function (data) {
                //console.log(data);
                $(data).each(function (i) { //jquery的数据使用，必须用$()包上
                    // console.log(data[i].cateId);
                    // console.log(data[i].cateName);
                    // console.log(cateId);
                    if (data[i].cateId == cateId){
                        var cateName = data[i].cateName;
                        $("#catename").html(cateName);
                    }
                });
            }
        }
    });
}

function getCateId() {
    var href = location.href;//http://localhost:8080/shop/product.html?cateId=4
    var start = href.lastIndexOf("?");
    var cateIdExp = href.substring(start+1,href.length);
    var array = cateIdExp.split("=");
    var cateId = -1;
    if (array[0] == "cateId"){
        cateId = array[1];
    }
    return cateId;
}