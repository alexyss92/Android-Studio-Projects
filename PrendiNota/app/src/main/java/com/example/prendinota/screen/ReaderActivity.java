package com.example.prendinota.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.prendinota.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        init();
    }

    private void init(){
        EditText noteList = findViewById(R.id.contentsList);
        List<String> contents = getContents();
        contents.stream().forEach(str->noteList.append(str.concat(System.getProperty("line.separator"))
                .replaceAll("##",System.getProperty("line.separator"))));
    }

    private List<String> getContents(){
        List<String> noteList = new ArrayList<>();
        try {
            FileInputStream fis= openFileInput("note.csv");
            Scanner reader = new Scanner(fis);
            while(reader.hasNextLine()){
                noteList.add(reader.nextLine());
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return noteList;
    }

    public void newNote(View source){
        Intent showIntent = new Intent(this, NoteActivity.class);
        String author = getIntent().getStringExtra("author");
        String noteType = getIntent().getStringExtra("type");
        showIntent.putExtra("author", author);
        showIntent.putExtra("type", author);
        startActivity(showIntent);
    }
}