package strongshine.web.backend.controllers;

import com.xspeeder.common.util.DateTimeUtil;
import com.xspeeder.common.util.FileUtil;
import com.xspeeder.common.util.StringUtil;
import com.xspeeder.common.util.XMLParser;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import strongshine.web.backend.dao.ProductsDao;
import strongshine.web.backend.model.ProductsModel;

@Controller
@RequestMapping({"/products"})
@Scope("request")
public class ProductsController
{
    @Autowired
    private ProductsModel productsModel = null;

    @RequestMapping({""})
    public String products(ModelMap map)
    {
        List<Map<String, Object>> product_list = this.productsModel.getProductsDao().getProductList();
        List<Map<String, Object>> category_list = this.productsModel.getProductsDao().getProductCategoryList();
        String product_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/PRODUCT");
        String title = "strongshine Admin Page";
        map.put("product_file_path", product_file_path);
        map.put("product_list", product_list);
        map.put("category_list", category_list);
        map.put("title", title);
        return "backend/products.tiles";
    }

    @RequestMapping(value={"/add-category"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String addCategory(@RequestParam String name_ko, @RequestParam String name_en, @RequestParam String name_zh, @RequestParam String name_ja, HttpServletRequest req)
    {
        try
        {
            Map<String, Object> add_category_data = new HashMap();
            add_category_data.put("name_ko_KR", name_ko);
            add_category_data.put("name_en_US", name_en);
            add_category_data.put("name_zh_CN", name_zh);
            add_category_data.put("name_ja_JP", name_ja);
            this.productsModel.getProductsDao().addProductCategory(add_category_data);
            return "redirect:/web/backend/admin/products";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/update-category"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateCategory(@RequestParam Long id, @RequestParam String name_ko, @RequestParam String name_en, @RequestParam String name_zh, @RequestParam String name_ja, HttpServletRequest req)
    {
        try
        {
            Map<String, Object> update_category_data = new HashMap();
            if (!StringUtil.isEmpty(name_ko)) {
                update_category_data.put("name_ko_KR", name_ko);
            }
            if (!StringUtil.isEmpty(name_en)) {
                update_category_data.put("name_en_US", name_en);
            }
            if (!StringUtil.isEmpty(name_zh)) {
                update_category_data.put("name_zh_CN", name_zh);
            }
            if (!StringUtil.isEmpty(name_ja)) {
                update_category_data.put("name_ja_JP", name_ja);
            }
            this.productsModel.getProductsDao().updateProductCategory(update_category_data, id);
            return "redirect:/web/backend/admin/products";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/delete-category"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String deleteCategory(@RequestParam Long id, HttpServletRequest req)
    {
        try
        {
            this.productsModel.getProductsDao().deleteProductCategory(id);
            return "redirect:/web/backend/admin/products";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/add-product"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String addProduct(@RequestParam Long category_id, @RequestParam MultipartFile product_img, @RequestParam String product_name_ko, @RequestParam String product_name_en, @RequestParam String product_name_zh, @RequestParam String product_name_ja, @RequestParam String product_content_ko, @RequestParam String product_content_en, @RequestParam String product_content_zh, @RequestParam String product_content_ja, HttpServletRequest req)
    {
        try
        {
            String product_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/PRODUCT");
            FileUtil.makeDir(product_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = product_img.getOriginalFilename();
            String product_img_file_name = "product_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
            FileUtil.saveStreamToFile(product_img_path + product_img_file_name, product_img.getInputStream());

            Map<String, Object> add_product_data = new HashMap();

            add_product_data.put("category_id", category_id);
            add_product_data.put("product_img", product_img_file_name);
            add_product_data.put("product_name_ko_KR", product_name_ko);
            add_product_data.put("product_name_en_US", product_name_en);
            add_product_data.put("product_name_zh_CN", product_name_zh);
            add_product_data.put("product_name_ja_JP", product_name_ja);
            add_product_data.put("product_content_ko_KR", product_content_ko);
            add_product_data.put("product_content_en_US", product_content_en);
            add_product_data.put("product_content_zh_CN", product_content_zh);
            add_product_data.put("product_content_ja_JP", product_content_ja);
            this.productsModel.getProductsDao().addProduct(add_product_data);

            return "redirect:/web/backend/admin/products";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/update-product"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateProduct(@RequestParam Long id, @RequestParam Long category_id, @RequestParam MultipartFile product_img, @RequestParam String product_name_ko, @RequestParam String product_name_en, @RequestParam String product_name_zh, @RequestParam String product_name_ja, @RequestParam String product_content_ko, @RequestParam String product_content_en, @RequestParam String product_content_zh, @RequestParam String product_content_ja, HttpServletRequest req)
    {
        try
        {
            String product_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/PRODUCT");

            FileUtil.makeDir(product_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = product_img.getOriginalFilename();
            Map<String, Object> update_product_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String product_img_file_name = "product_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(product_img_path + product_img_file_name, product_img.getInputStream());

                update_product_data.put("product_img", product_img_file_name);
            }
            update_product_data.put("category_id", category_id);
            if (!StringUtil.isEmpty(product_name_ko)) {
                update_product_data.put("product_name_ko_KR", product_name_ko);
            }
            if (!StringUtil.isEmpty(product_name_en)) {
                update_product_data.put("product_name_en_US", product_name_en);
            }
            if (!StringUtil.isEmpty(product_name_zh)) {
                update_product_data.put("product_name_zh_CN", product_name_zh);
            }
            if (!StringUtil.isEmpty(product_name_ja)) {
                update_product_data.put("product_name_ja_JP", product_name_ja);
            }
            if (!StringUtil.isEmpty(product_content_ko)) {
                update_product_data.put("product_content_ko_KR", product_content_ko);
            }
            if (!StringUtil.isEmpty(product_content_en)) {
                update_product_data.put("product_content_en_US", product_content_en);
            }
            if (!StringUtil.isEmpty(product_content_zh)) {
                update_product_data.put("product_content_zh_CN", product_content_zh);
            }
            if (!StringUtil.isEmpty(product_content_ja)) {
                update_product_data.put("product_content_ja_JP", product_content_ja);
            }
            this.productsModel.getProductsDao().updateProduct(update_product_data, id);

            return "redirect:/web/backend/admin/products";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/delete-product"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateBanner(@RequestParam Long id, HttpServletRequest req)
    {
        this.productsModel.getProductsDao().deleteProduct(id);
        return "redirect:/web/backend/admin/products";
    }

    @RequestMapping(value={"/get-product-info"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object getProductInfo(@RequestParam Long product_id, HttpServletRequest req)
            throws Exception
    {
        return this.productsModel.getProductsDao().getProductInfoList(product_id);
    }

    @RequestMapping(value={"/add-product-info"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object addProductInfo(@RequestParam Long product_id, @RequestParam MultipartFile img, @RequestParam String content_ko, @RequestParam String content_en, @RequestParam String content_zh, @RequestParam String content_ja, HttpServletRequest req)
    {
        try
        {
            String product_info_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/PRODUCT");
            FileUtil.makeDir(product_info_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = img.getOriginalFilename();
            Map<String, Object> add_product_info_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String product_img_file_name = "product_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(product_info_img_path + product_img_file_name, img.getInputStream());

                add_product_info_data.put("image", product_img_file_name);
            }
            add_product_info_data.put("product_id", product_id);
            add_product_info_data.put("content_ko_KR", content_ko);
            add_product_info_data.put("content_en_US", content_en);
            add_product_info_data.put("content_zh_CN", content_zh);
            add_product_info_data.put("content_ja_JP", content_ja);
            this.productsModel.getProductsDao().addProductInfo(add_product_info_data);
            return this.productsModel.getProductsDao().getProductInfoList(product_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/update-product-info"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object updateProductInfo(@RequestParam Long id, @RequestParam Long product_id, @RequestParam MultipartFile img, @RequestParam String content_ko, @RequestParam String content_en, @RequestParam String content_zh, @RequestParam String content_ja, HttpServletRequest req)
    {
        try
        {
            String product_info_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/PRODUCT");
            FileUtil.makeDir(product_info_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = img.getOriginalFilename();
            Map<String, Object> update_product_info_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String product_img_file_name = "product_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(product_info_img_path + product_img_file_name, img.getInputStream());

                update_product_info_data.put("image", product_img_file_name);
            }
            update_product_info_data.put("product_id", product_id);
            if (!StringUtil.isEmpty(content_ko)) {
                update_product_info_data.put("content_ko_KR", content_ko);
            }
            if (!StringUtil.isEmpty(content_en)) {
                update_product_info_data.put("content_en_US", content_en);
            }
            if (!StringUtil.isEmpty(content_zh)) {
                update_product_info_data.put("content_zh_CN", content_zh);
            }
            if (!StringUtil.isEmpty(content_ja)) {
                update_product_info_data.put("content_ja_JP", content_ja);
            }
            this.productsModel.getProductsDao().updateProductInfo(update_product_info_data, id);
            return this.productsModel.getProductsDao().getProductInfoList(product_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/delete-product-info"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public Object deleteProductInfo(@RequestParam Long id, @RequestParam Long product_id, HttpServletRequest req)
    {
        try
        {
            this.productsModel.getProductsDao().deleteProductInfo(id);
            return this.productsModel.getProductsDao().getProductInfoList(product_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
