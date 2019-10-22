package com.wgb.utils.util.serializable;

import java.io.*;

/**
 * 文件中写对象序列号测试
 * @author INNERPEACE
 * @date 2019/10/10 10:46
 */
public class ObjectStreamTest {

	public static void main(String[] args) {
		Employee xiaoJieJie = new Employee("beautifulXiaoJieJie", 50000, 2018, 8, 1);
		Manager mrA = new Manager("Mr.A", 60000, 2017, 6, 1);
		mrA.setSecretary(xiaoJieJie);
		Manager mrB = new Manager("Mr.B", 59999, 2017, 6, 2);
		mrB.setSecretary(xiaoJieJie);

		Employee[] staff = new Employee[3];
		staff[0] = mrA;
		staff[1] = xiaoJieJie;
		staff[2] = mrB;

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\courseStudy\\wgb\\employee.dat"));
			oos.writeObject(staff);
			oos.close();

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:\\courseStudy\\wgb\\employee.dat"));
			Employee[] newStaff = (Employee[]) ois.readObject();
			ois.close();

			newStaff[1].raiseSalary(10);

			for (Employee e : newStaff) {
				System.out.println(e);
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
