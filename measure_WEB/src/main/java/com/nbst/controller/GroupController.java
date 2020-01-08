package com.nbst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbst.model.Grouping;
import com.nbst.service.IGroupingService;

@Controller
@RequestMapping("/group")
public class GroupController {
	@Autowired IGroupingService groupService;
	
	//新增分组
	@RequestMapping("/add.action")
	@ResponseBody
	public Object addGroup(Grouping group) {
		return groupService.addGroup(group);
	}
	
	//更改分组（更新、删除）
	@RequestMapping("/update.action")
	@ResponseBody
	public Object updateGroup(Grouping group) {
		return groupService.alterGroup(group);
	}
	
	//查询所有分组
	@RequestMapping(value = "/searchTree.action", method = RequestMethod.GET)
	@ResponseBody
	public Object searchGroup(Integer limit,Integer offset) {
		return groupService.searchGroup(limit, offset);
	}

}
