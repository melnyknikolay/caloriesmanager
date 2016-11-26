package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.BaseEntity;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int testSeq = BaseEntity.START_SEQ + 2;

    public static final UserMeal breakfast = new UserMeal(testSeq, LocalDateTime.now(), "Завтрак", 1500);
    public static final UserMeal lunch = new UserMeal(testSeq + 1, LocalDateTime.now(), "Обед", 1500);
    public static final UserMeal dinner = new UserMeal(testSeq + 2, LocalDateTime.now(), "Ужин", 1500);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
