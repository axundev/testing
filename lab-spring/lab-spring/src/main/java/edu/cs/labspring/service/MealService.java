package edu.cs.labspring.service;

/*
  @author   AlexAT
  @project   lab4-crud
  @class  MealService
  @version  1.0.0
  @since 30.09.2024 - 22.59
*/

import edu.cs.labspring.model.Meal;
import edu.cs.labspring.repository.MealRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    private List<Meal> meals = new ArrayList<>();
    {
        meals.add(new Meal("1", "Pizza", "PZ001", "Cheese and tomato pizza", 800, 9.99));
        meals.add(new Meal("2", "Burger", "BG002", "Beef burger with fries", 950, 11.99));
        meals.add(new Meal("3", "Salad", "SL003", "Fresh vegetable salad", 350, 5.49));
        meals.add(new Meal("4", "Pasta", "PS004", "Spaghetti with marinara sauce", 650, 7.99));
        meals.add(new Meal("5", "Sushi", "SU005", "Salmon sushi rolls", 500, 12.49));
    }

    @PostConstruct
    void init() {
        mealRepository.deleteAll();
        mealRepository.saveAll(meals);
    }

    public List<Meal> getAll() {
        return mealRepository.findAll();
    }

    public Meal getById(String id) {
        return mealRepository.findById(id).orElse(null);
    }

    public Meal create(Meal Meal) {
        return mealRepository.save(Meal);
    }

    public  Meal update(Meal Meal) {
        return mealRepository.save(Meal);
    }

    public void delById(String id) {
        mealRepository.deleteById(id);
    }


}
