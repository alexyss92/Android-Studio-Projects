package com.example.memory.screen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memory.R;
import com.example.memory.model.Item;
import com.example.memory.screen.adapter.GameAdapter;
import com.example.memory.utils.Shuffler;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private String playerName;
    private Item lastItemSelected = null;
    private View lastView = null;
    private int counter = 0;

    private int forToWin = 12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.playerName = getIntent().getExtras().getString("playerName", "guest");
        //getIntent().getStringExtra("playerName");
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView playerName = findViewById(R.id.playerLabelName);
        playerName.setText(this.playerName);
        initGameTable();
    }

    private void initGameTable() {
        GridView gameTable = findViewById(R.id.gameTable);
        List<Item> valuesGrid = Shuffler.of(25).buildList();
        GameAdapter adapter = new GameAdapter(valuesGrid, this);
        gameTable.setAdapter(adapter);
        gameTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Item item = valuesGrid.get(position);
                ImageView imageView = view.findViewById(R.id.animalID);
                imageView.setImageResource(item.getId());
                if(item.getId()==R.drawable.smile){
                    buildEndGameAlert();
                    return;
                }
                new Thread(() -> {
                        SystemClock.sleep(1500);
                        updateGrid(item, view, imageView);
                }
                ).start();
            }
        });
    }

            private void updateGrid(Item item, View view, ImageView imageView) {
                if (lastItemSelected == null) {
                    lastItemSelected = item;
                    lastView = view;
                } else {
                    counter++;
                    TextView counterLbl = findViewById(R.id.counterId);
                    counterLbl.post(()->counterLbl.setText(String.valueOf(counter)));
                    if (item.getId() != lastItemSelected.getId()) {
                        // Ripristino immagine bat
                            imageView.post(()->imageView.setImageResource(R.drawable.bat));
                            ImageView imageView2 = lastView.findViewById(R.id.animalID);
                            imageView2.post(()->imageView2.setImageResource(R.drawable.bat));
                    } else {
                        view.setClickable(false);
                        lastView.setClickable(true);
                        forToWin--;
                        if(forToWin==0){
                            buildEndGameAlert();
                        }
                    }
                    lastItemSelected = null;
                    lastView = null;
                }
        }

        private void buildEndGameAlert(){
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle(getString(R.string.app_name));
            builder.setMessage(getString(R.string.loose_message));
            builder.setPositiveButton(getString(R.string.new_game_label), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    GridView gameTable = findViewById(R.id.gameTable);
                    List<Item> valuesGrid = Shuffler.of(25).buildList();
                    GameAdapter adapter = new GameAdapter(valuesGrid, GameActivity.this);
                    gameTable.setAdapter(adapter);
                    forToWin = 12;
                    lastItemSelected = null;
                    lastView = null;
                    counter = 0;
                    //da controllare due righe dopo
                    TextView counterLbl = findViewById(R.id.counterId);
                    counterLbl.post(()->counterLbl.setText(String.valueOf(counter)));
                }
            });
            builder.setNegativeButton(getString(R.string.exit_game_label), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    System.exit(0);
                }
            });
            builder.setCancelable(false);
            builder.create().show();
        }
        }