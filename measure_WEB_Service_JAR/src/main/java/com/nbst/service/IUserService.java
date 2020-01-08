package com.nbst.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.nbst.model.User;

public interface IUserService {
	// 用户新增
		Object addUser(User user);

		// 用户修改/删除
		Object alterUser(User user, Integer state);

		// 用户查询
		Object searchUser(Integer limit, Integer offset);
		
		/**
		 * 用户登录
		 * 
		 * @param userName
		 *            用户名
		 * @param password
		 *            密码
		 * @author huangh
		 * @return Map<String, Object>
		 */
		Map<String, Object> login(String userName, String password);

		/**
		 * 第一步，验证用户
		 * 
		 * @param yhUsername
		 *            用户名
		 * @author huangh
		 * @return Map<String, Object>
		 */
		Map<String, Object> homePage(String yhUsername);

		/**
		 * 第二步，验证邮箱，发送邮件
		 * 
		 * @param yhId
		 *            用户id
		 * @param request
		 * 
		 * @author huangh
		 * @return Map<String, Object>
		 */
		Map<String, Object> sendValidate(Integer yhId, HttpServletRequest request);

		/**
		 * 第三步，从邮件中访问该接口，进行超时验证，并返回更改密码的页面
		 * 
		 * @param yhExtend2
		 * 
		 * @param yhId
		 *            用户id
		 * @author huangh
		 * @return String
		 */
		String mailValidate(String yhExtend2, Integer yhId);

		/**
		 * 第四步，更改密码
		 * 
		 * @param yhId
		 *            用户id
		 * @param yhPassword
		 *            密码
		 * @author huangh
		 * @return Map<String, Object>
		 */
		Map<String, Object> change(Integer yhId, String yhPassword);

}
