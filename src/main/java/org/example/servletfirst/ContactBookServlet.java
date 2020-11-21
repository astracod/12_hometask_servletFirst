package org.example.servletfirst;

import com.google.gson.Gson;
import org.example.servletfirst.ContactBook.ContactBookService;
import org.example.servletfirst.ContactBook.Contact;
import org.example.servletfirst.dto.AddContactRequest;
import org.example.servletfirst.dto.AllContactBookResponse;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/*
вернее 3 пост и 1 get


POST /YourAppName/contacts - добавляет контакт принисая json вида / метод есть
{
  "name":"vasia",
  "phone":"+380930001122"
}

POST /YourAppName/search - ищет контакт принисая json вида
тело запроса
{
  "name":"vas"
}
тело ответа
[{
    "id":11,
    "name":"vasia",
   "phone":"+380971112233"
},{
  "id":17,
  "name":"vasilisa",
  "phone":"+380554444687"
}]

и удаление
POST /YourAppName/remove
принимает
{
  "id":7
}*/

/**
 * GET /YourAppName/contacts - возвращает список контактов / метод есть
 */
public class ContactBookServlet extends HttpServlet {
    ContactBookService contactBook = ContactBookService.getInstance();
    Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Content-type", "application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        writer.print(gson.toJson(contactBook.showContactBook()));
        writer.flush();
        writer.close();

    }

    /**
     * POST /YourAppName/contacts - добавляет контакт принисая json вида / метод есть
     * {
     * "name":"vasia",
     * "phone":"+380930001122"
     * }
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletInputStream istream = req.getInputStream();
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        resp.setHeader("Content-type", "application/json");
        String requestString = new String(istream.readAllBytes());

        try {
            AddContactRequest addContactRequest = gson.fromJson(requestString, AddContactRequest.class);
            Contact contact = new Contact(addContactRequest.getName(),addContactRequest.getPhone(),null);
            List<Contact> contacts = contactBook.addContactBook(contact);

            System.out.println("Add contact :");
            for (Contact s : contacts) {
                System.out.println(s);
            }
            AllContactBookResponse allContactBookResponse = new AllContactBookResponse("ok","");
            String respMe = gson.toJson(allContactBookResponse);
            writer.print(respMe);

        } catch (Exception e) {
            AllContactBookResponse allContactBookResponse = new AllContactBookResponse("error",e.getMessage());
            String respMe = gson.toJson(allContactBookResponse);
            writer.print(respMe);
        }
        writer.flush();
        writer.close();
    }
}


























