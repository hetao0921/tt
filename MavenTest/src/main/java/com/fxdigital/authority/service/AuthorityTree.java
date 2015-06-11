/**
 * 
 */
package com.fxdigital.authority.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fxdigital.authority.bean.Auth;

/**
 * @author lizehua
 *
 */
@Component
public class AuthorityTree {
	public List<Auth> create(int n,int centerID ){
		List<Auth> list=new ArrayList<Auth>();
		Auth root=new Auth();
		root.setCenterID(Integer.toString(centerID));
		root.setParentID(Integer.toString(centerID));
		list.add(root);
		for (int i = 1; i < 1<<(n-1); i++) {
			
			Auth auth=list.get(i-1);
			Auth left=new Auth();
			Auth right=new Auth();
			centerID=centerID+1;
			left.setParentID(auth.getCenterID());
			left.setCenterID(Integer.toString(centerID));
			centerID=centerID+1;
			right.setCenterID(Integer.toString(centerID));
			right.setParentID(auth.getCenterID());
			
			list.add(left);
			list.add(right);
			
		}
		return list;
	}
	public static void main(String[] args) {
		AuthorityTree tree=new AuthorityTree();
		 List<Auth> list=	tree.create(3, 1001);
		 for (Auth auth : list) {
			System.out.println(auth.getCenterID()); 
			System.out.println(auth.getParentID());
			
		}
	}

}
