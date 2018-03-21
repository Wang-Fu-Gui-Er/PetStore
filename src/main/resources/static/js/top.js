$(function () {
    addTop();
});
//jQuery合成html小页
function addTop() {
    $.get("../shop/top.html",function(data){
        $("#top").html(data);
    });
}
