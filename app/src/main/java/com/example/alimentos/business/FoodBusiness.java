package com.example.alimentos.business;

import com.example.alimentos.constants.Filter;
import com.example.alimentos.entity.FoodEntity;
import com.example.alimentos.repository.FoodRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FoodBusiness {

    private FoodRepository mFoodRepository = new FoodRepository();

    public FoodEntity get(int id){
        return new FoodRepository().get(id);
    }

    public List<FoodEntity> getList(Filter filter){

        List<FoodEntity> list = this.mFoodRepository.getList();

        if(filter.equals(Filter.CAL_LOW)) {
            return list.stream().filter(f -> f.getCalories() < 100).collect(Collectors.toList());
        } else if(filter.equals(Filter.CAL_MEDIUM)) {
            return list.stream().filter(f -> f.getCalories() >= 100 && f.getCalories() < 200).collect(Collectors.toList());
        } else if(filter.equals(Filter.CAL_HIGH)) {
            return list.stream().filter(f -> f.getCalories() >= 200).collect(Collectors.toList());
        }
        return list;
    }
}
