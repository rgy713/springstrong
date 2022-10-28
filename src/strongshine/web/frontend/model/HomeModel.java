package strongshine.web.frontend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.frontend.dao.HomeDao;

@Service
@Scope("request")
public class HomeModel
{
    @Autowired
    private HomeDao homeDao = null;

    public HomeDao getHomeDao()
    {
        return this.homeDao;
    }
}
