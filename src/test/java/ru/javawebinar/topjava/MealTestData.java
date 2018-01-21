package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

/**
 * Created by nik_PC on 21.01.2018.
 */
public class MealTestData {
    public static final int USER_MEAL_ID_1 = START_SEQ + 2;
    public static final int USER_MEAL_ID_2 = START_SEQ + 3;
    public static final int USER_MEAL_ID_3 = START_SEQ + 4;
    public static final int ADMIN_MEAL_ID_1 = START_SEQ + 5;
    public static final int ADMIN_MEAL_ID_2 = START_SEQ + 6;
    public static final int ADMIN_MEAL_ID_3 = START_SEQ + 7;

    public static final Meal userMeal1 = new Meal(USER_MEAL_ID_1, LocalDateTime.now(), "завтрак", 200);
    public static final Meal userMeal2 = new Meal(USER_MEAL_ID_2, LocalDateTime.now(), "обед", 500);
    public static final Meal userMeal3 = new Meal(USER_MEAL_ID_3, LocalDateTime.now(), "ужин", 300);
    public static final Meal adminMeal1 = new Meal(ADMIN_MEAL_ID_1, LocalDateTime.now(), "завтрак", 150);
    public static final Meal adminMeal2 = new Meal(ADMIN_MEAL_ID_2, LocalDateTime.now(), "обед", 1600);
    public static final Meal adminMeal3 = new Meal(ADMIN_MEAL_ID_3, LocalDateTime.now(), "ужин", 700);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dateTime");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }
}
