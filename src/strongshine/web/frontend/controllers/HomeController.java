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
import org.springframework.web.bind.annotation.RequestParam;
import strongshine.web.frontend.dao.AboutDao;
import strongshine.web.frontend.dao.HomeDao;
import strongshine.web.frontend.model.AboutModel;
import strongshine.web.frontend.model.HomeModel;
import strongshine.web.frontend.model.ProductsModel;

@Controller
@RequestMapping({"/"})
@Scope("request")
public class HomeController
        extends BaseService
{
    @Autowired
    private HomeModel homeModel = null;
    @Autowired
    private ProductsModel productsModel = null;
    @Autowired
    private AboutModel aboutModel = null;

    @RequestMapping(value={"/home"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String home(@RequestParam(required=false) String lang, ModelMap map, HttpServletRequest req)
    {
        String language = getLanguage(lang);
        List<Map<String, Object>> banner_list = this.homeModel.getHomeDao().getBannerList(language);
        List<List<Map<String, Object>>> product_list = this.productsModel.getProductList(language);
        List<Map<String, Object>> about_list = this.aboutModel.getAboutDao().getAboutList(language);
        String banner_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/BANNER");
        String product_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/PRODUCT");
        String about_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/ABOUT");
        map.put("banner_file_path", banner_file_path);
        map.put("product_file_path", product_file_path);
        map.put("about_file_path", about_file_path);
        map.put("banner_list", banner_list);
        map.put("product_list", product_list);
        map.put("about_list", about_list);
        return "frontend/home.tiles";
    }
}
