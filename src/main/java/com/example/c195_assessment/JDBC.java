package com.example.c195_assessment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC class used to store variables for connecting with MySQL server
 *
 * @author Sheldon Handler
 */
public class JDBC {

    /**
     * database driver protocol
     */
    public static final String protocol = "jdbc";
    /**
     * URL for connection to database server
     */
    public static final String location = "//localhost/";
    /**
     * databaseName on database server
     */
    public static final String databaseName = "client_schedule";
    /**
     * String of location to Java package of database driver
     */
    public static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**
     * userName for login when connecting to database server
     */
    public static final String userName = "sqlUser"; // Username
    /**
     * password for login when connecting to database server
     */
    public static final String password = "Passw0rd!"; // Password
    /**
     * database driver vendor
     */
    private static final String vendor = ":mysql:";
    /**
     * jdbcURL for connection to database on server
     */
    public static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone=SERVER"; // LOCAL
    /**
     * Connection class variable for database connection
     */
    public static Connection connection; // Connection Interface

    /**
     * Default constructor.
     */
    public JDBC() {
    }

    /**
     * makes connection with MySQL server using static variables jdbcUrl, userName, and password
     */
    public static void makeConnection() {

        try {
            //DriverManager.getDriver(driver);
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // reference Connection object
            System.out.println("Connection successful!");
        } catch (SQLException exception) {
            System.out.println("Error:" + exception.getMessage());
        }
    }

    /**
     * gets the Connection with MySQL server
     *
     * @return Connection returns connection with MySQL server
     */
    public static Connection getConnection() {
        return connection;
    }
}