package com.yx.controller;

import com.yx.model.*;
import com.yx.service.CartService;
import com.yx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = {"/cart","/order"})
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = ("/addRecord"))
    public ResponseEntity<String> addRecord(@RequestBody Cart cart){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("in_userid",cart.getUserid());
        map.put("in_itemid",cart.getItemId());
        map.put("in_quantity",cart.getQuantity());
        int result = cartService.addCart(map);
        String orderId =  map.get("out_orderid").toString();
        return new ResponseEntity<String>(orderId,HttpStatus.OK);
    }

    @RequestMapping(value = "/queryRecords/{userid}/{orderid}")
    public ResponseEntity<List<Cart>> queryRecords(
            @PathVariable String userid, @PathVariable String orderid){
        int userId = Integer.parseInt(userid);
        int orderId = Integer.parseInt(orderid);
        CartExample example = new CartExample();
        example.createCriteria().andUseridEqualTo(userId).andOrderIdEqualTo(orderId);
        List<Cart> carts = cartService.selectByExample(example);
        return new ResponseEntity<List<Cart>>(carts,HttpStatus.OK);//200
    }
    @RequestMapping(value = "/deleteRecord",method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteRecord(@RequestBody CartKey key){
        String oid = key.getOrderId().toString();
        key.setUserid(key.getUserid());
        key.setOrderId(key.getOrderId());
        key.setItemId(key.getItemId());
        cartService.deleteByPrimaryKey(key);
        return new ResponseEntity<String>(oid,HttpStatus.OK);//200
    }
    @RequestMapping(value = "/updateQuantity/{userid}/{orderid}/{itemid}/{newQuantity}", method = RequestMethod.GET)
    public ResponseEntity<String> updateQuantity(
            @PathVariable(value = "userid") String userid,
            @PathVariable(value = "orderid") String orderid,
            @PathVariable(value = "itemid") String itemid,
            @PathVariable(value = "newQuantity") String newQuantity
    ){
        Cart cart = new Cart();
        cart.setUserid(Integer.parseInt(userid));
        cart.setOrderId(Integer.parseInt(orderid));
        cart.setItemId(Integer.parseInt(itemid));
        cart.setQuantity(Integer.parseInt(newQuantity));
        cartService.updateByPrimaryKey(cart);
        return new ResponseEntity<String>(cart.getOrderId().toString(),HttpStatus.OK);
    }
    @RequestMapping(value = "/updateOrderRecord", method = RequestMethod.POST)
    public ResponseEntity<Void> updateOrderRecord(@RequestBody Orders orders){
        orders.setOrderDate(new Date()) ;
        ordersService.updateByPrimaryKey(orders);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value = "queryOrderId/{userid}", method = RequestMethod.GET)
    public ResponseEntity<String> queryOrderId(@PathVariable String userid){
        int userId = Integer.parseInt(userid);
        OrdersExample example = new OrdersExample();
        example.createCriteria().andUseridEqualTo(userId).andOrderDateIsNull();
        List<Orders> list = ordersService.selectByExample(example);
        int tmp = -1;
        //Orders orders = new Orders();
        for (Orders o : list){
            if (o.getOrderId() > tmp){
                tmp = o.getOrderId();
            }
        }
        return new ResponseEntity<String>(String.valueOf(tmp),HttpStatus.OK);
    }
}
