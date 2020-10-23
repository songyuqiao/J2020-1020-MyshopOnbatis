package com.mobiletrain.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobiletrain.dao.OperationDAO;
import com.mobiletrain.dao.OperationDAOImpl;
import com.mobiletrain.domain.Cart;
import com.mobiletrain.domain.Category;
import com.mobiletrain.domain.CategoryManager;
import com.mobiletrain.domain.Goods;
import com.mobiletrain.service.OperationService;
import com.mobiletrain.service.OperationServiceImpl;
import com.mobiletrain.utils.FileUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/category")
@MultipartConfig
public class CategoryServlet extends BaseServlet {
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //1.接收数据
        Map<String, String[]> map = request.getParameterMap();
        Category category = new Category();
        try {
            BeanUtils.populate(category , map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //2.处理数据
        CategoryManager.add(category);
        //3.响应数据

    }

    protected void queryByLevel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        String strLevel = request.getParameter("level");
        int level = Integer.parseInt(strLevel);
        //2.处理数据
        List<Category> categories = CategoryManager.queryByLevel(level);
        //3.相应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(categories));

    }

    protected void queryByParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        String parent = request.getParameter("parent");
        //2.处理数据
        List<Category> categories = CategoryManager.queryByparent(parent);
        //3.相应数据
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(categories));
    }
    //添加商品
    protected void addItem(HttpServletRequest request, HttpServletResponse response){
        try {
            OperationService operation = new OperationServiceImpl();
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            //1.接收数据

            Map<String, String[]> parameterMap = request.getParameterMap();
            Goods goods = new Goods();

            BeanUtils.populate(goods, parameterMap);

            String name = request.getParameter("name");
            Part picture = request.getPart("picture");
            String intro = request.getParameter("intro");

            String utf8name = new String(name.getBytes("iso-8859-1") , "utf-8");
            String utf8intro = new String(intro.getBytes("iso-8859-1") , "utf-8");



            String relativePath = FileUtils.storage(picture);

            goods.setPicture(relativePath);
            goods.setName(utf8name);
            goods.setIntro(utf8intro);

            operation.add(goods);
        } catch (Exception e){
            e.printStackTrace();
        }


        //2.处理数据

        //3.响应数据
    }
    //添加购物车
    protected void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        Cart cart = new Cart();
        BeanUtils.populate(cart , parameterMap);

        OperationDAO dao = new OperationDAOImpl();
        int result = dao.addCart(cart);

        //2.处理数据
        //3.响应数据
        if(result == 1){
            request.setAttribute("cart" , cart);
            request.getRequestDispatcher("/cartSuccess.jsp").forward(request , response);
        }
    }
    //订单
    protected void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        //1.接收数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        Cart cart = new Cart();
        BeanUtils.populate(cart , parameterMap);

        OperationDAO dao = new OperationDAOImpl();
        int result = dao.addCart(cart);

        //2.处理数据
        //3.响应数据
        if(result == 1){
            request.setAttribute("cart" , cart);
            request.getRequestDispatcher("/cartSuccess.jsp").forward(request , response);
        }
    }

}

