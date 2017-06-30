package com.jxust.ssm.autowired;

import org.springframework.stereotype.Repository;  
  
@Repository  
public class DaoImpl implements IDao {  
	@Override
    public void sayHello() {  
        System.err.println("Say Hello From DaoImpl");  
    }

	@Override
	public void quit() {
		// TODO Auto-generated method stub
        System.err.println("quit");  

	}  
} 