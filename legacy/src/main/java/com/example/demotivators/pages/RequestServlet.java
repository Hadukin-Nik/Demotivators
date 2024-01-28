package com.example.demotivators.pages;

import com.example.demotivators.dao_s.CommentsDAO;
import com.example.demotivators.dao_s.FriendshipRequestsDAO;
import com.example.demotivators.dao_s.MemesDAO;
import com.example.demotivators.entities.Comment;
import com.example.demotivators.entities.Request;
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
import java.util.Map;

import static com.example.demotivators.helper_s.MyHelper.getId;
import static com.example.demotivators.helper_s.MyHelper.getSpecificCookie;

public class RequestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        

        Map<String, Object> root = new HashMap<>();
        TemplateUtil temp = new TemplateUtil(request, response, "requests.ftl");

        Cookie userId = getSpecificCookie(request.getCookies(), "user_id");

        if(userId == null) {
            response.sendError(404);
            return;
        }
        root.put("requests", FriendshipRequestsDAO.getRequestsWithUser(Integer.parseInt(userId.getValue())));

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String URLAfterWebDomain = request.getRequestURI();
        int requestId = getId(URLAfterWebDomain);

        if(URLAfterWebDomain.endsWith("approve")) {
            Request req = FriendshipRequestsDAO.find(requestId);

            if(req == null) {
                response.sendError(405, "Wrong FriendShip request id!");
                return;
            }

            req.setApproved(true);

            FriendshipRequestsDAO.update(req);

        } else if(URLAfterWebDomain.endsWith("reject"))  {
            Request req = FriendshipRequestsDAO.find(requestId);

            if(req == null) {
                response.sendError(405, "Wrong FriendShip request id!");
                return;
            }
            FriendshipRequestsDAO.delete(req);
        }

        response.setContentType("text/plain");
        response.getWriter().write("ok");
        response.getWriter().flush();
    }
}
