package demotivators.pages;

import demotivators.entities.Request;
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

public class RequestServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        

        Map<String, Object> root = new HashMap<>();
        TemplateUtil temp = new TemplateUtil(request, response, "requests.ftl");

        Cookie userId = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

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
        int requestId = MyHelper.getId(URLAfterWebDomain);

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
