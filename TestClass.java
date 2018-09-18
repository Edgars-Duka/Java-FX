package application;

import java.sql.SQLException;

public class TestClass {

    @SuppressWarnings("static-access")
    public void start() throws SQLException {


        //================Phone p======================================\\
        Phone p = new Phone(null, null, null, null, null, null, null);
        p.setId("P001");
        p.setName("Apple");
        p.setDescription("IOS");
        p.setMake("Iphone");
        p.setModel("Iphone 6");
        p.setStorage("16 ");
        p.setPrice("700");
        //================End p======================================\\
        //================Phone p1======================================\\
        Phone p1 = new Phone(null, null, null, null, null, null, null);
        p1.setId("P002");
        p1.setName("Samsung");
        p1.setDescription("Andriod");
        p1.setMake("Samsung");
        p1.setModel("Galaxy s7");
        p1.setStorage("40");
        p1.setPrice("598");
        //================Phone p2======================================\\
		/*Phone p2 = new Phone (null, null, null, null, null, null, null);
		p2.setId("P003");
		p2.setName("Apple");
		p2.setDescription("IOS");
		p2.setMake("Apple");
		p2.setModel("Iphone 5");
		p2.setStorage("64");
		p2.setPrice("600");*/
        //================End P2======================================\\
        //================Phone p3======================================\\
		/*	Phone p3 = new Phone (null, null, null, null, null, null, null);
		p3.setId("P004");
		p3.setName("Samsung");
		p3.setDescription("Andriod");
		p3.setMake("Samsung");
		p3.setModel("Galaxy s6 edge");
		p3.setStorage("32");
		p3.setPrice("608");
		//================End P3======================================\\
		//================Phone p4======================================\\
				Phone p4 = new Phone (null, null, null, null, null, null, null);
		p3.setId("P005");
		p3.setName("Samsung");
		p3.setDescription("Andriod");
		p3.setMake("Samsung");
		p3.setModel("Galaxy s8");
		p3.setStorage("8");
		p3.setPrice("582");*/
        //================End P4======================================\\
        //================Phone P5======================================\\
		/*	Phone p5 = new Phone (null, null, null, null, null, null, null);
		p3.setId("P006");
		p3.setName("Samsung");
		p3.setDescription("Andriod");
		p3.setMake("Samsung");
		p3.setModel("Galaxy s7");
		p3.setStorage("128G")
		p3.setPrice("700");*/
        //================End P5======================================\\




		/*	System.out.print("\n\n=========== Print All Tv Details  ===================\n\n");

			Tv t1= new  Tv(0, null, null );
		t1.setSize(45);
		t1.settype("LCD");
		t1.setThreeD("Yes");
		t1.printTv();

		Tv t2 = new  Tv(0, null, null );
		t2.setSize(20);
		t2.settype("LCD");
		t2.setThreeD("No");
		t2.printTv();

		Tv t3 = new  Tv(0, null, null );
		t3.setSize(55);
		t3.settype("LED");
		t3.setThreeD("Yes");
		t3.printTv();

		Tv t5 = new  Tv(0, null, null );
		t5.setSize(75);
		t5.settype("LCD");
		t5.setThreeD("Yes");
		t5.printTv();
		 */
		/*	Tv t4 = new  Tv(0, null, null );
		t4.setSize(45);
		t4.settype("Plasma");
		t4.setThreeD("Yes");
		t4.printTv();
		 */

        System.out.print("\n\n============ Adding all Tv into Database ===================\n\n");
        //TvClass tv = new TvClass();
        //tv.addtv(t4);
        System.out.print("\n\n============ Adding all Phone into Database ===================\n\n");
        ProductDB database = new ProductDB();
        database.add(p);
        // database.add(p1);
        //database.add(p2);
        //database.add(p3);
        //database.add(p4);
        //database.add(p5);
        System.out.print("\n\n");
        System.out.print("\n\n============ Customer & their Order Details ==================\n\n");
        Order o = new Order();
        Customer Mary = new Customer(null, null, null, 0, 0, null, null);
        Mary.setCustomerid("C089");
        Mary.setName("Mary");
        Mary.setAddress("Dublin 205,st.lukes");
        //	Mary.addnewCustomer(Mary);
        o.AddOrder(Mary, p, 4);
        o.AddOrder(Mary, p1, 44);

    }
}
