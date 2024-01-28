package com.example.demotivators.pages;

import com.example.demotivators.helper_s.MyHelper;
import com.example.demotivators.helper_s.TemplateUtil;
import com.example.demotivators.helper_s.TemplatesLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FriendsScrollServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();



        Cookie cookie = MyHelper.getSpecificCookie(request.getCookies(), "admin");


        root.put("isAdmin", cookie != null);

        cookie = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

        if(cookie == null) {
            response.sendError(404);
            return;
        }

        int user_id = Integer.parseInt(cookie.getValue());

        root.put("userId", user_id);

        root.put("memes", MyHelper.friendsScroll(user_id));

        TemplateUtil temp = new TemplateUtil(request, response, "scroll.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
