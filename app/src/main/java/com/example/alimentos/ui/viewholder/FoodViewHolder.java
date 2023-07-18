package com.example.alimentos.ui.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alimentos.R;
import com.example.alimentos.entity.FoodEntity;
import com.example.alimentos.services.listener.OnListClick;

public class FoodViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextName;
    private TextView mTextCalories;
    private TextView mTextQuantity;
    private TextView mTextUnit;
    private Context mContext;

    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mContext = itemView.getContext();

        this.mTextName = itemView.findViewById(R.id.text_name);
        this.mTextCalories = itemView.findViewById(R.id.text_calories);
        this.mTextQuantity = itemView.findViewById(R.id.text_quantity);
        this.mTextUnit = itemView.findViewById(R.id.text_unit);
    }

    public void bind(FoodEntity food, OnListClick listener){
        this.mTextName.setText(food.getName());
        String calories = String.format("%s %s", food.getCalories(), this.mContext.getString(R.string.text_calorias));
        this.mTextCalories.setText(calories);
        this.mTextQuantity.setText(String.valueOf(food.getQuantity()));
        this.mTextUnit.setText(food.getUnit());

        this.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(food.getId());
            }
        });
    }
}
