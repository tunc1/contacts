package app.holder;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryHolder
{
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory()
    {
        if(sessionFactory==null)
            createSessionFactory();
        return sessionFactory;
    }
    private static void createSessionFactory()
    {
        sessionFactory=new Configuration().configure().buildSessionFactory();
    }
}