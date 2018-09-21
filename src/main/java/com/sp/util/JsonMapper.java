package com.sp.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonMapper {

    private static ObjectMapper m = new ObjectMapper();
    private static JsonFactory jf = new JsonFactory();

    public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) {
        Object object = null;
        try {
            object = m.readValue(jsonAsString, pojoClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static <T> Object fromJson(FileReader fr, Class<T> pojoClass) {
        Object object = null;
        try {
            object = m.readValue(fr, pojoClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String toJson(Object pojo, boolean prettyPrint) {
        StringWriter sw = new StringWriter();
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(sw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            m.getSerializationConfig().setDateFormat(df);
            m.writeValue(jg, pojo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) {
        JsonGenerator jg = null;
        try {
            jg = jf.createJsonGenerator(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (prettyPrint) {
            jg.useDefaultPrettyPrinter();
        }
        try {
            m.writeValue(jg, pojo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
