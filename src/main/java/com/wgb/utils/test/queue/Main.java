package com.wgb.utils.test.queue;

/**
 * @author INNERPEACE
 * @date 2019/11/14 14:25
 */
public class Main {
	public static void main(String[] args) {
		//声明数据生产者
		// DataProducer dataProducer = new DataProducer();
		//定期生产数据
		DataProducer.addData2DataQueue();
		// 定期唤醒消费者消费数据
		ConsumerWakeUp.wakeUp();
	}
}
