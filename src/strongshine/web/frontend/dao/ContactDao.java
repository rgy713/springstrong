package strongshine.web.frontend.dao;

import com.xspeeder.dao.BaseDao;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("request")
public class ContactDao
        extends BaseDao
{
    public void addFeedback(Map<String, Object> add_feedback_data)
    {
        insert("tbl_feedback", add_feedback_data);
    }
}
