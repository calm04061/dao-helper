package com.calm.dao.helper.jpa;

import com.calm.dao.it.entity.User;
import com.calm.dao.it.entity.UserDao;
import com.calm.dao.it.query.UserQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class UserDaoTest {
    @Resource
    private UserDao userDao;
    @Test
    public void testQuery(){
        assertNotNull(userDao);
        userDao.insert(new User());
        UserQuery query = userDao.query();
        query.orIdEq(1);
        User load = query.load();
        System.out.println(load);
    }
}
