package app.util;

import app.entity.Contact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ContactSerializer
{
    private final ObjectMapper objectMapper;
    public ContactSerializer()
    {
        objectMapper=new ObjectMapper();
    }
    public String serialize(Contact contact) throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(contact);
    }
    public Contact deserialize(String json) throws JsonProcessingException
    {
        return objectMapper.readValue(json,Contact.class);
    }
    public String serialize(List<Contact> contacts) throws JsonProcessingException
    {
        return objectMapper.writeValueAsString(contacts);
    }
}