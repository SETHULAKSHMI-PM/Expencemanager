package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.UserDao;
import razorthink.models.User;
import razorthink.utils.MurmurHash;


/**
 * Created by sethulakshmi on 4/5/17
 */



@Controller
public class LoginUser
{
    @Autowired
    private UserDao userDao;

    public static long login_user_id = 0;

    @RequestMapping("/login")
    @ResponseBody
    public String loginUser(String user_name, String user_password)
    {
        long login_encrypted_password = MurmurHash.hash32(user_password);

        User user1 = userDao.getByUsername(user_name);
        User user = userDao.getByUserDetails(user_name, login_encrypted_password);

        if (user1.equals(null))
        {
            return "No user found";
        }
        else
        {
            if (user.equals(null))
            {
                return "User found but password mismatched";
            }
            else
            {
                login_user_id = user.getUser_id();

                return "same password and user name"+login_user_id;

            }
        }
    }
}
