package com.tmh.hessian.service;

public interface TokenGenerator {
	
	 	public String generatorToken(String userName);  
     
	    public boolean validateToken(String token); 
}
