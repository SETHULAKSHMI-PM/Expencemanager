package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Account;
import razorthink.models.User;

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
    }

    public void delete(Account account)
    {
        getSession().delete(account);
    }

    public User getByUserId(long user_id)
    {
        return (User) getSession().createQuery("from User where user_id = :user_id")
                .setParameter("user_id", user_id)
                .uniqueResult();
    }

   /* public void update(Account account)
    {
        getSession().update(account);
        return;
    }*/
}
