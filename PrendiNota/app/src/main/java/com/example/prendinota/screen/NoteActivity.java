package com.example.prendinota.screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prendinota.R;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
    }

    @Override
    protected void onResume(){
        super.onResume();
        String author = getIntent().getStringExtra("author");
        String noteType = getIntent().getStringExtra("type");
        TextView label = findViewById(R.id.authorLabel);
        label.setText(ucFirst(author));
        label = findViewById(R.id.noteType);
        label.setText(noteType);

    }

    private String ucFirst(String input) {
        char firstLetter = input.charAt(0);
        firstLetter = Character.toUpperCase(firstLetter);
        return firstLetter+input.substring(1);
    }

    public void saveNote(View view){
        try {
            FileOutputStream fos = openFileOutput("note.csv", MODE_APPEND);
            PrintWriter pw = new PrintWriter(fos);
            String noteTamplate = "%s;%s;%s;";
            TextView authorLabel = findViewById(R.id.authorLabel),
                        typeLabel = findViewById(R.id.noteType);
            EditText noteContent = findViewById(R.id.noteContent);
            String note = String.format(noteTamplate, authorLabel.getText().toString(),
                    typeLabel.getText().toString(),
                    noteContent.getText().toString().replaceAll(System.getProperty("line.separator"), "##"));
            pw.println(note);
            pw.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void showNote(View source){
        Intent showIntent = new Intent(this, ReaderActivity.class);
        String author = getIntent().getStringExtra("author");
        String noteType = getIntent().getStringExtra("type");
        showIntent.putExtra("author", author);
        showIntent.putExtra("type", author);
        startActivity(showIntent);
    }

}