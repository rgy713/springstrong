package strongshine.web.frontend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.frontend.dao.ContactDao;

@Service
@Scope("request")
public class ContactModel
{
    @Autowired
    private ContactDao contactDao = null;

    public ContactDao getContactDao()
    {
        return this.contactDao;
    }
}
