package com.common.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: xupeng
 * Date: 13-12-1
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class CheckUtil {
    /**
     * 判断字符串是不是null或无字符（trim后）
     * @param o
     * @return
     */
    public static Boolean isEmpty(String o) {
        return (o == null || o.trim().length() == 0);
    }

    /**
     * 判断整形是否为null或0
     *
     * @param value
     * @return
     */
    public static Boolean isEmpty(Integer value) {
        return (value == null || value == 0);
    }

    /**
     * 判断整形是否为null或0
     *
     * @param value
     * @return
     */
    public static Boolean isEmpty(Long value) {
        return (value == null || value == 0);
    }

    /**
     * 判断List是否为空
     *
     * @param list
     * @return
     */
    public static Boolean isEmpty(List<?> list) {
        return (list == null || list.size() == 0);
    }

    /**
     * 判断Map是否为空
     * @param map
     * @return
     */
    public static Boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.size() == 0);
    }

    /**
     * 判断Set是否为空
     * @param set
     * @return
     */
    public static Boolean isEmpty(Set<?> set) {
        return (set == null || set.size() == 0);
    }

    /**
     * 判断Object是否为空
     * @param o
     * @return
     */
    public static Boolean isEmpty(Object o) {
//		return o == null;
        if (o == null) {
            return true;
        }
        boolean isEmpty = false;
        if (o instanceof String) {
            isEmpty = isEmpty((String) o);
        } else if (o instanceof Integer) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof List<?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Map<?, ?>) {
            isEmpty = isEmpty((Integer) o);
        } else if (o instanceof Set<?>) {
            isEmpty = isEmpty((Integer) o);
        }

        return isEmpty;
    }

    /**
     * 判断数组是否为空
     * @param o
     * @return
     */
    public static Boolean isEmpty(Object[] o) {
        return (o == null || o.length == 0);
    }




}
