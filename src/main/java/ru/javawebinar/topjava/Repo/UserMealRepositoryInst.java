package ru.javawebinar.topjava.Repo;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by nik_PC on 20.11.2016.
 */
public class UserMealRepositoryInst implements UserMealRepository {
    private Map<Integer, UserMeal> inMemory = UserMealsUtil.inMemoryList().stream().collect(Collectors.toMap(UserMeal::getID, um -> um));

    @Override
    public UserMeal create(UserMeal userMeal) {
        return inMemory.put(userMeal.getID(), userMeal);
    }

    @Override
    public int update(UserMeal userMeal) {
        return inMemory.put(userMeal.getID(), userMeal).getID();
    }

    @Override
    public void delete(int id) {
        inMemory.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return inMemory.get(id);
    }

    @Override
    public List<UserMeal> getAll() {
        return inMemory.values().stream().collect(Collectors.toList());
    }
}
