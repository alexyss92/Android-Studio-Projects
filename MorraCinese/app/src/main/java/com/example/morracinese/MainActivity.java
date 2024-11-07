package com.example.morracinese;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int myChoiseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        ImageView simbol = findViewById(R.id.paperID);
        simbol.setOnClickListener(source->myChoiseID = source.getId());

        simbol = findViewById(R.id.rockID);
        simbol.setOnClickListener(source->myChoiseID = source.getId());

        simbol = findViewById(R.id.scissorsID);
        simbol.setOnClickListener(source->myChoiseID = source.getId());
    }*/

    public void getMyChoise(View view) {
        myChoiseID = view.getId()==R.id.paperID?R.drawable.carta:view.getId()==R.id.rockID?R.drawable.pugno:R.drawable.forbice;
        Log.d("myChoise", String.valueOf(myChoiseID));
        int iaChoice = iaMakeAChoice();
        Log.d("iaChoice", String.valueOf(iaChoice));
        changeImage(findViewById(R.id.iaChoiceID), iaChoice);
        int result = whoWin(myChoiseID, iaChoice);
        changeImage(findViewById(R.id.resultID), result);
    }

    private int iaMakeAChoice(){
        Random choiceGetter = new Random();
        return switch(choiceGetter.nextInt(3)){
            case 0 -> R.drawable.carta;
            case 1 -> R.drawable.pugno;
            default -> R.drawable.forbice;
        };
    }

    private void changeImage(ImageView target, int drawableID){
        target.setImageDrawable(getDrawable(drawableID));
    }

    private int whoWin(int userChoiceID, int appChoiceID){
        if(userChoiceID == appChoiceID){
            return R.drawable.bored;
        }
        return switch(userChoiceID){
            case R.drawable.pugno -> switch(appChoiceID){
                                        case R.drawable.carta -> R.drawable.lose;
                                        default -> R.drawable.win;
            };
            case R.drawable.carta -> switch(appChoiceID){
                                        case R.drawable.pugno -> R.drawable.win;
                                        default -> R.drawable.lose;
            };
            default -> switch(appChoiceID){
                            case R.drawable.pugno -> R.drawable.lose;
                            default -> R.drawable.win;
            };
        };
    }
}