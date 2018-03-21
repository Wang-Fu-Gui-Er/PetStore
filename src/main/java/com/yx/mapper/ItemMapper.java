package com.yx.mapper;

import com.yx.model.Item;
import com.yx.model.ItemExample;
import java.util.List;

import com.yx.model.ProductWithBLOBs;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ItemMapper {
    @SelectProvider(type=ItemSqlProvider.class, method="countByExample")
    int countByExample(ItemExample example);

    @DeleteProvider(type=ItemSqlProvider.class, method="deleteByExample")
    int deleteByExample(ItemExample example);

    @Delete({
        "delete from item",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer itemId);

    @Insert({
        "insert into item (item_id, prod_id, ",
        "price, item_desc, ",
        "item_pic_path, item_pic)",
        "values (#{itemId,jdbcType=INTEGER}, #{prodId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DOUBLE}, #{itemDesc,jdbcType=VARCHAR}, ",
        "#{itemPicPath,jdbcType=VARCHAR}, #{itemPic,jdbcType=LONGVARBINARY})"
    })
    int insert(Item record);

    @InsertProvider(type=ItemSqlProvider.class, method="insertSelective")
    int insertSelective(Item record);

    @SelectProvider(type=ItemSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="item_desc", property="itemDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_pic_path", property="itemPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_pic", property="itemPic", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<Item> selectByExampleWithBLOBs(ItemExample example);

    @SelectProvider(type=ItemSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="item_desc", property="itemDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_pic_path", property="itemPicPath", jdbcType=JdbcType.VARCHAR)
    })
    List<Item> selectByExample(ItemExample example);

    @Select({
            "select",
            "item_id, prod_id, price, item_desc, item_pic_path, item_pic",
            "from item",
            "where prod_id = #{prod_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
            @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
            @Result(column="item_desc", property="itemDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="prod_id", property="productWithBLOBs",
                    javaType = ProductWithBLOBs.class,
                    one = @One(select = "com.yx.mapper.ProductMapper.selectByPrimaryKey")
            )
    })
    List<Item> selectByProId(Integer prodId);

    @Select({
        "select",
        "item_id, prod_id, price, item_desc, item_pic_path, item_pic",
        "from item",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="item_id", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER),
        @Result(column="price", property="price", jdbcType=JdbcType.DOUBLE),
        @Result(column="item_desc", property="itemDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_pic_path", property="itemPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="item_pic", property="itemPic", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column = "prod_id", property="productWithBLOBs",
                javaType = ProductWithBLOBs.class,
                one = @One(select = "com.yx.mapper.ProductMapper.selectByPrimaryKey")
        )
    })
    Item selectByPrimaryKey(Integer itemId);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Item record, @Param("example") ItemExample example);

    @UpdateProvider(type=ItemSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Item record);

    @Update({
        "update item",
        "set prod_id = #{prodId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DOUBLE},",
          "item_desc = #{itemDesc,jdbcType=VARCHAR},",
          "item_pic_path = #{itemPicPath,jdbcType=VARCHAR},",
          "item_pic = #{itemPic,jdbcType=LONGVARBINARY}",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Item record);

    @Update({
        "update item",
        "set prod_id = #{prodId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DOUBLE},",
          "item_desc = #{itemDesc,jdbcType=VARCHAR},",
          "item_pic_path = #{itemPicPath,jdbcType=VARCHAR}",
        "where item_id = #{itemId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Item record);
}