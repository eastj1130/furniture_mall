package com.east.furns.web;

import com.east.furns.pojo.Member;
import com.east.furns.service.MemberService;
import com.east.furns.service.impl.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.google.code.kaptcha.servlet.KaptchaServlet;

import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet("/memberServlet")
public class MemberServlet extends BasicServlet {

    MemberService memberService = new MemberServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * user-name: dfksadf
         * user-password: fsdfa
         */
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("user-name");
        String password = request.getParameter("user-password");
        Member member = new Member(null, username, password, null);
        if (!memberService.loginMember(member)) {
            request.setAttribute("login_error", "用户名或者密码输入错误");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }
        member.setId(memberService.queryIdByUserName(username));
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        request.getRequestDispatcher("/views/member/login_ok.jsp").forward(request, response);


    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        user-name: fdsfdscd
        user-password: 123456
        user-password: 123456
        user-email: dfsd@qq.com
         */
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("user-name");
        String password = request.getParameterValues("user-password")[0];
        String email = request.getParameter("user-email");
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        String kaptchaSessionKey = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        if (code != null & code != "" && kaptchaSessionKey != null & kaptchaSessionKey != "" && kaptchaSessionKey.equals(code)) {
            if (!memberService.isExistsUsername(username)) {
                if (!memberService.registerMember(new Member(null, username, password, email))) {
                    request.getRequestDispatcher("/views/member/register_fail.jsp").forward(request, response);
                    return;
                }
                request.getRequestDispatcher("/views/member/register_ok.jsp").forward(request, response);
                return;
            } else {
                request.getRequestDispatcher("/views/member/register_fail.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("active", "register");
            request.setAttribute("errorMsg", "验证码错误");
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }


    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 使当前用户的session立即失效
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");


    }
}

