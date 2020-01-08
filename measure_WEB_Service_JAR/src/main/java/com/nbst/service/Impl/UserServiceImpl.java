package com.nbst.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbst.comnutil.GlobalConstant;
import com.nbst.comnutil.Times;
import com.nbst.comnutil.Tools;
import com.nbst.dao.mapper.imesweb.UserMapper;
import com.nbst.model.User;
import com.nbst.overall.MailUtil;
import com.nbst.service.IUserService;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
@Transactional
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;

	/**
	 * 用户管理新增，如果用户名重复则插入失败
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Object addUser(User user) {
		Map<String, Object> resultMap = new HashMap<>();
		// 判断name是否重复
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("yhUsername", user.getYhUsername());
		conditionMap.put("yhState", 1);
		List<User> names = userMapper.findByCondition(conditionMap);
		if (!names.isEmpty()) {
			resultMap.put("message", "名称重复，新增失败");
			resultMap.put("code", "9999");
			return resultMap;
		}

		// 若name无重复则插入
		user.setYhState(1);
		// 为用户密码进行md5加密
		String password = DigestUtils.md5Hex(user.getYhPassword());
		user.setYhPassword(password);
		int flag = userMapper.insert(user);
		if (flag != 0) {
			resultMap.put("message", "新增成功");
			resultMap.put("code", "0000");
		} else {
			resultMap.put("message", "新增失败");
			resultMap.put("code", "9997");
			return resultMap;
		}

		return resultMap;
	}

	/**
	 * 用户管理修改/删除,state=0：删除,state=1:修改
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public Object alterUser(User user, Integer state) {
		Map<String, Object> resultMap = new HashMap<>();
		// 删除,逻辑删除，并非物理删除
		if (state == 0) {
			User u = userMapper.findById(user.getYhId());
			u.setYhState(2);
			int flag = userMapper.update(u);
			if (flag > 0) {
				resultMap.put("message", "删除成功");
				resultMap.put("code", "0000");
			} else {
				resultMap.put("message", "删除失败");
				resultMap.put("code", "9996");
			}
			return resultMap;
		}
		// 修改
//		// 为用户密码进行md5加密
		if (user.getYhPassword() != null) {
			String password = DigestUtils.md5Hex(user.getYhPassword());
			user.setYhPassword(password);
		}

//		User user2=userMapper.findByName(user.getYhUsername());
//		user.setYhId(user2.getYhId());
		int flag = userMapper.update(user);
		if (flag > 0) {
			resultMap.put("message", "修改成功");
			resultMap.put("code", "0000");
		} else {
			resultMap.put("message", "修改失败");
			resultMap.put("code", "9994");
		}
		return resultMap;
	}

	/**
	 * 用户管理查询
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */ 	
	@Override
	public Object searchUser(Integer limit, Integer offset) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("yhState", 1);
		int count = userMapper.count(conditionMap);
		conditionMap.put("limit", limit);
		conditionMap.put("offset", offset);

		resultMap.put("total", count);
		resultMap.put("code", "0000");
		resultMap.put("message", "查找成功");
		resultMap.put("rows", userMapper.findByCondition(conditionMap));
		return resultMap;
	}

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
	@Override
	public Map<String, Object> login(String userName, String password) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();

		param.put("yhUsername", userName);
		param.put("yhState", GlobalConstant.NORMAL_STATE);
		List<User> users = userMapper.findByCondition(param);
		if (!users.isEmpty()) {
			String yhPassword = DigestUtils.md5Hex(password);
			User user = users.get(0);
			if (yhPassword.equals(user.getYhPassword())) {
				result.put("success", GlobalConstant.SUCCESS_CODE);
				result.put("message", GlobalConstant.LOGIN_SUCCESS_MESSAGE);
				result.put("id", user.getYhId());
			} else {
				result.put("success", GlobalConstant.PASSWORD_WORING_CODE);
				result.put("message", GlobalConstant.PASSWORD_WORING_MESSAGE);
			}
		} else {
			result.put("success", GlobalConstant.NONENTITY_USERNAME_CODE);
			result.put("message", GlobalConstant.NONENTITY_USERNAME_MESSAGE);
		}
		return result;
	}

	/**
	 * 第一步，验证用户
	 * 
	 * @param yhUsername
	 *            用户名
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> homePage(String yhUsername) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("yhUsername", yhUsername);
		List<User> users = userMapper.findByCondition(param);
		if (!users.isEmpty()) {
			User user = users.get(0);
			user.setYhExtend2(Tools.encrypt(yhUsername));
			// 过期时间
			user.setYhExtend3(Times.getNow() + 1800000 + "");
			user.setYhUpdateTime(Times.getNow());
			userMapper.update(user);
			result.put("Dataset", user);
			result.put("success", GlobalConstant.SUCCESS_CODE);
			result.put("message", GlobalConstant.VERIFY_SUCCESS_MESSAGE);
		} else {
			result.put("success", GlobalConstant.NONENTITY_USERNAME_CODE);
			result.put("message", GlobalConstant.NONENTITY_USERNAME_MESSAGE);
		}
		return result;
	}

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
	@Override
	public Map<String, Object> sendValidate(Integer yhId, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		try {
			User user = userMapper.findById(yhId);
			String email = user.getYhEmail();
			String subject = "ISPC云平台密码验证邮件";
			String url = request.getRequestURL().toString().replace(request.getRequestURI(), "");
			url += request.getContextPath();
			String YhExtend2 = user.getYhExtend2();
			YhExtend2 = YhExtend2.replace("%", "%25");
			YhExtend2 = YhExtend2.replace("+", "%2B");
			YhExtend2 = YhExtend2.replace(" ", "%20");
			YhExtend2 = YhExtend2.replace("/", "%2F");
			YhExtend2 = YhExtend2.replace("?", "%2F");
			YhExtend2 = YhExtend2.replace("#", "%23");
			YhExtend2 = YhExtend2.replace("&", "%26");
			YhExtend2 = YhExtend2.replace("=", "%3D");
			YhExtend2 = YhExtend2.replace("!", "%21");
			url += "/login/mail.action?yhExtend2=" + YhExtend2 + "&yhId=" + yhId;
			String html = "此邮件为您在本公司ISPC云平台上的找回密码验证邮件，请点击下方地址进行验证：\n<a href=\"" + url + "\">密码验证地址</a>";
			MailUtil.sendSSLHtmlMail(email, subject, html);
			result.put("Dataset", user);
			result.put("success", GlobalConstant.SUCCESS_CODE);
			result.put("message", GlobalConstant.VERIFY_SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", GlobalConstant.WORING_CODE);
			result.put("message", e.getMessage());
		}
		return result;
	}

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
	@Override
	public String mailValidate(String yhExtend2, Integer yhId) {
		Map<String, Object> param = new HashMap<>();
		try {
			String yhUsername = Tools.decrypt(yhExtend2);
			param.put("yhUsername", yhUsername);
			List<User> users = userMapper.findByCondition(param);
			if (users.isEmpty()) {
				return "error";
			} else {
				return "changepassword";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

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
	@Override
	public Map<String, Object> change(Integer yhId, String yhPassword) {
		Map<String, Object> result = new HashMap<>();
		User user = userMapper.findById(yhId);
		if ("".equals(user.getYhExtend3())) {
			result.put("success", GlobalConstant.CURRENT_PAGE_INVALID_CODE);
			result.put("message", GlobalConstant.CURRENT_PAGE_INVALID_MESSAGE);
		} else {
			if (Times.getNow() > Long.parseLong(user.getYhExtend3())) {
				result.put("success", GlobalConstant.PASS_VERIFY_TIME_CODE);
				result.put("message", GlobalConstant.PASS_VERIFY_TIME_MESSAGE);
			} else {
				// u.setYhPassword(Tools.encrypt(yhPassword));
				String digest = DigestUtils.md5Hex(yhPassword);
				user.setYhPassword(digest);
				user.setYhExtend2("");
				user.setYhExtend3("");
				userMapper.update(user);
				result.put("success", GlobalConstant.SUCCESS_CODE);
				result.put("message", GlobalConstant.UPDATE_SUCCESS);
			}
		}
		return result;
	}
}
