package com.wgb.utils.mode.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : innerpeace
 * @date : 2018/11/22
 */
@Slf4j
public class Test4  extends Thread{
  public static void main(String[] args) {
     Test4 test  = new Test4();
     Test4 test1  = new Test4();
     Singleton4  sing  = Singleton4.getInstance();
     log.info("sing:{}", sing);
     test.start();
     test1.start();
     
}
  @Override
	public void run() {
	  Singleton4 test = Singleton4.getInstance();
	  log.info("test:{}", test);
	}
}
