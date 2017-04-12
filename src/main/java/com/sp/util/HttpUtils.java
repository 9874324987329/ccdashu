package com.sp.util;


import com.google.gson.Gson;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;

public class HttpUtils {

    public static String regSql = "exec|insert|select|delete|update|union|master|truncate|declare|performance_schema|";//防sql过滤


    public static void write(HttpServletResponse response, String str) {
        try {
            response.getWriter().write(str);
        } catch (Exception e) {

        }
    }

    public static void writeToJson(HttpServletResponse response, String flag) {
        try {
            String msg = "";
            if (flag.equals("1")) {
                msg = "操作成功";
            } else if (flag.equals("0")) {
                msg = "操作失败";
            } else {
                msg = flag;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.element("msg", msg);
            jsonObject.element("flag", flag);
            Gson gson = new Gson();
            String json = gson.toJson(jsonObject);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {

        }
    }

    public static void writeToJson(HttpServletResponse response, Object object, String flag) {
        try {
            String msg = "";
            if (flag.equals("1")) {
                msg = "操作成功";
            }
            if (flag.equals("0")) {
                msg = "操作失败";
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.element("data", object);
            jsonObject.element("msg", msg);
            jsonObject.element("flag", flag);
            Gson gson = new Gson();
            String json = gson.toJson(jsonObject);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {

        }
    }

    public static void writeToJson(HttpServletResponse response, Object object) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(object);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {

        }
    }

    public static String getString(HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        if (StringUtil.isEmpty(value)) {
            value = "";
        } else {
            value = value.replaceAll(regSql, "");
        }
        request.setAttribute(key, value);
        return value;
    }


    public static Integer getInteger(HttpServletRequest request, String key) {
        int _v = 0;
        try {
            String value = request.getParameter(key);
            if (StringUtil.isEmpty(value)) {
                value = "0";
            } else {
                _v = Integer.valueOf(value.replaceAll(regSql, ""));
            }
        } catch (Exception e) {
        }
        request.setAttribute(key, _v);
        return _v;
    }

    public static ArrayList<String> getStringList(HttpServletRequest request, String key) {
        ArrayList<String> list = new ArrayList();
        try {
            String[] res = request.getParameterValues(key);
            if (null != res && res.length > 0) {
                for (String value : res) {
                    value = value.replaceAll(regSql, "");
                    list.add(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static HttpServletResponse downloadFile(String path, HttpServletResponse response) {

        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
//            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }
}
