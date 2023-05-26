package com.example.stonks;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
@Getter
@Setter
public class UserModel {
    private int id;
    private String email = "";
    private String password = "";
    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private String connect = "root";
    private String dbPassword = "1234";
    private String url = "jdbc:mariadb://localhost:3306/stonks_db";
    private Connection conn = null;

    public void connection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost/stonks_db", "root", "1234");
            System.out.println("Connected database successfully...");
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean valid() {
        boolean status = false;
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("select * from user where username = ? and password = ? ");
            {
                preparedStatement.setString(1, getEmail());
                preparedStatement.setString(2, getPassword());
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                status = rs.next();
                if(status)
                {
                    return true;
                } else
                {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void create(){
        try {
            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO user (username, password) VALUES (?,? )");
            {
                preparedStatement.setString(1, getEmail());
                preparedStatement.setString(2, getPassword());
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
