/**
 * 
 */
package com.fxdigital.authority.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fxdigital.authority.service.AuthorityService;

/**
 * @author lizehua
 *
 */
@Component
public class TestWeb {
	@Autowired
	private AuthorityService authorityService;
	
	public void insert(){
//		authorityService.insert();
	}

}
