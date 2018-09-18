package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InsertProduct {
    /*
     *===============================
     *
     * -------Add New Phone method---
     * ==============================
     */
    public static Stage window;
    public static TextField Pname;
    public static TextField d;
    public static TextField id;
    public static TextField Make;
    public static TextField Model;
    public static TextField Storage;
    public static TextField Price;
    @SuppressWarnings("unused")
    public static PreparedStatement selectAllPeople = null;
    public static PreparedStatement insertNewPhone = null;

    @SuppressWarnings("unused")
    void addnewphone() throws IOException {


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane gridPane = createRegistrationFormPane();
        addUIControls(gridPane);

        Scene scene = new Scene(gridPane, 400, 500);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(scene);
        window.showAndWait();
    }

    private GridPane createRegistrationFormPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints
        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    public void addUIControls(GridPane gridPane) throws IOException {
        // Add Header
        Label addNewPhone = new Label("Add New Phone");
        addNewPhone.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(addNewPhone, 0, 0, 2, 1);
        GridPane.setHalignment(addNewPhone, HPos.CENTER);
        GridPane.setMargin(addNewPhone, new Insets(20, 0, 20, 0));
        // Add Name Label
        Label nameLabel = new Label("Product id: ");
        gridPane.add(nameLabel, 0, 1);

        // Add Name Text Field
        id = new TextField();
        id.setPrefHeight(40);
        gridPane.add(id, 1, 1);

        // Add Name Label
        Label Phone_Name_Label = new Label("Name of Phone: ");
        gridPane.add(Phone_Name_Label, 0, 2);

        // Add Name Text Field
        Pname = new TextField();
        Pname.setPrefHeight(40);
        gridPane.add(Pname, 1, 2);


        // Add Email Label
        Label emailLabel = new Label("Description: ");
        gridPane.add(emailLabel, 0, 3);

        // Add Email Text Field
        d = new TextField();
        d.setPrefHeight(40);
        gridPane.add(d, 1, 3);

        // Add Name Label
        Label make = new Label("Make: ");
        gridPane.add(make, 0, 4);

        // Add Make of phone TextField
        Make = new TextField();
        Make.setPrefHeight(40);
        gridPane.add(Make, 1, 4);


        // Add Model Label
        Label m = new Label("Model: ");
        gridPane.add(m, 0, 5);
        Model = new TextField();
        Model.setPrefHeight(40);
        gridPane.add(Model, 1, 5);

        Label storage = new Label("Storage: ");
        gridPane.add(storage, 0, 6);

        // Add storage Text Field
        Storage = new TextField();
        Storage.setPrefHeight(40);
        gridPane.add(Storage, 1, 6);

        // Add Email Text Field
        Label price = new Label("Price: ");
        gridPane.add(price, 0, 7);
        Price = new TextField();
        Price.setPrefHeight(40);
        gridPane.add(Price, 1, 7);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 8, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(e -> {
            if (id.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please Product id:");
                return;
            } else {
                String Search = "SELECT * from Shop.Products Where ProductNo LIKE '" + id.getText() + "%'";
                BuildData.Exist(Search, id.getText());

            }
            if (Pname.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your Name of Phone");
                return;
            }
            if (d.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter  " + Pname.getText() + " Description");
                return;
            }
            if (Make.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter  " + Pname.getText() + " Make");
                return;
            }
            if (Model.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter " + Pname.getText() + " Model");
                return;
            }
            if (Storage.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter " + Pname.getText() + " Storage");
                return;
            }
            if (Price.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter  Price");
                return;
            } else
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Phone Added  Successful!", Pname.getText() + "  " + d.getText());

            String g = "GB";
            String Euro = "� ";
            try {
                ProductDB.result = addPerson(
                        id.getText(),
                        Pname.getText(),
                        d.getText(),
                        Make.getText(),
                        Model.getText(),
                        Storage.getText() + g,
                        Euro + Price.getText());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            window.hide();
            if (ProductDB.result == 1) {
                System.out.print("Added Sucessfully");

            } else {
                System.out.print(" Not Added!, Try again later. ");
            }


        });
    }

    private void showAlert(Alert.AlertType alertType, javafx.stage.Window window, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(window);
        alert.showAndWait();

    }

    public static int addPerson(String ProductNo, String Name, String Descriptiom, String Make, String Model, String Storage, String Price) throws SQLException {
        int result = 0;

        // set parameters, then execute insertNewPhone
        insertNewPhone.setString(1, ProductNo);
        insertNewPhone.setString(2, Name);
        insertNewPhone.setString(3, Descriptiom);
        insertNewPhone.setString(4, Make);
        insertNewPhone.setString(5, Model);
        insertNewPhone.setString(6, Storage);
        insertNewPhone.setString(7, Price);

        // insert the new entry; returns # of rows updated
        result = insertNewPhone.executeUpdate();
        return result;
    }
}
