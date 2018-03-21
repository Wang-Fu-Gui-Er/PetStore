package com.yx.mapper;

import com.yx.model.Orders;
import com.yx.model.OrdersExample;
import com.yx.model.OrdersKey;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface OrdersMapper {
    @SelectProvider(type=OrdersSqlProvider.class, method="countByExample")
    int countByExample(OrdersExample example);

    @DeleteProvider(type=OrdersSqlProvider.class, method="deleteByExample")
    int deleteByExample(OrdersExample example);

    @Delete({
        "delete from orders",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(OrdersKey key);

    @Insert({
        "insert into orders (userid, order_id, ",
        "order_date, totleprice)",
        "values (#{userid,jdbcType=INTEGER}, #{orderId,jdbcType=INTEGER}, ",
        "#{orderDate,jdbcType=TIMESTAMP}, #{totleprice,jdbcType=INTEGER})"
    })
    int insert(Orders record);

    @InsertProvider(type=OrdersSqlProvider.class, method="insertSelective")
    int insertSelective(Orders record);

    @SelectProvider(type=OrdersSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_date", property="orderDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="totleprice", property="totleprice", jdbcType=JdbcType.INTEGER)
    })
    List<Orders> selectByExample(OrdersExample example);

    @Select({
        "select",
        "userid, order_id, order_date, totleprice",
        "from orders",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_date", property="orderDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="totleprice", property="totleprice", jdbcType=JdbcType.INTEGER)
    })
    Orders selectByPrimaryKey(OrdersKey key);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    @UpdateProvider(type=OrdersSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Orders record);

    @Update({
        "update orders",
        "set order_date = #{orderDate,jdbcType=TIMESTAMP},",
          "totleprice = #{totleprice,jdbcType=INTEGER}",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Orders record);
}