package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razorthink.dao.AccountDao;
import razorthink.dao.UserDao;
import razorthink.models.Account;
import razorthink.models.User;
import razorthink.pojo.AccountPojo;

import java.util.ArrayList;
import java.util.Collection;
import static razorthink.controllers.LoginUser.loginUserId;

/**
 * Created by sethulakshmi on 25/4/17.
 */

@RestController
@RequestMapping(value = "rest/account")
public class AccountController
{
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@RequestBody AccountPojo accountPojo)
    {
        double balance=0;
        if (loginUserId == 0)
        {
            return "Please Do User Login";
        }
        else {
            try {
                User user = accountDao.getByUserId(loginUserId);

                Collection<Account> accounts = new ArrayList<Account>();
                Account account = new Account(accountPojo.getAccount_name(), accountPojo.getAccount_desc(), accountPojo.getAccount_amount());
                accounts.add(account);

                balance = user.getUser_total_balance() + accountPojo.getAccount_amount();
                user.setUser_total_balance(balance);
                user.setAccount(accounts);
                account.setUser(user);

                accountDao.save(account);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Account saved sucessfully "+balance;
        }
    }

    @RequestMapping("delete")
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

