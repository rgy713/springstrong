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
import strongshine.web.frontend.dao.ProductsDao;
import strongshine.web.frontend.model.ProductsModel;

@Controller
@RequestMapping({"/"})
@Scope("request")
public class ProductsController
        extends BaseService
{
    @Autowired
    private ProductsModel productsModel = null;

    @RequestMapping({"/products"})
    public String products(ModelMap map, HttpServletRequest req)
    {
        String lang = getLanguage(null);
        List<List<Map<String, Object>>> product_list = this.productsModel.getProductList(lang);
        String product_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/PRODUCT");
        map.put("product_file_path", product_file_path);
        map.put("product_list", product_list);
        return "frontend/products.tiles";
    }

    @RequestMapping(value={"/product-info"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public String ProductInfo(@RequestParam Long product_id, ModelMap map, HttpServletRequest req)
    {
        String lang = getLanguage(null);
        List<Map<String, Object>> product_info_list = this.productsModel.getProductsDao().getProductInfoList(product_id, lang);
        String file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/PRODUCT");
        map.put("file_path", file_path);
        map.put("product_info_list", product_info_list);
        return "frontend/product_info.tiles";
    }
}
