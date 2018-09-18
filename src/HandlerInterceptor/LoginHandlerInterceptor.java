package HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.itheima.entity.User;

//自定义拦截器
public class LoginHandlerInterceptor implements  HandlerInterceptor{

	//页面渲染后（第三执行）
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)
			throws Exception {
	}
	//方法后（第二执行）
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
	}

	//方法前（最早执行）
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		//获取用户的请求地址
		String requestURI = request.getRequestURI();
		if(requestURI.contains("login")){
			return true;
		}else{
			//1.从request获取session
			HttpSession session = request.getSession();
			//2.获取session中的值
			User user = (User) session.getAttribute("user");
			//3.判断
			if(user==null){
				response.sendRedirect(request.getContextPath()+"/login.jsp");
				return false;//表示不放行
			}
			return true;//表示放行
		}
	}
	
}
