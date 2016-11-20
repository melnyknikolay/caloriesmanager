package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.web.meal.UserMealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.create(new User(1, "userName", "email", "password", Role.ROLE_ADMIN)));

            UserMealRestController userMealRestController = appCtx.getBean(UserMealRestController.class);
            userMealRestController.create(new UserMeal(LocalDateTime.now(), "Ужин", 400));
            userMealRestController.create(new UserMeal(LocalDateTime.now(), "Ужин1", 500));
            userMealRestController.create(new UserMeal(LocalDateTime.now(), "Ужин2", 1600));
            userMealRestController.getAll().forEach(System.out::println);
            userMealRestController.delete(2);
            userMealRestController.getAll().forEach(System.out::println);
        }
    }
}
