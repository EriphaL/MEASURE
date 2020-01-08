package com.nbst.service;

import com.nbst.model.DetectionItem;

/**
 * @author zhangml
 *
 */
public interface IDetectionItemService {
	// 检测项新增
	Object addDetectionItem(DetectionItem detectionItem);

	// 检测项修改
	Object alterDetectionItem(DetectionItem detectionItem);
	
	// 检测项删除
	Object deleteDetectionItem(Integer detectionItemId);

	// 检测项查询
	Object searchDetectionItem(Integer limit, Integer offset);
}
