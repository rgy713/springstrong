package strongshine.web.backend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class ContactDao
        extends BaseDao
{
    public List<Map<String, Object>> getFeedbackList()
    {
        String sql = "SELECT *  FROM tbl_feedback";

        return this.jdbcTemplate.queryForList(sql);
    }
}
