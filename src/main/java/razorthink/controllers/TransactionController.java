package razorthink.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import razorthink.dao.*;
import razorthink.models.*;
import razorthink.pojo.TransactionPojo;
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
    public String save(@RequestBody TransactionPojo transactionPojo)
    {
        String payeeNameDuplication = String.valueOf(payeeDao.getByPayeeName(transactionPojo.getPayee_name()));
        String accountNameDuplication = String.valueOf(accountDao.getByAccountName(transactionPojo.getAccount_name()));
        String categoryNameDupilcation = String.valueOf(categoryDao.getByCategoryName(transactionPojo.getCategory_name()));

        //User do not logged in, then do user login
        if(loginUserId==0)
        {
            return "Please Do User Login";
        }
        //Checks for account existing ot not.
        else if(accountNameDuplication.equals("null"))
        {
            return "Account not existing, Please careate Account details";

        }
        //If payment name is not existing but its category exists
        else if((payeeNameDuplication.equals("null")) && (!(categoryNameDupilcation.equals("null"))))
        {//create payee under the given category
            try
            {
                Category category = categoryDao.getByCategoryName(transactionPojo.getCategory_name());

                Collection<Payee> payees = new ArrayList<Payee>();
                Payee payee = new Payee(transactionPojo.getPayee_name());
                payees.add(payee);

                category.setPayees(payees);
                payee.setCategory(category);

                payeeDao.save(payee);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            transactionSaving(transactionPojo);
            return "Payee name is not existing, but its category exists. ";
        }
        //payee name and category name not existing
        else if((payeeNameDuplication.equals("null")) && (categoryNameDupilcation.equals("null")))
        {
            try
            {
                Category category1 = new Category(transactionPojo.getCategory_name());
                categoryDao.save(category1);

                Collection<Payee> payees = new ArrayList<Payee>();
                Payee payee = new Payee(transactionPojo.getPayee_name());
                payees.add(payee);

                category1.setPayees(payees);
                payee.setCategory(category1);

                payeeDao.save(payee);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            transactionSaving(transactionPojo);
            return "payee name and category name not existing.";
        }

        //payee name and category name existing but mapping mismatched
        else if(!(payeeNameDuplication.equals("null")) && (!(categoryNameDupilcation.equals("null"))))
        {
            Payee payee = payeeDao.getByPayeeName(transactionPojo.getPayee_name());
            long payeeCategoryId1 = payee.getCategory().getCategory_id();

            Category category = categoryDao.getByCategoryName(transactionPojo.getCategory_name());
            long payeeCategoryId2 = category.getCategory_id();

            //given payee name belongs to given category
            if(payeeCategoryId1 == payeeCategoryId2)
            {
                transactionSaving(transactionPojo);
                return "payee name and category name existing, mapping matched ";
            }

            //given payee and category mapping mismatcched
            else
            {
                Category category1 = categoryDao.getByCategoryName(transactionPojo.getCategory_name());
                long id = category1.getCategory_id();
                return "Given payee and category mapping mismatched, cat id of given cat name is : "+id;
            }
        }

        else
        {
            return "Something went Wrong, Please try again later";
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

    //For transaction details saving
    private String transactionSaving(TransactionPojo transactionPojo)
    {
        double userNewTotalBalance=0;
        double accountNewBalance=0;
        try
        {
            User user = transactionDao.getByUserId(loginUserId);
            long Id = user.getUser_id();

            Payee payee = payeeDao.getByPayeeName(transactionPojo.getPayee_name());
            long categoryId = payee.getCategory().getCategory_id();

            Account account = transactionDao.getByAccountIdByUsingName(transactionPojo.getAccount_name());

            Collection<Transaction> transactions = new ArrayList<Transaction>();

            Transaction transaction = new Transaction(transactionPojo.getTransaction_type(), transactionPojo.getTransaction_amount(), transactionPojo.getTransaction_desc());
            transaction.setUserId(Id);
            transaction.setCategoryId(categoryId);
            transactions.add(transaction);

            userNewTotalBalance = user.getUser_total_balance() - transactionPojo.getTransaction_amount();
            accountNewBalance = account.getAccount_amount() - transactionPojo.getTransaction_amount();

            user.setUser_total_balance(userNewTotalBalance);


            account.setAccount_amount(accountNewBalance);

            payee.setTransaction(transactions);
            transaction.setPayee(payee);

            account.setTransactions(transactions);
            transaction.setAccount(account);

            transactionDao.save(transaction);
            userDao.update(user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Transaction Saved successfully ";
    }
}
