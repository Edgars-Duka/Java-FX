package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GuiApplication extends Tab {
    private Label banner;
    private Button Exit;
    int count = 0;

    public void start() {
        setText("Phone");
        banner = new Label();
        banner.setText("\tWelcome to Ed Electronics\n\n");
        banner.setTextFill(Color.web("#00008B"));
        banner.setFont(new Font("Arial", 20));

        Label phoneLabel = new Label();
        phoneLabel.setText("1.");
        Button phone = new Button();
        phone.setText("Create a New Phone");
        phone.setOnAction(e ->
        {
            try {
                Add();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Label searchLabel = new Label();
        searchLabel.setText("2.");
        Button search = new Button();
        search.setText("Search for a Product with the Product Id");
        search.setOnAction(e ->
        {
            try {
                SearchProduct.Search();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });


        Label displayLabel = new Label();
        displayLabel.setText("3.");
        Button display = new Button();
        display.setText("Display All Products");
        display.setOnAction(e ->
        {
            try {
                BuildData displayDataP = new BuildData();
                displayDataP.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        Label orderLabel = new Label();
        orderLabel.setText("4.");
        Button order = new Button();
        order.setText("Order Products");
        order.setOnAction(e ->
        {
            InsertOrder orderData = new InsertOrder();
            try {
                orderData.addnewOrder();
            } catch (IOException | SQLException e1) {
                e1.printStackTrace();
            }
        });

        Label dspAllOrd_Lbl = new Label();
        dspAllOrd_Lbl.setText("5.");
        Button dspAllOrd_Btn = new Button();
        dspAllOrd_Btn.setText("Display All Order");
        dspAllOrd_Btn.setOnAction(e ->
        {
            OrderDetails displayDataO = new OrderDetails();
            try {
                displayDataO.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        Label exit = new Label();
        exit.setText("6.");
        Exit = new Button();
        Exit.setText("Exit");
        Exit.setOnAction(e -> quit());


        HBox createPhoneBox = new HBox();
        createPhoneBox.setSpacing(10);
        createPhoneBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        createPhoneBox.getChildren().addAll(phoneLabel, phone);

        HBox search_ProductID = new HBox();
        search_ProductID.setSpacing(10);
        search_ProductID.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        search_ProductID.getChildren().addAll(searchLabel, search);

        HBox displayBox_Products = new HBox();
        displayBox_Products.setSpacing(10);
        displayBox_Products.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        displayBox_Products.getChildren().addAll(displayLabel, display);


        HBox orderBox = new HBox();
        orderBox.setSpacing(10);
        orderBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        orderBox.getChildren().addAll(orderLabel, order);

        HBox displayBox_AllOrders = new HBox();
        displayBox_AllOrders.setSpacing(10);
        displayBox_AllOrders.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        displayBox_AllOrders.getChildren().addAll(dspAllOrd_Lbl, dspAllOrd_Btn);


        HBox exitBox = new HBox();
        exitBox.setSpacing(10);
        exitBox.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        exitBox.getChildren().addAll(exit, Exit);
        //end

        VBox fullCollection = new VBox();
        fullCollection.setStyle("-fx-padding: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5");
        fullCollection.getChildren().addAll(banner, createPhoneBox, search_ProductID, displayBox_Products, orderBox, displayBox_AllOrders, exitBox);
        //==============Form==================================
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(0, 10, 10, 10));
        vbButtons.getChildren().addAll(fullCollection);
        /*------------- End of Tab1-------------------------------*/

//	  RunDB.InsertProduct();

        setContent(vbButtons);
    }

    private void quit() {
        Stage stage = (Stage) Exit.getScene().getWindow();
        System.out.print("\nThank you for visiting Ed Electronics");
        stage.close();
    }

    @SuppressWarnings("unused")
    private void Add() throws IOException {
        InsertProduct p = new InsertProduct();
        p.addnewphone();
    }
}
