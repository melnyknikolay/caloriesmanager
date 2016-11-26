package ru.javawebinar.topjava.model;

import org.springframework.jdbc.core.RowMapper;
import ru.javawebinar.topjava.util.TimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class UserMeal extends BaseEntity {

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    public UserMeal(){
    }
    public UserMeal(UserMeal userMeal){
        this.id = userMeal.getId();
        this.dateTime = userMeal.getDateTime();
        this.description = userMeal.getDescription();
        this.calories = userMeal.getCalories();
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }
    public UserMeal(String description, int calories) {
        this(null, null, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime == null ? LocalDateTime.now() : dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime.format(TimeUtil.DATE_TME_FORMATTER) +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

    RowMapper<UserMeal> MAPPER = new RowMapper<UserMeal>() {
        @Override
        public UserMeal mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
        }
    };
}
