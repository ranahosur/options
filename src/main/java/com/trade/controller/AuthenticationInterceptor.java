package com.trade.controller;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * The Authentication Interceptor class. This class implements the spring HandlerInteceptor
 * to processing requests into three steps:<br>
 * - pre-handle<br>
 * - post-handle<br>
 * - after-completition
 *
 * @see org.springframework.web.servlet.HandlerInterceptor
 * @author Giuseppe Urso
 *
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    public AuthenticationInterceptor() {
    }

    private static final Logger log = Logger.getLogger(AuthenticationInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

        log.info("Interceptor: Pre-handle - URI -> "+request.getRequestURI());
        String requestPath = request.getRequestURI().substring(request.getContextPath().length());
        log.info("Interceptor: Pre-handle - requestPath -> "+requestPath);
        // Avoid a redirect loop for some urls
        if( !requestPath.contains("loginnew") && !requestPath.equals("/")
                && !requestPath.contains("resetPassword")  && !requestPath.contains("loginProcessNew") && !requestPath.contains("loginProcess")
                && !requestPath.contains("registernew") && !requestPath.contains("registerProcessNew") && !requestPath.contains("verifyRegistration")
                && !requestPath.contains("css") && !requestPath.contains("images") && !requestPath.contains("modifyPassword") && !requestPath.contains("stockPrice")
                && !requestPath.contains("forgotPassword")  && !requestPath.contains("portfolio")  && !requestPath.contains("optionsPortfolio"))
        {
            Object usernameObj =  request.getSession().getAttribute("username");
            if(usernameObj == null || usernameObj.equals("")){
                response.sendRedirect(request.getContextPath()+"/loginnew");
                return false;
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.debug("Post-handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.debug("After-completion");
    }

}
