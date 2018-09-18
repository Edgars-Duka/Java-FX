package application;

import java.awt.TextField;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;

public class ProductDB {

    static int result = 0;

    ProductDB(String name, String Description, int i, int j, String t, String l) {
        super();
    }

    public Product p;

    ProductDB() {

    }

    public ProductDB(String description, String description2, String productId, String productId2, String description3,
                     String description4) {
        super();
    }

    @SuppressWarnings("static-access")
    public ProductDB(String id) {
        super();
        this.p.id = id;
    }

    @SuppressWarnings("static-access")
    public void add(Phone p) {

        Connection c;
        BuildData.data = FXCollections.observableArrayList();
        try {
            c = DBConnect.connect();

            String Search = "SELECT * from Shop.Products Where ProductNo LIKE '" + p.id + "%'";

            //SQL FOR SELECTING ALL OF CUSTOMER
            //ResultSet
            ResultSet rs = c.createStatement().executeQuery(Search);

            if (!rs.next()) {

                //	AlertBoxes.addCustomer(p.id);
                try {
                    result = InsertProduct.addPerson(
                            p.id,
                            p.name,
                            p.Description,
                            p.make,
                            p.model,
                            p.storage + "GB",
                            "ï¿½" + p.price);


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            } else {

                // System.out.println("Row [1] added "+row );
                //AlertBoxes.Exist(p.id);
				/* try {
						DBConnect.stmt = DBConnect.conn.createStatement();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
			 		   String del = "DELETE  From Shop.Products WHERE ProductNo LIKE '"+p.id+"%'";


			 		   try {
			 			   DBConnect.stmt.executeUpdate(del);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

			 		    // Now you can extract all the records
			 		    // to see the remaining records
			 		  del = "SELECT ProductNo,NameofPhone,Description,Make,Model,Price FROM  Shop.Products";

						 try {
							 RemoveProduct.rs = DBConnect.stmt.executeQuery(del);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}*/


            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }

    public void close() {
        try {
            DBConnect.conn.close();
        } // end try
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } // end catch
    } // end method close

}

