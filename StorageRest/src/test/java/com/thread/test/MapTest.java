package com.thread.test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapTest {
	private static ConcurrentMap<Integer,String> hintMap=new ConcurrentHashMap<Integer,String>();

	public void add(int key,String value){
		hintMap.put(key, value);
	}
	
	public static void main(String[] args) {
		int a=1;
		String b="tt";
		MapTest mapTest=new MapTest();
		mapTest.add(a, b);
		mapTest.add(2,"aa");
		Iterator iterator = hintMap.keySet().iterator();
		while(iterator.hasNext()) {
			int key=Integer.valueOf(iterator.next().toString());
			System.out.println("key:"+key);
			System.out.println("1aaa "+hintMap.get(key));
			hintMap.remove(a,b);
//			iterator.next();
		}
		mapTest.add(a,b);
		iterator = hintMap.keySet().iterator();
		while(iterator.hasNext()) {
			System.out.println("2aaa "+iterator.hasNext()+hintMap.get(iterator.next()));
		}
		
		
		Set set = hintMap.entrySet() ;
		java.util.Iterator it = hintMap.entrySet().iterator();
		while(it.hasNext()){
		java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
		// entry.getKey() 返回与此项对应的键
		// entry.getValue() 返回与此项对应的值
		System.out.println(entry.getKey());
		System.out.println(entry.getValue());
		hintMap.remove(entry.getKey());
		System.out.println(hintMap);
		} 
		
	}
}
