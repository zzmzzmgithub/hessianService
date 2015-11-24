package com.tmh.hessian.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.DigestUtils;

import com.tmh.hessian.service.TokenGenerator;



public class TokenGeneratorImpl implements TokenGenerator{

	Map<String, Date> tokenStore = new ConcurrentHashMap<String, Date>();  
    
    /** 
     * 客户端发送请求得到token. 
     * @param userName 
     * @return 
     */  
    public String generatorToken(String userName){  
        Date time = new Date();  
        try {  
            byte[] b =  (time + DigestUtils.md5DigestAsHex(userName.getBytes())).getBytes("utf-8"); 
            String token = DigestUtils.md5DigestAsHex(b);   
            tokenStore.put(token,time);//存储这个时间点的token  
            return token;  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return userName;  
    }  
      
    /** 
     * 服务器在接收到请求时验证token,并把刚才的Token设置为失效 
     * @param token 
     * @return 
     */  
    public boolean validateToken(String token){  
        if(tokenStore.containsKey(token)){  
            Date time= tokenStore.get(token);  
            Date normal = new Date();  
            if(normal.getTime() - time.getTime() > 100*1000){  
                //日志进退时  
                return false;  
            }  
            tokenStore.remove(token);  
        }else{  
            //日志，没有权限  
            return false;  
        }  
        return true;  
    } 
	
}
