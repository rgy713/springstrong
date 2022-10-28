package strongshine.web.frontend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class HomeDao
        extends BaseDao
{
    public Integer getBannerCount()
    {
        String sql = "SELECT COUNT(banner_img) AS count FROM tbl_banner";
        return (Integer)this.jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public List<Map<String, Object>> getBannerList(String lang)
    {
        String sql = "SELECT banner_img AS img, banner_title_" +
                lang + " AS title, " +
                "banner_content_" + lang + " AS content " +
                "FROM" + " tbl_banner";
        return this.jdbcTemplate.queryForList(sql);
    }
}
