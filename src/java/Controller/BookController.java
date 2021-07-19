/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Bean.Book;
import DAO.BookDAO;
import DAOImpl.BookDAOImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zilidazn
 */
public class BookController {

    BookDAO bookDAO = new BookDAOImpl();

    public void create(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        Book b = new Book(data.get("name").getAsString(), data.get("quantity").getAsInt(), data.get("price").getAsDouble());
        bookDAO.create(b);
    }

    public void read(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book b = bookDAO.read(id);
        String json = new Gson().toJson(b);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void readSpecific(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        ArrayList<Book> books = bookDAO.readSpecific(name);
        String json = new Gson().toJson(books);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void readAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ArrayList<Book> books = bookDAO.readAll();
        String json = new Gson().toJson(books);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public void update(HttpServletRequest request, HttpServletResponse response, JsonObject data) throws IOException {
        Book b = new Book(data.get("id").getAsInt(), data.get("name").getAsString(), data.get("quantity").getAsInt(), data.get("price").getAsDouble());
        bookDAO.update(b);
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookDAO.delete(id);
    }

}
