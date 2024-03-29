package com.demotivators.site.controllers.filter;

import com.demotivators.site.dao.UserDAO;
import com.demotivators.site.models.User;
import com.demotivators.site.models.UserAuthData;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
@RequiredArgsConstructor
@Getter
public class Token implements Filter {
    private final UserDAO userDAO;

    private UserAuthData userAuthData;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        User userByToken = userDAO.getUserByToken(httpRequest.getHeader("Token"));

        userAuthData = new UserAuthData(userByToken.getToken());
        userAuthData.setLogin(userByToken.getLogin());
        //new UserAuthData();
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
