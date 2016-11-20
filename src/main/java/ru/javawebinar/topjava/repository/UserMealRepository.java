package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface UserMealRepository {
    //null, is user not found
    UserMeal save(UserMeal userMeal);

    //false, is not found
    boolean delete(int id, int userId);

    //null, is not found
    UserMeal get(int id, int userId);

    //null, is user not found
    Collection<UserMeal> getAll(int userId);
}
