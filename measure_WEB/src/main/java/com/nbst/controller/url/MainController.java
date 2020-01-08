package com.nbst.controller.url;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

	@RequestMapping(value = "/index.action")
	public String index() {
		return "/index";
	}
	//主页面
	 @RequestMapping(value = "/MainPage.action")
	 public String MainPage() {
	  return "/MainPage";
	 }
	 //产品管理
	 @RequestMapping(value = "/DetectionItem.action")
	 public String DetectionItem() {
	  return "/DetectionItem";
	 }
	 //检测项管理
	 @RequestMapping(value = "/jcxManagement.action")
	 public String jcxManagement() {
	  return "/jcxManagement";
	 }
	 //计算公式 关联检测项
	 @RequestMapping(value = "/calculate.action")
	 public String calculate() {
	  return "/calculate";
	 }
}
