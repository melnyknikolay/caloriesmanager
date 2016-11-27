package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (userMeal.isNew()){
            userMeal.setUser(ref);
            em.persist(userMeal);
            return userMeal;
        }else {
            if (userMeal.getUser().getId() == userId)
            return em.merge(userMeal);
        }
         return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        User ref = em.getReference(User.class, userId);
        return em.createNamedQuery(UserMeal.DELETE).setParameter("id", id).setParameter("user", ref).executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        User ref = em.getReference(User.class, userId);
        UserMeal um;
        try {
            um = em.createNamedQuery(UserMeal.GET_MEAL, UserMeal.class).setParameter("user", ref).setParameter("id", id).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return um;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        User ref = em.getReference(User.class, userId);
        List<UserMeal> meals;
        try {
            meals = em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class).setParameter("user", ref).getResultList().stream().sorted(Comparator.comparing(UserMeal::getDateTime).reversed()).collect(Collectors.toList());
        } catch (NoResultException e){
            return Collections.emptyList();
        }
        return meals;
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        User ref = em.getReference(User.class, userId);
        List<UserMeal> meals;
        try {
            meals = em.createNamedQuery(UserMeal.BETWEN, UserMeal.class).setParameter("user", ref).setParameter("start", startDate).setParameter("end", endDate).getResultList().stream().sorted(Comparator.comparing(UserMeal::getDateTime).reversed()).collect(Collectors.toList());;
        } catch (NoResultException e){
            return Collections.emptyList();
        }
        return meals;
    }
}