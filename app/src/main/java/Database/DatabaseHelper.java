
package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ArrayAdapter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import Session.Customer;
import Session.ProdConsuming;
import Session.Product;
import Session.ProductElement;
import Session.Rating;
import Session.Report;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public DatabaseHelper(Context context) {
        super(context, "Database", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Customers\" (\"CustID\" INTEGER DEFAULT 0, \"CustName\" TEXT,\"Email\" TEXT UNIQUE,\"Password\" TEXT,\"Gender\" TEXT, \"Birthdate\" TEXT, \"job\" TEXT, PRIMARY KEY(\"CustID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Orders\" ( \"OrdID\" INTEGER DEFAULT 0, \"OrdDate\" TEXT, \"CustID\" INTEGER, \"Address\" TEXT, PRIMARY KEY(\"OrdID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Categories\" ( \"CatID\" INTEGER DEFAULT 0, \"CatName\" TEXT, PRIMARY KEY(\"CatID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Products\" ( \"ProdID\" INTEGER DEFAULT 0, \"ProdName\" TEXT, \"Price\" NUMERIC, \"Quantity\" INTEGER, \"CatID\" INTEGER, \"Description\"TEXT, Image BLOB, PRIMARY KEY(\"ProdID\" AUTOINCREMENT));");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"OrderDetails\" ( \"OrdID\"INTEGER, \"ProdID\" INTEGER, \"Quantity\" INTEGER);");
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Rate\" (OrdID INTEGER, Stars NUMERIC, Comment TEXT);");

        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Suits')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Ties')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Blazer')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Casual t-shirts')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Cufflinks')");
        sqLiteDatabase.execSQL("INSERT INTO Categories(CatName) VALUES('Classic pants')");

        //Suits:
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (1,'Slim Fit Suit Dark Brown',2500,100,1,'A sophisticated classic that’s fit for any smart occasion, this stunning two piece Two-piece nested suit Flat front trousers All nested suit trousers have a 6-inch drop from jacket Lightweight ideal for all months Dry clean only ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (2,'Slim Fit Suit Black',2495,100,1,'An elegant, lightweight suit that is well-suited to wear during the all months and ideal for formal engagements or business wear. Three-piece nested suit. Flat front trousers. All nested suit trousers have a 6-inch drop from jacket. Trousers are left unhemmed to be finished to the desired length. Lightweight ideal for all months. Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (3,'Slim Fit Suit Blue',2015,100,1,'A sophisticated classic that’s fit for any smart occasion, this stunning two piece Two-piece nested suit Flat front trousers All nested suit trousers have a 6-inch drop from jacket Lightweight ideal for all months Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (4,'Slim Fit Suit Beige',2200,100,1,'A sophisticated classic that’s fit for any smart occasion, this stunning two piece Two-piece nested suit Flat front trousers All nested suit trousers have a 6-inch drop from jacket Lightweight ideal for all months Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (5,'Regular Fit Suit Beige',2495,100,1,'A stylish, lightweight jumpsuit that is well-suited for all-month wear and perfect for formal occasions or business wear. Three-piece interlocking suit. Flat front pants. All cross suit pants have a 4″ drop and except for the size 62 which has a 2″ drop except for the size 50 which has a 6″ drop, of the jacket. The pants are left unstitched until they reach the desired length. Lightweight and perfect for all months. Dry clean only.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (6,'Regular Fit Suit Gray',3400,100,1,'A stylish, lightweight jumpsuit that is well-suited for all-month wear and perfect for formal occasions or business wear. Three-piece interlocking suit. Flat front pants. All cross suit pants have a 4″ drop and except for the size 62 which has a 2″ drop except for the size 50 which has a 6″ drop, of the jacket. The pants are left unstitched until they reach the desired length. Lightweight and perfect for all months. Dry clean only.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (7,'Regular Fit Suit Light Gray',3200,100,1,'A stylish, lightweight jumpsuit that is well-suited for all-month wear and perfect for formal occasions or business wear. Three-piece interlocking suit. Flat front pants. All cross suit pants have a 4″ drop and except for the size 62 which has a 2″ drop except for the size 50 which has a 6″ drop, of the jacket. The pants are left unstitched until they reach the desired length. Lightweight and perfect for all months. Dry clean only.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (8,'Regular Fit Suit Gray',3120,100,1,'A stylish, lightweight jumpsuit that is well-suited for all-month wear and perfect for formal occasions or business wear. Three-piece interlocking suit. Flat front pants. All cross suit pants have a 4″ drop and except for the size 62 which has a 2″ drop except for the size 50 which has a 6″ drop, of the jacket. The pants are left unstitched until they reach the desired length. Lightweight and perfect for all months. Dry clean only.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (9,'Regular Fit Suit Dark Blue',2500,100,1,'A stylish, lightweight jumpsuit that is well-suited for all-month wear and perfect for formal occasions or business wear. Three-piece interlocking suit. Flat front pants. All cross suit pants have a 4″ drop and except for the size 62 which has a 2″ drop except for the size 50 which has a 6″ drop, of the jacket. The pants are left unstitched until they reach the desired length. Lightweight and perfect for all months. Dry clean only.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (10,'Slim Fit Suit Dark Blue',3500,100,1,'An elegant, lightweight suit that is well-suited to wear during the all months and ideal for formal engagements or business wear. Three-piece nested suit. Flat front trousers. All nested suit trousers have a 6-inch drop from jacket. Trousers are left unhemmed to be finished to the desired length. Lightweight ideal for all months. Dry clean only',NULL);");

        //Ties
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (11,'Jacquard Tie 7.5 cm Yellow',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (12,'Jacquard Tie 7.5 cm Maroon',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (13,'Jacquard Tie 7.5 cm Dark Blue',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (14,'Jacquard Tie 7.5 cm Red',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (15,'Jacquard Tie 7.5 cm Blue',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO Products VALUES (16,'Jacquard Tie 7.5 cm Light Blue',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (17,'Jacquard Tie 7.5 cm Maroon',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (18,'Jacquard Tie 7.5 cm Maroon',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (19,'Jacquard Tie 7.5 cm Orange',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (20,'Jacquard Tie 7.5 cm Maroon',149,100,2,'A tie is a long, decorative piece of cloth worn around the neck, typically beneath the collar of a shirt, as a formal or fashion accessory.',NULL);");

        //blazer
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (21,'Slim Fit Blazer Gabardine White',995,10,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (22,'Slim Fit Blazer Blue',1495,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (23,'Slim Fit Blazer Blue',1495,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (24,'Slim Fit Blazer Gray',1100,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (25,'Slim Fit Blazer Dark Gray',1495,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (26,'Slim Fit Blazer Beige',1200,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (27,'Slim Fit Blazer Dark Blue',800,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (28,'Slim Fit Blazer Brown',1345,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (29,'Slim Fit Blazer Gabardine Beige',1000,100,3,' ',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (30,'Slim Fit Blazer Brown',1220,100,3,' ',NULL);");

        //Casual t-shirts
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (31,'Slim Fit Shirt Dark Blue',275,100,4,'A classic all-occasion pick. Tailored to a reguler fit, it features a classic collar and single cuff. Made Of 20% Cotton , 80% Polyester. Launder with care.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (32,'Slim Fit Shirt Dark Purple',225,100,4,'A classic choice for all occasions. Designed for a slim fit, it features a classic collar and single cuff. Made of viscose and polyester. Wash it carefully.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (33,'Slim Fit Shirt Blue',225,100,4,'A classic choice for all occasions. Designed for a slim fit, it features a classic collar and single cuff. Made of viscose and polyester. Wash it carefully.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (34,'Slim Fit Shirt Dark Blue',225,100,4,'A classic choice for all occasions. Designed for a slim fit, it features a classic collar and single cuff. Made of viscose and polyester. Wash it carefully.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (35,'Slim Fit White Evening shirt',476,100,4,'The evening shirt is designed,. It features a formal wingtip, matching white and black tie, a tailored cut, and plain cuffs. It is fastened with dress studs cotton polyester, wash carefully',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (36,'Slim Fit Shirt Light Blue',275,100,4,'A classic all-occasion pick. Tailored to a slim fit, it features a classic collar and single cuff. Made Of 20% Cotton , 80% Polyester. Launder with care.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (37,'Slim Fit Shirt Dark Blue',275,100,4,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (38,'Slim Fit Shirt Rose',225,100,4,'A classic choice for all occasions. Designed for a slim fit, it features a classic collar and single cuff. Made of viscose and polyester. Wash it carefully.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (39,'Slim Fit Shirt Rose',275,100,4,'A classic all-occasion pick. Tailored to a reguler fit, it features a classic collar and single cuff. Made Of 20% Cotton , 80% Polyester. Launder with care.',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (40,'Regular fit Shirt Gray',250,100,4,'A classic all-occasion pick. Tailored to a reguler fit, it features a classic collar and single cuff. Made Of 20% Cotton , 80% Polyester. Launder with care.',NULL);");

        //Cufflinks
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (41,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (42,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (43,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (44,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (45,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (46,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (47,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (48,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (49,'Cuff link',260,100,5,'',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (50,'Cuff link',260,100,5,'',NULL);");

        //Classic pants
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (51,'Slim Fit Classic Pants Blue',499,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (52,'Regular Fit Classic Pants Dark Blue',449,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (53,'Regular Fit Classic Pants Blue',449,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (54,'Slim Fit Classic Pants Gray',595,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (55,'Slim Fit Classic Pants Dark Blue',595,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (56,'Slim Fit Classic Pants Light Blue',449,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (57,'Slim Fit Classic Pants Light Brown',449,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (58,'Regular Fit Classic Pants Dark Blue',545,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (59,'Regular Fit Classic Pants Dark Blue',545,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");
        sqLiteDatabase.execSQL("INSERT INTO \"Products\" VALUES (60,'Regular Fit Classic Pants Gray',490,100,6,'ideal for formal engagements or businesswear. Flat front trousers Trousers are left unhemmed to be finished to the desired le Dry clean only',NULL);");



        sqLiteDatabase.execSQL("INSERT INTO Customers(CustName,Email,Password, Gender, Birthdate, job) VALUES('abdalla'," + " 'abdalla@gmail.com', '123', 'Male', '31/5/2002', 'programmer');");
        sqLiteDatabase.execSQL("INSERT INTO Customers(CustName,Email,Password, Gender, Birthdate, job) VALUES('ahmed'," + " 'ahmed@gmail.com', '123', 'Male', '31/5/2002', 'flutter developer');");


    //-----------------------------------------------------------------------------------
        //for loop that assigns photos to products
        Bitmap[] bitmaps = new Bitmap[60];
        ByteArrayOutputStream[] byteArray = new ByteArrayOutputStream[bitmaps.length];
        for (int i = 0; i < bitmaps.length; i++) {
            String uri = "@drawable/p"+String.valueOf(i+1);
            bitmaps[i] = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier(uri, null, context.getPackageName()));
            byteArray[i] = new ByteArrayOutputStream();
            bitmaps[i].compress(Bitmap.CompressFormat.PNG, 100, byteArray[i]);
            addImage(i+1, byteArray[i].toByteArray(), sqLiteDatabase);
        }
    }
    //-----------------------------------------------------------------------------------------------
    //abstract fuction (OnUpgrade) that must be implemented (that drop the tables)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CUSTOMERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Categories");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Products");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OrderDetails");
    }

    //Add new customer to the database
    public void addNewCustomer(String CustName, String Email, String Password, String Gender, String Birthdate, String job) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO Customers(CustName,Email,Password, Gender, Birthdate, job)" +
                " VALUES ('" + CustName + "','" + Email + "','" + Password + "','" + Gender + "','" + Birthdate + "','" + job + "');");
    }

    // see if the email is already registered (used in Signup )
    public boolean isExist(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Email FROM Customers Where Email = '" + email + "';", null);
        cursor.moveToFirst();
        boolean b = false;
        while (!cursor.isAfterLast()) {
            if (cursor.getString(0).equals(email)) {
                b = true;
            }
            cursor.moveToNext();
        }
        return b;
    }

    //used in search option , to find if the product is found or not
    public ArrayAdapter<String> getProducts(String search) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        Cursor cursor = getReadableDatabase().rawQuery("SELECT ProdName FROM Products WHERE ProdName  LIKE '%" + search + "%'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            adapter.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return adapter;
    }

    //used for the Sign in validation (SignInActivity)
    public boolean signInValidation(String em, String pass) {
        boolean check = false;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select Email,Password from Customers where Email ='" + em + "' and Password ='" + pass + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            check = true;
            cursor.moveToNext();
        }
        return check;
    }

    //used in SignInActivity
    public Customer getCustomerInfo(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select CustID, CustName  from Customers where Email ='" + email + "'", null);
        cursor.moveToFirst();
        Customer customer = new Customer();
        while (!cursor.isAfterLast()) {
            customer.setId(cursor.getInt(0));
            customer.setUsername(cursor.getString(1));
            customer.setEmail(email);
            cursor.moveToNext();
        }
        return customer;
    }

    public void updatePassword(String newPassword, String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Customers set Password ='"+newPassword+"' where Email ='"+email+"'");
    }

    // used for ForgetPasswordActivity
    public boolean changePassowordValidation(String em, String jo) {
        boolean check =false;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select Email,job from Customers where Email ='"+em+"' and job ='"+jo+"'",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            check=true;
            cursor.moveToNext();
        }
        return check;
    }

    //fuction that get all product details (used only once - in ProductViewActivity.java
    public String[] getProductDetails(String name)
    {
        String[] Details = new String[5];
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor1=sqLiteDatabase.rawQuery("select Categories.CatName from Products,Categories where Products.CatID=Categories.CatID and Products.ProdName = '"+name+"'" ,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select ProdName,Price,Description,ProdID from Products where ProdName  ='"+name+"' ",null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            //save (ProdName,Price,Description,ProdID) in Details[]
            Details[0]=cursor.getString(0);
            Details[1]=cursor.getString(1);
            Details[2]=cursor.getString(2);
            Details[3]=cursor.getString(3);
            cursor.moveToNext();
        }
        cursor1.moveToFirst();
        while(!cursor1.isAfterLast()) {
            Details[4]=cursor1.getString(0);
            cursor1.moveToNext();
        }
        return Details;
    }

    public void addProductToCart(int productID,int quantity)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO OrderDetails( ProdID, Quantity) Values('"+productID+"','"+quantity+"');");
    }

    //---------------------------------------------------------------------------
    public void addImage(int id, byte[] img, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Image", img);
        db.update("Products", contentValues, "ProdID = ?", new String[]{String.valueOf(id)});
    }
    //-------------------------------------------------------------------

    public String[] getCategories() {
        ArrayList<String> strings = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatName FROM Categories", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            strings.add(cursor.getString(0));
            cursor.moveToNext();
        }
        String[] result = new String[strings.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = strings.get(i);
        }
        return result;
    }

    public Product[] getProductsInCategory(String CatName) {
        ArrayList<Product> strings = new ArrayList<Product>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Products.ProdName, Products.Price FROM Categories, Products WHERE Categories.CatID = Products.CatID and Categories.CatName = '"+CatName+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Product p = new Product();
            p.setName(cursor.getString(0));
            p.setPrice(cursor.getString(1));
            strings.add(p);
            cursor.moveToNext();
        }
        Product[] result = new Product[strings.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = new Product();
            result[i].setName(strings.get(i).getName());
            result[i].setPrice(strings.get(i).getPrice());
        }
        return result;
    }

    public Bitmap getImage(String ProdName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select Image from Products WHERE ProdName = '"+ProdName+"'", null);
        Bitmap bmp;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            byte[] image = c.getBlob(0);
            if (image == null) {
                bmp = BitmapFactory.decodeResource(context.getResources(), context.getResources().getIdentifier("@drawable/startlogo", null, context.getPackageName()));
            } else {
                bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            }
            c.moveToNext();
            return bmp;
        }
        return null;
    }

    public void addProductToCartInOrderDelails(int orderId,int productID,int quantity)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO OrderDetails( OrdID,ProdID, Quantity) Values('"+orderId+"','"+productID+"','"+quantity+"');");
    }
    public void addOrderOfUser(int customertID)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("INSERT INTO Orders(CustID) Values('"+customertID+"');");

    }

    public String getOrderId(int customertID)
    {
        String id = null;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select max(OrdID) from Orders where CustID  ='"+customertID+"' ",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            id=cursor.getString(0);
            cursor.moveToNext();
        }
        return id;
    }
    public Product showCartDetails(int OrdID)
    {
        ArrayList<String> prodName = new ArrayList<String>();
        ArrayList<String> prodPrice = new ArrayList<String>();
        ArrayList<String> quantity = new ArrayList<String>();
        ArrayList<Bitmap> image = new ArrayList<Bitmap>();
        Product product;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select ProdName,Price,Image,OrderDetails.Quantity from Products,OrderDetails,Orders where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Orders.OrdID = '"+OrdID+"'"   ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            prodName.add(cursor.getString(0));
            prodPrice.add(cursor.getString(1));
            quantity.add(cursor.getString(3));
            image.add(getImage(cursor.getString(0)));
            cursor.moveToNext();
        }
        String[] prodName1 = new String[prodName.size()];
        String[] prodPrice1 = new String[prodPrice.size()];
        String[] Quantity1 = new String[quantity.size()];
        Bitmap[] image1=new Bitmap[image.size()];
        for (int i = 0; i < prodName1.length; i++) {
            prodName1[i] = prodName.get(i);
            prodPrice1[i] = prodPrice.get(i);
            Quantity1[i]=quantity.get(i);
            image1[i]=image.get(i);
        }
        product=new Product(prodName1,prodPrice1,image1,Quantity1);
        return product;
    }

    public void ordIncrementQuantity(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE OrderDetails SET Quantity = Quantity+1 WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void ordDecrementQuantity(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE OrderDetails SET Quantity = Quantity-1 WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void ordDeleteProduct(String OrdID, String ProdName) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM OrderDetails WHERE OrdID = '"+OrdID+"' AND ProdID = (SELECT ProdID FROM Products WHERE ProdName = '"+ProdName+"')");
    }

    public void setDateAndAddressInOrders(String OrdID, String Date, String Address) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE Orders SET OrdDate = '"+Date+"', Address = '"+Address+"' WHERE OrdID = '"+OrdID+"'");
    }

    public void insertRate(String OrdID, String Stars, String Comments) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Rate VALUES('"+OrdID+"', '"+Stars+"','"+Comments+"');");
    }

    public void insertNewOrder(String CustID) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO Orders(CustID) VALUES('"+CustID+"')");
    }

    //------------------------------------------------------------------------------------------------
    //                                      Reports section
    //--------------------------------------------------------------------------------------------------

    //a function to determine which reports to output  <<-------depending on a date & customer name --------->>
    public Report[] specificUserAndDateReport(String name, String date)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        int count=0;
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID and Customers.CustName='"+name+"' and Orders.OrdDate='"+date+"'  "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    //a function to determine which reports to output  <<-------depending on a date only--------->>
    public Report[] specificOrderDateReport(String date)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID  and Orders.OrdDate='"+date+"'  "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    //a function to determine which reports to output  <<-------depending on a Customer name only --------->>
    public Report[] specificUserReport(String name)
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID  and Customers.CustName='"+name+"' "  ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    //shows all reports on the system
    public Report[] showAllDataInReport()
    {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<Report> report = new ArrayList<Report>();
        Cursor cursor=sqLiteDatabase.rawQuery("select Orders.OrdDate,Customers.CustName,Products.ProdName,OrderDetails.Quantity,Products.Price from Products,OrderDetails,Orders,Customers where Products.ProdID=OrderDetails.ProdID and Orders.OrdID=OrderDetails.OrdID and Customers.CustID=Orders.CustID " ,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Report r = new Report(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));
            report.add(r);
            cursor.moveToNext();
        }
        Report[] report1 = new Report[report.size()];
        for (int i = 0; i < report1.length; i++) {
            report1[i] = new Report();
            report1[i].setOrderDate(report.get(i).getOrderDate());
            report1[i].setCustomerName(report.get(i).getCustomerName());
            report1[i].setProductName(report.get(i).getProductName());
            report1[i].setProductPrice(report.get(i).getProductPrice());
            report1[i].setProductQuantity(report.get(i).getProductQuantity());
        }
        return report1;
    }

    //--------------------------------------------------------------------------------------------------




    public String[] showCategories() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatName FROM Categories", null);
        cursor.moveToFirst();
        ArrayList<String> res = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            res.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return res.toArray(new String[res.size()]);
    }

    public void updateProduct(String productName,String quantity,String price,String description, String oldProdName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Products set ProdName ='"+productName+"' , Quantity='"+quantity+"' , Price='"+price+"' , Description='"+description+"' where ProdName='"+oldProdName+"' ");
    }

    public void updateCategory(String oldName, String newName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("update Categories set CatName ='"+newName+"' where CatName='"+oldName+"'");
    }

    public void deleteCategory(String categoryName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM Categories WHERE CatName='"+categoryName+"'");
    }

    public void deleteProduct(String productName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM Products WHERE ProdName='"+productName+"'");
    }

    public void addProduct(String productName,String quantity,String price,String description, String CatID)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into Products(ProdName,Price,Quantity,Description,CatID) values ('"+productName+"','"+price+"','"+quantity+"','"+description+"','"+CatID+"');");
    }

    public void addCategory(String categoryName)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("insert into Categories (CatName)values('"+categoryName+"')");
    }

    public String[] showProductsByCatName(String CatName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Products.ProdName FROM Products, Categories WHERE Products.CatID = Categories.CatID AND Categories.CatName = '"+CatName+"'",null);
        cursor.moveToFirst();
        ArrayList<String> res = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            res.add(cursor.getString(0));
            cursor.moveToNext();
        }
        return res.toArray(new String[res.size()]);
    }

    public ProductElement getProductInfo(String ProdName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ProdName, Price, Quantity, Description FROM Products WHERE ProdName = '"+ProdName+"'",null);
        cursor.moveToFirst();
        ProductElement p = new ProductElement();
        while (!cursor.isAfterLast()) {
            p.setProdName(cursor.getString(0));
            p.setProdPrice(cursor.getString(1));
            p.setProdQuantity(cursor.getString(2));
            p.setProdDescription(cursor.getString(3));
            cursor.moveToNext();
        }
        return p;
    }

    public String getCatIDViaCatName(String CatName) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT CatID From Categories WHERE CatName = '"+CatName+"'", null);
        cursor.moveToFirst();
        String res = "";
        while (!cursor.isAfterLast()) {
            res = cursor.getString(0);
            cursor.moveToNext();
        }
        return res;
    }

    //a function that gets the quantity of each product bought in a single order
    //used to update the quantity of the a product after buying it
    public ProdConsuming[] getQuantityFromOrderDetails(int OrdID)
    {
        ArrayList<ProdConsuming> res = new ArrayList<ProdConsuming>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ProdID, Quantity FROM OrderDetails Where OrdID = '" + OrdID + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res.add(new ProdConsuming(cursor.getString(0), cursor.getString(1)));
            cursor.moveToNext();
        }
        return res.toArray(new ProdConsuming[res.size()]);
    }

    //a function that updates the quantity of product after
    public void updateQuantity(int OrdID){
        ProdConsuming[] consumings = getQuantityFromOrderDetails(OrdID);
        SQLiteDatabase db = getWritableDatabase();
        for (int i = 0; i < consumings.length; i++) {
            db.execSQL("UPDATE Products SET Quantity = Quantity - "+consumings[i].getQuantity()+" WHERE ProdID = "+consumings[i].getProdID()+"");
        }
    }


    public Product prcentageofsell(){
        Product product;
        ArrayList<String> prodName = new ArrayList<String>();
        ArrayList<String> sumqunitity = new ArrayList<String>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT Products.ProdName, sum(OrderDetails.Quantity) FROM Products, OrderDetails WHERE Products.ProdID = OrderDetails.ProdID GROUP BY Products.ProdName;",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            prodName.add(cursor.getString(0));
            sumqunitity.add(cursor.getString(1));
            cursor.moveToNext();
        }
        String[] prodname = new String[prodName.size()];
        String[] sumqunit = new String[sumqunitity.size()];
        for (int i = 0; i < prodname.length; i++) {
            prodname[i] = prodName.get(i);
            sumqunit[i] = sumqunitity.get(i);
        }
        product=new Product(prodname,sumqunit);
        return product;
    }

    public Rating[] getFeedbacks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Customers.CustName, Rate.Stars, Rate.Comment FROM Customers, Rate, Orders WHERE Orders.OrdID = Rate.OrdID AND Orders.CustID = Customers.CustID;",null);
        ArrayList<Rating> res = new ArrayList<Rating>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            res.add(new Rating(cursor.getString(0), Float.parseFloat(cursor.getString(1)), cursor.getString(2)));
            cursor.moveToNext();
        }
        return res.toArray(new Rating[res.size()]);
    }
}
