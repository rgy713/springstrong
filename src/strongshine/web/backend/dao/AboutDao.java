package strongshine.web.backend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class AboutDao
        extends BaseDao
{
    public List<Map<String, Object>> getAboutList()
    {
        String sql = "SELECT id, image AS img,  title_ko_KR AS title_ko,  title_en_US AS title_en,  title_zh_CN AS title_zh,  title_ja_JP AS title_ja,  content_ko_KR AS content_ko,  content_en_US AS content_en,  content_zh_CN AS content_zh,  content_ja_JP AS content_ja  FROM tbl_about";

        return this.jdbcTemplate.queryForList(sql);
    }

    public void addAbout(Map<String, Object> add_about_data)
    {
        insert("tbl_about", add_about_data);
    }

    public void updateAbout(Map<String, Object> update_about_data, Long id)
    {
        update("tbl_about", update_about_data, id);
    }

    public void deleteAbout(Long id)
    {
        delete("tbl_about", id);
    }
}
