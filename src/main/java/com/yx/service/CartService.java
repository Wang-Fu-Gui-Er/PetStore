package com.yx.service;

import com.yx.mapper.CartMapper;
import com.yx.model.Cart;
import com.yx.model.CartExample;
import com.yx.model.CartKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    private CartMapper cartDao;

    public int addCart(Map map){
        return cartDao.addCart(map);
    }
    public List<Cart> selectByExample(CartExample example){
        return cartDao.selectByExample(example);
    }
    public int deleteByPrimaryKey(CartKey key){
        return cartDao.deleteByPrimaryKey(key);
    }
    public int updateByPrimaryKey(Cart record){
        return cartDao.updateByPrimaryKey(record);
    }
}
