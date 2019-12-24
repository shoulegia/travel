package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    User findUserByName(String username);

    void saveUser(User user);

    User findUserByCode(String code);
}
