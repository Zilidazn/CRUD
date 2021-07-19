/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import Bean.Book;
import Connection.JDBCConnection;
import DAO.BookDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author zilidazn
 */
public class BookDAOImpl implements BookDAO {

    @Override
    public boolean create(Book b) {
        int flag = 0;
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO book (name, quantity, price) VALUES (?, ?, ?);");
            preparedStatement.setString(1, b.getName());
            preparedStatement.setInt(2, b.getQuantity());
            preparedStatement.setDouble(3, b.getPrice());
            flag = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag > 0;
    }

    @Override
    public Book read(int id) {
        Book b = null;
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                b = new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getDouble("price"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    @Override
    public ArrayList<Book> readSpecific(String name) {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE name LIKE \"%" + name + "%\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book b = new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getDouble("price"));
                books.add(b);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public ArrayList<Book> readAll() {
        ArrayList<Book> books = new ArrayList<>();
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book b = new Book(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getDouble("price"));
                books.add(b);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean update(Book b) {
        int flag = 0;
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name = ?, quantity = ?, price = ? WHERE id = ?;");
            preparedStatement.setString(1, b.getName());
            preparedStatement.setInt(2, b.getQuantity());
            preparedStatement.setDouble(3, b.getPrice());
            preparedStatement.setInt(4, b.getId());
            flag = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag > 0;
    }

    @Override
    public boolean delete(int id) {
        int flag = 0;
        try (Connection connection = JDBCConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id = ?;");
            preparedStatement.setInt(1, id);
            flag = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag > 0;
    }

}
