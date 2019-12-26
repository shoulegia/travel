package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Pagebean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();

    @Override
    public Pagebean<Route> pageQuery(int cid, int currentPage, int pageSize) {
        //封装pageBean
        Pagebean<Route> pagebean = new Pagebean<>();

        //设置当前页及每页显示条数
        pagebean.setCurrentPage(currentPage);
        pagebean.setPageSize(pageSize);

        //设置总条数
        int totalCount = routeDao.findTotalCount(cid);
        pagebean.setTotalCount(totalCount);

        //设置具体信息
        int start = (currentPage - 1) * pageSize;
        List<Route> routeList = routeDao.findByPage(cid, start, pageSize);
        pagebean.setList(routeList);

        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pagebean.setTotalPage(totalPage);

        return pagebean;
    }
}
