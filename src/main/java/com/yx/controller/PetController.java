package com.yx.controller;

import com.yx.model.Category;
import com.yx.model.CategoryExample;
import com.yx.model.Item;
import com.yx.model.Product;
import com.yx.service.CategoryService;
import com.yx.service.ItemService;
import com.yx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = {"/category","/product","/item"})
public class PetController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/queryAll")
    public ResponseEntity<List<Category>> getAllCategory(){
        CategoryExample example = new CategoryExample();
        example.createCriteria().andCateIdIsNotNull();
        List<Category> list = categoryService.selectByExample(example);
        return new ResponseEntity<List<Category>>(list, HttpStatus.OK);//200
    }

    @RequestMapping(value = "/queryProductByCateid/{cateId}",
            method = RequestMethod.GET)
    public ResponseEntity<List<Product>> queryProductByCateid(
            @PathVariable(value = "cateId") String cateId
    ){
        int cateid = Integer.parseInt(cateId);
        List<Product> products = productService.selectByCateId(cateid);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);//200
    }

    @RequestMapping(value = "/queryItemsByProdId/{prodId}",
            method = RequestMethod.GET)
    public ResponseEntity<List<Item>> queryItemsByProdId(
            @PathVariable(value = "prodId") String prodId
    ){
        int prodid = Integer.parseInt(prodId);
        List<Item> items = itemService.selectByProId(prodid);
        return new ResponseEntity<List<Item>>(items,HttpStatus.OK);//200
    }
    @RequestMapping(value = "/queryItemByItemId/{itemId}",
            method = RequestMethod.GET)
    public ResponseEntity<Item> queryItemByItemId(
            @PathVariable(value = "itemId") String itemId
    ){
        int itemid = Integer.parseInt(itemId);
        Item item = itemService.selectByPrimaryKey(itemid);
        return new ResponseEntity<Item>(item,HttpStatus.OK);//200
    }
}
