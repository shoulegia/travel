package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

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
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            userDao.saveUser(user);
            String content = "<a href='http://localhost:18083/activeUserServlet?code="+user.getCode()+"'>点击激活</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
        }
    }

     /***
      *
      * 功能描述:点击邮箱激活
      *
      * @param:
      * @return:
      * @auther: wangjialing
      * @date: 2019/12/24 0024 15:32
      */
    @Override
    public boolean active(String code) {
        User userByCode = userDao.findUserByCode(code);
        if (userByCode!=null){
            return true;
        }
        return false;
    }
}
