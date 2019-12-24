package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //验证码
        String check = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //移除已生成的验证码
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equals(check)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            String json = new ObjectMapper().writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        //获取参数map
        Map<String, String[]> map = req.getParameterMap();

        User user = new User();
        try {
            //将map集合封装到对象中
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UserService service = new UserServiceImpl();
        boolean bl = service.register(user);
        ResultInfo info = new ResultInfo();
        if (bl) {
            info.setFlag(true);
        } else {
            info.setFlag(false);
            info.setErrorMsg("注册失败");
        }
        String json = new ObjectMapper().writeValueAsString(info);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
