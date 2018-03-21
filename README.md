# PetStore
论坛网站
项目简介：
    实现对商品分类查询、精确查询、商品浏览、购买，对于添加到购物车的商品可以进行数量更改等功能的小动物购买网站。
技术：MVC Restful
M:Mybatis
V:HTML CSS JQuery AJAX
C:Spring SpringMVC
综合详述：
1.使用RestFul的架构风格，使得前后端分离，只需各自提供JSON数据即可，前端通过ajax提交请求给后端，
后端进行数据处理，返回数据也以json格式返回。
2.宏观结构依然是MVC，视图层均使用的是HTML页，使得视图解析速度变快，功能实现了对用户的注册、登录
进行合法性校验，对查找的数据进行页面展示，通过AJAX技术向控制层提交请求；
3.控制层由Spring、SpringMVC实现，根据AJAX提交的请求地址匹配@RequestMapping(value),寻找具体控制器，
然后调用模型层的方法进行业务处理，对于返回数据，由于使用了@RestController【controller+responseBody】
注解，所以直接对数据结果进行return操作。
4.模型层：使用了轻量级的半自动框架mybatis，使得sql查询速度变快，与hibernate相比的一丢丢缺点就是，
没有了查询级联的设置，需要自己修改一些SQL语句。

要点总结：
往购物车中添加商品，生成预订单，和最后确认订单的业务逻辑是这个项目的重点，用了一个存储过程完成一系列操作。
当向购物车添加商品时，首先判断是否有预订单【】，如果没有，则向orders新插入一条订单数据；然后判断购物
车中是否存在同类商品，有则更新数量，没有则向cart表新插入一条记录。
判断是否有预订单：购物车中有商品未结算时，所属订单dataTime字段为NULL，只有结算后，日期dataTime
才被设置为付款时间。所以通过对orders表中的dataTime字段进行查询即可判断。
其次就是JQuery框架对HTML控件的数据绑定操作性确实不如angular等新流行的前端框架，之后应用可以替换使用。
window.location.href重定向操作，和<a>标签href属性冲突；js函数嵌套调用，ajax回调函数响应顺序问题。
后端的Response不向前端移交，所以把数据传给前端，由前端操作cookie，对用户登录信息进行存储
