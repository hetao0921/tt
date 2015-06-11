package com.fxdigital.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class SyncDataController {
	@RequestMapping(value = {"/waitupload"}, method = RequestMethod.POST)
	public String waitupload(){
		System.out.println("waitupload!!!!!!!!!");
		return "syncviews/waitupload";
	}
	@RequestMapping(value = {"/waitdownload"}, method = RequestMethod.POST)
	public String waitdownload(){
		System.out.println("waitdownload!!!!!!!!!");
		return "syncviews/waitdownload";
	}
	@RequestMapping(value = {"/systemset"}, method = RequestMethod.POST)
	public String systemset(){
		System.out.println("systemset!!!!!!!!!");
		return "syncviews/systemset";
	}
	@RequestMapping(value = {"/logrecord"}, method = RequestMethod.POST)
	public String logrecord(){
		System.out.println("logrecord!!!!!!!!!");
		return "syncviews/logrecord";
	}
	@RequestMapping(value = {"/waitedatabackup"}, method = RequestMethod.POST)
	public String waitedatabackup(){
		System.out.println("waitedatabackup!!!!!!!!!");
		return "syncviews/waitedatabackup";
	}
}
