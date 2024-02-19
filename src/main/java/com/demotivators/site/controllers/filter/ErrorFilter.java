package com.demotivators.site.controllers.filter;

import com.demotivators.site.controllers.ErrorHandler;
import com.demotivators.site.controllers.filter.exceptions.FilterError;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Order(value = 0)
public class ErrorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(request, response);
        } catch(RuntimeException runtimeException) {
            HttpServletResponse httpResponse = (HttpServletResponse ) response;
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

            if(runtimeException instanceof FilterError) {
                httpResponse.getWriter().write(getJson((FilterError) runtimeException));
            } else {
                httpResponse.getWriter().write("Unknown issue");
            }
        }
    }

    private String getJson(FilterError filterError) {
        String string = "{\n" +
                        "  \"message\": \""+filterError.getMessage()+"\"\n" +
                        "}";
        return string;
    }
}
