package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static org.junit.Assert.*;

/**
 * Created by nik_PC on 26.11.2016.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceImplTest {

    @Autowired
    UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    private int userId = LoggedUser.id();

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() throws Exception {
        UserMeal expected = service.get(breakfast.getId(), userId);
        MATCHER.assertEquals(expected, breakfast);
    }

    @Test(expected = NotFoundException.class)
    public void testGetWithunRegistredUser() throws Exception{
        service.get(breakfast.getId(), userId+1);
    }

    @Test
    public void delete() throws Exception {
        service.delete(breakfast.getId(), userId);
        MATCHER.assertCollectionEquals(Arrays.asList(lunch, dinner), service.getAll(userId));
    }

    @Test(expected = NotFoundException.class)
    public void deleteOtherUser() throws Exception {
        service.delete(breakfast.getId(), userId + 3);
    }

    @Test
    public void getBetweenDateTimes_OtherUser() throws Exception {
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.getBetweenDates(LocalDate.MIN, LocalDate.MAX, userId - 5));
    }

    @Test
    public void getAll() throws Exception {
        List<UserMeal> list = (List<UserMeal>)service.getAll(userId);
        MATCHER.assertCollectionEquals(list, Arrays.asList(breakfast, lunch, dinner));
    }

    @Test
    public void update() throws Exception {
        UserMeal um = new UserMeal(breakfast);
        um.setDescription("Полдник");
        um.setCalories(2000);
        service.update(um, userId);
        MATCHER.assertEquals(um, service.get(um.getId(), userId));
    }

    @Test(expected = NotFoundException.class)
    public void updateOtherUser() throws Exception {
        UserMeal um = new UserMeal(breakfast);
        um.setDescription("Полдник");
        um.setCalories(2000);
        service.update(um, userId+1);
    }

    @Test
    public void save() throws Exception {
        UserMeal um = new UserMeal(LocalDateTime.now(), "бутерброд", 150);
        UserMeal userMeal = service.save(um, userId);
        um.setId(userMeal.getId());
        MATCHER.assertEquals(service.get(um.getId(), userId), um);
    }

}