package com.wgb.utils.test.queue.listener;

import java.util.Scanner;

/**
 * 自定义消息队列测试类
 * @author INNERPEACE
 * @date 2019/11/14 15:23
 */
public class MQTest {
	public static void main(String[] args) {
		// 获取消息队列的单例，并启动队列监听器
		PushBlockQueue.getInstance().start();
		// 循环向队列写入数据
		/**
		 * 生产者----生产消息----》入队列----监听器----通知消费者---》消费
		 */
		Scanner sc = new Scanner(System.in);
		try {
			while (true){
				String content = sc.nextLine();
				if(content.trim().equals("stop")){
					System.exit(1);
				}
				PushBlockQueue.getInstance().put(content);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
