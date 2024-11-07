package com.example.prendinota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.prendinota.screen.NoteActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callNoteActivity(View view){
        Intent noteIntent = new Intent(MainActivity.this, NoteActivity.class);
        Spinner selector =findViewById(R.id.noteTypeSelector);
        EditText author = findViewById(R.id.authorField);
        noteIntent.putExtra("author", author.getText().toString());
        noteIntent.putExtra("type", selector.getSelectedItem().toString());
        startActivity(noteIntent);
    }
}