package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.*;
import razorthink.models.Payee;
import razorthink.models.Transaction;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sethulakshmi on 28/4/17.
 */

@Controller
@RequestMapping("/transaction")
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

    @RequestMapping("/save")
    @ResponseBody
    public String save(long payee_id, String transaction_type, double transaction_amount, String transaction_desc)
    {
        try
        {
            Payee payee = transactionDao.getByPayeeId(payee_id);

            Collection<Transaction> transactions = new ArrayList<Transaction>();
            String name = payee.getCategory().getCategory_name();
            String name1 = payee.getPayee_name();

            Transaction transaction = new Transaction(transaction_type, transaction_amount, transaction_desc,name,name1);
            transaction.setPayee(payee);

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

    @RequestMapping("/delete")
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
