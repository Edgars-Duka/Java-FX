package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class SearchProduct {
    public static PreparedStatement SearchbyProductid = null;
    /*
     *===============================
     *
     * -------Search method----------
     * ==============================
     */
    private static Button Search;
    private static Label find;
    public static Stage windows;
    public static TextField input;
    public static TextField in;
    public static Label products;
    @SuppressWarnings("rawtypes")


    public static TableView tableview;

    public static void Search() throws Exception {
        //TableView
        tableview = new TableView();
        //=============Label and Button and TextField===========================
        find = new Label();

        find.setText("Please Enter PhoneId:");
        Search = new Button();
        Search.setText("Search");
        input = new TextField();
        Search.setOnAction(e ->
        {
            if (input.getText().isEmpty()) {
                AlertBoxes.showAlertWithDefaultHeaderText();
            } else {

                String Search = "SELECT * from Shop.Products Where ProductNo LIKE '" + input.getText() + "%'";
                BuildData.Search(Search, input.getText(), tableview);
                input.clear();
            }
        });

        //==============End==================


        HBox h = new HBox();
        h.setSpacing(10);
        h.setStyle("-fx-padding: 10;" +
                        //     "-fx-border-style: solid inside;" +
                        "-fx-border-width: 2;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-radius: 5;"
                //    "-fx-border-color: black"
        );
        h.getChildren().addAll(find, input, Search);

        VBox v = new VBox();
        v.setSpacing(10);
        v.getChildren().addAll(h, tableview);
        windows = new Stage();
        windows.initModality(Modality.APPLICATION_MODAL);
        windows.setTitle("Find Product.");
        windows.setResizable(false);

        //Main Scene
        Scene scene = new Scene(v, 750, 300);

        windows.setScene(scene);
        windows.show();
    }

    public static void call() {
        SQL = "SELECT * from Shop.Products ORDER BY ProductNo";
        BuildData.buildProducts(SQL, tableview);
    }

    public static String SQL;
//	public static  void AddProduct() throws Exception {
//	       //TableView
//	       tableview = new TableView();
//	       //=============Label and Button and TextField===========================
//	       find = new Label();
//	     call();
//	     	Label products = new Label();
//	       find.setText("Please Enter PhoneId:");
//	       Search = new Button();
//	       Search.setText("Add");
//	       Search.setOnAction(e->
//	       {
//	    	   if (in.getText().isEmpty()) 
//	    	   {
//	    		   AlertBoxes.showAlertWithDefaultHeaderText();
//	    	   }
//	    	   else {
//	    		   String Search = "SELECT * from Shop.Products Where ProductNo LIKE '"+in.getText()+"%'";
//	    		   BuildData.display(Search, in.getText(), tableview);
//	    		 
//	    		    products.setText(in.getText());
//	    			
//	    		   
//	    	   }
//	    	   });
//	       in = new TextField();
//	       //==============End==================
//	        
//	 
//	   		HBox h = new HBox();
//	   		h.setSpacing(10);
//	   		h.setStyle("-fx-padding: 10;" + 
//					//     "-fx-border-style: solid inside;" + 
//					"-fx-border-width: 2;" +
//					"-fx-border-insets: 5;" + 
//					"-fx-border-radius: 5;"  
//					//    "-fx-border-color: black"
//					);
//	   		h.getChildren().addAll(find,in,Search);
//	   		
//	   		VBox v = new VBox();
//	   		v.setSpacing(10);
//	   		v.getChildren().addAll(h,tableview);
//	   		windows= new Stage();
//	     	windows.initModality(Modality.APPLICATION_MODAL);
//	     	windows.setTitle("Find Product.");
//	     	windows.setResizable(false);
//	    
//	     	//Main Scene
//	       Scene scene = new Scene(v,560,300);        
//
//	       windows.setScene(scene);
//	       windows.show();
//	     }


}
