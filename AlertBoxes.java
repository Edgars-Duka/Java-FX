package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertBoxes {

    // Show a Warning Alert with default header Text
    static String showAlertWithDefaultHeaderText() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("Please Enter Product ID!");

        alert.showAndWait();
        return null;
    }

    static String ShownotFound(String id) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Product Id " + id + " does not Exit! ");

        alert.showAndWait();
        return null;
    }

    static String del(String id) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Product Id " + id + " Deleted Successful! ");

        alert.showAndWait();
        return null;
    }

    static String Searchadd(String id) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Added Sccessful");
        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Product Id  " + id + " Added Successful! ");
        alert.showAndWait();
        SearchProduct.windows.hide();
        return null;
    }

    static String addPhone(String id) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Added Sccessful");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Product Id  " + id + " Added Successful! ");

        alert.showAndWait();

        return null;
    }

    static String Exist(String id) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Product Id " + id + "    Already Exit! ");

        alert.showAndWait();
        return null;
    }

    static String addCustomer(String id) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Added Sccessful");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  Customer Id  " + id + " Added Successful! ");

        alert.showAndWait();

        return null;
    }

    static String exist(String id) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("This  CustomerNo " + id + "    Already Exit! ");

        alert.showAndWait();
        return null;

    }

    static String isEmpty(String id) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Warning alert");

        // alert.setHeaderText("Battery Status:");
        alert.setContentText("fill the empty blanks!");

        alert.showAndWait();
        return null;

    }
}