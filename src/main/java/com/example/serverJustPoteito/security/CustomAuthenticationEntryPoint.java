package com.example.serverJustPoteito.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(response.getStatus() == HttpServletResponse.SC_FORBIDDEN){
            //token válido pero usuario sin permisos
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization failed");
        }else{
            //token no válido o inexistente
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization failed");
        }
    }
}
