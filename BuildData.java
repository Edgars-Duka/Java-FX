package application;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.text.TabableView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class BuildData {
    public static ObservableList<ObservableList> data;
    @SuppressWarnings("rawtypes")
    private static TableView tableview;
    public static TextField in;
    @SuppressWarnings("unchecked")
    public static Stage window;

    /*************************************************************************
     * Gui-Application view All Details from Table*
     **************************************************************************/
    @SuppressWarnings("rawtypes")
    public void start() throws Exception {
        //TableView
        tableview = new TableView();
        DisplayProducts();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //		        stage.setMaxWidth(285);
        //		        stage.setMinWidth(285);
        window.setTitle("All Products");
        window.setResizable(false);
        //Main Scene
        Scene scene = new Scene(tableview);

        window.setScene(scene);
        window.show();
    }

    /*************************************************************************
     * Search if Product id Exist then Display on tableview *
     **************************************************************************/
    public static void display(String S, String a, TableView tableview) {

        Connection c;
        data = FXCollections.observableArrayList();

        try {
            in = new TextField();
            in.setText(a);

            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(S);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            tableview.getColumns().clear();
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

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");

            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            ObservableList<String> row = FXCollections.observableArrayList();
            if (!rs.next()) {
                tableview.setPrefWidth(300);
                tableview.setPrefHeight(300);
                tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                AlertBoxes.ShownotFound(in.getText());
                SearchProduct.input.clear();
                SearchProduct.windows.close();
                //	SearchProduct.AddProduct();
                in.setText(null);

            } else {
                do {
                    tableview.setPrefWidth(600);
                    tableview.setPrefHeight(300);
                    tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    for (int i = 2; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                } while (rs.next());
                System.out.println("Row [1] added " + row);
                AlertBoxes.Searchadd(in.getText());
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    /*************************************************************************
     * Search if Product id Exist then Display on tableview *
     **************************************************************************/

    /*************************************************************************
     *  Display All Details of Product Table *
     **************************************************************************/
    public void DisplayProducts() {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF Datbase.Phone
            String SQL = "SELECT * from Shop.Products ORDER BY ProductNo";
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


    /*************************************************************************
     * Search if Customer id  if exits Then Stop User \ *
     **************************************************************************/
    public static void exist(String S, String a) {

        Connection c;
        data = FXCollections.observableArrayList();
        in = new TextField();
        in.setText(a);
        try {
            c = DBConnect.connect();
            ResultSet rs = c.createStatement().executeQuery(S);
            if (!rs.next()) {
                System.out.print("Added!");
            } else {
                AlertBoxes.Exist(in.getText());
                InsertOrder.id.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    //=========================================================================
    //				Search if ProductNo Exist Dislpay!
    //=========================================================================
    public static void Search(String S, String a, TableView tableview) {

        Connection c;
        data = FXCollections.observableArrayList();
        in = new TextField();
        in.setText(a);
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(S);

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            tableview.getColumns().clear();

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

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            ObservableList<String> row = FXCollections.observableArrayList();
            if (!rs.next()) {
                tableview.setPrefWidth(300);
                tableview.setPrefHeight(300);
                tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                AlertBoxes.ShownotFound(in.getText());

            } else {
                do {
                    tableview.setPrefWidth(600);
                    tableview.setPrefHeight(300);
                    tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                } while (rs.next());
                System.out.println("Row [1] added " + row);
                //	 AlertBoxes.add(in.getText());
                data.add(row);
            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    //=========================================================================
    // 				Search if ProductNo Already-Exist Then Stop User!
    //=========================================================================
    public static void Exist(String S, String a) {

        Connection c;
        data = FXCollections.observableArrayList();
        in = new TextField();
        in.setText(a);
        try {
            c = DBConnect.connect();

            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(S);
            if (!rs.next()) {
                AlertBoxes.addPhone(in.getText());

            } else {
                // System.out.println("Row [1] added "+row );
                AlertBoxes.Exist(in.getText());
                InsertProduct.id.clear();
                InsertProduct.Pname.clear();
                InsertProduct.d.clear();
                InsertProduct.Make.clear();
                InsertProduct.Model.clear();
                InsertProduct.Storage.clear();
                InsertProduct.Price.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    //====================================================================
//		Print Product Table for user to Select ProductNo
//====================================================================
    public static void buildProducts(String s, TableView tableview) {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();
            //SQL FOR SELECTING ALL OF Datbase.Phone

            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(s);
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
}
