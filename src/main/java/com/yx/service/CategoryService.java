package com.yx.service;

import com.yx.mapper.CategoryMapper;
import com.yx.model.Category;
import com.yx.model.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper dao;

    // 查找全部种类
    public List<Category> selectByExample(CategoryExample example){
        return dao.selectByExample(example);
    }
}
