package strongshine.web.backend.controllers;

import com.xspeeder.ws.service.BaseService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import strongshine.web.backend.dao.ContactDao;
import strongshine.web.backend.model.ContactModel;

@Controller
@RequestMapping({"/contact"})
@Scope("request")
public class ContactController
        extends BaseService
{
    @Autowired
    private ContactModel contactModel = null;

    @RequestMapping({""})
    public String admin(ModelMap map)
    {
        List<Map<String, Object>> feedback_list = this.contactModel.getContactDao().getFeedbackList();
        map.put("feedback_list", feedback_list);
        return "backend/contact.tiles";
    }
}
