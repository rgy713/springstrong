package strongshine.web.frontend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class ProductsDao
        extends BaseDao
{
    public List<Map<String, Object>> getProductList(String lang)
    {
        String sql = "SELECT t1.id,  t2.id AS category_id,  t2.name_" +

                lang + " AS category_name, " +
                " t1.product_img AS img, " +
                " t1.product_name_" + lang + " AS name, " +
                " t1.product_content_" + lang + "  AS content " +
                " FROM tbl_home_product AS t1 " +
                " LEFT JOIN  tbl_product_category AS t2 " +
                " ON  t1.category_id = t2.id " +
                " ORDER BY category_id;";
        return this.jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getProductInfoList(Long id, String lang)
    {
        String sql = "SELECT t1.id AS id,  t2.id AS info_id,  t1.product_name_" +

                lang + " AS name, " +
                " t2.image AS img, " +
                " t2.content_" + lang + " AS content " +
                " FROM tbl_home_product AS t1 " +
                " LEFT JOIN tbl_product_info AS t2 " +
                " ON t1.id=t2.product_id " +
                " WHERE t1.id=" + id;
        return this.jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getProductCategoryList(String lang)
    {
        String sql = "SELECT id,  name_" +
                lang + " AS name_ko " +
                " FROM" + " tbl_product_category;";
        return this.jdbcTemplate.queryForList(sql);
    }
}
