package edu.cs.labspring.repository;

/*
  @author   AlexAT
  @project   lab4-crud
  @class  MealRepository
  @version  1.0.0
  @since 30.09.2024 - 23.02
*/

import edu.cs.labspring.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends MongoRepository<Meal, String> {
    Optional<Meal> findByCode(String code);
    List<Meal> findMealsByPriceBetween(double minPrice, double maxPrice);
}
