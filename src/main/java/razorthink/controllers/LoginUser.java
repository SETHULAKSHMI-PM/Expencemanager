package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import razorthink.dao.UserDao;
import razorthink.models.User;
import razorthink.pojo.LoginPojo;
import razorthink.utils.MurmurHash;

/**
 * Created by sethulakshmi on 4/5/17
 */

@RestController
public class LoginUser
{
    @Autowired
    private UserDao userDao;

    public static long loginUserId = 0;

    @RequestMapping(value = "rest/login")
    public String loginUser(@RequestBody LoginPojo loginPojo)
    {
        long login_encrypted_password = MurmurHash.hash32(loginPojo.getUser_password());

        User user1 = userDao.getByUsername(loginPojo.getUser_name());
        User user = userDao.getByUserDetails(loginPojo.getUser_name(), login_encrypted_password);

        if (user1 == null)
        {
            return "No user found";
        }
        else
        {
            if (user == null)
            {
                return "User found but password mismatched";
            }
            else
            {
                loginUserId = user.getUser_id();

                return "same user name and password, id is : "+loginUserId;

            }
        }
    }
}