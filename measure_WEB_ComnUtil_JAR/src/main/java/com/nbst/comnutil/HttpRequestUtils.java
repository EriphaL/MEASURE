package com.nbst.comnutil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
/**
 * 模拟http请求工具类
 * 
 * @author zm
 *
 */
@Slf4j
public class HttpRequestUtils {
    
	/**
	 * 发送get请求，返回字符串结果
	 * 
	 * @param url
	 * @return
	 */
	public static String sendGet(String url) {
		log.debug("sendGet url=" + url);
		// 1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.生成一个get请求
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			// 3.执行get请求并返回结果
			response = httpclient.execute(httpGet);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String result = null;
		try {
			// 4.处理结果，这里将结果返回为字符串
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送无参Post请求，返回字符串结果
	 * 
	 * @param url
	 * @return
	 */
	public static String sendPost(String url) {
		log.debug("sendPost url=" + url);
		// 1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.生成一个get请求
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			// 3.执行get请求并返回结果
			response = httpclient.execute(httpPost);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String result = null;
		try {
			// 4.处理结果，这里将结果返回为字符串
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送有参Post请求，返回结果字符串
	 * 
	 * @param url
	 * @return
	 */
	public static String sendPost(String url, Map<String, String> param) {
		log.debug("sendPost url=" + url + " param=" + param);
		// 1.获得一个httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 2.生成一个get请求
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : param.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		UrlEncodedFormEntity urlUnicodeFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
		httpPost.setEntity(urlUnicodeFormEntity);
		CloseableHttpResponse response = null;
		try {
			// 3.执行get请求并返回结果
			response = httpclient.execute(httpPost);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String result = null;
		try {
			// 4.处理结果，这里将结果返回为字符串
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
	
	/**
	 * post请求
	 */
	public static JSONObject doPostStr(String url,String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = jsonObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
