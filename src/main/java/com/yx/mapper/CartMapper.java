package com.yx.mapper;

import com.yx.model.Cart;
import com.yx.model.CartExample;
import com.yx.model.CartKey;
import java.util.List;
import java.util.Map;

import com.yx.model.Item;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;
import org.hibernate.validator.internal.engine.messageinterpolation.el.SimpleELContext;

public interface CartMapper {
    @Insert(value = "{ call addCart(" +
            "#{in_userid, mode=IN, jdbcType=INTEGER}," +
            "#{in_itemid, mode=IN, jdbcType=INTEGER}," +
            "#{in_quantity, mode=IN, jdbcType=INTEGER}," +
            "#{out_orderid, mode=OUT, jdbcType=INTEGER}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)//表明是存储过程
    int addCart(Map map);

    @SelectProvider(type=CartSqlProvider.class, method="countByExample")
    int countByExample(CartExample example);

    @DeleteProvider(type=CartSqlProvider.class, method="deleteByExample")
    int deleteByExample(CartExample example);

    @Delete({
        "delete from cart",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}",
          "and item_id = #{itemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(CartKey key);

    @Insert({
        "insert into cart (userid, order_id, ",
        "item_id, quantity)",
        "values (#{userid,jdbcType=INTEGER}, #{orderId,jdbcType=INTEGER}, ",
        "#{itemId,jdbcType=INTEGER}, #{quantity,jdbcType=INTEGER})"
    })
    int insert(Cart record);

    @InsertProvider(type=CartSqlProvider.class, method="insertSelective")
    int insertSelective(Cart record);

    @SelectProvider(type=CartSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER),
        @Result(column="item_id", property="item", javaType = Item.class,
            one = @One(select = "com.yx.mapper.ItemMapper.selectByPrimaryKey")
        )
    })
    List<Cart> selectByExample(CartExample example);

    @Select({
        "select",
        "userid, order_id, item_id, quantity",
        "from cart",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}",
          "and item_id = #{itemId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="userid", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="quantity", property="quantity", jdbcType=JdbcType.INTEGER)
    })
    Cart selectByPrimaryKey(CartKey key);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    @UpdateProvider(type=CartSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Cart record);

    @Update({
        "update cart",
        "set quantity = #{quantity,jdbcType=INTEGER}",
        "where userid = #{userid,jdbcType=INTEGER}",
          "and order_id = #{orderId,jdbcType=INTEGER}",
          "and item_id = #{itemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Cart record);
}