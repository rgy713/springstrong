package strongshine.web.frontend.controllers;

import com.xspeeder.common.util.DateTimeUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import strongshine.web.frontend.dao.ContactDao;
import strongshine.web.frontend.model.ContactModel;

@Controller
@RequestMapping({"/contact"})
@Scope("request")
public class ContactController
{
    @Autowired
    private ContactModel contactModel = null;

    @RequestMapping({""})
    public String index(Model m)
    {
        m.addAttribute("someAttribute", "strongshine Page");
        return "frontend/contact.tiles";
    }

    @RequestMapping(value={"/add-feedback"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
    @ResponseBody
    public String addFeedback(@RequestParam String e_mail, @RequestParam String name, @RequestParam(required=false, defaultValue="") String subject, @RequestParam String content, HttpServletRequest req)
    {
        try
        {
            Map<String, Object> add_product_data = new HashMap();
            add_product_data.put("e_mail", e_mail);
            add_product_data.put("name", name);
            add_product_data.put("subject", subject);
            add_product_data.put("content", content);
            Date cur_tm = DateTimeUtil.utcNow();
            add_product_data.put("send_time", cur_tm);
            this.contactModel.getContactDao().addFeedback(add_product_data);
            return "SUCCESS";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
