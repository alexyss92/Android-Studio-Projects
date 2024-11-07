package com.example.shoppingcart.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shoppingcart.MainActivity;
import com.example.shoppingcart.R;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String cart = getIntent().getStringExtra("cart");
        TextView lastCart = findViewById(R.id.LastCart);

        lastCart.setText(cart);

    }
    public void showAllCarts(View v) {
        LinearLayout layout = findViewById(R.id.restList);
        List<String> allCartUpdate = openStream();
        allCartUpdate.stream().forEach(str->layout.addView(addCart(str)));
    }
    private View addCart(String text) {
       EditText eText = new EditText(this);
       eText.setText(text.concat(System.getProperty("line.separator")).replaceAll("##", ","));
       return eText;

    }
    private List<String> openStream() {
        List<String> updateCart = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput("cart.csv");
            Scanner reader = new Scanner(fis);
            while(reader.hasNextLine()){
                updateCart.add(reader.nextLine());
            }
           fis.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return updateCart;
    }

    public void newCart(){
        Intent newCart = new Intent(ShowCartActivity.this, MainActivity.class);
        startActivity(newCart);
    }
}