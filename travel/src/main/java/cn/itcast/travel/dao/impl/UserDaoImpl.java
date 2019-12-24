package cn.itcast.travel.dao.impl;
/**
 * 描述:用户相关
 *
 * @author wangjialing
 * @date 2019/12/24 0024
 */

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserByName(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {
            //e.printStackTrace();

        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    @Override
    public User findUserByCode(String code) {
        String sql = "select * from tab_user where code = ?";
        User user = null;
        try {
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (user!=null){
            sql =  "UPDATE tab_user set status = 'Y' where code = ?";
            template.update(sql,code);
        }
        return user;
    }
}
