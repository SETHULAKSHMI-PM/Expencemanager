package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Category;
import razorthink.models.User;
import javax.transaction.Transactional;

/**
 * Created by sethulakshmi on 24/4/17.
 */

@Repository
@Transactional
public class UserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    //save user
    public void save(User user)
    {
        getSession().save(user);
    }

    //delete user
    public void delete(User user)
    {
        getSession().delete(user);
    }

    public User getByUserDetails(String user_name, long user_password)
    {
        return (User) getSession().createQuery("from User where user_name = :user_name and user_password = :user_password")
                .setParameter("user_name", user_name).setParameter("user_password", user_password)
                .uniqueResult();
    }

    public User getByUsername(String user_name)
    {
        return (User) getSession().createQuery("from User where user_name = :user_name")
                .setParameter("user_name", user_name)
                .uniqueResult();
    }

    public User getByUserId(long user_id)
    {
        return (User) getSession().createQuery("from User where user_id = :user_id")
                .setParameter("user_id", user_id).uniqueResult();
    }
}