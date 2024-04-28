package com.east.furns.web;

import com.east.furns.pojo.Manager;
import com.east.furns.pojo.Member;
import com.east.furns.service.ManagerService;
import com.east.furns.service.impl.ManagerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/managerServlet")
public class ManagerServlet extends BasicServlet{
    ManagerService managerService = new ManagerServiceImpl();
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * user-name: fds
         * user-password: fas
         */

        String username = request.getParameter("user-name");
        String password = request.getParameter("user-password");
        Manager manager = new Manager(null, username, password);
        if(!managerService.loginManager(manager)){
            request.setAttribute("login_error","用户名或者密码输入错误");
            request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request,response);

    }
}
