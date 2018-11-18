package top.zacharye.service;

import org.springframework.stereotype.Service;
import top.zacharye.dao.UserDao;
import top.zacharye.entity.User;
import javax.annotation.Resource;
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public User findUserByLoginName(String username) {
        return userDao.findUserByLoginName(username);
    }
}
