package ru.javawebinar.topjava.Repo;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;

/**
 * Created by nik_PC on 20.11.2016.
 */
public interface UserMealRepository {
    UserMeal create(UserMeal userMeal);
    int update (UserMeal userMeal);
    void delete (int id);
    UserMeal get (int id);
    List<UserMeal> getAll();
}
