package com.mobiletrain.web;

import com.mobiletrain.domain.Order;
import com.mobiletrain.service.CartService;
import com.mobiletrain.service.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/order_servlet")
public class OrderServlet extends BaseServlet {
    CartService cartService = new CartServiceImpl();
    protected void getCarts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        //1.接收数据
        //2.处理数据
        //先获取此id的订单
        String strId = request.getParameter("id");
        int id = Integer.parseInt(strId);
        //获取cart表里的
        int money = cartService.queryCarts(strId);

        int result = cartService.addOrder(strId , money , format);

        order.setUid(id);
        order.setMoney(money);
        order.setTime(format);
        //3.响应数据
        request.setAttribute("orderMessage" , order);
        request.getRequestDispatcher("/orderDetail.jsp").forward(request , response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
