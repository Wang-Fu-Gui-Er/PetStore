package com.yx.mapper;

import com.yx.model.Category;
import com.yx.model.Product;
import com.yx.model.ProductExample;
import com.yx.model.ProductWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface ProductMapper {
      // 根据外键cateId查找product

    @Select({
            "select",
            "prod_id, prod_name, proc_pic_path, cate_id, prod_desc, proc_pic",
            "from product",
            "where cate_id = #{cateId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="prod_name", property="prodName", jdbcType=JdbcType.VARCHAR),
            @Result(column="proc_pic_path", property="procPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER),
            @Result(column="cate_id", property="category", javaType = Category.class,
                one = @One(select = "com.yx.mapper.CategoryMapper.selectByPrimaryKey")
            )
    })
    List<Product> selectByCateId(Integer cateId);


    @SelectProvider(type=ProductSqlProvider.class, method="countByExample")
    int countByExample(ProductExample example);

    @DeleteProvider(type=ProductSqlProvider.class, method="deleteByExample")
    int deleteByExample(ProductExample example);

    @Delete({
        "delete from product",
        "where prod_id = #{prodId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer prodId);

    @Insert({
        "insert into product (prod_id, prod_name, ",
        "proc_pic_path, cate_id, ",
        "prod_desc, proc_pic)",
        "values (#{prodId,jdbcType=INTEGER}, #{prodName,jdbcType=VARCHAR}, ",
        "#{procPicPath,jdbcType=VARCHAR}, #{cateId,jdbcType=INTEGER}, ",
        "#{prodDesc,jdbcType=LONGVARCHAR}, #{procPic,jdbcType=LONGVARBINARY})"
    })
    int insert(ProductWithBLOBs record);

    @InsertProvider(type=ProductSqlProvider.class, method="insertSelective")
    int insertSelective(ProductWithBLOBs record);

    @SelectProvider(type=ProductSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_name", property="prodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="proc_pic_path", property="procPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER),
        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="proc_pic", property="procPic", jdbcType=JdbcType.LONGVARBINARY)
    })
    List<ProductWithBLOBs> selectByExampleWithBLOBs(ProductExample example);

    @SelectProvider(type=ProductSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_name", property="prodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="proc_pic_path", property="procPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER)
    })
    List<Product> selectByExample(ProductExample example);

    @Select({
        "select",
        "prod_id, prod_name, proc_pic_path, cate_id, prod_desc, proc_pic",
        "from product",
        "where prod_id = #{prodId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="prod_id", property="prodId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="prod_name", property="prodName", jdbcType=JdbcType.VARCHAR),
        @Result(column="proc_pic_path", property="procPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER),
        @Result(column="prod_desc", property="prodDesc", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="proc_pic", property="procPic", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="cate_id", property="category", javaType = Category.class,
            one = @One(select = "com.yx.mapper.CategoryMapper.selectByPrimaryKey")
        )
    })
    ProductWithBLOBs selectByPrimaryKey(Integer prodId);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") ProductWithBLOBs record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    @UpdateProvider(type=ProductSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    @Update({
        "update product",
        "set prod_name = #{prodName,jdbcType=VARCHAR},",
          "proc_pic_path = #{procPicPath,jdbcType=VARCHAR},",
          "cate_id = #{cateId,jdbcType=INTEGER},",
          "prod_desc = #{prodDesc,jdbcType=LONGVARCHAR},",
          "proc_pic = #{procPic,jdbcType=LONGVARBINARY}",
        "where prod_id = #{prodId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    @Update({
        "update product",
        "set prod_name = #{prodName,jdbcType=VARCHAR},",
          "proc_pic_path = #{procPicPath,jdbcType=VARCHAR},",
          "cate_id = #{cateId,jdbcType=INTEGER}",
        "where prod_id = #{prodId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}