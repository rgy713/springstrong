package strongshine.web.frontend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.frontend.dao.AboutDao;

@Service
@Scope("request")
public class AboutModel
{
    @Autowired
    private AboutDao aboutDao = null;

    public AboutDao getAboutDao()
    {
        return this.aboutDao;
    }
}
