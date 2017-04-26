/*
package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.UserAccountMappingDao;
import razorthink.models.UserAccountMapping;

import javax.transaction.Transactional;

*/
/**
 * Created by sethulakshmi on 26/4/17.
 *//*


@Controller
@RequestMapping("/userAccount")
public class UserAccountMappingController
{
    @Autowired
    UserAccountMappingDao userAccountMappingDao;

    @RequestMapping("/save")
    @ResponseBody
    public String save(long amount_balance, boolean user_account_is_active)
    {
        try
        {
            UserAccountMapping userAccountMapping = new UserAccountMapping(amount_balance, user_account_is_active);
            userAccountMappingDao.save(userAccountMapping);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Account saved sucessfully";
    }
}
*/
