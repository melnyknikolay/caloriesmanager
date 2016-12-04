package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    ProxyUserRepository userProxy;

    @Override
    public UserMeal save(UserMeal userMeal, int userId) {
        User user = userProxy.findOne(userId);
        userMeal.setUser(user);
        if (userMeal.isNew() || proxy.get(userMeal.getId(), userId) != null){
            return proxy.save(userMeal);
        }else
            return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        List<UserMeal> list = proxy.get(id, userId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.getAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(startDate, endDate, userId);
    }

    public UserMeal getWithUser(int id) {
        return proxy.findOne(id);
    }

}
