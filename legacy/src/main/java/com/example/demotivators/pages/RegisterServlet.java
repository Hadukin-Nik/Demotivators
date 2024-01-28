package com.example.demotivators.pages;

import com.example.demotivators.Config;
import com.example.demotivators.dao_s.UsersDAO;
import com.example.demotivators.entities.User;
import com.example.demotivators.helper_s.MyHelper;
import com.example.demotivators.helper_s.TemplateUtil;
import com.example.demotivators.helper_s.TemplatesLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javax.servlet.annotation.MultipartConfig;



import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@MultipartConfig

public class RegisterServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();
        root.put("address", request.getContextPath());

        TemplateUtil temp = new TemplateUtil(request, response, "register.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        String name = request.getParameter("name");
        String nickname = request.getParameter("nickname");


        Part filePart = null;
        String fileName = UUID.randomUUID().toString();
        String extension;

        try {
            filePart = request.getPart("file");
            if(filePart == null) {
                fileName = Config.DEFAULT_PNG;
                 extension = "";
            } else {
                extension = "." + MyHelper.getAStringAfterPattern(filePart.getContentType(), "image/");
                filePart.write(Config.sourceImagePath + fileName + extension);
            }

        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

        if(name != null && nickname != null  && login != null && password != null) {
            User user = new User(fileName + extension, name, nickname, "000");

            UsersDAO.addUser(user, login, password);
        }

        try {
            response.sendRedirect(request.getContextPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        if(login != null && password != null && (UsersContainer.checkUser(login, password) < 0)) {
            try {
                UsersContainer.addUser(login, password);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if(login != null && password != null) {
            try {
                response.sendError(403);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
    }
}
