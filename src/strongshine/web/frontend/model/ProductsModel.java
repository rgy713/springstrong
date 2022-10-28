package strongshine.web.frontend.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import strongshine.web.frontend.dao.ProductsDao;

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

    public List<List<Map<String, Object>>> getProductList(String lang)
    {
        List<Map<String, Object>> product_list = this.productsDao.getProductList(lang);
        List<List<Map<String, Object>>> product_group = new ArrayList();
        List<Map<String, Object>> product_group_one = new ArrayList();
        int product_count = product_list.size();
        Long category_id = Long.valueOf(0L);
        for (int i = 0; i < product_count; i++)
        {
            Map<String, Object> one_product = (Map)product_list.get(i);
            Long one_category_id = (Long)one_product.get("category_id");
            if (i == 0)
            {
                product_group_one.add(one_product);
                category_id = one_category_id;
            }
            else if (category_id.equals(one_category_id))
            {
                product_group_one.add(one_product);
            }
            else
            {
                product_group.add(product_group_one);
                category_id = one_category_id;
                product_group_one = new ArrayList();
                product_group_one.add(one_product);
            }
        }
        if (!product_group_one.isEmpty()) {
            product_group.add(product_group_one);
        }
        return product_group;
    }
}
