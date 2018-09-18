package com.itheima.exception.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.exception.MyException;
//�Զ����쳣�����࣬ȫ���쳣������
public class MyHandlerException implements HandlerExceptionResolver{

	/**
	 * request �������
	 * response ��Ӧ����
	 * message ����ķ���λ�� 
	 * exception �쳣����
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object message,Exception exception) {
		//��ȡexception�е�����
		//����������ͼ����
		ModelAndView mav = new ModelAndView();
		//���������˵������Ԥ�ڵ��쳣
		if(exception instanceof MyException){
			String msg=((MyException)exception).getMessage().toString();
			mav.addObject("error", msg);
		}else{
			mav.addObject("error", "δ֪�쳣!");
		}
		mav.setViewName("/error.jsp");
		return mav;
	}

}
