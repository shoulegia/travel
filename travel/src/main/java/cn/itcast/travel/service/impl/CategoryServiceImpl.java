package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //从redis中查询，没有再从数据库查询，减小数据库压力
        Jedis jedis = JedisUtil.getJedis();
        Set<String> category = jedis.zrange("category", 0, -1);
        List<Category> cs = null;
        if (category == null || category.size() == 0) {
            System.out.println("数据库");
            cs = categoryDao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        } else {
            System.out.println("缓存");
            cs = new ArrayList<Category>();
            for (String name : category) {
                Category category1 = new Category();
                category1.setCname(name);
                cs.add(category1);
            }
        }

        return cs;
    }
}
