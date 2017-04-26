package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.UserDao;
import razorthink.models.User;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@Controller
@RequestMapping(value = "/user")
public class UserControl
{
    @Autowired
    private UserDao userDao;

    //Save user details
    @RequestMapping("/save")
    @ResponseBody
    public String save(String user_name, String user_email, String user_password, boolean user_is_ative, double user_total_balance)
    {
        try
        {
            User user = new User(user_name, user_email, user_password, user_is_ative, user_total_balance);
            userDao.save(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "successfully saved";
    }

    //delete user details based on user_id
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long user_id)
    {
        try
        {
            User user = new User(user_id);
            userDao.delete(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Successfully Deleted";
    }

    /*//display user details based on user_id
    @RequestMapping("/view")
    @ResponseBody
    public String view(long user_id)
    {
        try
        {
            User user = userDao.viewByID(user_id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "view";
    }
*/

}
