package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavouriteDaoImpl implements FavouriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite isFavourite(int uid, int rid) {
        String sql = "select * from tab_favorite where uid = ? and rid = ?";
        Favorite favorite = null;
        try {
            favorite = template.queryForObject(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid,rid);
        }catch (Exception e){

        }
        return favorite;
    }

    @Override
    public Integer countFav(int rid) {
        String sql = "select count(*) from tab_favorite where rid = ?";
        Integer count = 0;
        try {
            count = template.queryForObject(sql,Integer.class,rid);
        }catch (Exception e){

        }
        return count;
    }

    @Override
    public Integer addFavourite(int uid, int rid) {
        String sql = "insert into tab_favorite  values(?,?,?)";
        Integer count = 0;
        try {
            count = template.update(sql,rid,new Date(),uid);
        }catch (Exception e){

        }
        return count;
    }
}
