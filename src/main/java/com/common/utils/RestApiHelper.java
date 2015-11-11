package com.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by henry on 3/11/14.
 */
@Repository
public class RestApiHelper {

//	public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final Locale DATE_LOCALE = Locale.US;

	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, DATE_LOCALE);

	private static String API_URL;
	
	@SuppressWarnings("static-access")
	public void setApiUrl(String apiUrl) {
		this.API_URL = apiUrl;
	}

	public static String genPath(String path, Map<String, Object> attributes) {
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			Object value = entry.getValue();
			path = path.replaceAll("\\{" + entry.getKey() + "\\}", value instanceof Date ? sdf.format(value) : String.valueOf(value));
		}
		return path;
	}

	public static String genPath(String path, Object... attributes) {
		for (Object attribute : attributes) {
			path = path.replaceAll("\\{.+\\}", String.valueOf(attribute));
		}
		return path;
	}

	private static String parseParameters(String path, Map<String, Object> parameters) {
		if (parameters != null && parameters.size() != 0) {
			Form form = Form.form();
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
				if (entry.getValue() instanceof Map) {
					for (Map.Entry<String, Object> filterEntry : ((Map<String,Object>)entry.getValue()).entrySet()) {
						form.add("filter['"+filterEntry.getKey()+"']",filterEntry.getValue().toString());
					}
				} else {
					Object value = entry.getValue();
					form.add(entry.getKey(), value instanceof Date ? sdf.format(value) : String.valueOf(value));
				}
			}
			String paramString = URLEncodedUtils.format(form.build(), "UTF-8");
			return path + (path.indexOf('?') == -1 ? '?' : '&') + paramString;
		} else {
			return path;
		}
	}

	public static String baseGet(String path, Map<String, Object> parameters) throws IOException {
		String url = API_URL + parseParameters(path, parameters);
		return Request.Get(url).execute().returnContent().asString();
	}

	private static String baseDelete(String path) throws IOException {
		String url = API_URL + path;
		return Request.Delete(url).execute().returnContent().asString();
	}

	public static String basePost(String path, Map<String, Object> parameters) throws IOException {
		String url = API_URL + path;
		Request request = Request.Post(url);
		if (parameters != null && parameters.size() != 0) {
			Form form = Form.form();
			for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                if(entry.getValue() instanceof List){
                    for(int i=0;i<((List) entry.getValue()).size();i++){
                        Map<String,Object> temp=MapUtil.ConvertObjToMap(((List) entry.getValue()).get(i));
                        for (Map.Entry<String, Object> entry2 : temp.entrySet()) {
                            form.add(entry.getKey()+"["+i+"]."+entry2.getKey(), entry2.getValue() instanceof Date ? sdf.format(entry2.getValue()) : String.valueOf(entry2.getValue()));
                        }
                    }
                }else {
                    Object value = entry.getValue();
                    form.add(entry.getKey(), value instanceof Date ? sdf.format(value) : String.valueOf(value));
                }
            }
            request = request.bodyForm(form.build(), Charset.forName("UTF-8"));
		}
		return request.execute().returnContent().asString();
	}

	private static String basePut(String path, Map<String, Object> parameters) throws IOException {
		String url = API_URL + parseParameters(path, parameters);
		return Request.Put(url).execute().returnContent().asString();
	}

	public static <T> T get(String path, Class<T> type) throws IOException {
        String response = baseGet(path, null);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T get(String path, TypeReference<T> type) throws IOException {
        String response = baseGet(path, null);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T get(String path, Map<String, Object> parameters, Class<T> type) throws IOException {
        String response = baseGet(path, parameters);
        if (CheckUtil.isEmpty(response) || response.equals("{}")) {
            return null;
        }
        return JSON.parseObject(response, type);
	}

	public static <T> T get(String path, Map<String, Object> parameters, TypeReference<T> type) throws IOException {
        String response = baseGet(path, parameters);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T delete(String path, Class<T> type) throws IOException {
		return JSON.parseObject(baseDelete(path), type);
	}

	public static <T> T delete(String path, TypeReference<T> type) throws IOException {
		return JSON.parseObject(baseDelete(path), type);
	}

	public static <T> T post(String path, Map<String, Object> parameters, Class<T> type) throws IOException {
        String response = basePost(path, parameters);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T post(String path, Map<String, Object> parameters, TypeReference<T> type) throws IOException {
        String response = basePost(path, parameters);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T put(String path, Map<String, Object> parameters, Class<T> type) throws IOException {
        String response = basePut(path, parameters);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

	public static <T> T pub(String path, Map<String, Object> parameters, TypeReference<T> type) throws IOException {
        String response = basePut(path, parameters);
        if(CheckUtil.isEmpty(response) || response.equals("{}")){
            return null;
        }
		return JSON.parseObject(response, type);
	}

    public static <T> T postList(String path,Object object,Class<T> type) throws IOException {
        String response = baseList(path,object);
        return JSON.parseObject(response, type);
    }

    public static <T> T postList(String path,Object object,TypeReference<T> type) throws IOException {
        String response = baseList(path, object);
        return JSON.parseObject(response, type);
    }

    public static String baseList(String path,Object object) throws IOException {
        String url = API_URL + path;
        Request request = Request.Post(url);
        request = request.bodyString(JSON.toJSONString(object), ContentType.create("text/plain", "UTF-8"));
        Executor executor = Executor.newInstance();
        String response = executor.execute(request).returnContent().asString();
        return response;
    }

}
