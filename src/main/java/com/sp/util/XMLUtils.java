package com.sp.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.xu
 * User:徐强
 * Date:2017/2/24
 * Time:15:14
 * 用途:
 */

public class XMLUtils {

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map<String,String> doXMLParse(String strxml) throws JDOMException,
            IOException
    {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml))
        {
            return null;
        }

        Map<String,String> m = new HashMap<String,String>();

        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while (it.hasNext())
        {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty())
            {
                v = e.getTextNormalize();
            }
            else
            {
                v = XMLUtils.getChildrenText(children);
            }

            m.put(k, v);
        }

        // 关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    public static String getChildrenText(List children)
    {
        StringBuffer sb = new StringBuffer();
        if (!children.isEmpty())
        {
            Iterator it = children.iterator();
            while (it.hasNext())
            {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("&lt;" + name + "&gt;");
                if (!list.isEmpty())
                {
                    sb.append(XMLUtils.getChildrenText(list));
                }
                sb.append(value);
                sb.append("&lt;/" + name + "&gt;");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws JDOMException, IOException
    {
        String xml = " <xml><appid><![CDATA[wxe34d871de7c26207]]></appid>					 <bank_type><![CDATA[CFT]]></bank_type>					 <cash_fee><![CDATA[1]]></cash_fee>					 <fee_type><![CDATA[CNY]]></fee_type>					 <is_subscribe><![CDATA[Y]]></is_subscribe>					 <mch_id><![CDATA[1267108701]]></mch_id>					 <nonce_str><![CDATA[7b502f64-793f-44b3-89c5-ff2143]]></nonce_str>					 <openid><![CDATA[ofOVUswj_Y7Jap4Iriz78ju7aQXw]]></openid>					 <out_trade_no><![CDATA[3_1441958735]]></out_trade_no>					 <result_code><![CDATA[SUCCESS]]></result_code>					 <return_code><![CDATA[SUCCESS]]></return_code>					 <sign><![CDATA[EF9D65EDFBFE274280EC7FA3E986E55A]]></sign>					 <time_end><![CDATA[20150911160543]]></time_end>					 <total_fee>1</total_fee>					 <trade_type><![CDATA[JSAPI]]></trade_type>					 <transaction_id><![CDATA[1001060824201509110847636865]]></transaction_id>					 </xml>";
        Map<String, String> map = XMLUtils.doXMLParse(xml);
        String appid = map.get("appid");
        String mechid = map.get("mch_id");
        String resultcode = map.get("result_code");
        if ("SUCCESS".equals(resultcode))
        {
            String[] orderids = map.get("out_trade_no").split("_");
            String orderid = orderids[0];
            System.out.println("-------" + appid + "-----------");
            System.out.println("-------" + mechid + "-----------");
            System.out.println("-------" + orderids[0] + "-----------");


        }
    }

}
