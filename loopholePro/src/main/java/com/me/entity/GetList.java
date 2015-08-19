package com.me.entity;

import java.util.HashSet;
import java.util.Set;

/** 
 * @author liujun E-mail: 
 * @version 创建时间：2015年8月16日 下午4:52:14 
 */
public class GetList {
	public static void main(String[] args) {
		Set<String> set=new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("a");
		for(String string : set){
			System.out.println(string);
		}
	}
}
