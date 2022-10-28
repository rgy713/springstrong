package strongshine.web.backend.dao;

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
    public List<Map<String, Object>> getProductList()
    {
        String sql = "SELECT id, category_id, product_img AS img, product_name_ko_KR AS name_ko, product_name_en_US AS name_en, product_name_zh_CN AS name_zh, product_name_ja_JP AS name_ja, product_content_ko_KR AS content_ko, product_content_en_US AS content_en, product_content_zh_CN AS content_zh, product_content_ja_JP AS content_ja  FROM tbl_home_product";

        return this.jdbcTemplate.queryForList(sql);
    }

    public void addProduct(Map<String, Object> add_product_data)
    {
        insert("tbl_home_product", add_product_data);
    }

    public void updateProduct(Map<String, Object> update_product_data, Long id)
    {
        update("tbl_home_product", update_product_data, id);
    }

    public void deleteProduct(Long id)
    {
        delete("tbl_home_product", id);
    }

    public List<Map<String, Object>> getProductInfoList(Long product_id)
    {
        String sql = "SELECT id, product_id,  image AS image,  content_ko_KR AS content_ko,  content_en_US AS content_en,  content_zh_CN AS content_zh,  content_ja_JP AS content_ja  FROM tbl_product_info  WHERE product_id=" +

                product_id;
        return this.jdbcTemplate.queryForList(sql);
    }

    public void addProductInfo(Map<String, Object> add_product_info_data)
    {
        insert("tbl_product_info", add_product_info_data);
    }

    public void updateProductInfo(Map<String, Object> update_product_info_data, Long id)
    {
        update("tbl_product_info", update_product_info_data, id);
    }

    public void deleteProductInfo(Long id)
    {
        delete("tbl_product_info", id);
    }

    public List<Map<String, Object>> getProductCategoryList()
    {
        String sql = "SELECT id,  name_ko_KR AS name_ko,  name_en_US AS name_en,  name_zh_CN AS name_zh,  name_ja_JP AS name_ja  FROM tbl_product_category;";

        return this.jdbcTemplate.queryForList(sql);
    }

    public void addProductCategory(Map<String, Object> add_product_category_data)
    {
        insert("tbl_product_category", add_product_category_data);
    }

    public void updateProductCategory(Map<String, Object> update_product_category_data, Long id)
    {
        update("tbl_product_category", update_product_category_data, id);
    }

    public void deleteProductCategory(Long id)
    {
        delete("tbl_product_category", id);
    }
}
