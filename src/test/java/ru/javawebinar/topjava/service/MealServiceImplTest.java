package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by nik_PC on 21.01.2018.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-test_db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceImplTest {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;

    @Autowired
    private MealService service;

    @Test
    public void get() throws Exception {
        Meal actual = service.get(USER_MEAL_ID_1, USER_ID);
        assertMatch(actual, userMeal1);
    }

    @Test(expected = NotFoundException.class)
    public void getMealOtherUser() throws Exception {
        service.get(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(USER_MEAL_ID_2, USER_ID);
        assertMatch(service.getAll(USER_ID), userMeal1, userMeal3);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(5, USER_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        assertMatch(service.getAll(USER_ID), userMeal1, userMeal2, userMeal3);
    }

    @Test
    public void update() throws Exception {
        Meal meal = service.get(USER_MEAL_ID_1, USER_ID);
        meal.setCalories(50000);
        service.update(meal, USER_ID);
        assertMatch(service.get(USER_MEAL_ID_1, USER_ID), meal);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() throws Exception {
        Meal meal = service.get(USER_MEAL_ID_1, USER_ID);
        meal.setId(ADMIN_MEAL_ID_1);
        service.update(meal, USER_ID);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "description", 1000);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        assertMatch(service.getAll(USER_ID), userMeal1, userMeal2, userMeal3, newMeal);
    }

}