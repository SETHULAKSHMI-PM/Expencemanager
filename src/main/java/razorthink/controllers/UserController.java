package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razorthink.dao.UserDao;
import razorthink.models.User;
import razorthink.pojo.UserPojo;
import razorthink.utils.MurmurHash;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@RestController
@RequestMapping(value = "rest/user")
public class UserController
{
    @Autowired
    private UserDao userDao;
    String ab;

    //Save user details
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody UserPojo userPojo)
    {
        User user=userDao.getByUsername(userPojo.getUser_name());
        if(user==null)
        {
            try
            {
                long encrypted_password = MurmurHash.hash32(userPojo.getUser_password());
                User user1 = new User(userPojo.getUser_name().toUpperCase(), userPojo.getUser_email(), encrypted_password);
                userDao.save(user1);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "successfully saved CAPITAL : ";
        }
        else
        {
            return "Username is already used";
        }
    }

    //delete user details based on user_id
    @RequestMapping("delete")
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

  /*  //display user details based on user_id
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
