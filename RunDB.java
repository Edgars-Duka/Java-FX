package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class RunDB {

    //TABLE VIEW AND DATA
    private static ObservableList<ObservableList> data;
    private static TableView tableview;

    public static void InsertProduct() {
        try {
            DBConnect.conn = DriverManager.getConnection(DBConnect.url, DBConnect.user, DBConnect.pass);
            InsertProduct.insertNewPhone = DBConnect.conn.prepareStatement(
                    "Insert INTO  Shop.Products " +
                            "(ProductNo,NameofPhone, Description, Make, Model,Storage,Price ) " +
                            "VALUES (?,?, ?, ?, ?,?,?)");
        }// end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
        InsertCustomer();
    }

    public static void InsertCustomer() {
        try {
            DBConnect.conn = DriverManager.getConnection(DBConnect.url, DBConnect.user, DBConnect.pass);
            InsertOrder.InsertNewPerson = DBConnect.conn.prepareStatement(
                    "Insert INTO  Shop.Customer" +
                            "(CustomerNo,CustomerName,Address) " +
                            "VALUES (?,?,?)");
        }// end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch
        InsertOrder();
    }

    public static void InsertOrder() {
        try {
            DBConnect.conn = DriverManager.getConnection(DBConnect.url, DBConnect.user, DBConnect.pass);
            InsertOrder.InsertNewOrder = DBConnect.conn.prepareStatement(
                    "Insert INTO  Shop.Orders" +
                            "(CustomerNo,ProductNo,Quantity) " +
                            "VALUES (?,?,?)");
        }// end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch

        InsertTv();
    }

    public static void InsertTv() {
        try {
            DBConnect.conn = DriverManager.getConnection(DBConnect.url, DBConnect.user, DBConnect.pass);
            TvClass.insertNewTv = DBConnect.conn.prepareStatement(
                    "Insert INTO  Shop.Tv" +
                            "(Type,Size,ThreeDSize) " +
                            "VALUES (?,?,?)");
        }// end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        } // end catch

    }


    //CONNECTION DATABASE
    @SuppressWarnings("unchecked")
    public void buildData() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF Datbase.Phone
            String SQL = "SELECT * from Shop.Products";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            System.out.println(rs.getMetaData().getColumnCount());
            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                @SuppressWarnings("rawtypes")
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(@SuppressWarnings("rawtypes") CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/

            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                int i;
                //i=1 is going by  Columns like 1 column,2 Columns
                for (i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));

                }
                System.out.println("Row [" + rs.getFetchSize() + 1 + "] added " + row);
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }


    public static Stage window;

    @SuppressWarnings("rawtypes")
    public void start() throws Exception {
        //TableView
        tableview = new TableView();
        buildData();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("All Products");
        window.setResizable(false);
        //Main Scene
        Scene scene = new Scene(tableview);
        window.setScene(scene);
        window.show();
    }
}