package com.nbst.overall;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.nbst.comnutil.NormalResult;
import com.nbst.model.session.SessionConstant;

import lombok.extern.slf4j.Slf4j;
/**
 * 自定义权限验证拦截器，每一个请求都对其权限进行验证
 * @author zfm
 *
 */
@Slf4j
public class PermissionVerificationInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
//		log.debug(handler);
		//获取session对象
		HttpSession session = request.getSession();
		log.debug(session.getAttributeNames()+"");
		//获取登录信息
		Object userName = session.getAttribute(SessionConstant.USER);
		log.debug(userName+"");
//		String password = Tools.decrypt((String)session.getAttribute("yhPassword"));
		//获取权限url
		Set<String> urls = (Set<String>) session.getAttribute("urls");
		//获取请求url
		String url = request.getServletPath();
		log.debug(url);
		log.debug(urls+"");
		//如果未登录
		if(userName != null && !userName.equals("")){
			session.setMaxInactiveInterval(30*60);//设置为30分钟失效
			//如果登录了并且权限包括要访问的url
			if(urls.contains(url)){
				log.info("pass");
				return true;
			}else{
				log.info("unpass：without power!");
				response.setCharacterEncoding("UTF-8");
		        response.setContentType("text/html; charset=utf-8");
		        PrintWriter writer = response.getWriter();
		        NormalResult result = new NormalResult();
		        result.setCode("6666");
		        String json = JSON.toJSONString(result);
		        writer.write(json);
		        if(writer != null){
		        	writer.close();
		        }
				return false;
			}
			
		}else{
			//未登录转到登录页面
			log.info("unlogin");
			response.sendRedirect("/idevcloudweb/login.action");
			return false;
		}
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
