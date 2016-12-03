package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by nik_PC on 03.12.2016.
 */
@ActiveProfiles({Profiles.POSTGRES, Profiles.JPA})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserMealServicePropPostgresJPATest extends UserMealServiceTest {
}
