package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InsertOrder {
    public static Stage window;
    public static TextField CustomerN;
    public static TextField d;
    public static TextField id;
    public static TextField address;
    public static TextField quantity;
    public static TextField Storage;
    public static TextField Price;
    public static Button C;
    public static Stage windows;
    public static TextField input;
    @SuppressWarnings("rawtypes")

    public static TableView tableview;

    public static PreparedStatement pst = null;

    public static PreparedStatement InsertNewOrder = null;
    public static PreparedStatement InsertNewPerson = null;
    @SuppressWarnings("rawtypes")
    private ObservableList option = FXCollections.observableArrayList();

    void addnewOrder() throws IOException, SQLException {


        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane gridPane = createRegistrationFormPane();
        addUIControls(gridPane);
        fillCombox();
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void addUIControls(GridPane gridPane) throws IOException {
        // Add Header
        Label headerLabel = new Label("Add New Order");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));
        // Add Name Label
        Label nameLabel = new Label("Customer id: ");
        gridPane.add(nameLabel, 0, 1);

        // Add Name Text Field
        id = new TextField();
        id.setPrefHeight(40);
        gridPane.add(id, 1, 1);

        // Add Name Label
        Label Label = new Label("Name: ");
        gridPane.add(Label, 0, 2);

        // Add Name Text Field
        CustomerN = new TextField();
        CustomerN.setPrefHeight(40);
        gridPane.add(CustomerN, 1, 2);


        // Add Email Label
        Label address = new Label("Address: ");
        gridPane.add(address, 0, 3);

        // Add Email Text Field
        d = new TextField();
        d.setPrefHeight(40);
        gridPane.add(d, 1, 3);
        //		@SuppressWarnings({ "rawtypes", "unchecked" })


        Label A = new Label("ProductNo : ");
        gridPane.add(A, 0, 4);

        ComboBox c = new ComboBox(option);
        c.setMaxWidth(100);
        gridPane.add(c, 1, 4);
        Label p = new Label("Product No:");
        gridPane.add(p, 0, 5);
        Label ProductNo = new Label();

        gridPane.add(ProductNo, 1, 5);

        Label nameofp = new Label("Name of Phone:");
        gridPane.add(nameofp, 0, 6);
        Label n = new Label();
        gridPane.add(n, 1, 6);

        Label d1 = new Label("Description:");
        gridPane.add(d1, 0, 7);
        Label Description = new Label();
        gridPane.add(Description, 1, 7);

        Label make = new Label("Make");
        gridPane.add(make, 0, 8);
        Label Make = new Label();
        gridPane.add(Make, 1, 8);

        Label model = new Label("Model");
        gridPane.add(model, 0, 9);
        Label Model = new Label();
        gridPane.add(Model, 1, 9);

        Label st = new Label("Storage");
        gridPane.add(st, 0, 10);
        Label storage = new Label();

        gridPane.add(storage, 1, 10);

        Label pc = new Label("Price");
        gridPane.add(pc, 0, 11);
        Label price = new Label();

        gridPane.add(price, 1, 11);

        Label m = new Label("Quantity: ");
        gridPane.add(m, 0, 12);

        c.setOnAction(e ->
        {
            String query = "Select * from Shop.Products where ProductNo = ?";
            try {
                pst = DBConnect.conn.prepareStatement(query);

                pst.setString(1, (String) c.getSelectionModel().getSelectedItem());

                DBConnect.rs = pst.executeQuery();


                while (DBConnect.rs.next()) {

                    ProductNo.setText(DBConnect.rs.getString("ProductNo"));
                    n.setText(DBConnect.rs.getString("NameofPhone"));
                    Description.setText(DBConnect.rs.getString("Description"));
                    Make.setText(DBConnect.rs.getString("Make"));
                    Model.setText(DBConnect.rs.getString("Model"));
                    storage.setText(DBConnect.rs.getString("Storage"));
                    price.setText(DBConnect.rs.getString("PRICE"));

                }

                pst.close();

                DBConnect.rs.close();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        quantity = new TextField();
        quantity.setPrefHeight(40);
        gridPane.add(quantity, 1, 12);

        // Add Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 13, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        submitButton.setOnAction(e -> {
            if (id.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please Customer id:");
                return;
            } else {
                String Search = "SELECT * from Shop.Customer Where CustomerNo LIKE '" + id.getText() + "%'";
                BuildData.exist(Search, id.getText());

            }
            if (CustomerN.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your Name");
                return;
            }


            if (d.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter  " + CustomerN.getText() + " address");
                return;
            }
            if (ProductNo.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please Select  " + CustomerN.getText() + " ProductNo");
                quantity.clear();
                return;
            }

            if (quantity.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter  " + CustomerN.getText() + " address");
                return;
            }
            String a = null;
            if ((id.getText().isEmpty()) || (CustomerN.getText().isEmpty()) ||
                    (d.getText().isEmpty()) || (quantity.getText().isEmpty())) {

                AlertBoxes.isEmpty(a);
            } else {
                try {
                    AlertBoxes.addCustomer(id.getText());
                    showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Ordered Successful!",
                            "Welcome " + CustomerN.getText() + "  " + quantity.getText() + "   " + ProductNo.getText());
                    ProductDB.result = addPerson(
                            id.getText(),
                            CustomerN.getText(),
                            d.getText());

                    window.hide();

                    //	ProductDB.result = addOrder();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    ProductDB.result = addOrder(
                            id.getText(),
                            ProductNo.getText(),
                            quantity.getText());
                    //	ProductDB.result = addOrder();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();


                }
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


    public static int addPerson(String CustomerNo, String CustomerName, String Address) throws SQLException {
        int result = 0;

        // set parameters, then execute InsertNewOrder
        InsertNewPerson.setString(1, CustomerNo);
        InsertNewPerson.setString(2, CustomerName);
        InsertNewPerson.setString(3, Address);

        // insert the new entry; returns # of rows updated
        result = InsertNewPerson.executeUpdate();


        return result;
    } // end method addPerson

    @SuppressWarnings("unchecked")
    public static int addOrder(String CustomerNo, String ProductNo, String Quantity) throws SQLException {
        int result = 0;

        // set parameters, then execute InsertNewOrder

        InsertNewOrder.setString(1, CustomerNo);
        InsertNewOrder.setString(2, ProductNo);
        InsertNewOrder.setString(3, Quantity);


        // insert the new entry; returns # of rows updated
        result = InsertNewOrder.executeUpdate();


        return result;
    } // end method addPerson


    @SuppressWarnings("unchecked")
    private void fillCombox() throws SQLException {
        String query = "Select ProductNo from Shop.Products";
        pst = DBConnect.conn.prepareStatement(query);
        DBConnect.rs = pst.executeQuery();
        while (DBConnect.rs.next()) {
            option.add(DBConnect.rs.getString("ProductNo"));
        }
        pst.close();
        DBConnect.rs.close();
    }


}

