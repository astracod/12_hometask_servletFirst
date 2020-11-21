package org.example.servletfirst;

import com.google.gson.Gson;
import org.example.servletfirst.ContactBook.Contact;
import org.example.servletfirst.ContactBook.ContactBookService;
import org.example.servletfirst.dto.AllContactBookResponse;
import org.example.servletfirst.dto.RemoveFromContactBookRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*и удаление
POST /YourAppName/remove
принимает
{
  "id":7
}*/
public class RemoveFromContactBookServlet extends HttpServlet {

    ContactBookService contactBook = ContactBookService.getInstance();
    Gson gson = new Gson();

    /**
     * Удаление контакта
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        resp.setHeader("Content-type", "application/json");
        String requestString = new String(req.getInputStream().readAllBytes());

        try {
            RemoveFromContactBookRequest removeFromContactBookRequest = gson.fromJson(requestString, RemoveFromContactBookRequest.class);
            List<Contact> newContactBook = contactBook.removeContact(removeFromContactBookRequest.getId());

            for (Contact contact : newContactBook) {
                System.out.println(contact);
            }

            AllContactBookResponse allContactBookResponse = new AllContactBookResponse("ok", "");
            String respMe = gson.toJson(allContactBookResponse);
            writer.print(respMe);
        } catch (Exception e) {
            AllContactBookResponse allContactBookResponse = new AllContactBookResponse("error", e.getMessage());
            String respMe = gson.toJson(allContactBookResponse);
            writer.print(respMe);
        }
        writer.flush();
        writer.close();
    }
}




























