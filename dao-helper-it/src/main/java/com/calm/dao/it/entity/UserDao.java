package com.calm.dao.it.entity;

import com.calm.dao.helper.IBaseDao;
import com.calm.dao.helper.Mapper;
import com.calm.dao.it.query.UserQuery;

import java.util.List;

public class UserDao implements IBaseDao<String, User, UserQuery> {
    @Override
    public User loadById(Class<User> clazz, String id) {
        return null;
    }

    @Override
    public User insert(User entity) {
        return null;
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(User... entities) {

    }

    @Override
    public void delete(List<User> entity) {

    }

    @Override
    public UserQuery query(Class<User> clazz) {
        return null;
    }

    @Override
    public List<User> listByQuery(String jql, Class<User> clazz, Object... args) {
        return null;
    }

    @Override
    public User loadByQuery(String jql, Class<User> clazz, Object... args) {
        return null;
    }

    @Override
    public List<User> listByNativeQuery(String sql, Class<User> clazz, Object... args) {
        return null;
    }

    @Override
    public User loadByNativeQuery(String sql, Class<User> clazz, Object... args) {
        return null;
    }

    @Override
    public User loadNativeQuery(String sql, Mapper<User> mapper, Class<User> clazz, Object... args) {
        return null;
    }

    @Override
    public List<User> listNativeQuery(String sql, Mapper<User> mapper, Class<User> clazz, Object... args) {
        return null;
    }
}