package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao= new UserDaoImpl();
    /**
     *描述: 注册用户
     * @author wangjialing
     * @date 2019/12/16
     */
    @Override
    public boolean register(User user) {
        User u = userDao.findUserByName(user.getUsername());
        if (u!=null){
            return false;
        }else {
            userDao.saveUser(user);
            return true;
        }
    }
}
