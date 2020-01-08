package com.nbst.comnutil;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class JsonUtil {

	@SuppressWarnings("unchecked")
	public static Map<String, Object> json2Map(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = mapper.readValue(json, Map.class);
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> toMap(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> map = mapper.readValue(json, Map.class);
		return map;
	}

	public static String pojoToJson(Object obj) throws JsonGenerationException,
			JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(obj);
		return json;
	}

	public static <T> T json2Pojo(String json, Class<T> toParseClass)
			throws JsonParseException, JsonMappingException, IOException {
		if (json == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, toParseClass);
	}




}
