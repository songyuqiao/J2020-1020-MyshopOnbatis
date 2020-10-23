package com.mobiletrain.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiletrain.domain.User;
import com.mobiletrain.service.UserService;
import com.mobiletrain.service.UserServiceImpl;
import com.mobiletrain.utils.EmailUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    UserServiceImpl userService = new UserService();
    protected void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");

        PrintWriter out = response.getWriter();

        //1.接收数据
        String username = request.getParameter("username");
        boolean result = userService.queryUsername(username);

        //2.处理数据
        if(result){
            out.write("false");
        }else{
            out.write("true");
        }
    }

    protected void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");

        PrintWriter out = response.getWriter();

        //1.接收数据
        String email = request.getParameter("email");
        boolean result = userService.queryByEmail(email);

        //2.处理数据
        if(result){
            out.write("false");
        }else{
            out.write("true");
        }
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        //1.接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String vcode = request.getParameter("vcode");

        Map<String , Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("captcha");
        //2.处理数据

        List<User> list = userService.query(username , password);

        if(!vcode.equalsIgnoreCase(captcha)){
            map.put("success" , false);
            map.put("msg" , "验证码有误，请重新输入");
        }else if(list.size() >= 1){
            map.put("success" , true);
            map.put("msg" , "operation success");
            session.setAttribute("loginUser" , list.get(0));
        }else{
            map.put("success" , false);
            map.put("msg" , "用户名或密码输入错误");
        }

        //3.响应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));

    }

    protected void activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");

        PrintWriter out = response.getWriter();

        //1.接收数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2.处理数据
        boolean result = userService.updateByName(user);
        if(result){
            out.write("激活成功");
        }else{
            out.write("激活失败");
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter out = response.getWriter();

        //1.接收数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user , map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString();
        user.setCode(code);
        EmailUtils.sendRegisterSuccess(request.getParameter("email"), code);
        //2.处理数据
        int result = userService.insert(user);
    }
}
