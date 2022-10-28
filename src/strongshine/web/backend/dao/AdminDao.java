package strongshine.web.backend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class AdminDao
        extends BaseDao
{
    public List<Map<String, Object>> getBannerList()
    {
        String sql = "SELECT id, banner_img AS img, banner_title_ko_KR AS title_ko, banner_title_en_US AS title_en, banner_title_zh_CN AS title_zh, banner_title_ja_JP AS title_ja, banner_content_ko_KR AS content_ko, banner_content_en_US AS content_en, banner_content_zh_CN AS content_zh, banner_content_ja_JP AS content_ja  FROM tbl_banner";

        return this.jdbcTemplate.queryForList(sql);
    }

    public void addBanner(Map<String, Object> add_banner_data)
    {
        insert("tbl_banner", add_banner_data);
    }

    public void updateBanner(Map<String, Object> update_banner_data, Long id)
    {
        update("tbl_banner", update_banner_data, id);
    }

    public void deleteBanner(Long id)
    {
        delete("tbl_banner", id);
    }
}
