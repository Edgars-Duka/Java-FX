package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveProduct {
    private static TableView tableview;
    /*
     *===============================
     *
     * -------Remove method----------
     * ==============================
     */
    public static Button delete;
    public static Label clear;
    public static Stage windowss;
    public static TextField in;
    static Statement stmt = null;
    static ResultSet rs;

    @SuppressWarnings("rawtypes")
    public static void RemoveProducts() throws Exception {
        //TableView
        tableview = new TableView();
        //=============Label and Button and TextField===========================
        clear = new Label();
        clear.setText("Enter the Product Id: ");
        delete = new Button();
        delete = new Button();
        delete.setText("Remove");
        delete.setOnAction(e ->
        {
            if (in.getText().isEmpty()) {
                AlertBoxes.showAlertWithDefaultHeaderText();
            } else {
                try {
                    stmt = DBConnect.conn.createStatement();
                } catch (SQLException e2) {
                    // TODO Auto-generated catch block
                    e2.printStackTrace();
                }
                String del = "DELETE  From Shop.Products WHERE ProductNo LIKE '" + in.getText() + "%'";


                try {
                    stmt.executeUpdate(del);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                // Now you can extract all the records
                // to see the remaining records
                del = "SELECT ProductNo,NameofPhone,Description,Make,Model,Price FROM  Shop.Products";

                try {
                    rs = stmt.executeQuery(del);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }


            }
        });
        in = new TextField();
        //==============End==================


        HBox h = new HBox();
        h.setSpacing(10);
        h.setStyle("-fx-padding: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;"
        );
        h.getChildren().addAll(clear, in, delete);

        VBox v = new VBox();
        v.setSpacing(10);
        v.getChildren().addAll(h);
        windowss = new Stage();
        windowss.initModality(Modality.APPLICATION_MODAL);
        windowss.setTitle("Find Product.");
        Scene scene = new Scene(v, 390, 50);

        windowss.setScene(scene);
        windowss.show();
    }

}
