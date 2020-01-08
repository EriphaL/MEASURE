package com.nbst.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbst.comnutil.GlobalConstant;
import com.nbst.dao.mapper.imesweb.DetectionItemMapper;
import com.nbst.model.DetectionItem;
import com.nbst.service.IDetectionItemService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangl
 *
 */
@Slf4j
@Service
@Transactional
public class DetectionItemServiceImpl implements IDetectionItemService {
	@Autowired
	private DetectionItemMapper detectionItemMapper;


	/**
	 * 新增检测项，如果编号或者名称重复则插入失败
	 * 
	 * @param detectionItem
	 * @return
	 */
	@Override
	public Object addDetectionItem(DetectionItem detectionItem) {
		Map<String, Object> resultMap = new HashMap<>();
		// 判断name是否重复
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("jcxmName", detectionItem.getJcxmName());
		conditionMap.put("jcxmExtend1", "1");
		List<DetectionItem> names = detectionItemMapper.findByCondition(conditionMap);
		if (!names.isEmpty()) {
			resultMap.put("message", "名称重复，新增失败");
			resultMap.put("code", "9999");
			return resultMap;
		}
		// 判断code是否重复
		conditionMap.clear();
		conditionMap.put("jcxmCode", detectionItem.getJcxmCode());
		conditionMap.put("jcxmExtend1", "1");
		List<DetectionItem> codes = detectionItemMapper.findByCondition(conditionMap);
		if (!codes.isEmpty()) {
			resultMap.put("message", "编号重复，新增失败");
			resultMap.put("code", "9998");
			return resultMap;
		}

		// 若name和code都无重复则插入
		detectionItem.setJcxmExtend1("1");
		int flag = detectionItemMapper.insert(detectionItem);
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
	 * @param detectionItem
	 * @return
	 */
	@Override
	public Object alterDetectionItem(DetectionItem detectionItem) {
		Map<String, Object> resultMap = new HashMap<>();
		return resultMap;
	}
	
	
	/**
	 * @param detectionItem
	 * @return
	 */
	@Override
	public Object deleteDetectionItem(Integer detectionItemId) {
		Map<String, Object> resultMap = new HashMap<>();
		return resultMap;
	}

	/**
	 * 查询检测项
	 * 
	 * @param limit
	 * @param offset
	 * @return
	 */
	@Override
	public Object searchDetectionItem(Integer limit, Integer offset) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("jcxmExtend1", "1");

		int count = detectionItemMapper.count(conditionMap);
		conditionMap.put("limit", limit);
		conditionMap.put("offset", offset);

		resultMap.put("total", count);
		resultMap.put("code", "0000");
		resultMap.put("message", "查找成功");
		resultMap.put("rows", detectionItemMapper.findByCondition(conditionMap));
		return resultMap;
	}
}
