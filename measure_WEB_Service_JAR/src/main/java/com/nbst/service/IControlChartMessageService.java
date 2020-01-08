package com.nbst.service;

import java.util.Map;

import com.nbst.model.ControlChartInfomation;

public interface IControlChartMessageService {

	/**
	 * 自动生成控制图编号
	 * 
	 * @author huangh
	 * @return Map<String, Object>
	 */
	Map<String, Object> autoGeneration();

	/**
	 * 查询层次类型名称
	 * 
	 * @author huangh
	 * @return Map<String, Object>
	 */
	Map<String, Object> getTypeLevelName();

	/**
	 * 控制图信息查询
	 * 
	 * @param groupId     分组id
     * @param countFlag   是否需要更新计算   0：不是  1：是
	 * @param limit
	 * @param offset
	 * @author huangh
	 * @return Map<String, Object>
	 */
	Map<String, Object> getControlChartByGroupId(Integer groupId,Integer countFlag, Integer limit, Integer offset);

	/**
	 * 控制图信息新增
	 * 
	 * @param ControlChartInfomation
	 *            新增时需保存的数据
	 * @author huangh
	 * @return Map<String, Object>
	 */
	Map<String, Object> addControlChartMessage(ControlChartInfomation controlChartInfomation);

	/**
	 * 控制图信息修改/删除
	 * 
	 * @param ControlChartInfomation
	 *            修改时传需要修改的数据，删除只传id
	 * @param state
	 *            状态 0：修改；1：删除
	 * @author huangh
	 * @return Map<String, Object>
	 */
	Map<String, Object> updateControlChartMessage(ControlChartInfomation controlChartInfomation, Integer state);

	/**
	 * 根据控制图id查询控制图属性
	 * 
	 * @param jcsjControlChartId
	 *            控制图的id
	 * @author yangl
	 * @return Object
	 */
}
