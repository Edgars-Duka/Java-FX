package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class OrderDetails extends ProductDB {

    public OrderDetails() {
        super(null, null, 0, 0, null, null);
        // TODO Auto-generated constructor stub
    }

    private static ObservableList<ObservableList> data;
    @SuppressWarnings("rawtypes")
    private static TableView tableview;
    /*
     *===============================
     *
     * -------Search method----------
     * ==============================
     */
    private static Button display;

    public static Stage windows;
    public static TextField input;

    @SuppressWarnings("rawtypes")


    public static void Display() throws Exception {

        Connection c;
        data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();

            String Search = "SELECT    O.CustomerNo AS CustomerNo," +
                    "	   C.CustomerName AS Name,O.Quantity AS Quantity," +
                    "	   P.ProductNo as ProductNo," +
                    "       P.NameofPhone AS ProductName," +
                    "		P.Description AS Description," +
                    "       P.Model AS Model," +
                    "		P.Make AS Make," +
                    " 		P.Storage AS Storage," +
                    "		P.Price AS Price," +
                    "       C.Address as Address" +
                    "    FROM Shop.Orders O " +
                    "  JOIN Shop.Products P ON P.ProductNo = O.ProductNo" +
                    "  JOIN Shop.Customer C ON C.CustomerNo = O.CustomerNo";
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(Search);
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

    public void start() throws Exception {
        //TableView
        tableview = new TableView();
        Display();
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        //      stage.setMaxWidth(285);
        //      stage.setMinWidth(285);
        window.setTitle("All Products");
        window.setResizable(false);
        //Main Scene
        Scene scene = new Scene(tableview, 940, 400);

        window.setScene(scene);
        window.show();
    }
}
