package strongshine.web.frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GalleryController
{
    @RequestMapping({"/gallery"})
    public String index(Model m)
    {
        m.addAttribute("someAttribute", "strongshine Page");
        return "frontend/gallery.tiles";
    }
}
