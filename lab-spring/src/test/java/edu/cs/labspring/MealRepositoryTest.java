package edu.cs.labspring;

import edu.cs.labspring.model.Meal;
import edu.cs.labspring.repository.MealRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MealRepositoryTest {

    @Autowired
    MealRepository underTest;

    @BeforeAll
    void beforeAll() {}

    @BeforeEach
    void setUp() {
        Meal pizza = new Meal("Pizza", "M001", "Cheesy pizza with pepperoni", 1200, 18.99);
        Meal burger = new Meal("Burger", "M002", "Beef burger with cheese", 850, 5.49);
        Meal salad = new Meal("Salad", "M003", "Healthy green salad", 300, 4.99);
        underTest.saveAll(List.of(pizza, burger, salad));
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @AfterAll
    void afterAll() {}

    // Test 1: Найкалорійніша їжа
    @Test
    void shouldFindHighCalorieMeals() {
        // when
        List<Meal> highCalorieMeals = underTest.findAll().stream()
                .filter(meal -> meal.getCalories() > 1000)
                .toList();
        // then
        assertEquals("Pizza", highCalorieMeals.get(0).getName(), "Найбільш калорійною їжею має бути піца.");
    }

    // Test 2: Тестування пошуку їжі в проміжку ціни
    @Test
    void shouldFindMealsByPriceRange() {
        // when
        List<Meal> meals = underTest.findAll().stream()
                .filter(meal -> meal.getPrice() >= 4.00 && meal.getPrice() <= 6.00)
                .toList();
        // then
        assertEquals(2, meals.size(), "Саме дві страви повинні бути знайдені у проміжку з $4.00 по $6.00.");
        assertTrue(meals.stream().anyMatch(m -> m.getName().equals("Burger")), "Burger має бути у списку.");
        assertTrue(meals.stream().anyMatch(m -> m.getName().equals("Salad")), "Salad має бути у списку.");
    }

    // Test 3: Тестування додавання та авто генерації ID
    @Test
    void shouldSaveMealAndGenerateId() {
        // given
        Meal pasta = new Meal("Pasta", "M004", "Italian pasta with sauce", 700, 7.99);
        // when
        Meal savedMeal = underTest.save(pasta);
        // then
        assertNotNull(savedMeal.getId(), "ID має бути згенерованим та не бути null");
        assertEquals(24, savedMeal.getId().length(), "Має бути 24 символів в коді (із-за авто інкременту в MongoDB).");
    }

    // Test 4: Тестування оновлення по Code
    @Test
    void shouldUpdateMealInfo() {
        // given
        Optional<Meal> optionalMeal = underTest.findByCode("M002");
        assertTrue(optionalMeal.isPresent());
        Meal meal = optionalMeal.get();
        meal.setName("Updated Burger");
        // when
        underTest.save(meal);
        Meal updatedMeal = underTest.findById(meal.getId()).orElse(null);
        // then
        assertNotNull(updatedMeal, "Оновленна страва не має бути null");
        assertEquals("Updated Burger", updatedMeal.getName(), "Страва має бути оновленою");
    }

    // Test 5: Тестування видалення по Code
    @Test
    void shouldDeleteMealById() {
        // given
        Optional<Meal> optionalMeal = underTest.findByCode("M003");
        assertTrue(optionalMeal.isPresent());
        Meal meal = optionalMeal.get();
        // when
        underTest.deleteById(meal.getId());
        // then
        assertFalse(underTest.findById(meal.getId()).isPresent(), "Страва має бути видаленою");
    }
}


