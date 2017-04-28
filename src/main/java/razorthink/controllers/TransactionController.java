package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import razorthink.dao.PayeeDao;
import razorthink.dao.TransactionDao;
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

    @RequestMapping("/save")
    @ResponseBody
    public String save(long payee_id, String category_name, String transaction_type, double transaction_amount, String transaction_desc)
    {
        try
        {
            Payee payee = transactionDao.getByPayeeId(payee_id);

            Collection<Transaction> transactions = new ArrayList<Transaction>();
            Transaction transaction = new Transaction(transaction_type, transaction_amount, transaction_desc);
            transactions.add(transaction);

            payee.setTransaction(transactions);
            String name=payee.getCategory().getCategory_name();
            transaction.setPayee(payee);
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
