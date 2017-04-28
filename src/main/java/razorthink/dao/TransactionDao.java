package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Payee;
import razorthink.models.Transaction;

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
                .setParameter("PayeeeId", payee_id)
                .uniqueResult();
    }

}
