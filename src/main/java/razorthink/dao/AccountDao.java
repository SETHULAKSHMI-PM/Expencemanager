package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Account;

import javax.transaction.Transactional;

/**
 * Created by sethulakshmi on 25/4/17.
 */

@Repository
@Transactional
public class AccountDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }


    public void save(Account account)
    {
        getSession().save(account);
        return;
    }

    public void delete(Account account)
    {
        getSession().delete(account);
        return;
    }

    public Account getById(long account_id)
    {
        return (Account) getSession().createQuery("from Account where account_id = :account_id").setParameter("account_id", account_id).uniqueResult();
    }

   /* public void update(Account account)
    {
        getSession().update(account);
        return;
    }*/
}
