package com.wx.util;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


public class SortUtils {
	public static String sortByFword(HttpServletRequest request)
	{
		Map<String, String[]> paramMap = request.getParameterMap();
		
		int index = 0;
		
		ArrayList<String> tempArray = new ArrayList();
		  for (String key : paramMap.keySet())
		  {
			  if(key.equals("sign"))
				  continue;
			  Object v = paramMap.get(key);
			  String vv = ((String[])v)[0];
			  if(vv.length() == 0)
				  continue;
			  tempArray.add(key+"="+vv);
			 // strArray[index++] =  new String(key+"="+vv);
			
		}
		 String[] strArray = new String[tempArray.size()];//[ad,c,d]
		 for(int i=0;i<tempArray.size();i++)
		 {
			 strArray[i] = tempArray.get(i);
		 }
		Collator comparator = Collator.getInstance(java.util.Locale.ENGLISH);
		Arrays.sort(strArray,comparator);
		String param2 = "";
		for(int i=0;i<strArray.length;i++){
			if(i>0){
				param2=param2+"&";
			}
			param2=param2+strArray[i];
		}
		/*for(int i=0;i<str.length-1;i++){
			for(int j=0;j<str.length-i-1;j++){
				String temp = str[j];
				str[j]=str[j+1];
				str[j+1]=temp;
			}
		}*/
		return param2;
	}
	public static void main(String args[]){
		
		HashMap< String, String> map = new HashMap<String, String>();
		map.put("userid","1");
		map.put("pagesize", "10");
		map.put("topageno", "1");
		
		//String param2=SortUtils.sortByFword(map);
		//System.out.println(param2);
	}
}
