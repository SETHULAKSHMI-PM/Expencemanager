package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Payee;

import javax.transaction.Transactional;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@Repository
@Transactional
public class PayeeDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void save(Payee payee)
    {
        getSession().save(payee);
    }

    public void delete(Payee payee)
    {
        getSession().delete(payee);
    }
    public Payee getByPayeeName(String payee_name)
    {
        return (Payee) getSession().createQuery("from Payee where payee_name = :payee_name")
                .setParameter("payee_name", payee_name)
                .uniqueResult();
    }
}
