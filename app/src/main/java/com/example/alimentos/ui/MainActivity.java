package com.example.alimentos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.alimentos.R;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Elementos de interface
        this.mViewHolder.mRecyclerFood = findViewById(R.id.recycler_food);
    }

    private static class ViewHolder {
        RecyclerView mRecyclerFood;
    }
}