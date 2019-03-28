package com.wgb.utils.util.math;

import com.wgb.utils.util.math.combination.C;

import java.math.BigDecimal;
import java.util.Random;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

/**
 * @author INNERPEACE
 * @date 2019/1/13 12:15
 **/
public class Test {

    public static double probability(int sum, int select1, int select2) {
        BigDecimal numerator = new C(sum, select1).getValueByFormula().multiply(new C(sum - select1, select2 - select1).getValueByFormula());
        BigDecimal denominator = new C(sum, select1).getValueByFormula().multiply(new C(sum, select2).getValueByFormula());
        return numerator.divide(denominator, 1000, ROUND_HALF_DOWN).doubleValue();
        // return numerator.divide(denominator);
    }

    public static double probability2(int sum, int select1, int select2) {
        BigDecimal numerator = new C(select2, select1).getValueByFormula();
        BigDecimal denominator = new C(sum, select1).getValueByFormula();
        return numerator.divide(denominator, 1000, ROUND_HALF_DOWN).doubleValue();
        // return numerator.divide(denominator);
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Random random = new Random();
            //　范围在[100,200)之间
            int sum = random.nextInt(100) + 100;
            //　范围在[10,30)之间
            int select1 = random.nextInt(20) + 10;
            //　范围在[70,100)之间
            int select2 = random.nextInt(30) + 70;
            if (probability(sum, select1, select2) == probability2(sum, select1, select2)) {
                System.out.println(i + "1[" + sum + "," + select1 + "," + select2 + "]:" + probability(sum, select1, select2));
                System.out.println(i + "2[" + sum + "," + select1 + "," + select2 + "]:" + probability2(sum, select1, select2));
            } else {
                System.out.println("出现不等情况");
                return ;
            }
           /*
           所求：先选七个的情况，然后五个在七个里面的情况C(11, 7) * C(7, 5)
           总数：选七个的情况，再选五个的情况。C(11, 7) * C(11, 5)

            C(7, 5) * C(11, 7)      C(11, 5) * C(6, 2)     C(6,4)    C(6,5) + C(6,4)
            ------------------     ---------------------   C(11,4)   C(11,5) + C(11,4)
            C(11, 5) * C(11, 7)     C(11, 5) * C(11, 7)

            C(7, 5) * C(11, 7)
            ------------------
            C(11, 5) * C(11, 7)

            */
        }
        /*int m = 115;
        int select1 = 56;
        int select2 = 77;
        // System.out.println("===" + new C(m, select1).getValueByFormula());
        System.out.println("1:" + probability(m, select1, select2));
        System.out.println("2:" + probability2(m, select1, select2));*/

    }
}
