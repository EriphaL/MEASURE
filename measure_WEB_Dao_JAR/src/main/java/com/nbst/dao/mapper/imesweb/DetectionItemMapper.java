package com.nbst.dao.mapper.imesweb;

import java.util.List;
import java.util.Map;

import com.nbst.model.DetectionItem;

public interface DetectionItemMapper {
    int delete(Integer jcxmId);

    int insert(DetectionItem record);

    DetectionItem findById(Integer jcxmId);

    int update(DetectionItem record);

    List<DetectionItem> findByCondition(Map<String,Object> map);
    
    int count(Map<String,Object> map);
}