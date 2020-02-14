package com.calm.dao.helper.jpa;

import com.calm.dao.helper.Mapper;
import com.calm.dao.it.entity.User;
import com.calm.dao.it.entity.UserDao;
import com.calm.dao.it.query.UserQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml", "classpath*:spring-hibernate.xml"})
@EnableTransactionManagement
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

    @Test
    public void testNativeQueryOr() {
        List<User> query = userDao.listByNativeQuery("select * from user");
        assertNotNull(query);
    }

    @Test
    public void testNativeQuery() {
        List<User> query = userDao.listByNativeQuery("select * from user where id =?", 1);
        assertNotNull(query);
    }

    @Test
    public void testLoadNativeQuery() {
        User query = userDao.loadByNativeQuery("select * from user where id =?", 1);
        assertNotNull(query);
    }
    @Test
    public void testLoadNativeQueryMapper() {
        User query = userDao.loadNativeQuery("select id,age,name from user where id =?", "user", 1);
        assertNotNull(query);
    }
}
