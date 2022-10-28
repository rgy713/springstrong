package strongshine.web.backend.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.backend.dao.ProductsDao;

@Service
@Scope("request")
public class ProductsModel
{
    @Autowired
    private ProductsDao productsDao = null;

    public ProductsDao getProductsDao()
    {
        return this.productsDao;
    }
}
