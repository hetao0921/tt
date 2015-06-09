package com.test.chain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

public class Client {

	public static void main(String[] args) {
		// // 组装责任链
		// Handler handler1 = new ConcreteHandler();
		// Handler handler2 = new ConcreteHandler();
		// handler1.setSuccessor(handler2);
		// // 提交请求
		// handler1.handleRequest();

//		ConcurrentMap<String, ConcurrentLinkedQueue<ConcurrentMap<String,String>>> wordCounts = new ConcurrentHashMap<>();
//		
//		ConcurrentLinkedQueue<ConcurrentMap<String,String>> queue=new ConcurrentLinkedQueue<>();
//		
//		ConcurrentMap<String,String> map=new ConcurrentHashMap<>();
//		
//		map.put("c", "sss");
//		map.put("b", "ddd");
//		
//		queue.add(map);
//		
//		wordCounts.put("start", queue);
//		
//		
//		ConcurrentMap<String, AtomicInteger> mapsc=new ConcurrentHashMap<>();
//		 AtomicInteger ai=new AtomicInteger(111);
//		mapsc.put("aa", ai);
//		System.out.println(mapsc.get("aa"));
//		
//		ai=new AtomicInteger(ai.incrementAndGet());
//		mapsc.put("aa", ai);
//		System.out.println("ttttt"+mapsc.get("aa"));
//		
//		
//		System.out.println(wordCounts.get("start").size());
//		System.out.println(wordCounts.get("start").poll().get("c"));
//		System.out.println(wordCounts.get("start").size());
//		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//
//		
//		
//		ConcurrentMap<String, ConcurrentMap<String,AtomicInteger>> maptt=new ConcurrentHashMap<>();
//		
//		ConcurrentMap<String,AtomicInteger> maprstp=new  ConcurrentHashMap<>();
//		maprstp.put("start", new AtomicInteger(3));
//		
//		maprstp.put("stop", new AtomicInteger(1));
//		
//		maprstp.put("handle", new AtomicInteger(4444));
//		
//		maptt.put("http://dfsa", maprstp);
//		
//		System.out.println(maptt.get("http://dfsa").get("start"));
		
		
		
		
	}

}
