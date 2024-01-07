package com.spring.aws.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class SecurityFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse= (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("token");

        if (token == null || token.isBlank() || !token.equalsIgnoreCase("ABC123")) {

            var objectMapper = new ObjectMapper();
            var objectNode = objectMapper.createObjectNode();
            objectNode.put("Error", "Invalid token");

            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getOutputStream().write(objectMapper.writeValueAsBytes(objectNode));
            httpServletResponse.getOutputStream().flush();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}
