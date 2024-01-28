package demotivators.pages;

import demotivators.Config;
import demotivators.entities.Mem;
import demotivators.helper_s.MyHelper;
import demotivators.dao_s.MemesDAO;
import demotivators.helper_s.TemplateUtil;
import demotivators.helper_s.TemplatesLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@MultipartConfig
public class MemUpload extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        

        Map<String, Object> root = new HashMap<>();
        TemplateUtil temp = new TemplateUtil(request, response, "memUpload.ftl");

        try {
            temp.process(root, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        Template temp;
        PrintWriter out = response.getWriter();
        Map<String, Object> vars = new HashMap<>();


        Boolean isCommentsAllowed = request.getParameter("isCommentsAllowed") != null;
        String description = request.getParameter("description");
        String tags = request.getParameter("tags");


        Part filePart = null;
        String fileName = UUID.randomUUID().toString();
        String extension;

        try {
            filePart = request.getPart("file");
            if(filePart == null) {
                response.sendRedirect(request.getContextPath());
            }
            extension = "." + MyHelper.getAStringAfterPattern(filePart.getContentType(), "image/");
            filePart.write(Config.sourceImagePath + fileName + extension);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


        temp = TemplatesLoader.getConfiguration().getTemplate("afterMemUpload.ftl");


        vars.put("img_src", "images/" + fileName + extension);


        try {
            temp.process(vars, out);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
        Cookie[] cookies = request.getCookies();

        int user_id = Integer.parseInt(MyHelper.getSpecificCookie(cookies, "user_id").getValue());


        MemesDAO.InsertMem(new Mem(user_id, fileName + extension, description, tags, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), isCommentsAllowed));


    }


}
