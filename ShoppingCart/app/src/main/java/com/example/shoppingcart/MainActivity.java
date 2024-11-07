package com.example.shoppingcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.shoppingcart.screen.CartActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callCartActivity(View view){
        Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
        EditText author = findViewById(R.id.nameAuthor);
        EditText nameList = findViewById(R.id.nameList);
        cartIntent.putExtra("author", author.getText().toString());
        cartIntent.putExtra("cart", nameList.getText().toString());
        startActivity(cartIntent);
    }

}