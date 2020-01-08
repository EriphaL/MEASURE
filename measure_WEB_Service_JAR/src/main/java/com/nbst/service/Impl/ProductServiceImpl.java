package com.nbst.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nbst.dao.mapper.imesweb.ControlChartInfomationHierarchicalInformationRelationshipMapper;
import com.nbst.dao.mapper.imesweb.ControlChartInfomationMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelOneMapper;
import com.nbst.dao.mapper.imesweb.TypeLevelTwoMapper;
import com.nbst.model.ControlChartInfomation;
import com.nbst.model.ControlChartInfomationHierarchicalInformationRelationship;
import com.nbst.model.Product;
import com.nbst.model.TypeLevelOne;
import com.nbst.model.TypeLevelTwo;
import com.nbst.service.IControlChartMessageService;
import com.nbst.service.IProductService;


@Service
@Transactional
public class ProductServiceImpl implements IProductService{
	@Autowired
	private TypeLevelOneMapper oneMapper;
	@Autowired
	private TypeLevelTwoMapper twoMapper;
	
	@Autowired
	private ControlChartInfomationMapper controlChartInfomationMapper;
	@Autowired 
	private IControlChartMessageService controlChartMessageServiceImpl;
	@Autowired
	private ControlChartInfomationHierarchicalInformationRelationshipMapper controlChartRelationshipMapper;
	
	/**
	 * @author zhangml 
	 * 
	 * 新增产品信息
	 */
	@Override
	public Object addProduct(Product product) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("cclxoneName", product.getProductName());
		//如果产品名称不重复则插入
		List<TypeLevelTwo> list = twoMapper.findByCondition(condition);
		if(list.isEmpty()) {
			TypeLevelTwo typeLevelTwo = new TypeLevelTwo();
			typeLevelTwo.setCclxtwoName(product.getProductName());
			typeLevelTwo.setCclxtwoExtend1("1");
			int flag2= twoMapper.insert(typeLevelTwo);
			if(flag2!=0) {
				TypeLevelOne typeLevelOne = new TypeLevelOne();
				typeLevelOne.setCclxoneName(product.getProductType());
				typeLevelOne.setCclxoneExtend1("1");
				int flag1=oneMapper.insert(typeLevelOne);
				if(flag1!=0) {
					//新增控制图
					ControlChartInfomation chartInfomation = new ControlChartInfomation();
					chartInfomation.setKztxxGroupId(product.getProductId());
					chartInfomation.setKztxxExtend1("1");
					chartInfomation.setKztxxNumber(controlChartMessageServiceImpl.autoGeneration().get("code").toString());
					int flag3=controlChartInfomationMapper.insert(chartInfomation);
					
					//新增控制图层次关联图
					ControlChartInfomationHierarchicalInformationRelationship chartRelationship = new ControlChartInfomationHierarchicalInformationRelationship();
					chartRelationship.setKztxxccglControlChartId(chartInfomation.getKztxxId());
					chartRelationship.setKztxxccglTypeLevelOneId(typeLevelOne.getCclxoneId());
					chartRelationship.setKztxxccglTypeLevelOneState(1);
					chartRelationship.setKztxxccglTypeLevelTwoId(typeLevelTwo.getCclxtwoId());
					chartRelationship.setKztxxccglTypeLevelTwoState(1);
					int flag4=controlChartRelationshipMapper.insert(chartRelationship);
					if(flag3!=0&&flag4!=0) {
						resultMap.put("code","0000");
						resultMap.put("message","新增成功");
						return resultMap;
					}
				}
			}
		}else {
			resultMap.put("code","2222");
			resultMap.put("message","产品名已经存在，请重新输入！");
			return resultMap;
		}
		
		resultMap.put("code","1111");
		resultMap.put("message","新增失败");
		return resultMap;
	}

	
	/**
	 * 修改产品信息
	 */
	@Override
	public Object updateProduct(Product product) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
	}

	/**
	 * 删除产品信息
	 * @author zhangml
	 * @param productId  产品id
	 */
	@Override
	public Object deleteProduct(Integer productId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		TypeLevelTwo typeLevelTwo=twoMapper.findById(productId);
		if(typeLevelTwo!=null) {
			typeLevelTwo.setCclxtwoExtend1("0");
			int flag2=twoMapper.update(typeLevelTwo);
			Map<String, Object> conditionMap=new HashMap<String, Object>();
			conditionMap.put("kztxxccglTypeLevelTwoId",typeLevelTwo.getCclxtwoId());
			conditionMap.put("kztxxccglTypeLevelTwoState","1");
			List<ControlChartInfomationHierarchicalInformationRelationship> relationship = controlChartRelationshipMapper.findByCondition(conditionMap);
			int oneId=relationship.get(0).getKztxxccglTypeLevelOneId();
			TypeLevelOne typeLevelOne=oneMapper.findById(oneId);
			typeLevelOne.setCclxoneExtend1("0");
			int falg3=oneMapper.update(typeLevelOne);
			
			
		}
		resultMap.put("code","1111");
		resultMap.put("message","id不存在，删除失败！");
		return resultMap;
	}

	@Override
	public Object findAllProduct() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		return resultMap;
	}
	
}
