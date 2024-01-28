package demotivators.pages;

import demotivators.helper_s.MyHelper;
import demotivators.dao_s.FriendshipRequestsDAO;
import demotivators.helper_s.TemplateUtil;
import freemarker.template.TemplateException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class FriendsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        

        Map<String, Object> root = new HashMap<>();
        TemplateUtil temp = new TemplateUtil(request, response, "friends.ftl");

        Cookie userId = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

        if(userId == null) {
            response.sendError(404);
            return;
        }
        root.put("friends", FriendshipRequestsDAO.getApprovedRequestsWithUser(Integer.parseInt(userId.getValue())));
        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{ }
}
