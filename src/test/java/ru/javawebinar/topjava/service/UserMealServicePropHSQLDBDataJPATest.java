package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;

/**
 * Created by nik_PC on 03.12.2016.
 */
@ActiveProfiles({Profiles.HSQLDB, Profiles.DATAJPA})
@Sql(scripts = "classpath:db/initDB_hsql.sql", config = @SqlConfig(encoding = "UTF-8"))
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserMealServicePropHSQLDBDataJPATest extends UserMealServiceTest {

    @Test
    public void getWithUserTest(){
        User user = Mockito.mock(User.class);

    }
}
