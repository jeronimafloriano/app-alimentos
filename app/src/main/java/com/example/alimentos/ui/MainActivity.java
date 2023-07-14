package com.example.alimentos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.alimentos.R;
import com.example.alimentos.adapter.FoodAdapter;
import com.example.alimentos.business.FoodBusiness;
import com.example.alimentos.constants.FoodConstants;
import com.example.alimentos.entity.FoodEntity;
import com.example.alimentos.viewholder.OnListClick;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<FoodEntity> alimentos = new FoodBusiness().getList();

        // 1 - Obter os elementos de interface - No caso, recycler view
        this.mViewHolder.mRecyclerFood = findViewById(R.id.recycler_food);

        OnListClick foodListener = new OnListClick(){
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(FoodConstants.FOOD_ID, id);

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        };

        // 2 - Defnir um Adapter: Adapter junta os elementos de interface com os dados
        FoodAdapter adapter = new FoodAdapter(alimentos, foodListener);
        this.mViewHolder.mRecyclerFood.setAdapter(adapter);

        // 3 - Definir um layout
        this.mViewHolder.mRecyclerFood.setLayoutManager(new LinearLayoutManager(this)); // ou new LinearLayoutManager(getApplicationContext())



    }

    private static class ViewHolder {
        RecyclerView mRecyclerFood;
    }
}