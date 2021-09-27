package app.servlet;

import app.entity.Contact;
import app.repository.ContactRepository;
import app.util.ContactSerializer;
import app.util.RequestReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/contact/*")
public class ContactServlet extends HttpServlet
{
    private final ContactRepository contactRepository;
    private final ContactSerializer contactSerializer;
    private final RequestReader requestReader;
    public ContactServlet()
    {
        contactRepository=ContactRepository.getInstance();
        contactSerializer=new ContactSerializer();
        requestReader=new RequestReader();
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String uri=request.getRequestURI();
        if(uri.endsWith("/")||uri.endsWith("/api/contact"))
        {
            List<Contact> list=contactRepository.list();
            write(response,contactSerializer.serialize(list));
        }
        else
        {
            long id=getId(uri);
            Contact contact=contactRepository.getById(id);
            write(response,contactSerializer.serialize(contact));
        }
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String input=requestReader.read(request);
        Contact contact=contactSerializer.deserialize(input);
        Contact saved=contactRepository.save(contact);
        write(response,contactSerializer.serialize(saved));
    }
    protected void doPut(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String uri=request.getRequestURI();
        long id=getId(uri);
        String input=requestReader.read(request);
        Contact contact=contactSerializer.deserialize(input);
        contact.setId(id);
        Contact updated=contactRepository.update(contact);
        write(response,contactSerializer.serialize(updated));
    }
    protected void doDelete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {
        String uri=request.getRequestURI();
        long id=getId(uri);
        Contact contact=contactRepository.getById(id);
        contactRepository.delete(contact);
    }
    private void write(HttpServletResponse response,String data) throws IOException
    {
        PrintWriter writer=response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.print(data);
    }
    private Long getId(String uri)
    {
        String idString=uri.substring(uri.lastIndexOf("/")+1);
        return Long.parseLong(idString);
    }
}