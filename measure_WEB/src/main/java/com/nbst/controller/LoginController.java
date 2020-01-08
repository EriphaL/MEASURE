package com.nbst.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbst.comnutil.AppUtil;
import com.nbst.service.IUserService;

@Controller
@CrossOrigin
@Transactional
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;

	/**
	 * 用户登录
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @author huangh
	 * @return
	 */
	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	@ResponseBody
	public Object login(String userName, String password) {
		return AppUtil.conversionJsonp(userService.login(userName, password));
	}

	/**
	 * 第一步，验证用户
	 * 
	 * @param yhUsername
	 * @return
	 */
	@RequestMapping(value = "/validateUser.action", method = RequestMethod.POST)
	@ResponseBody
	public Object homePage(String yhUsername) {
		return AppUtil.conversionJsonp(userService.homePage(yhUsername));
	}

	/**
	 * 第二步，验证邮箱，发送邮件
	 * 
	 * @param yhId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendValidate.action", method = RequestMethod.POST)
	@ResponseBody
	public Object sendValidate(Integer yhId, HttpServletRequest request) {
		return AppUtil.conversionJsonp(userService.sendValidate(yhId, request));
	}

	/**
	 * 第三步，从邮件中访问该接口，进行超时验证，并返回更改密码的页面
	 * 
	 * @param yhExtend2
	 * @param yhId（本接口不使用，用作转给下一页面使用）
	 * @return
	 */
	@RequestMapping(value = "/mail.action", method = RequestMethod.GET)
	public String mailValidate(String yhExtend2, Integer yhId) {
		return userService.mailValidate(yhExtend2, yhId);
	}

	/**
	 * 第四步，更改密码
	 * 
	 * @param yhId
	 * @param yhPassword
	 * @return
	 */
	@RequestMapping(value = "/change.action", method = RequestMethod.POST)
	@ResponseBody
	public Object change(Integer yhId, String yhPassword) {
		return AppUtil.conversionJsonp(userService.change(yhId, yhPassword));
	}
}
