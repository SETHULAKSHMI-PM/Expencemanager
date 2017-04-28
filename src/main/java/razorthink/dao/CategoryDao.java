package razorthink.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import razorthink.models.Category;

import javax.transaction.Transactional;

/**
 * Created by sethulakshmi on 27/4/17.
 */

@Repository
@Transactional
public class CategoryDao
{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession()
    {
        return sessionFactory.getCurrentSession();
    }

    public void save(Category category)
    {
        getSession().save(category);
    }

    public void delete(Category category)
    {
        getSession().delete(category);
    }
}
