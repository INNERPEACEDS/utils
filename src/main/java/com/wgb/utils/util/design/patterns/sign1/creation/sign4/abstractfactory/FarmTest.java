package com.wgb.utils.util.design.patterns.sign1.creation.sign4.abstractfactory;

import javax.swing.*;
import java.awt.*;

/**
 * @author INNERPEACE
 * @date 2019/6/19 15:30
 **/
public class FarmTest {
	public static void main(String[] args) {
		try {
			Farm f;
			Animal a;
			Plant p;
			f = (Farm) ReadXML.getObject();
			a = f.newAnimal();
			p = f.newPlant();
			a.show();
			p.show();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/**
 * 抽象产品：动物类
 */
interface Animal
{
	public void show();
}

/**
 * 具体产品：马类
 */
class Horse implements Animal {
	JScrollPane sp;
	JFrame jf = new JFrame("抽象工厂模式测试");

	public Horse() {
		Container contentPane = jf.getContentPane();
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
		sp = new JScrollPane(p1);
		contentPane.add(sp, BorderLayout.CENTER);
		JLabel l1 = new JLabel(new ImageIcon("src/main/resources/static/img/horse.png"));
		p1.add(l1);
		jf.pack();
		jf.setVisible(false);
		// 用户点击窗口关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void show() {
		jf.setVisible(true);
	}
}

/**
 * 具体产品：牛类
 */
class Cattle implements Animal {

	JScrollPane sp;
	JFrame jf = new JFrame("抽象工厂模式测试");

	public Cattle() {
		Container contentPane = jf.getContentPane();
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("动物：牛"));
		sp = new JScrollPane(p1);
		contentPane.add(sp, BorderLayout.CENTER);
		// JLabel l1 = new JLabel(new ImageIcon("target/classes/static/img/ox.png"));
		JLabel l1 = new JLabel(new ImageIcon("src/main/resources/static/img/ox.png"));
		p1.add(l1);
		jf.pack();
		jf.setVisible(false);
		// 用户点击窗口关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void show() {
		jf.setVisible(true);
	}
}


/**
 * 抽象产品：植物类
 */
interface Plant {
	public void show();

}

/**
 * 具体产品：水果类
 */
class Fruitage implements Plant
{
	JScrollPane sp;
	JFrame jf=new JFrame("抽象工厂模式测试");
	public Fruitage()
	{
		Container contentPane=jf.getContentPane();
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(1,1));
		p1.setBorder(BorderFactory.createTitledBorder("植物：水果"));
		sp=new JScrollPane(p1);
		contentPane.add(sp, BorderLayout.CENTER);
		// JLabel l1=new JLabel(new ImageIcon("target/classes/static/img/P_Fruitage.jpg"));
		JLabel l1=new JLabel(new ImageIcon("src/main/resources/static/img/P_Fruitage.jpg"));
		p1.add(l1);
		jf.pack();
		jf.setVisible(false);
		// 用户点击窗口关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void show() {
		jf.setVisible(true);
	}
}

/**
 * 具体产品：蔬菜类
 */
class Vegetables implements Plant {
	JScrollPane sp;
	JFrame jf = new JFrame("抽象工厂模式测试");

	public Vegetables() {
		Container contentPane = jf.getContentPane();
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory.createTitledBorder("植物：蔬菜"));
		sp = new JScrollPane(p1);
		contentPane.add(sp, BorderLayout.CENTER);
		// JLabel l1 = new JLabel(new ImageIcon("target/classes/static/img/vegetables.png"));
		JLabel l1 = new JLabel(new ImageIcon("src/main/resources/static/img/vegetables.png"));
		p1.add(l1);
		jf.pack();
		jf.setVisible(false);
		// 用户点击窗口关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void show() {
		jf.setVisible(true);
	}
}

/**
 * 抽象工厂：农场类
 */
interface Farm {
	public Animal newAnimal();
	public Plant newPlant();
}


/**
 * 具体工厂：北京郊区农场类
 */
class SGfarm implements Farm {
	@Override
	public Animal newAnimal() {
		System.out.println("新牛出生！");
		return new Cattle();
	}

	@Override
	public Plant newPlant() {
		System.out.println("蔬菜长成！");
		return new Vegetables();
	}
}

/**
 * 具体工厂：天津郊区农场类
 */
class SRfarm implements Farm {
	@Override
	public Animal newAnimal() {
		System.out.println("新马出生！");
		return new Horse();
	}

	@Override
	public Plant newPlant() {
		System.out.println("水果长成！");
		return new Fruitage();
	}
}