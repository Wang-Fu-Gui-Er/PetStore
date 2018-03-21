package com.yx.service;

import com.yx.mapper.ProductMapper;
import com.yx.model.Product;
import com.yx.model.ProductWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper dao;

    // 按外键查找product
    public List<Product> selectByCateId(Integer cateId){
        return dao.selectByCateId(cateId);
    }
}
