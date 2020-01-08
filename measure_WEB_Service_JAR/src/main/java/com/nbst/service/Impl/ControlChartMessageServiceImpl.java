package com.nbst.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.ibatis.session.ExecutorType;
//import org.apache.ibatis.session.SqlSession;
//import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nbst.enums.Enum;
import com.nbst.comnutil.GlobalConstant;
import com.nbst.comnutil.Times;
import com.nbst.dao.mapper.imesweb.ControlChartInfomationHierarchicalInformationRelationshipMapper;
import com.nbst.dao.mapper.imesweb.ControlChartInfomationMapper;
import com.nbst.dao.mapper.imesweb.DetectionDataMapper;
import com.nbst.dao.mapper.imesweb.DetectionItemMapper;
import com.nbst.dao.mapper.imesweb.GroupingMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelOneMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelTwoMapper;
import com.nbst.model.ControlChartInfomation;
import com.nbst.model.ControlChartInfomationHierarchicalInformationRelationship;
import com.nbst.model.DetectionData;
import com.nbst.model.Grouping;
import com.nbst.service.IControlChartMessageService;

@Service
@Transactional
public class ControlChartMessageServiceImpl implements IControlChartMessageService {

	@Autowired
	private ControlChartInfomationMapper controlChartInfomationMapper;
	@Autowired
	private DetectionDataMapper detectionDatamapper;
	@Autowired
	private TypeLevelOneMapper typeLevelOneMapper;
	@Autowired
	private TypeLevelTwoMapper typeLevelTwoMapper;
	@Autowired
	private ControlChartInfomationHierarchicalInformationRelationshipMapper controlChartRelationshipMapper;
	@Autowired
	private GroupingMapper groupingMapper;
	// @Autowired
	// private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 自动生成控制图编号
	 * 
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> autoGeneration() {
		Map<String, Object> result = new HashMap<>();
		result.put("code", Times.getNow());
		return result;
	}

	/**
	 * 查询层次类型名称
	 * 
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getTypeLevelName() {
		Map<String, Object> result = new HashMap<>();

		String cc1 = typeLevelOneMapper.findTypeLevelName();
		String cc2 = typeLevelTwoMapper.findTypeLevelName();

		result.put("cc1", cc1);
		result.put("cc2", cc2);
		result.put("success", GlobalConstant.SUCCESS_CODE);
		result.put("message", GlobalConstant.SEARCH_SUCCESS);
		return result;
	}

	/**
	 * 根据传入的分组id查询所有的下级分组id
	 * 
	 * @param groupId
	 * @author huangh
	 * @return
	 */
	public List<Integer> getAllIdByGroupId(Integer groupId) {
		List<Integer> result = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		result.add(groupId);
		map.put("fzExtend1", groupId);
		List<Grouping> groupings = groupingMapper.findByCondition(map);
		if (!groupings.isEmpty()) {
			for (Grouping grouping : groupings) {
				List<Integer> list = getAllIdByGroupId(grouping.getFzId());
				result.addAll(list);
			}
		}
		return result;
	}

