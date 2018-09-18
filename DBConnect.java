package application;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    public static Connection conn;
    public static String url = "jdbc:derby:E:\\Cit\\Semster No2\\JavaFx\\database\\MyDB";
    public static String user = "";
    public static String pass = "";

    static Statement stmt = null;
    static ResultSet rs;

    public static Connection connect() throws SQLException {
        try {
            Class.forName("").newInstance();
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Error: " + cnfe.getMessage());
        } catch (InstantiationException ie) {
            System.err.println("Error: " + ie.getMessage());
        } catch (IllegalAccessException iae) {
            System.err.println("Error: " + iae.getMessage());
        }


//     conn = DriverManager.getConnection(url,user,pass);
        //STEP 4: Execute a query
        System.out.println("Creating table in given database...");


        return conn;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn != null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }

}

