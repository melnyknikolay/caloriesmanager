package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: gkislin
 * Date: 26.08.2014
 */

@Repository
public class JdbcUserMealRepositoryImpl implements UserMealRepository {

    private static final BeanPropertyRowMapper<UserMeal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(UserMeal.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcUserMealRepositoryImpl(DataSource dataSource) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("MEALS")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public UserMeal save(UserMeal UserMeal, int userId) {
        if (userId != LoggedUser.id())return null;
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", UserMeal.getId())
                .addValue("datetime", Timestamp.valueOf(UserMeal.getDateTime()))
                .addValue("description", UserMeal.getDescription())
                .addValue("calories", UserMeal.getCalories());

        if (UserMeal.isNew()){
            Number numKey = insertMeal.executeAndReturnKey(map);
            UserMeal.setId(numKey.intValue());
        }else
            namedParameterJdbcTemplate.update(
                    "UPDATE meals SET datetime=:datetime, description=:description, calories=:calories", map);
        return UserMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (userId != LoggedUser.id())return false;
        return jdbcTemplate.update("DELETE FROM meals WHERE id=?", id) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        if (userId != LoggedUser.id())return null;
        List<UserMeal> meals = jdbcTemplate.query("Select * from meals where id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        if (userId != LoggedUser.id())return Collections.emptyList();
        return jdbcTemplate.query("select * from meals ORDER BY datetime", ROW_MAPPER);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        if (userId != LoggedUser.id())return Collections.emptyList();
        List<UserMeal> list = jdbcTemplate.query("select * from meals ORDER BY datetime", ROW_MAPPER);
        return list.stream().filter(um -> TimeUtil.isBetween(um.getDateTime(), startDate, endDate)).collect(Collectors.toList());
    }
}
