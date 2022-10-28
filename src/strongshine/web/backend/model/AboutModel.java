package strongshine.web.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.backend.dao.AboutDao;

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
