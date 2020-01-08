package com.nbst.comnutil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.extern.slf4j.Slf4j;  
  
/** 
 *  
 * @author zhuyao 
 * @date 2018.03.22
 */  
@Slf4j
public class AppUtil {  
      
    /** 
     * 判断json字符串是否需要转化成jsonp格式 
     * @param request 
     * @param result 
     * @return 
     */  
    public static Object conversionJsonp(Object result){  
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
        return conversionJsonp(request, result);  
    }  
      
  
    public static Object conversionJsonp(HttpServletRequest request,Object result){  
        String callback = request.getParameter("callback");  
        if(StringUtils.isNotEmpty(callback)){  
            return new JSONPObject(callback, result);  
        }  
        return result;  
    }  
}  