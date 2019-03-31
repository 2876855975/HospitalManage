package com.accp.interceptor;

import com.accp.util.LoginSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(LoginSession.changeTime(request.getSession(),request.getRemoteAddr()) == 2){
            new LoginSession().removeLoginUser(request.getSession(),request.getRemoteAddr());
            request.getSession().invalidate();
            response.sendRedirect("/Erlogin");
            return false;
        }
        String requestURI = request.getRequestURI();
        if(requestURI.equals("/")){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf(".jpg")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf("jump")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf("Jump")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf("duQrUser")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf(".json")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf(".js")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf("css")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf(".css")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf(".png")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.indexOf("doLogin")>0){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.equals("/Erlogin")){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.equals("/Exit")){
            return super.preHandle(request, response, handler);
        }
        if(requestURI.equals("/valiEmail")){
            return super.preHandle(request, response, handler);
        }
        Object nowUser = request.getSession().getAttribute("user");
        if(nowUser==null){
            response.sendRedirect("/Erlogin");
            return false;
        }else{
            return super.preHandle(request, response, handler);
        }
    }
}
