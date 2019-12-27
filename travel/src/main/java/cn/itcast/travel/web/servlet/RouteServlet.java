package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Pagebean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavouriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService service = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavouriteServiceImpl();

    /***
     *
     * 功能描述:分页查询
     *
     * @param:
     * @return:
     * @auther: wangjialing
     * @date: 2019/12/25 0025 15:15
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        String rname = request.getParameter("rname");
        if (rname != null) {
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }

        int cid = 0;
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        //每页显示条数
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        Pagebean<Route> pagebean = service.pageQuery(cid, currentPage, pageSize, rname);
        writeValue(pagebean, response);
    }

     /***
      *
      * 功能描述:商品详情
      *
      * @param: id
      * @return:
      * @auther: wangjialing
      * @date: 2019/12/26 0026 16:13
      */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");

        Route route = service.findOne(rid);
        writeValue(route,response);
    }

     /***
      *
      * 功能描述:判断用户是否收藏
      *
      * @param:
      * @return:
      * @auther: wangjialing
      * @date: 2019/12/27 0027 9:37
      */
    public void isFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        int rid = 0;
        if (user != null){
            uid = user.getUid();
        }
        if (ridStr != null && ridStr.length()>0){
            rid = Integer.parseInt(ridStr);
        }
        Boolean flag = favoriteService.isFavourite(uid,rid);
        writeValue(flag,response);
    }

    /***
     *
     * 功能描述:用户收藏
     *
     * @param:
     * @return:
     * @auther: wangjialing
     * @date: 2019/12/27 0027 9:37
     */
    public void addFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ridStr = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        int rid = 0;
        if (user != null){
            uid = user.getUid();
        }else {
            return;
        }
        if (ridStr != null && ridStr.length()>0){
            rid = Integer.parseInt(ridStr);
        }
        Boolean flag = favoriteService.addFavourite(uid,rid);
        writeValue(flag,response);
    }

}
