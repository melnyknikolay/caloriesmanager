package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */

@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST.forEach(um -> {um.setUserId(1);this.save(um);});
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        LOG.info("Save" + userMeal);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
        }
        repository.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("Delete" + id);
        if (repository.get(id).getUserId() == userId)
        return repository.remove(id) != null;
        return false;
    }

    @Override
    public UserMeal get(int id, int userId) {
        LOG.info("Get" + id);
        if (repository.get(id).getUserId() == userId)
        return repository.get(id);
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        LOG.info("GetAll");
        return repository.values().stream().filter(um -> um.getUserId() == userId).sorted(Comparator.comparing(UserMeal::getDateTime)).collect(Collectors.toList());
    }
}

