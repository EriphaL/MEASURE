package com.nbst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbst.comnutil.AppUtil;
import com.nbst.model.User;
import com.nbst.service.IUserService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author zhangml
 *
 */
@Controller
@RequestMapping("/userManagement")
@Slf4j
@CrossOrigin
public class UserController {
	@Autowired
	private IUserService userService;

	/**
	 * 用户管理新增
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
	@ResponseBody
	public Object addUser(User user) {
		return AppUtil.conversionJsonp(userService.addUser(user));
	}

	/**
	 * 用户管理修改/删除
	 * 
	 * @param user
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	@ResponseBody
	public Object update(User user, Integer state) {
		return AppUtil.conversionJsonp(userService.alterUser(user, state));
	}

	/**
	 * 用户管理查询
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	@RequestMapping(value = "/search.action", method = RequestMethod.GET)
	@ResponseBody
	public Object search(Integer limit, Integer offset) {
		return AppUtil.conversionJsonp(userService.searchUser(limit, offset));
	}
}