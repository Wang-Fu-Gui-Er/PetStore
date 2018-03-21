package com.yx.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.yx.model.ProductExample.Criteria;
import com.yx.model.ProductExample.Criterion;
import com.yx.model.ProductExample;
import com.yx.model.ProductWithBLOBs;
import java.util.List;
import java.util.Map;

public class ProductSqlProvider {

    public String countByExample(ProductExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("product");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(ProductExample example) {
        BEGIN();
        DELETE_FROM("product");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(ProductWithBLOBs record) {
        BEGIN();
        INSERT_INTO("product");
        
        if (record.getProdId() != null) {
            VALUES("prod_id", "#{prodId,jdbcType=INTEGER}");
        }
        
        if (record.getProdName() != null) {
            VALUES("prod_name", "#{prodName,jdbcType=VARCHAR}");
        }
        
        if (record.getProcPicPath() != null) {
            VALUES("proc_pic_path", "#{procPicPath,jdbcType=VARCHAR}");
        }
        
        if (record.getCateId() != null) {
            VALUES("cate_id", "#{cateId,jdbcType=INTEGER}");
        }
        
        if (record.getProdDesc() != null) {
            VALUES("prod_desc", "#{prodDesc,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getProcPic() != null) {
            VALUES("proc_pic", "#{procPic,jdbcType=LONGVARBINARY}");
        }
        
        return SQL();
    }

    public String selectByExampleWithBLOBs(ProductExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("prod_id");
        } else {
            SELECT("prod_id");
        }
        SELECT("prod_name");
        SELECT("proc_pic_path");
        SELECT("cate_id");
        SELECT("prod_desc");
        SELECT("proc_pic");
        FROM("product");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String selectByExample(ProductExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("prod_id");
        } else {
            SELECT("prod_id");
        }
        SELECT("prod_name");
        SELECT("proc_pic_path");
        SELECT("cate_id");
        FROM("product");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        ProductWithBLOBs record = (ProductWithBLOBs) parameter.get("record");
        ProductExample example = (ProductExample) parameter.get("example");
        
        BEGIN();
        UPDATE("product");
        
        if (record.getProdId() != null) {
            SET("prod_id = #{record.prodId,jdbcType=INTEGER}");
        }
        
        if (record.getProdName() != null) {
            SET("prod_name = #{record.prodName,jdbcType=VARCHAR}");
        }
        
        if (record.getProcPicPath() != null) {
            SET("proc_pic_path = #{record.procPicPath,jdbcType=VARCHAR}");
        }
        
        if (record.getCateId() != null) {
            SET("cate_id = #{record.cateId,jdbcType=INTEGER}");
        }
        
        if (record.getProdDesc() != null) {
            SET("prod_desc = #{record.prodDesc,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getProcPic() != null) {
            SET("proc_pic = #{record.procPic,jdbcType=LONGVARBINARY}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("product");
        
        SET("prod_id = #{record.prodId,jdbcType=INTEGER}");
        SET("prod_name = #{record.prodName,jdbcType=VARCHAR}");
        SET("proc_pic_path = #{record.procPicPath,jdbcType=VARCHAR}");
        SET("cate_id = #{record.cateId,jdbcType=INTEGER}");
        SET("prod_desc = #{record.prodDesc,jdbcType=LONGVARCHAR}");
        SET("proc_pic = #{record.procPic,jdbcType=LONGVARBINARY}");
        
        ProductExample example = (ProductExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("product");
        
        SET("prod_id = #{record.prodId,jdbcType=INTEGER}");
        SET("prod_name = #{record.prodName,jdbcType=VARCHAR}");
        SET("proc_pic_path = #{record.procPicPath,jdbcType=VARCHAR}");
        SET("cate_id = #{record.cateId,jdbcType=INTEGER}");
        
        ProductExample example = (ProductExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(ProductWithBLOBs record) {
        BEGIN();
        UPDATE("product");
        
        if (record.getProdName() != null) {
            SET("prod_name = #{prodName,jdbcType=VARCHAR}");
        }
        
        if (record.getProcPicPath() != null) {
            SET("proc_pic_path = #{procPicPath,jdbcType=VARCHAR}");
        }
        
        if (record.getCateId() != null) {
            SET("cate_id = #{cateId,jdbcType=INTEGER}");
        }
        
        if (record.getProdDesc() != null) {
            SET("prod_desc = #{prodDesc,jdbcType=LONGVARCHAR}");
        }
        
        if (record.getProcPic() != null) {
            SET("proc_pic = #{procPic,jdbcType=LONGVARBINARY}");
        }
        
        WHERE("prod_id = #{prodId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(ProductExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}