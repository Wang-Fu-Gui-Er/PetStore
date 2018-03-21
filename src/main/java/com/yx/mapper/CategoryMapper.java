package com.yx.mapper;

import com.yx.model.Category;
import com.yx.model.CategoryExample;
import java.util.List;

import com.yx.model.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface CategoryMapper {
    @SelectProvider(type=CategorySqlProvider.class, method="countByExample")
    int countByExample(CategoryExample example);

    @DeleteProvider(type=CategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from category",
        "where cate_id = #{cateId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cateId);

    @Insert({
        "insert into category (cate_id, cate_name, ",
        "cate_desc)",
        "values (#{cateId,jdbcType=INTEGER}, #{cateName,jdbcType=VARCHAR}, ",
        "#{cateDesc,jdbcType=LONGVARCHAR})"
    })
    int insert(Category record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    int insertSelective(Category record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cate_name", property="cateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="cate_desc", property="cateDesc", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Category> selectByExampleWithBLOBs(CategoryExample example);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cate_name", property="cateName", jdbcType=JdbcType.VARCHAR)
    })
    List<Category> selectByExample(CategoryExample example);

    @Select({
        "select",
        "cate_id, cate_name, cate_desc",
        "from category",
        "where cate_id = #{cateId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="cate_id", property="cateId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="cate_name", property="cateName", jdbcType=JdbcType.VARCHAR),
        @Result(column="cate_desc", property="cateDesc", jdbcType=JdbcType.LONGVARCHAR)
//        @Result(column="cate_id", property="products", javaType = List.class,
//                many = @Many(select = "com.yx.mapper.ProductMapper.selectByCateId")
//        )
    })
    Category selectByPrimaryKey(Integer cateId);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update category",
        "set cate_name = #{cateName,jdbcType=VARCHAR},",
          "cate_desc = #{cateDesc,jdbcType=LONGVARCHAR}",
        "where cate_id = #{cateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(Category record);

    @Update({
        "update category",
        "set cate_name = #{cateName,jdbcType=VARCHAR}",
        "where cate_id = #{cateId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Category record);
}