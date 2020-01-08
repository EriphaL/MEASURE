package com.nbst.comnutil;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class NormalResult implements Serializable {
    private String code;  //返回码
    
    private String message; //返回消息
    
    private Object dataset; //返回结果集
    
    private String id; //返回新增的数据ID
    
    private Object rows;//返回表单数据
    
    private Integer total;//返回分页数据
    
    private String sjkUrl;//返回数据库url
    
    private String tgt;//cas tgt
    
    private List<String> tickets;//cas service访问ticket

}

