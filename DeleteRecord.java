package application;

import java.sql.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class DeleteRecord {
    // JDBC driver name and database URL

    static final String DB_URL = "jdbc:derby:E:\\Cit\\Semster No2\\JavaFx\\database\\MyDB";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";
    public static ResultSet rs;
    static ObservableList<ObservableList> data;
    @SuppressWarnings("rawtypes")
    private static TableView tableview;

    @SuppressWarnings("unchecked")
    public static void Start() {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            data = FXCollections.observableArrayList();

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
   /*// String sql = "DELETE  From Shop.Products WHERE ProductNo LIKE '"+ProductDB.in.getText()+"%'";
    stmt.executeUpdate(sql);

    // Now you can extract all the records
    // to see the remaining records
    sql = "SELECT ProductNo,NameofPhone,Description,Make,Model,Price FROM  Shop.Products";
    ResultSet rs = stmt.executeQuery(sql);
    
    
    

    *//********************************
             * Data added to ObservableList *
             ********************************//*
    while(rs.next()){
    	   //Iterate Row
        ObservableList<String> row = FXCollections.observableArrayList();
        for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
            //Iterate Column
            row.add(rs.getString(i));
        }
        System.out.println("Row [1] added "+row );
        data.add(row);
    }*/
            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                @SuppressWarnings("rawtypes")
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                // tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end JDBCExample