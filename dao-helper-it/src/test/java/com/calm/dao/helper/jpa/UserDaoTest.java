package com.calm.dao.helper.jpa;

import com.calm.dao.it.entity.User;
import com.calm.dao.it.entity.UserDao;
import com.calm.dao.it.query.UserQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-hibernate.xml"})
public class UserDaoTest {
    @Resource
    private UserDao userDao;

    @Before
    public void setUp() {
        assertNotNull(userDao);
        userDao.insert(new User());
    }

    @Test
    public void testQueryLoad() {
        UserQuery query = userDao.query();
        query.idEq(1);
        User load = query.load();
        System.out.println(load);
    }

    @Test
    public void testQueryOr() {
        UserQuery query = userDao.query();
        query.nameEndWith("2");
        UserQuery or = query.or();
        or.idEq(1).ageGe(3);
        User load = or.load();
        System.out.println(load);
    }

    @Test
    public void testQueryOr2() {
        UserQuery query = userDao.query();
        UserQuery or = query.or();
        or.idEq(1).ageGe(3);
        User load = or.load();
        System.out.println(load);
    }
}
