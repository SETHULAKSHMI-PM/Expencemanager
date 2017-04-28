package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.CategoryDao;
import razorthink.dao.PayeeDao;
import razorthink.models.Category;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@Controller
@RequestMapping("/category")
public class CategoryController
{
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/save")
    @ResponseBody
    public String save(String category_name, String category_desc)
    {
        Category category = new Category(category_name, category_desc);
        categoryDao.save(category);
        return "Saved successfully";
    }
}