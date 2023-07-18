package com.example.alimentos.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.alimentos.R;
import com.example.alimentos.services.business.FoodBusiness;
import com.example.alimentos.services.constants.FoodConstants;
import com.example.alimentos.entity.FoodEntity;

public class DetailsActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Elementos de interface
        this.mViewHolder.mTextName = findViewById(R.id.text_name);
        this.mViewHolder.mTextCalories = findViewById(R.id.text_calories);
        this.mViewHolder.mTextQuantity = findViewById(R.id.text_quantity);
        this.mViewHolder.mTextUnit = findViewById(R.id.text_unit);
        this.mViewHolder.mTextDescription = findViewById(R.id.text_information);

        this.getData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int id = bundle.getInt(FoodConstants.FOOD_ID);
            FoodEntity foodEntity = new FoodBusiness().get(id);

            this.mViewHolder.mTextName.setText(foodEntity.getName());
            String calories = String.format("%s %s", foodEntity.getCalories(), this.getString(R.string.text_calorias));
            this.mViewHolder.mTextCalories.setText(calories);
            this.mViewHolder.mTextQuantity.setText(String.valueOf(foodEntity.getQuantity()));
            this.mViewHolder.mTextUnit.setText(foodEntity.getUnit());

            if(!foodEntity.getDescription().equals("")) {
                this.mViewHolder.mTextDescription.setText(foodEntity.getDescription());
            }
        }
    }

    private static class ViewHolder {
        TextView mTextName;
        TextView mTextCalories;
        TextView mTextQuantity;
        TextView mTextUnit;
        TextView mTextDescription;
    }
}