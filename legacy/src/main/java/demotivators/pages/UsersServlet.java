package demotivators.pages;

import demotivators.entities.User;
import demotivators.dao_s.UsersDAO;
import demotivators.helper_s.TemplateUtil;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UsersServlet extends HttpServlet {
    public void init() {}
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();

        List<User> users = UsersDAO.getAll();

        root.put("users", users);


        TemplateUtil temp = new TemplateUtil(request, response, "/usersTable.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
