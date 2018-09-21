package com.sp.util;

import com.sp.bean.DomBean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ccdashu on 17/5/8.
 */
public class DataUtils {

    private static  final String detail = "detail";
    private static final String list = "list";

    public static Map<String, Object> formatData(List<DomBean> domBeans) {
        try {
            LinkedHashMap<String, Object> objsMap = new LinkedHashMap();
            LinkedHashMap<String, Object> detailMap = new LinkedHashMap();
            for (DomBean domBean : domBeans) {
                if (detailMap.get(domBean.getKey()) == null) {
                    detailMap.put(domBean.getKey(), domBean.getText());
                } else {
                    detailMap.remove(domBean.getKey());
                }
            }
            objsMap.put(detail, detailMap);
            return objsMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
