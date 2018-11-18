package top.zacharye.dao;

import top.zacharye.entity.User;

public interface UserDao {
    User findUserByLoginName(String username);
}
