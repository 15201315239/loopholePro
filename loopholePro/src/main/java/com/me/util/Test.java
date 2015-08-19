package com.me.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		/*String s1 = "123456abcde";
		String s2 = "111222bbbcc";
		System.out.println(new Test().findSame(s1, s2));*/
		/*int random = new Random().nextInt(4)+ 1;
		System.out.println(random);*/
		String s2 = ",";
		String[] ss = s2.split(",");
		System.out.println(ss.length);
//		System.out.println(s2.substring(0,s2.length()-1));
		
	}

	public String findSame(String s1, String s2) {
		Map map = new HashMap();
		Set result = new HashSet();
		char[] char1 = s1.toCharArray();
		char[] char2 = s2.toCharArray();
		for (int i = 0; i < char1.length; i++) {
			map.put("" + char1[i], char1[i]);
		}
		for (int j = 0; j < char2.length; j++) {
			if (map.get("" + char2[j]) != null) {
				result.add(char2[j]);
			}
		}
		/*for( Iterator   it = result.iterator();  it.hasNext(); )  
		{               
		    System.out.println("value="+it.next().toString());              
		}*/ 
		return result.toString();
	}
}