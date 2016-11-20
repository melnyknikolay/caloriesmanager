package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.Repo.UserMealRepository;
import ru.javawebinar.topjava.Repo.UserMealRepositoryInst;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by nik_PC on 17.11.2016.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    UserMealRepository repository = new UserMealRepositoryInst();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        List<UserMealWithExceed> list = UserMealsUtil.getFilteredWithExceeded(repository.getAll(), LocalTime.of(07, 00), LocalTime.of(22, 00), 2000);
        request.setAttribute("list", list);
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);

    }
}
