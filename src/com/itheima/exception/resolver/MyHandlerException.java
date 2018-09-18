package com.itheima.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.exception.MyException;
//自定义异常处理类，全局异常处理器
public class MyHandlerException implements HandlerExceptionResolver{

	/**
	 * request 请求对象
	 * response 响应对象
	 * message 出错的方法位置 
	 * exception 异常类型
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object message,Exception exception) {
		//获取exception中的数据
		//创建数据试图对象
		ModelAndView mav = new ModelAndView();
		//如果成立，说明是我预期的异常
		if(exception instanceof MyException){
			String msg=((MyException)exception).getMessage().toString();
			mav.addObject("error", msg);
		}else{
			mav.addObject("error", "未知异常!");
		}
		mav.setViewName("/error.jsp");
		return mav;
	}

}
