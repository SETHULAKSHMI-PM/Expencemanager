package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.UserDao;
import razorthink.models.User;
import razorthink.utils.MurmurHash;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController
{
    @Autowired
    private UserDao userDao;

    //Save user details
    @RequestMapping("/save")
    @ResponseBody
    public String save(String user_name, String user_email, String user_password)
    {
        /*User user=userDao.getByUsername(user_name);
        if(user.equals(null))
        {*/
            try
            {
                long encrypted_password = MurmurHash.hash32(user_password);
                User user1 = new User(user_name, user_email, encrypted_password);
                userDao.save(user1);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "successfully saved";
        /*}
        else
        {
            return "Username is already used";
        }*/
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
