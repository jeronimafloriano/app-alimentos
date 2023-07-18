package com.example.alimentos.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.alimentos.R;
import com.example.alimentos.ui.adapter.FoodAdapter;
import com.example.alimentos.services.business.FoodBusiness;
import com.example.alimentos.services.constants.Filter;
import com.example.alimentos.services.constants.FoodConstants;
import com.example.alimentos.entity.FoodEntity;
import com.example.alimentos.services.listener.OnListClick;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewHolder mViewHolder = new ViewHolder();
    private OnListClick mFoodListener;
    private Filter mFilter = Filter.NO_FILTER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 - Obter os elementos de interface - No caso, recycler view
        this.mViewHolder.mRecyclerFood = findViewById(R.id.recycler_food);

        this.mFoodListener = new OnListClick(){
            @Override
            public void onClick(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt(FoodConstants.FOOD_ID, id);

                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        };

        // 3 - Definir um layout
        this.mViewHolder.mRecyclerFood.setLayoutManager(new LinearLayoutManager(this)); // ou new LinearLayoutManager(getApplicationContext())

        this.listFood();
    }

    //Cria e adiciona o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Responde aos eventos do menu, chamado ao clicar em um item do menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.filter_low: {
                this.mFilter = Filter.CAL_LOW;
                break;
            }
            case R.id.filter_medium: {
                this.mFilter = Filter.CAL_MEDIUM;
                break;
            }
            default: {
                this.mFilter = Filter.CAL_HIGH;
                break;
            }
        }

        this.listFood();
        return super.onOptionsItemSelected(item);
    }

    private void listFood() {
        List<FoodEntity> alimentos = new FoodBusiness().getList(this.mFilter);
        // 2 - Defnir um Adapter: Adapter junta os elementos de interface com os dados
        FoodAdapter adapter = new FoodAdapter(alimentos, this.mFoodListener);
        this.mViewHolder.mRecyclerFood.setAdapter(adapter);
    }

    private static class ViewHolder {
        RecyclerView mRecyclerFood;
    }
}