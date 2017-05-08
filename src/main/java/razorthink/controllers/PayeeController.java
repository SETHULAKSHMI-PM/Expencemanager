package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import razorthink.dao.CategoryDao;
import razorthink.dao.PayeeDao;
import razorthink.models.Category;
import razorthink.models.Payee;
import razorthink.pojo.PayeePojo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@RestController
@RequestMapping(value = "rest/payee")
public class PayeeController
{
    @Autowired
    private PayeeDao payeeDao;
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody PayeePojo payeePojo)
    {
        String category_name_dup = String.valueOf(categoryDao.getByCategoryName(payeePojo.getCategory_name()));
        String payee_name_duplicate = String.valueOf(payeeDao.getByPayeeName(payeePojo.getPayee_name()));

        if((category_name_dup.equals("null")) && (payee_name_duplicate.equals("null")))
        {
            try
            {
                Category category1 = new Category(payeePojo.getCategory_name(), payeePojo.getCategory_desc());
               /* String cat_name = category1.getCategory_name();*/
                categoryDao.save(category1);

                Collection<Payee> payees = new ArrayList<Payee>();
                Payee payee = new Payee(payeePojo.getPayee_name(), payeePojo.getPayee_desc()/*, payeePojo.getCategory_name()*/);
                payees.add(payee);

                category1.setPayees(payees);
                payee.setCategory(category1);

                payeeDao.save(payee);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return "Saved successfully";
        }
        else if(!(category_name_dup.equals("null")) && (payee_name_duplicate.equals("null")))
        {
            try
            {
                Category category = categoryDao.getByCategoryName(payeePojo.getCategory_name());
                Collection<Payee> payees = new ArrayList<Payee>();
                Payee payee = new Payee(payeePojo.getPayee_name(), payeePojo.getPayee_desc()/*, payeePojo.getCategory_name()*/);
                payees.add(payee);
                category.setPayees(payees);
                payee.setCategory(category);
                payeeDao.save(payee);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return "Payee saved successfully";
        }
        else
        {
            return "Category name already existing";
        }
    }
}
