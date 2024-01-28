package com.example.demotivators.pages;
import com.example.demotivators.helper_s.MyHelper;
import com.example.demotivators.helper_s.TemplatesLoader;
import com.example.demotivators.entities.User;
import com.example.demotivators.dao_s.UsersDAO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.servlet.http.*;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class HelloServlet extends HttpServlet {
    public void init() {}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();
        Template temp = null;

        Cookie userId = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

        if(userId == null) {
            temp = TemplatesLoader.getConfiguration().getTemplate("/hello.ftl");
            root.put("address", request.getContextPath());

            try {
                temp.process(root, out);
            } catch (TemplateException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/menu");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = UsersDAO.checkUser(login, password);

        if(login != null && password != null && user != null) {
            try {
                initUser(user, response);

                response.sendRedirect(request.getContextPath() + "/menu");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(login != null && password != null) {
            try {
                response.sendError(403);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void initUser(User user,HttpServletResponse response) {

        if (user.getRole() == User.Role.SuperAdmin || user.getRole() == User.Role.Admin)
            response.addCookie(new Cookie("admin", "true"));

        response.addCookie(new Cookie("name", user.getName()));
        response.addCookie(new Cookie("nick_name", user.getNickname()));
        response.addCookie(new Cookie("user_id", ""+user.getUserId()));
    }
}