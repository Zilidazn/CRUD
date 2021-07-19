/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Book;
import java.util.ArrayList;

/**
 *
 * @author zilidazn
 */
public interface BookDAO {

    public boolean create(Book b);

    public Book read(int id);

    public ArrayList<Book> readSpecific(String name);

    public ArrayList<Book> readAll();

    public boolean update(Book b);

    public boolean delete(int id);
}
