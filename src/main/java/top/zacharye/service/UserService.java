package top.zacharye.service;

import top.zacharye.entity.User;

public interface UserService {
    User findUserByLoginName(String username);
}
