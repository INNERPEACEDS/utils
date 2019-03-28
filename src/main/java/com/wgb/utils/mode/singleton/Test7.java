package com.wgb.utils.mode.singleton;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : innerpeace
 * @date : 2018/11/22
 */
@Slf4j
public class Test7 {
	
	public static void main(String[] args) throws Exception {
		
		Singleton7 s1 = Singleton7.getInstance();
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
	    ObjectOutputStream oos = new ObjectOutputStream(os);  
	    oos.writeObject(s1);  
	          
	    InputStream is = new ByteArrayInputStream(os.toByteArray());  
	    ObjectInputStream ois = new ObjectInputStream(is);  
	    Singleton7 obj = (Singleton7) ois.readObject();
	    log.info("s1==obj为:{}",s1==obj);
	}
	
}
