package com.example.stonks;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class StockDao implements Serializable {
    private static Logger log= Logger.getLogger(StockDao.class.getSimpleName());
    // Die ListederKunden
    private List<UserModel> customers = new ArrayList<UserModel>();
    // Die Datenbankconnection
    private Connection connection;
    @PostConstruct
    private void init() {
        log.info("--------MySQL JDBC Connection Testing ------------");

    try {
        // Treiber-KlasseLaden
        Class.forName("com.mysql.jdbc.Driver");
        log.info("the driver is loaded");
    } catch (ClassNotFoundException e) {
        log.info("Where is your MySQL JDBC Driver?");
        e.printStackTrace();
    }log.info("MySQL JDBC Driver Registered!");
    connection = null;
    try {
        // Verbindungaufbauen
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stonks_db", "root", "1234");
    } catch (SQLException e) {log.info("Connection Failed! Check output console");
        e.printStackTrace();
    }if (connection != null) {
        log.info("You made it, take control your database now!");
    } else {
    }
}
    @PreDestroy
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserModel getCustomer (int id) throws SQLException {
        ResultSet rs = null;
        try{
        PreparedStatement preparedStatement = connection
                .prepareStatement("select * from user where id = ?");
        preparedStatement.setInt(1, id);
        while (rs.next()) {
            UserModel customer = new UserModel();
            customer.setId(rs.getInt(1));
            customer.setUsername(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setBalance(rs.getInt(4));
            log.info("customer: "+customer);
            customers.add(customer);
        }
        rs.close();
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return (UserModel) customers;
    }
}



