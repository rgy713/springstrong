package strongshine.web.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.backend.dao.AdminDao;

@Service
@Scope("request")
public class AdminModel
{
    @Autowired
    private AdminDao adminDao = null;

    public AdminDao getAdminDao()
    {
        return this.adminDao;
    }
}
