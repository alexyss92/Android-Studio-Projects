package com.example.memory.utils;

import com.example.memory.R;
import com.example.memory.model.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shuffler {
    private int size;
    private List<Integer> used;
    private Shuffler(int size){
        this.size = size;
        this.used = new ArrayList<>();
    }

    public static Shuffler of(int size){
        return new Shuffler(size);
    }

    public List<Item> buildList() {
        List<Item> item = new ArrayList<>();
        item.add(new Item(0, R.drawable.bat));
        int drawable = getDrawable();
        for(int i = 0;  i < size/2; i++){
            item.add(new Item(i, getDrawable()));
            item.add(new Item(i, getDrawable()));
        }
        Collections.shuffle(item);
        return item;
    }

    public int getDrawable(){
        Random rand = new Random();
        int val;
        while(this.used.contains(val = rand.nextInt(20)));
        this.used.add(val);
        return imagesID[val];
    }

    private final static int imagesID[]= {R.drawable.acquarium, R.drawable.bat, R.drawable.cat, R.drawable.emoji, R.drawable.smile, R.drawable.smile1, R.drawable.tiger, R.drawable.aaa, R.drawable.bbb, R.drawable.ccc, R.drawable.ddd, R.drawable.fff, R.drawable.ggg, R.drawable.hhh, R.drawable.iii, R.drawable.lll, R.drawable.mmm, R.drawable.nnn, R.drawable.ooo, R.drawable.ppp};


}
