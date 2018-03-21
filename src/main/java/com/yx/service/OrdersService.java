package com.yx.service;

import com.yx.mapper.OrdersMapper;
import com.yx.model.Orders;
import com.yx.model.OrdersExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {
    @Autowired
    private OrdersMapper ordersDao;

    public int updateByPrimaryKey(Orders record){
        return ordersDao.updateByPrimaryKey(record);
    }
    public List<Orders> selectByExample(OrdersExample example){
        return ordersDao.selectByExample(example);
    }

}
