package com.example.alimentos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alimentos.R;
import com.example.alimentos.entity.FoodEntity;
import com.example.alimentos.viewholder.FoodViewHolder;
import com.example.alimentos.viewholder.OnListClick;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private List<FoodEntity> mList;
    private OnListClick mListClick;

    public FoodAdapter(List<FoodEntity> list, OnListClick listener) {
        this.mList = list;
        this.mListClick = listener;
    }

    @NonNull
    @Override
    //Cria uma nova linha do layout
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Criação do layout
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_food, parent, false);
        //attachToRoot: Se está ligado ao elemento pai. No nosso caso é independente.

        return new FoodViewHolder(view);
    }

    @Override
    //Usa o linha já existente do layout
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        FoodEntity foodEntity = this.mList.get(position);
        holder.bind(foodEntity, this.mListClick);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