	/**
	 * 根据分组Id查询控制图信息
	 * 
	 * @param groupId         分组id
     * @param countFlag   是否需要更新计算   0：不是  1：是
	 * @param limit
	 * @param offset
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getControlChartByGroupId(Integer groupId, Integer countFlag,Integer limit, Integer offset) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();

		List<Integer> groupIds = getAllIdByGroupId(groupId);
		param.put("groupIds", groupIds);
		param.put("kztxxExtend1", GlobalConstant.NORMAL_STATE);
		int count = controlChartInfomationMapper.count(param);
		param.put("limit", limit);
		param.put("offset", offset);
		// 根据分组id查询关联的控制图
		List<Map<String, Object>> messages = controlChartInfomationMapper.findListByGroupId(param);

		result.put("total", count);
		result.put("success", GlobalConstant.SUCCESS_CODE);
		result.put("message", GlobalConstant.SEARCH_SUCCESS);
		result.put("rows", messages);
		return result;
	}

	/**
	 * 控制图信息新增
	 * 
	 * @param ControlChartInfomation 新增时需保存的数据
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> addControlChartMessage(ControlChartInfomation controlChartInfomation) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();

		if (controlChartInfomation.getKztxxNumber() == null) {
			result.put("success", GlobalConstant.NO_CONTROL_CHART_NUMBER_CODE);
			result.put("message", GlobalConstant.NO_CONTROL_CHART_NUMBER);
			return result;
		}
		param.put("kztxxExtend1", GlobalConstant.NORMAL_STATE);
		param.put("kztxxNumber", controlChartInfomation.getKztxxNumber());
		List<ControlChartInfomation> controlChartInfomations = controlChartInfomationMapper.findByCondition(param);
		if (controlChartInfomations.isEmpty()) {
			controlChartInfomation.setKztxxExtend1(GlobalConstant.NORMAL_STATE);
			controlChartInfomation.setKztxxExtend2(String.valueOf(Times.getNow()));
			controlChartInfomationMapper.insert(controlChartInfomation);
			ControlChartInfomationHierarchicalInformationRelationship message = new ControlChartInfomationHierarchicalInformationRelationship();
			message.setKztxxccglControlChartId(controlChartInfomation.getKztxxId());
			// 只写入控制图Id,之后根据id更新关联记录
			controlChartRelationshipMapper.insert(message);
			result.put("id", controlChartInfomation.getKztxxId());
			result.put("success", GlobalConstant.SUCCESS_CODE);
			result.put("message", GlobalConstant.INSERT_SUCCESS);
		} else {
			result.put("success", GlobalConstant.SAME_NUMBER_CODE);
			result.put("message", GlobalConstant.SAME_NUMBER);
		}

		return result;
	}

	/**
	 * 控制图信息修改/删除
	 * 
	 * @param ControlChartInfomation 修改时传需要修改的数据，删除只传id
	 * @param state                  状态 0：修改；1：删除
	 * @author huangh
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> updateControlChartMessage(ControlChartInfomation controlChartInfomation, Integer state) {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		// 判断执行的操作是修改还是删除
		if (state.equals(Enum.UPDATE.getCode().intValue())) {
			// 根据控制图信息的编号查询是否存在同编号的控制图
			param.put("kztxxNumber", controlChartInfomation.getKztxxNumber());
			param.put("kztxxExtend1", GlobalConstant.NORMAL_STATE);
			List<ControlChartInfomation> controlChartInfomations = controlChartInfomationMapper.findByCondition(param);
			for (ControlChartInfomation ControlChartInfomation : controlChartInfomations) {
				if (!ControlChartInfomation.getKztxxId().equals(controlChartInfomation.getKztxxId())) {
					state++;
				}
			}
			ControlChartInfomation ControlChartInfomation = controlChartInfomationMapper
					.findById(controlChartInfomation.getKztxxId());
			if (!ControlChartInfomation.getKztxxSampleCapacity()
					.equals(controlChartInfomation.getKztxxSampleCapacity())) {
				param = new HashMap<>();
				param.put("jcsjControlChartId", controlChartInfomation.getKztxxId());
				List<DetectionData> list = detectionDatamapper.findByCondition(param);
				if (!list.isEmpty()) {
					result.put("success", GlobalConstant.CONTROL_CHART_HAVE_DATA_CODE);
					result.put("message", GlobalConstant.CONTROL_CHART_HAVE_DATA);
					return result;
				}
			}
			// 如果存在同编号的控制图则提示已存在相同编号
			if (state.equals(0)) {
				controlChartInfomation.setKztxxExtend2(String.valueOf(Times.getNow()));
				controlChartInfomationMapper.update(controlChartInfomation);
				result.put("success", GlobalConstant.SUCCESS_CODE);
				result.put("message", GlobalConstant.UPDATE_SUCCESS);
			} else {
				result.put("success", GlobalConstant.SAME_NUMBER_CODE);
				result.put("message", GlobalConstant.SAME_NUMBER);
			}
		} else if (state.equals(Enum.DELETE.getCode().intValue())) {
			// 将控制图信息的记录状态改为2
			controlChartInfomation.setKztxxExtend1(GlobalConstant.DELETE_STATE);
			controlChartInfomation.setKztxxExtend2(String.valueOf(Times.getNow()));
			controlChartInfomationMapper.update(controlChartInfomation);
			result.put("success", GlobalConstant.SUCCESS_CODE);
			result.put("message", GlobalConstant.DELETE_SUCCESS);
		}

		return result;
	}

}
