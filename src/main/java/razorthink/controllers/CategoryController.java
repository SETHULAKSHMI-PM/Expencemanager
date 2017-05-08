package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import razorthink.dao.CategoryDao;
import razorthink.models.Category;
import razorthink.pojo.CategoryPojo;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@RestController
@RequestMapping(value = "rest/category")
public class CategoryController
{
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody CategoryPojo categoryPojo)
    {
        String cat_name = String.valueOf(categoryDao.getByCategoryName(categoryPojo.getCategory_name()));
        if(cat_name.equals("null"))
        {
            try
            {
                Category category = new Category(categoryPojo.getCategory_name(), categoryPojo.getCategory_desc());
                categoryDao.save(category);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "Category Saved successfully. ";
        }
        else
        {
            return "Category Name is already existing";
        }
    }
}