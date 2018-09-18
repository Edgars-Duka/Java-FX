package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Order extends OrderDetails {

    static int quantity;
    static int productno;

    static ArrayList<String> orderDetails = new ArrayList<String>();

    public Order() {
        super();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        Order.quantity = quantity;
    }

    @SuppressWarnings("static-access")
    public void AddOrder(Customer C, Phone p, int quantity) throws SQLException {

        int number = quantity;
        String numberAsString = String.valueOf(number);
        System.out.print("\nYour Id is :" + C.Customerid +
                "\nYour Productid:" + p.id +
                "\nYour Quantity is :" + numberAsString);
//		ProductDB.result = InsertOrder.addOrder(
        //C.Customerid, p.id, numberAsString);

    }


}

	
