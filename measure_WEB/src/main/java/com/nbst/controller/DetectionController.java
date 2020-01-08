package com.nbst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nbst.model.DetectionItem;
import com.nbst.service.IDetectionItemService;

@Controller
@RequestMapping("/detection")
public class DetectionController {
	@Autowired
	private IDetectionItemService detectionItemService;
	
	//新增检测项
	@RequestMapping("/add.action")
	@ResponseBody
	public Object addDetection(DetectionItem detectionItem) {
		return detectionItemService.addDetectionItem(detectionItem);
	}
	
	//更新检测项
	@RequestMapping("/update.action")
	@ResponseBody
	public Object updateDetection(DetectionItem detectionItem) {
		return detectionItemService.alterDetectionItem(detectionItem);
	}
	
	//删除检测项
	@RequestMapping("/delete.action")
	@ResponseBody
	
	public Object addDetection(Integer detectionItemId) {
		return detectionItemService.deleteDetectionItem(detectionItemId);
	}
}
