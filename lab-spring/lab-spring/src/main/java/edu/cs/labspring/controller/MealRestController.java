package edu.cs.labspring.controller;

/*
  @author   AlexAT
  @project   lab4-crud
  @class  MealRestController
  @version  1.0.0
  @since 30.09.2024 - 22.50
*/

import edu.cs.labspring.model.Meal;
import edu.cs.labspring.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/meals/")
@RequiredArgsConstructor
public class MealRestController {
    private final MealService mealService;

    @GetMapping
    public List<Meal> getAll() {
        return mealService.getAll();
    }

    @GetMapping("{id}")
    public Meal showOneById(@PathVariable String id) {
        return mealService.getById(id);
    }

    @PostMapping
    public Meal insert(@RequestBody Meal Meal) {
        return mealService.create(Meal);
    }

    @PutMapping
    public Meal edit(@RequestBody Meal Meal) {
        return mealService.update(Meal);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        mealService.delById(id);
    }

}
