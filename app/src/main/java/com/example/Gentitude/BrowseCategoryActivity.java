package com.example.Gentitude;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Database.DatabaseHelper;
import RecyclerView.CategoryRecyclerViewAdapter;
import Session.Product;
import Session.Session;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class BrowseCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_category);

        ImageButton back = findViewById(R.id.browse_category_back_btn);
        ImageButton search = findViewById(R.id.browse_category_search_btn);
        ImageButton cart = findViewById(R.id.browse_category_cart_btn);
        ImageButton logout = findViewById(R.id.browse_category_menu_btn);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when pressing logout button the remember me will be equal false
                SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("rememberMe","false");
                editor.apply();
                Session session = Session.getInstance();
                session.setCustomer(null);
                startActivity(new Intent(BrowseCategoryActivity.this, SignInActivity.class));
            }
        });

        //return to the super page (last page when pressing back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BrowseCategoryActivity.super.onBackPressed();
            }
        });


        //search button
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this closes the (BrpwseCategoryActivity and opens the SearchActivity class
                startActivity(new Intent(BrowseCategoryActivity.this, SearchActivity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BrowseCategoryActivity.this, CartActivity.class));
            }
        });

        //------------------------------------------
        //this code is for only one category
        TextView CategoryName = findViewById(R.id.browse_category_category_name);
        CategoryName.setText(getIntent().getStringExtra("CatName"));
        RecyclerView recyclerView = findViewById(R.id.browse_category_recycler_view);
        DatabaseHelper db = new DatabaseHelper(this);
        Product[] products = db.getProductsInCategory(getIntent().getStringExtra("CatName"));
        String[] prod_titles = new String[products.length];
        String[] prod_prices = new String[products.length];
        Bitmap[] prod_images = new Bitmap[products.length];
        for (int i = 0; i < products.length; i++) {
            prod_titles[i] = products[i].getName();
            prod_prices[i] = products[i].getPrice();
            prod_images[i] = db.getImage(prod_titles[i]);
        }
        //--------------------------------------
        //we will use CategoryRecyclerViewAdapter to show other product categories
        CategoryRecyclerViewAdapter recyclerViewAdapter = new CategoryRecyclerViewAdapter(this,
                prod_titles, prod_prices, prod_images);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}