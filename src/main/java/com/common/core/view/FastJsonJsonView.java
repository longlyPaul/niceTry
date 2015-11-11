package com.common.core.view;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xupeng on 14-11-16.
 */
public class FastJsonJsonView extends com.alibaba.fastjson.support.spring.FastJsonJsonView{
    private Set<String> renderedAttributes;

    public void setRenderedAttributes(Set<String> renderedAttributes)
    {
        this.renderedAttributes=renderedAttributes;
    }

    protected Object filterModel(Map<String, Object> model) {
        Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = !CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        if (result.size() == 1) {
            return result.values().iterator().next();
        }
        return result;
    }

}
