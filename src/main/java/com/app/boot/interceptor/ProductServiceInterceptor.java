package com.app.boot.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.app.boot.exceptionHandler.InvalidHeaderValueException;
import com.app.boot.exceptionHandler.MissingHeaderArgException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Pre Handle method is Calling");
		String str = request.getHeader("SYS_IP");
		System.out.println(str);
		if (str.length() == 0 || str == null) {
			throw new MissingHeaderArgException("Header is Missing");
		}
		String[] parts = str.split("\\.");
		if (parts.length != 4) {
			System.out.println("Enter valid ip");
			throw new InvalidHeaderValueException("This is Invalid Ip address");
		}
		for (String s : parts) {
			if (s.length() > 3 || s.length() < 2) {
				System.out.println("Invalid formate");
				throw new InvalidHeaderValueException("This formate is Invalid");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post Handle method is Calling");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {

		System.out.println("Request and Response is completed");
	}
}
