package cn.itcast.travel.service;

import cn.itcast.travel.domain.Pagebean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    Pagebean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    Route findOne(String rid);
}
