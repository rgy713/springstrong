package strongshine.web.backend.controllers;

import com.xspeeder.common.util.DateTimeUtil;
import com.xspeeder.common.util.FileUtil;
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
import strongshine.web.backend.dao.AboutDao;
import strongshine.web.backend.model.AboutModel;

@Controller
@RequestMapping({"/about"})
@Scope("request")
public class AboutController
        extends BaseService
{
    @Autowired
    private AboutModel aboutModel = null;

    @RequestMapping({""})
    public String admin(ModelMap map)
    {
        List<Map<String, Object>> about_list = this.aboutModel.getAboutDao().getAboutList();
        String about_file_path = XMLParser.getClientConfigParam("/CONFIG/STORAGE/ABOUT");
        map.put("about_file_path", about_file_path);
        map.put("about_list", about_list);
        return "backend/about.tiles";
    }

    @RequestMapping(value={"/add-about"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String addBanner(@RequestParam MultipartFile image, @RequestParam String title_ko, @RequestParam String title_en, @RequestParam String title_zh, @RequestParam String title_ja, @RequestParam String content_ko, @RequestParam String content_en, @RequestParam String content_zh, @RequestParam String content_ja, HttpServletRequest req)
    {
        try
        {
            String img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/ABOUT");

            FileUtil.makeDir(img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = image.getOriginalFilename();
            Map<String, Object> add_about_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String img_file_name = "about_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(img_path + img_file_name, image.getInputStream());

                add_about_data.put("image", img_file_name);
            }
            add_about_data.put("title_ko_KR", title_ko);
            add_about_data.put("title_en_US", title_en);
            add_about_data.put("title_zh_CN", title_zh);
            add_about_data.put("title_ja_JP", title_ja);
            add_about_data.put("content_ko_KR", content_ko);
            add_about_data.put("content_en_US", content_en);
            add_about_data.put("content_zh_CN", content_zh);
            add_about_data.put("content_ja_JP", content_ja);
            this.aboutModel.getAboutDao().addAbout(add_about_data);

            return "redirect:/web/backend/admin/about";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/update-about"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateBanner(@RequestParam Long id, @RequestParam MultipartFile image, @RequestParam String title_ko, @RequestParam String title_en, @RequestParam String title_zh, @RequestParam String title_ja, @RequestParam String content_ko, @RequestParam String content_en, @RequestParam String content_zh, @RequestParam String content_ja, HttpServletRequest req)
    {
        try
        {
            String img_path = XMLParser.getConfigParam("/CONFIG/STORAGE/ABOUT");

            FileUtil.makeDir(img_path);
            Date cur_tm = DateTimeUtil.utcNow();
            String org_name = image.getOriginalFilename();
            Map<String, Object> update_about_data = new HashMap();
            if (!StringUtil.isEmpty(org_name))
            {
                String img_file_name = "about_" + cur_tm.getTime() + FileUtil.getExtension(org_name);
                FileUtil.saveStreamToFile(img_path + img_file_name, image.getInputStream());

                update_about_data.put("image", img_file_name);
            }
            if (!StringUtil.isEmpty(title_ko)) {
                update_about_data.put("title_ko_KR", title_ko);
            }
            if (!StringUtil.isEmpty(title_en)) {
                update_about_data.put("title_en_US", title_en);
            }
            if (!StringUtil.isEmpty(title_zh)) {
                update_about_data.put("title_zh_CN", title_zh);
            }
            if (!StringUtil.isEmpty(title_ja)) {
                update_about_data.put("title_ja_JP", title_ja);
            }
            if (!StringUtil.isEmpty(content_ko)) {
                update_about_data.put("content_ko_KR", content_ko);
            }
            if (!StringUtil.isEmpty(content_en)) {
                update_about_data.put("content_en_US", content_en);
            }
            if (!StringUtil.isEmpty(content_zh)) {
                update_about_data.put("content_zh_CN", content_zh);
            }
            if (!StringUtil.isEmpty(content_ja)) {
                update_about_data.put("content_ja_JP", content_ja);
            }
            this.aboutModel.getAboutDao().updateAbout(update_about_data, id);

            return "redirect:/web/backend/admin/about";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value={"/delete-about"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    public String updateBanner(@RequestParam Long id, HttpServletRequest req)
    {
        this.aboutModel.getAboutDao().deleteAbout(id);
        return "redirect:/web/backend/admin/about";
    }
}
