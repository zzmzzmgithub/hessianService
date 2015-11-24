package com.tmh.hessian.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmh.hessian.service.AddService;
import com.tmh.hessian.service.TokenGenerator;

@Service
public class AddServiceImpl implements AddService{
	
	@Autowired
	TokenGenerator tokenGenerator;
	
	public int add(int a, int b,String token) {
		if(tokenGenerator.validateToken(token)){
			 System.out.println("add(int a, int b) is invoked");  
		     return a + b;  
		 }else{
			 System.out.println("你没有访问权限");
			 return 0;
		 } 
	}

	
}
