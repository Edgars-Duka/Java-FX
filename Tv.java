package application;

public class Tv extends Product {
    public float size;
    public String type;
    public String threeD;

    static int i;

    public Tv(float size, String type, String threeD) {
        super(type, threeD, threeD);
        this.size = size;
        this.type = type;
        this.threeD = threeD;
        settype(type);
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getThreeD() {
        return threeD;
    }

    public void setThreeD(String threeD) {
        this.threeD = threeD;
    }

    public void printTv() {
        System.out.print("\n--------Tv---------------"
                + "\n\nType:                " + type
                + "\nSize:                " + size
                + "\nIt's 3D or Not:      " + threeD + "\n\n");
    }
}
