package com.common.filter;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with XssHttpServletRequestWrapper
 * User: pual
 * Date: 2016/4/26
 * Desc:
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getQueryString() {
        return StringEscapeUtils.unescapeEcmaScript(super.getQueryString());
    }

    @Override
    public String getHeader(String name) {
        return StringEscapeUtils.unescapeEcmaScript(super.getHeader(name));
    }

    @Override
    public String getParameter(String name) {
        return StringEscapeUtils.unescapeEcmaScript(super.getParameter(name));
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> temp = super.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iterator= temp.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String[]> entry = iterator.next();
            String[] values = entry.getValue();
            for(int i=0;i<values.length;i++){
                values[i]=StringEscapeUtils.escapeEcmaScript(values[i]);
            }
        }
        return super.getParameterMap();
    }
}
