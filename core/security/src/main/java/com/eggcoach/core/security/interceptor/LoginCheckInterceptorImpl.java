package com.eggcoach.core.security.interceptor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.eggcoach.core.common.account.UserType;
import com.eggcoach.core.domain.security.interceptor.LoginCheckInterceptor;
import com.eggcoach.core.security.interceptor.annotation.LoginUserCheck;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptorImpl implements LoginCheckInterceptor, HandlerInterceptor {

	private boolean isAuthenticated() {
		return !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser");
	}

	@Override
	public boolean isLoginCheck() {
		return isAuthenticated();
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginUserCheck loginUserCheck = handlerMethod.getMethodAnnotation(LoginUserCheck.class);

		if (loginUserCheck != null && !isAuthenticated()) {
			throw new RuntimeException("LOGIN PLEASE");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
