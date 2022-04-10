package bookmall.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器
 * 登录检查
 * 1、配置拦截器要拦截那些请求
 * 2、把这些配置放在容器中
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	/**
	 * 执行之前
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();

		log.info("拦截的请求路径是{}", requestURI);

		//登录检查逻辑
		HttpSession session = request.getSession();

		Object user = session.getAttribute("user");
		Object admin = session.getAttribute("admin");

		if ((user != null) && (!requestURI.equals("/manager")&&
				!requestURI.equals("/bookManager")&&
				!requestURI.equals("/orderManager"))) {
			//放行
			return true;
		} else {
			if(admin != null){
				//放行
				return true;
			}else{
				request.setAttribute("msg", "请先登录");
				request.getRequestDispatcher("/login").forward(request, response);
				return false;
			}
		}

	}

	/**
	 * 执行之后
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param modelAndView
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 页面渲染之后
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
