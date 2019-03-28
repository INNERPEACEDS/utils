package com.wgb.utils.util.math.factorial;
import java.math.BigDecimal;
import java.math.BigInteger;//导入类
import java.util.ArrayList;

/**
 * @author INNERPEACE
 * @date 2019/1/13 12:39
 **/
public class Factorial {

    private static BigDecimal bd0 = new BigDecimal(0);

    private static BigDecimal bd1 = new BigDecimal(1);

    /**
     * 简单的循环计算的阶乘
     * @param num
     * @return
     */
    public static int simpleCircle(int num){
        int sum=1;
        // 判断传入数是否为负数

        if(num<0){
            // 抛出不合理参数异常
            throw new IllegalArgumentException("必须为正整数!");
        }
        // 循环num
        for(int i=1;i<=num;i++){
            // 每循环一次进行乘法运算
            sum *= i;
        }
        //返回阶乘的值
        return sum;
    }

    /**
     * 利用递归计算阶乘
     * @param num
     * @return
     */
    public static int recursionReturnInt(int num){
        int sum = 1;
        if(num < 0) {
            // 抛出不合理参数异常
            throw new IllegalArgumentException("必须为正整数!");
        }
        if(num == 1){
            // 根据条件,跳出循环
            return 1;
        } else {
            // 运用递归计算
            sum = num * recursionReturnInt(num-1);
            return sum;
        }
    }

    /**
     * 数组添加计算阶乘
     * @param num
     * @return
     */
    public static long addArray(int num){
        // 创建数组
        long[]arr=new long[21];
        arr[0]=1;

        int last=0;
        if(num>=arr.length){
            // 抛出传入的数太大异常
            throw new IllegalArgumentException("传入的值太大");
        }
        if(num < 0) {
            // 抛出不合理参数异常
            throw new IllegalArgumentException("必须为正整数!");
        }
        // 建立满足小于传入数的while循环
        while(last<num){
            // 进行运算
            arr[last+1]=arr[last]*(last+1);
            // last先进行运算，再将last的值加1
            last++;
        }
        return  arr[num];
    }

    /**
     * 利用BigInteger类计算阶乘（该方式能获取较大数值的阶乘）
     * @param num
     * @return
     */
    public static synchronized BigInteger bigNumber(int num){
        // 创建集合数组
        ArrayList list = new ArrayList();
        // 往数组里添加一个数值
        list.add(BigInteger.valueOf(1));
        for (int i = list.size(); i <= num; i++) {
            // 获得第一个元素
            BigInteger lastfact = (BigInteger) list.get(i - 1);
            // 获得下一个数组
            BigInteger nextfact = lastfact.multiply(BigInteger.valueOf(i));
            list.add(nextfact);
        }
        // 返回数组中的下标为num的值
        return (BigInteger) list.get(num);

    }

    /**
     * java程序的主入口处
     * @param args
     */
    public static void main(String []args){
        int num=5;
        int num1=23566;

        int num2 = 11;
        //调用simpleCircle
        System.out.println("简单的循环计算"+num+"的阶乘为" + simpleCircle(num));
        //调用recursion
        System.out.println("利用递归计算"+num2+"的阶乘为" + recursion(new BigDecimal(num2)));
        //调用addArray
        System.out.println("数组添加计算"+num+"的阶乘为" + addArray(num));
        //调用bigNumber
        System.out.println("利用BigInteger类计算"+num1+"的阶乘为" + bigNumber(num1));
    }

    /**
     * 使用BigDecimal计算任何精度的阶乘(该方式在进行乘的时候易发生栈溢出)
     * @param num
     * @return
     */
    public static BigDecimal recursion(BigDecimal num) {
        // System.out.println("此时num为：" + num.toString());
        if (num.compareTo(bd0) == -1) {
            throw new RuntimeException("[阶乘递归错误]-阶乘不能出现小于0的数");
        }
        BigDecimal result = bd1;
        if (!(num.compareTo(bd0) == 0 || num.compareTo(bd1) == 0)) {
            result = num.multiply(recursion(num.subtract(bd1)));
        }
        return result;
    }
}
