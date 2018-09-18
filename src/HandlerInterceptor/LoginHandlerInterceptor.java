package HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.entity.User;

//�Զ���������
public class LoginHandlerInterceptor implements  HandlerInterceptor{

	//ҳ����Ⱦ�󣨵���ִ�У�
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
	}
	//�����󣨵ڶ�ִ�У�
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
	}

	//����ǰ������ִ�У�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//��ȡ�û��������ַ
		String requestURI = request.getRequestURI();
		if(requestURI.contains("login")){
			return true;
		}else{
			//1.��request��ȡsession
			HttpSession session = request.getSession();
			//2.��ȡsession�е�ֵ
			User user = (User) session.getAttribute("user");
			//3.�ж�
			if(user==null){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return false;//��ʾ������
			}
			return true;//��ʾ����
		}
	}
	
}
