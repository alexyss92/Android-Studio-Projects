package com.example.sharecycleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.sharecycleapp.fragments.FragmentLoader;

public class MainActivity extends AppCompatActivity implements FragmentLoader {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void load(Fragment fragment, int idView)

}