package com.example.demotivators.pages;

import com.example.demotivators.dao_s.CollectionDAO;
import com.example.demotivators.dao_s.DownloadDAO;
import com.example.demotivators.dao_s.UsersDAO;
import com.example.demotivators.entities.Collection;
import com.example.demotivators.entities.Download;
import com.example.demotivators.entities.User;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();

        List<Collection> collections = CollectionDAO.getCollections();

        root.put("collections", collections);

        TemplateUtil temp = new TemplateUtil(request, response, "/collectionsTable.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        boolean isPrivate = request.getParameter("isPrivate") != null;

        Cookie userId = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

        if(userId == null) {
            response.sendError(404);

            return;
        }

        CollectionDAO.insertNewCollection(new Collection(Integer.parseInt(userId.getValue()), name, description, isPrivate));

        DownloadDAO.insert(new Download(Integer.parseInt(userId.getValue()), CollectionDAO.getLasttId(), new Date(System.currentTimeMillis())));
        response.sendRedirect("/app/collections");
    }
}
