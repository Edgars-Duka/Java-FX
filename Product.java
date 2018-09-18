package application;

public class Product extends ProductDB {
    static String name;
    static String Description;
    static String price;
    public static String id;


    Product(String id, String name, String Description) {
        super(Description, Description, id, id, Description, Description);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Product.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        Product.price = price;
    }

}
