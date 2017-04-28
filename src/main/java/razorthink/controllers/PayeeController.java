package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.CategoryDao;
import razorthink.dao.PayeeDao;
import razorthink.models.Category;
import razorthink.models.Payee;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@Controller
@RequestMapping("/payee")
public class PayeeController
{
    @Autowired
    private PayeeDao payeeDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping("/save")
    @ResponseBody
    public String save(String category_name, String category_desc,String payee_name, String payee_desc)
    {
        try
        {
            Category category1 = new Category(category_name, category_desc);
            categoryDao.save(category1);

            Collection<Payee> payees = new ArrayList<Payee>();
            Payee payee = new Payee(payee_name, payee_desc);
            payees.add(payee);

            category1.setPayees(payees);
            payee.setCategory(category1);

            payeeDao.save(payee);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Saved successfully";
    }
}
