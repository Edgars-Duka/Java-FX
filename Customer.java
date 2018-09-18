package application;

import java.sql.SQLException;

public class Customer extends Order {

    public static String name;
    public static String address;
    public static String Customerid;

    Customer(String id, String name, String address, double d, float s, String t, String l) {

        super();
        Customer.Customerid = id;
        Customer.name = name;
        Customer.address = address;
    }

    public static String getCustomerid() {
        return Customerid;
    }

    public static void setCustomerid(String customerid) {
        Customerid = customerid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        Customer.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        Customer.address = address;
    }

    public static void addnewCustomer(Customer c) throws SQLException {
        ProductDB.result = InsertOrder.addPerson(
                Customerid,
                name,
                address);
    }
}
