package com.example.demotivators.helper_s;

import com.example.demotivators.entities.Request;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TemplateUtil {
    private Template template;

    private Map<String, Object> defaults;

    private HttpServletResponse response;

    public TemplateUtil(HttpServletRequest request, HttpServletResponse response, String templateName) throws IOException {
        this.response = response;

        defaults = new HashMap<>();

        defaults.put("URL", request.getRequestURI());
        defaults.put("backToAuthorisation", request.getContextPath());
        defaults.put("toScroll", request.getContextPath() + "/scroll");
        defaults.put("toFriendsScroll", request.getContextPath() + "/friendsScroll");

        defaults.put("toUsers", request.getContextPath() + "/users");
        defaults.put("toMemUpload", request.getContextPath() + "/mem_upload");
        defaults.put("toRequest", request.getContextPath() + "/requests");
        defaults.put("toFriends", request.getContextPath() + "/friends");
        defaults.put("toAddCollections", request.getContextPath() + "/addToCollection");
        defaults.put("toCollections", request.getContextPath() + "/collections");

        defaults.put("isAdmin", Arrays.stream(request.getCookies()).filter(t -> t.getName().equals("admin")).count()>=1);

        template = TemplatesLoader.getConfiguration().getTemplate(templateName);
    }

    public void process(Map<String, Object> args, PrintWriter out) throws TemplateException, IOException {
        args.putAll(defaults);

        template.process(args, out);
    }
}
