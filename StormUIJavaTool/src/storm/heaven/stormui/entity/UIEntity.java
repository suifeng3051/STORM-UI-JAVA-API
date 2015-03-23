package storm.heaven.stormui.entity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/*******************************************************************************
 * 版权信息：博睿宏远科技发展有限公司
 * Copyright: Copyright (c) 2014 博睿宏远科技发展有限公司,Inc.All Rights Reserved.
 * BidPlanStructForm.java Created on 2015-3-23
 * Author: <a href=mailto:wanghd@bonree.com>王厚达</a>
 * @Title: UIEntity.java
 * @Package bonree.utils
 * Description:
 * Version: 1.0
 ******************************************************************************/
public abstract class UIEntity {
	public abstract void parse(String result) throws JsonParseException, JsonMappingException, IOException;
	public void parse(String json,Object obj) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, String> map = mapper.readValue(json, new TypeReference<Object>() {
		});
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				// 若是final类型字段，不能改变其值
				boolean isFinal = Modifier.isFinal(fields[i].getModifiers());
				if (isFinal) {
					continue;
				}
				Object value = map.get(fields[i].getName());
				fields[i].set(obj, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public void parse(HashMap<String, String> map,Object obj) throws JsonParseException, JsonMappingException, IOException {
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			try {
				// 若是final类型字段，不能改变其值
				boolean isFinal = Modifier.isFinal(fields[i].getModifiers());
				if (isFinal) {
					continue;
				}
				Object value = map.get(fields[i].getName());
				fields[i].set(obj, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
