package com.example.memory;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.memory.screen.GameActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadPrefs();
    }

    public void startGame(View view) {
        EditText nicknameField = findViewById(R.id.nicknameField);
        // 1-recupero nickname
        try {
            checkEmptyfield(nicknameField);
            String nicknameValue = nicknameField.getText().toString().trim();

            //2-verifica se checkbox
            CheckBox savePrefs = findViewById(R.id.savePrefsBtn);
            if (savePrefs.isChecked()) {
                //3-in caso affermativo salvare nel file delle preferenze
                savePrefs(nicknameValue);
            }
            Intent gameIntent = new Intent(this, GameActivity.class);
            gameIntent.putExtra("playerName",nicknameValue);
            startActivity(gameIntent);
        } catch(Exception ex){
            buildErrorAlert(ex);
        }

    }

    private void buildErrorAlert(Exception ex){
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        //builder.setTitle(getString((R.string.app_name)));
        //builder.setMessage(getString(R.string.empty_field_message));

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.empty_field_alert_layout, null);
        ((TextView)view.findViewById(R.id.errorMsg)).setText(ex.getMessage());
        builder.setView(view);
        builder.create().show();
    }

    private void checkEmptyfield(EditText input) throws Exception {
       if(input.getText().toString().isBlank()){
           throw new Exception(getString(R.string.empty_field_message));
       }
    }
    private void savePrefs(String nicknameValue) {
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nickname", nicknameValue);
        editor.commit();
    }

    private void loadPrefs(){
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        String savedNickname = prefs.getString("nickname", "");
        EditText inputField = findViewById(R.id.nicknameField);
        inputField.setText(savedNickname);
    }
}

//4-avviare una nuova activity