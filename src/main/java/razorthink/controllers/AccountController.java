package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.AccountDao;
import razorthink.dao.UserDao;
import razorthink.models.Account;
import razorthink.models.User;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 25/4/17.
 */

@Controller
@RequestMapping("/account")
public class AccountController
{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping("/save")
    @ResponseBody
    public String save(long user_id, String account_name, String account_desc)
    {
        try
        {
            User user = accountDao.getByUserId(user_id);

            Collection<Account> accounts = new ArrayList<Account>();
            Account account = new Account(account_name, account_desc);
            accounts.add(account);

            user.setAccount(accounts);
            account.setUser(user);

            accountDao.save(account);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Account saved sucessfully";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long account_id)
    {
        try
        {
            Account account = new Account(account_id);
            accountDao.delete(account);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Deleted successfully";
    }

    /*@RequestMapping("/update")
    @ResponseBody
    public String update(long account_id, String account_name, String account_desc, boolean account_is_ative)
    {
        try
        {
            Account account = new Account(account_id, account_name, account_desc, account_is_ative);
            accountDao.update(account);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Updated successfully";
    }*/
}