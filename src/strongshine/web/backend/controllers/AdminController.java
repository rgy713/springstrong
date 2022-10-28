package strongshine.web.backend.controllers;

import com.xspeeder.common.util.DateTimeUtil;
import com.xspeeder.common.util.FileUtil;
import com.xspeeder.common.util.ImageUtil;
import com.xspeeder.common.util.StringUtil;
import com.xspeeder.common.util.XMLParser;
import com.xspeeder.ws.service.BaseService;
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
import org.springframework.web.multipart.MultipartFile;
import strongshine.base.Const;
import strongshine.web.backend.dao.AdminDao;
import strongshine.web.backend.model.AdminModel;

@Controller
@RequestMapping({""})
@Scope("request")
public class AdminController extends BaseService
{
    @Autowired
    private AdminModel adminModel = null;

    @RequestMapping({"/"})
    public String home(ModelMap map)
    {
        return "redirect:/web/backend/admin/home";
    }

    @RequestMapping({"/home"})
    public String admin(ModelMap map)
    {
        List<Map<String, Object>> banner_list = this.adminModel.getAdminDao().getBannerList();
        String banner_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/BANNER");
        map.put("banner_file_path", banner_file_path);
        map.put("banner_list", banner_list);
        return "backend/admin.tiles";
    }

    @RequestMapping(value={"/home/add-banner"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String addBanner(@RequestParam MultipartFile banner_img, @RequestParam String banner_title_ko, @RequestParam String banner_title_en, @RequestParam String banner_title_zh, @RequestParam String banner_title_ja, @RequestParam String banner_content_ko, @RequestParam String banner_content_en, @RequestParam String banner_content_zh, @RequestParam String banner_content_ja, HttpServletRequest req)
    {
        try
        {
            String banner_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/BANNER");

            FileUtil.makeDir(banner_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = banner_img.getOriginalFilename();
            String banner_img_file_name = "banner_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
            FileUtil.saveStreamToFile(banner_img_path + banner_img_file_name, banner_img.getInputStream());
            ImageUtil.resizeImage(banner_img_path + banner_img_file_name, banner_img_path + banner_img_file_name, Const.BANNER_IMG_WIDTH.intValue(), Const.BANNER_IMG_HEIGHT.intValue());
            Map<String, Object> add_banner_data = new HashMap();

            add_banner_data.put("banner_img", banner_img_file_name);
            add_banner_data.put("banner_title_ko_KR", banner_title_ko);
            add_banner_data.put("banner_title_en_US", banner_title_en);
            add_banner_data.put("banner_title_zh_CN", banner_title_zh);
            add_banner_data.put("banner_title_ja_JP", banner_title_ja);
            add_banner_data.put("banner_content_ko_KR", banner_content_ko);
            add_banner_data.put("banner_content_en_US", banner_content_en);
            add_banner_data.put("banner_content_zh_CN", banner_content_zh);
            add_banner_data.put("banner_content_ja_JP", banner_content_ja);
            this.adminModel.getAdminDao().addBanner(add_banner_data);

            return "redirect:/web/backend/admin/home";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/home/update-banner"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateBanner(@RequestParam Long id, @RequestParam MultipartFile banner_img, @RequestParam String banner_title_ko, @RequestParam String banner_title_en, @RequestParam String banner_title_zh, @RequestParam String banner_title_ja, @RequestParam String banner_content_ko, @RequestParam String banner_content_en, @RequestParam String banner_content_zh, @RequestParam String banner_content_ja, HttpServletRequest req)
    {
        try
        {
            String banner_img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/BANNER");

            FileUtil.makeDir(banner_img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = banner_img.getOriginalFilename();
            Map<String, Object> update_banner_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String banner_img_file_name = "banner_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(banner_img_path + banner_img_file_name, banner_img.getInputStream());
                ImageUtil.resizeImage(banner_img_path + banner_img_file_name, banner_img_path + banner_img_file_name, Const.BANNER_IMG_WIDTH.intValue(), Const.BANNER_IMG_HEIGHT.intValue());
                update_banner_data.put("banner_img", banner_img_file_name);
            }
            if (!StringUtil.isEmpty(banner_title_ko)) {
                update_banner_data.put("banner_title_ko_KR", banner_title_ko);
            }
            if (!StringUtil.isEmpty(banner_title_en)) {
                update_banner_data.put("banner_title_en_US", banner_title_en);
            }
            if (!StringUtil.isEmpty(banner_title_zh)) {
                update_banner_data.put("banner_title_zh_CN", banner_title_zh);
            }
            if (!StringUtil.isEmpty(banner_title_ja)) {
                update_banner_data.put("banner_title_ja_JP", banner_title_ja);
            }
            if (!StringUtil.isEmpty(banner_content_ko)) {
                update_banner_data.put("banner_content_ko_KR", banner_content_ko);
            }
            if (!StringUtil.isEmpty(banner_content_en)) {
                update_banner_data.put("banner_content_en_US", banner_content_en);
            }
            if (!StringUtil.isEmpty(banner_content_zh)) {
                update_banner_data.put("banner_content_zh_CN", banner_content_zh);
            }
            if (!StringUtil.isEmpty(banner_content_ja)) {
                update_banner_data.put("banner_content_ja_JP", banner_content_ja);
            }
            this.adminModel.getAdminDao().updateBanner(update_banner_data, id);

            return "redirect:/web/backend/admin/home";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/home/delete-banner"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateBanner(@RequestParam Long id, HttpServletRequest req)
    {
        this.adminModel.getAdminDao().deleteBanner(id);
        return "redirect:/web/backend/admin/home";
    }
}
