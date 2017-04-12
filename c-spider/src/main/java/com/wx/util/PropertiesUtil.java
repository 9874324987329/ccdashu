package com.wx.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;


public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static Map<String, String> getProperty(String filePath) {
        Map map = new HashMap();
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(filePath);
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (Exception e) {
            logger.error("{} read failed", filePath);
        }
        Set set = p.keySet();

        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            map.put(key, p.get(key));
        }
        return map;
    }

    public static void setProperty(String filePath, String key, String value) throws IOException {
        File file = new File(filePath);
        Properties properties = new Properties();
        if (!file.exists())
            file.createNewFile();
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        //修改之前必须关闭
        inputStream.close();
        OutputStream outputStream = new FileOutputStream(filePath);
        properties.setProperty(key, value);
        properties.store(outputStream, "Update '" + key + "' value");
        outputStream.close();
    }

    public static Map<String, String> getProperty(File file) {
        Map map = new HashMap();
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            logger.error("{} read failed", file);
        }
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (Exception e) {
            logger.error("{} read failed", file);
        }
        Set set = p.keySet();

        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            map.put(key, p.get(key));
        }
        return map;
    }
}