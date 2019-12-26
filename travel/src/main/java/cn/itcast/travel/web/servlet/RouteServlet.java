package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Pagebean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    
    private RouteService service = new RouteServiceImpl();
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

        int cid = 0;
        if (cidStr !=null && cidStr.length() > 0){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;
        if (currentPageStr !=null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else {
            currentPage = 1;
        }

        //每页显示条数
        int pageSize = 0;
        if (pageSizeStr !=null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else {
            pageSize = 5;
        }

        Pagebean<Route> pagebean = service.pageQuery(cid, currentPage, pageSize);
        writeValue(pagebean,response);
    }

}
