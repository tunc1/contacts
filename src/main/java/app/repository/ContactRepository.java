package app.repository;

import app.entity.Contact;
import app.holder.SessionFactoryHolder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ContactRepository
{
    private static final ContactRepository instance=new ContactRepository();
    public static ContactRepository getInstance()
    {
        return instance;
    }
    private final SessionFactory sessionFactory;
    private ContactRepository()
    {
        this.sessionFactory=SessionFactoryHolder.getSessionFactory();
    }
    public List<Contact> list()
    {
        Session session=sessionFactory.openSession();
        List<Contact> list=session.createQuery("from Contact").list();
        session.close();
        return list;
    }
    public Contact save(Contact contact)
    {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        return contact;
    }
    public Contact update(Contact contact)
    {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.update(contact);
        session.getTransaction().commit();
        return contact;
    }
    public void delete(Contact contact)
    {
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
    }
    public Contact getById(long id)
    {
        Session session=sessionFactory.openSession();
        Contact contact=session.get(Contact.class,id);
        session.close();
        return contact;
    }
}