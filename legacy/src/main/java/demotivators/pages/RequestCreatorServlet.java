package demotivators.pages;

import demotivators.entities.Request;
import demotivators.helper_s.MyHelper;
import demotivators.dao_s.FriendshipRequestsDAO;
import demotivators.helper_s.TemplateUtil;
import freemarker.template.TemplateException;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestCreatorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        

        Map<String, Object> root = new HashMap<>();
        TemplateUtil temp = new TemplateUtil(request, response, "requestCreation.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        String text = request.getParameter("text");

        Cookie[] cookies = request.getCookies();
        Cookie cookie = MyHelper.getSpecificCookie(cookies, "user_id");

        if(cookie == null) {
            response.sendError(404);

            return;
        }

        int user_id = Integer.parseInt(cookie.getValue());

        FriendshipRequestsDAO.insert(new Request(MyHelper.getId(request.getRequestURI()), user_id, text, new Date(System.currentTimeMillis())));

        response.sendRedirect("/app/scroll");
    }
}
