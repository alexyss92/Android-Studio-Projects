package com.example.shoppingcart.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shoppingcart.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String author = getIntent().getStringExtra("author");
        String nameCart = getIntent().getStringExtra("cart");
        TextView authorName = findViewById(R.id.authorName),
                nameShoppingCart = findViewById(R.id.nameCart);
        authorName.setText(author);
        nameShoppingCart.setText(nameCart);
    }

    private String getCart(){
        EditText cartList = findViewById(R.id.cartList);
        String cartTemplate = "%s, %s, %s";
        TextView authorName = findViewById(R.id.authorName),
                nameShoppingCart = findViewById(R.id.nameCart);
        String cart = String.format(cartTemplate, authorName.getText().toString(), nameShoppingCart.getText().toString(),
                cartList.getText().toString().replaceAll(System.getProperty("line.separator"),"##" ));
        return cart;
    }

    public void saveCart(View view){
        try {
            FileOutputStream fos = openFileOutput("cart.csv", MODE_APPEND);
            PrintWriter pw = new PrintWriter(fos);
            String cart = getCart();
            pw.println(cart);
            pw.close();
            Intent showCartActivity = new Intent(CartActivity.this, ShowCartActivity.class);
            showCartActivity.putExtra("cart",cart);
            startActivity(showCartActivity);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}