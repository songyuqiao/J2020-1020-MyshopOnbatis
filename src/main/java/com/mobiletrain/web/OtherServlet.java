package com.mobiletrain.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiletrain.domain.*;
import com.mobiletrain.service.OperationService;
import com.mobiletrain.service.OperationServiceImpl;
import com.mobiletrain.service.UserService;
import com.mobiletrain.service.UserServiceImpl;
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

@WebServlet("/other")
public class OtherServlet extends BaseServlet {
    UserServiceImpl userService = new UserService();

    protected void showGoodsType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //1.接收数据
        //2.处理数据
        List<Category> categorys = CategoryManager.getCategorys();
        //3.响应数据
        request.setAttribute("category", categorys);
        request.getRequestDispatcher("/admin/showGoodsType.jsp").forward(request, response);
    }

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Map<String, Object> map = new HashMap<>();
        //2.处理数据
        boolean result = userService.queryRole(username, password);

        if (result) {
            map.put("success", true);
            map.put("msg", "登录成功");
        } else {
            map.put("success", false);
            map.put("msg", "您非管理员或用户名或密码错误，请重新输入");
        }
        //3.响应数据4
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));

    }

    protected void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        List<Goods> goods = CategoryManager.getGoods();
        //2.处理数据
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/admin/showGoods.jsp").forward(request, response);
        //3.响应数据


    }

    protected void goodsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收数据

        //2.处理数据
        List<Goods> goods = CategoryManager.getGoods();
        //3.响应数据
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/goodsList.jsp").forward(request, response);

    }

    protected void goodsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Goods> goods = CategoryManager.queryById(id);

        //3.响应数据
        request.setAttribute("goods", goods.get(0));
        request.getRequestDispatcher("/goodsDetail.jsp").forward(request, response);
    }

    protected void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationService operation = new OperationServiceImpl();
        //1.接收数据
        String uid = request.getParameter("uid");
        //2.处理数据
        List<Cart> list = operation.query(uid);
        int money = operation.sumPrice(uid);
        //3.响应数据
        request.setAttribute("uid", uid);
        request.setAttribute("list", list);
        request.setAttribute("money", money);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }

    protected void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationService operation = new OperationServiceImpl();
        //1.接收数据

        String id = request.getParameter("id");
        //2.处理数据
        //    obtain（获取）
        List<Cart> obtain = userService.queryById(id);
        //3.响应数据
        HttpSession session = request.getSession();
        session.setAttribute("obtain", obtain);
        response.sendRedirect("other?action=getAddress&id="+id);

    }

    protected void getAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationService operation = new OperationServiceImpl();
        //1.接收数据

        String id = request.getParameter("id");
        //2.处理数据
        List<Address> address = operation.queryAddress(id);
        //3.响应数据
        HttpSession session = request.getSession();
        session.setAttribute("address", address);
        response.sendRedirect("order.jsp");

    }

    protected void addAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {



        OperationService operation = new OperationServiceImpl();
        //1.接收数据

        Map<String, String[]> parameterMap = request.getParameterMap();
        Address newAddress = new Address();
        BeanUtils.populate(newAddress, parameterMap);
        HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");
        System.out.println("-----------------");
        System.out.println(loginUser.getId());
        System.out.println("-----------------");
        String utf8Name = new String(newAddress.getName().getBytes("iso-8859-1"), "utf-8");
        String utf8Detail = new String(newAddress.getDetail().getBytes("iso-8859-1"), "utf-8");

        //2.处理数据
        //插入一条地址信息
        int result = operation.insert(newAddress, utf8Name, utf8Detail);
        //通过id查询回来的数据    obtain(获取)
        List<Address> obtain = operation.getAddress(loginUser.getId());
        //3.响应数据
        session.setAttribute("obtain", obtain);
        response.sendRedirect("self_info.jsp");

    }
}
