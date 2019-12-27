package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImageDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavouriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImageDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImageDao routeImageDao = new RouteImageDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private FavouriteDao favouriteDao = new FavouriteDaoImpl();

    @Override
    public Pagebean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        //封装pageBean
        Pagebean<Route> pagebean = new Pagebean<>();

        //设置当前页及每页显示条数
        pagebean.setCurrentPage(currentPage);
        pagebean.setPageSize(pageSize);

        //设置总条数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pagebean.setTotalCount(totalCount);

        //设置具体信息
        int start = (currentPage - 1) * pageSize;
        List<Route> routeList = routeDao.findByPage(cid, start, pageSize,rname);
        pagebean.setList(routeList);

        //设置总页数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pagebean.setTotalPage(totalPage);

        return pagebean;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));

        List<RouteImg> routeImgList = routeImageDao.findByRid(route.getRid());

        Seller seller = sellerDao.findById(route.getSid());
        Integer fav = favouriteDao.countFav(Integer.parseInt(rid));
        route.setRouteImgList(routeImgList);
        route.setSeller(seller);
        route.setCount(fav);
        return route;
    }
}
