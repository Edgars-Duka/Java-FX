package application;

public class Phone extends Product {
    public String make;
    public String model;
    public String storage;


    @SuppressWarnings("static-access")
    Phone(String id, String make, String model, String storage, String name, String Description, String price) {
        super(name, Description, id);
        Product.id = id;
        this.make = make;
        Product.price = price;
        Product.name = name;
        Product.Description = Description;
        this.model = model;
        this.storage = storage;
        //count++;
    }

    public String getMake() {

        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    void printPhone() {

        //super.Print();
    }
}
