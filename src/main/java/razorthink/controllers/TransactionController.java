package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import razorthink.dao.*;
import razorthink.models.Account;
import razorthink.models.Payee;
import razorthink.models.Transaction;
import razorthink.models.User;

import java.util.ArrayList;
import java.util.Collection;
import static razorthink.controllers.LoginUser.loginUserId;

/**
 * Created by sethulakshmi on 28/4/17.
 */

@RestController
@RequestMapping(value = "rest/transaction")
public class TransactionController
{
    @Autowired
    private TransactionDao transactionDao;
    @Autowired
    private PayeeDao payeeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private AccountDao accountDao;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(String transaction_type, String payee_name, String account_name, double transaction_amount, String transaction_desc)
    {
        if(loginUserId==0){
            return "Please Do User Login";
        }
        else {
        try
        {
            User user = transactionDao.getByUserId(loginUserId);
            long Id = user.getUser_id();

            Payee payee = transactionDao.getByPayeeIdByUsingName(payee_name);
            long payeeId = payee.getPayee_id();
            long categoryId = payee.getCategory().getCategory_id();

            Account account = transactionDao.getByAccountId(account_name);

            Collection<Transaction> transactions = new ArrayList<Transaction>();

            Transaction transaction = new Transaction(transaction_type, transaction_amount, transaction_desc);
            transaction.setPayee(payee);

            transaction.setUserId(Id);

            transactions.add(transaction);

            payee.setTransaction(transactions);
            transactionDao.save(transaction);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Saved successfully";
        }

    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(long transactionId)
    {
        try
        {
            Transaction transaction = new Transaction(transactionId);
            transactionDao.delete(transaction);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Deleted successfully";
    }
}
