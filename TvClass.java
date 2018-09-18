package application;


//UN-COMMENT LINE 140 to 156


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TvClass extends Tab {

    @SuppressWarnings("rawtypes")
    private ObservableList tv = FXCollections.observableArrayList();
    public static PreparedStatement insertNewTv = null;

    @SuppressWarnings("unchecked")

    public void start() throws SQLException {
        setText("Tv");

        Label banner = new Label();
        banner.setText("\tWelcome to Ed Electronics\n\n");
        banner.setTextFill(Color.web("#00008B"));
        banner.setFont(new Font("Arial", 20));

        Label Tv_ID = new Label();
        Tv_ID.setText("Tv ID");

        Label type = new Label();
        type.setText("Type:");
        Label DB_type = new Label();

        Label size = new Label();
        size.setText("Size:");
        Label DB_size = new Label();

        Label is_3D = new Label();
        is_3D.setText("Is it 3D or Not:");
        Label DB_is_3D = new Label();


        @SuppressWarnings("rawtypes")
        ComboBox comboBox = new ComboBox(tv);
        comboBox.setMaxWidth(100);
        comboBox.setOnAction(e ->
        {
            String query = "Select * from Shop.Tv where Tvid = ?";
            try {
                InsertOrder.pst = DBConnect.conn.prepareStatement(query);

                InsertOrder.pst.setString(1, (String) comboBox.getSelectionModel().getSelectedItem());

                DBConnect.rs = InsertOrder.pst.executeQuery();


                while (DBConnect.rs.next()) {

                    DB_type.setText(DBConnect.rs.getString("Type"));
                    DB_size.setText(DBConnect.rs.getString("Size"));
                    DB_is_3D.setText(DBConnect.rs.getString("ThreeDSize"));
                }
                InsertOrder.pst.close();
                DBConnect.rs.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        HBox dropdown_menu = new HBox();
        dropdown_menu.setSpacing(10);
        dropdown_menu.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        dropdown_menu.getChildren().addAll(Tv_ID, comboBox);

        HBox type_Display = new HBox();
        type_Display.setSpacing(10);
        type_Display.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        type_Display.getChildren().addAll(type, DB_type);

        HBox size_Display = new HBox();
        size_Display.setSpacing(10);
        size_Display.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        size_Display.getChildren().addAll(size, DB_size);

        HBox is_3D_Display = new HBox();
        is_3D_Display.setSpacing(10);
        is_3D_Display.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: black"
        );
        is_3D_Display.getChildren().addAll(is_3D, DB_is_3D);

        Label Type = new Label();
        Type.setText("Type");
        VBox Display_All = new VBox();
        Display_All.setStyle("-fx-padding: 10;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;"
        );
        Display_All.getChildren().addAll(banner, dropdown_menu, type_Display, size_Display, is_3D_Display);

        //==============Form==================================
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(10);
        vbButtons.setPadding(new Insets(10, 10, 10, 10));
        vbButtons.getChildren().addAll(Display_All);

        /*------------- End of Tab1-------------------------------*/
/////////////////////////////////////////////////////////////////////////////UN-COMMENT TILL LINE 156//////////////////////////////////////////////
        //fillCombox();

        setContent(vbButtons);
    }

    @SuppressWarnings({"unused", "unchecked"})
//private  void fillCombox() throws SQLException
//{
//	String query="Select Tvid from Shop.Tv";
//	InsertOrder.pst=DBConnect.conn.prepareStatement(query);
//	DBConnect.rs=InsertOrder.pst.executeQuery();
//	while(DBConnect.rs.next())
//	{
//		tv.add(DBConnect.rs.getString("Tvid"));
//	}
//	InsertOrder.pst.close();
//	DBConnect.rs.close();
//}
    public void addtv(Tv t) {
        Connection c;
        BuildData.data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();


            //	AlertBoxes.addCustomer(p.id);
            try {
                ProductDB.result = addPerson(
                        t.size,
                        t.type,
                        t.threeD
                );
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public static int addPerson(float Size, String type, String ThreeD) throws SQLException {
        int result = 0;

        // set parameters, then execute insertNewPhone
        insertNewTv.setFloat(1, Size);
        insertNewTv.setString(2, type);
        insertNewTv.setString(3, ThreeD);


        // insert the new entry; returns # of rows updated
        result = insertNewTv.executeUpdate();

        return result;
    }
}
