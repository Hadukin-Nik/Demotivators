package demotivators.pages;

import demotivators.helper_s.MyHelper;
import demotivators.dao_s.CollectionDAO;
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

public class AddToCollectionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        
        Map<String, Object> root = new HashMap<>();

        TemplateUtil temp = new TemplateUtil(request, response, "/collectionToAdd.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkingOnNumeric(String number) {
        for(int i = 0; i < number.length(); i++) {
            if(!(number.charAt(i) >= '0' && number.charAt(i) <= '9')) {
                return false;
            }
        }

        return true;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String memId = request.getParameter("memId");
        String collectionId = request.getParameter("collectionId");
        boolean delete = request.getParameter("delete") != null;

        Cookie userId = MyHelper.getSpecificCookie(request.getCookies(), "user_id");

        if(memId == null || collectionId == null || userId == null) {
            response.sendError(404);

            return;
        }

        if(!checkingOnNumeric(memId) || !checkingOnNumeric(collectionId)) {
            response.sendError(405, "Wrong id\'s");

            return;
        }

        if(delete) {
            CollectionDAO.insertMemToCollection(Integer.parseInt(memId), Integer.parseInt(collectionId));
        } else {
            CollectionDAO.deleteMemFromCollection(Integer.parseInt(memId), Integer.parseInt(collectionId));
        }

        response.sendRedirect("/app/collections");
    }
}
