package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by nik_PC on 27.01.2018.
 */
@ActiveProfiles(Profiles.JDBC)
public class JdbcUserSeviceTest extends UserServiceTest {
}
