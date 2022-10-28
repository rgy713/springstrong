package strongshine.web.frontend.controllers;

import com.xspeeder.common.util.XMLParser;
import com.xspeeder.ws.service.BaseService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import strongshine.web.frontend.dao.AboutDao;
import strongshine.web.frontend.model.AboutModel;

@Controller
@RequestMapping({"/about"})
@Scope("request")
public class AboutController
        extends BaseService
{
    @Autowired
    private AboutModel aboutModel = null;

    @RequestMapping({""})
    public String index(ModelMap map, HttpServletRequest req)
    {
        String lang = getLanguage(null);
        List<Map<String, Object>> about_list = this.aboutModel.getAboutDao().getAboutList(lang);
        String about_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/ABOUT");
        map.put("about_list", about_list);
        map.put("file_path", about_file_path);
        return "frontend/about.tiles";
    }
}
