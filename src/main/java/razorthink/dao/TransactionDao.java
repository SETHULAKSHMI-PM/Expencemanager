package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Account;
import razorthink.models.Payee;
import razorthink.models.Transaction;
import razorthink.models.User;

import javax.transaction.Transactional;

/**
 * Created by sethulakshmi on 28/4/17.
 */

@Repository
@Transactional
public class TransactionDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void save(Transaction transaction)
    {
        getSession().save(transaction);
    }

    public void delete(Transaction transaction)
    {
        getSession().delete(transaction);
    }

    public Payee getByPayeeId(long payee_id)
    {
        return (Payee) getSession().createQuery("from Payee where payee_id = :payee_id")
                .setParameter("payee_id", payee_id)
                .uniqueResult();
    }

    public User getByUserId(long user_id)
    {
        return (User) getSession().createQuery("from User where user_id = :user_id")
                .setParameter("user_id", user_id)
                .uniqueResult();
    }

    public Account getByAccountIdByUsingName(String account_name)
    {
        return (Account) getSession().createQuery("from Account where account_name = :account_name")
                .setParameter("account_name", account_name)
                .uniqueResult();
    }
}
