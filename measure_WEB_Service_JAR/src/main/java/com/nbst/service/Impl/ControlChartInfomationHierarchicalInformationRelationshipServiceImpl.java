package com.nbst.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbst.comnutil.GlobalConstant;
import com.nbst.dao.mapper.imesweb.ControlChartInfomationHierarchicalInformationRelationshipMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelOneMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelTwoMapper;
import com.nbst.model.ControlChartInfomationHierarchicalInformationRelationship;
import com.nbst.service.IControlChartInfomationHierarchicalInformationRelationshipService;

import lombok.extern.slf4j.Slf4j;

/**
 * 控制图与层次信息之间的关联service实现
 * @author yangl
 *
 */
@Slf4j
@Service
@Transactional
public class ControlChartInfomationHierarchicalInformationRelationshipServiceImpl
		implements IControlChartInfomationHierarchicalInformationRelationshipService {
	//控制图层次信息关联的Mapepr
	@Autowired
	ControlChartInfomationHierarchicalInformationRelationshipMapper relationshipMapper;
	
	//各个层次信息的Mapper
	@Autowired
	private TypeLevelOneMapper typeLevelOneMapper;

	@Autowired
	private TypeLevelTwoMapper typeLevelTwoMapper;


	/**
	 *对 控制图与层次信息之间关联进行修改
	 * 
	 * @param relationship
	 * @return
	 */
	@Override
	public Object alterRelationship(ControlChartInfomationHierarchicalInformationRelationship relationship) {
		System.out.println("...........进来了");
		Map<String, Object> resultMap = new HashMap<>();
		
		//通过对象的控制图ID查询到关联ID并设置回对象
		Integer kztxxccglId=relationshipMapper.getGlIdByChartId(relationship.getKztxxccglControlChartId());
		if(kztxxccglId == null) {
			fullFailMapWithCode(resultMap, GlobalConstant.SEARCH_FAILURE_CODE);
		}
		
		// kztxxccglId != null
		relationship.setKztxxccglId(kztxxccglId);
		
		//进行更新
		int flag = relationshipMapper.update(relationship);
		
		if (flag > 0) {
			fullSuccessMapWithCode(resultMap, GlobalConstant.UPDATE_SUCCESS);
		} else {
			fullFailMapWithCode(resultMap, GlobalConstant.UPDATE_FAILURE_CODE);
		}
		return resultMap;
	}

	/**
	 * 对控制图和层次信息的关联进行查询
	 * 
	 * @param relationship
	 * @return
	 */
	@Override
	public Object searchRelationship(ControlChartInfomationHierarchicalInformationRelationship relationship) {
		Map<String, Object> resultMap = new HashMap<>();
		Map<String, Object> levels = new HashMap<>();
		
		//获得各层次的名称
		resultMap=getTypeLevelName();
		
		//通过关联对象的控制图id获得各层次的ID、值等信息
		levels=relationshipMapper.getLevels(relationship);
		
		//将查询到的名称和层次信息放到一个map
		if(levels!=null)
		resultMap.putAll(levels);
		
		//放入层次ID
		for(int i=1;i<=9;i++) {
			resultMap.put("ccid"+i,i);
		}
		
		
			resultMap.put("code", GlobalConstant.SUCCESS_CODE);
			resultMap.put("message", GlobalConstant.SEARCH_SUCCESS);
		
		return resultMap;
	}

	@Override
	public Map<String, Object> getTypeLevelName() {
		Map<String, Object> result = new HashMap<>();

		String cc1 = typeLevelOneMapper.findTypeLevelName();
		String cc2 = typeLevelTwoMapper.findTypeLevelName();

		result.put("name1", cc1);
		result.put("name2", cc2);
		result.put("success", GlobalConstant.SUCCESS_CODE);
		result.put("message", GlobalConstant.SEARCH_SUCCESS);
		return result;
	}
	
	private void fullSuccessMapWithCode(Map<String,Object> result,String msg) {
		result.put("success", GlobalConstant.SUCCESS_CODE);
		result.put("message", msg);
	}
	
	private void fullFailMapWithCode(Map<String,Object> result,String msg) {
		result.put("success", GlobalConstant.SEARCH_FAILURE_CODE);
		result.put("message", msg);
	}

}
