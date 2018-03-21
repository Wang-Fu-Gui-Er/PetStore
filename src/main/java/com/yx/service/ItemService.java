package com.yx.service;

import com.yx.mapper.ItemMapper;
import com.yx.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemMapper itemDao;

    public List<Item> selectByProId(Integer prodId){
        return itemDao.selectByProId(prodId);
    }
    public Item selectByPrimaryKey(Integer itemId){
        return itemDao.selectByPrimaryKey(itemId);
    }
}
