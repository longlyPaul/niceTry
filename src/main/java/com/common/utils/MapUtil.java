package com.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体类转化成Map集合
 * @author HEQUAN
 *
 */
public class MapUtil {

	public static Map<String,Object> ConvertObjToMap(Object obj){
        Map<String,Object> reMap = new HashMap<String,Object>();
        if (obj == null){
        	return null;
        }
         
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
        	for(int i=0;i<fields.length;i++){
        		try {
        			Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    if(o != null && !o.equals("")){
                    	reMap.put(fields[i].getName(), o);
                    }
        		} catch (NoSuchFieldException e) {
                    e.printStackTrace();
        		} catch (IllegalArgumentException e) {
        			e.printStackTrace();
        		} catch (IllegalAccessException e) {
        			e.printStackTrace();
        		}
        	}
        } catch (SecurityException e) {
        	e.printStackTrace();
        } 
        return reMap;
    }
}
