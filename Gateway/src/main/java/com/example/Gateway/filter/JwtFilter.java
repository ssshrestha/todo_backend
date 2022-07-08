package com.example.Gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;

        String authenticationHeader=httpServletRequest.getHeader("authorization");

        if(httpServletRequest.getMethod().equals("OPTIONS")){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
        else{
            if(authenticationHeader==null || !authenticationHeader.startsWith("Bearer")){
                throw new ServletException("Missing or Invalid Authorization Header");
            }
        }

        //"authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWRoaXIiLCJpYXQiOjE2NTQ1Nzc4MDB9.yZxGqDy4VviNhcsK_0Jp7mgqh-5YluztDaCdz3QCFUs"

        //Get the token String using the substring method
        final String token=authenticationHeader.substring(7);

        //Get the Claim information of the token
        Claims claims=Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody();

        //Adding the claim information to the request object so to use in the protected resource
        httpServletRequest.setAttribute("claims",claims);

        //Moving to the Protected resource.
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
