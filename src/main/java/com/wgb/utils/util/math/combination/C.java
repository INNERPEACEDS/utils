package com.wgb.utils.util.math.combination;

import com.wgb.utils.util.math.factorial.Factorial;

import java.math.BigDecimal;

/**
 * 无序排列组合（C(n, m)）
 * @author INNERPEACE
 * @date 2019/1/13 12:36
 **/
public class C {
    private BigDecimal n;
    private BigDecimal m;

    public C(int n, int m) {
        this.n = new BigDecimal(n);
        this.m = new BigDecimal(m);
    }

    public C(String n, String m) {
        this.n = new BigDecimal(n);
        this.m = new BigDecimal(m);
    }

    public C(BigDecimal n, BigDecimal m) {
        this.n = n;
        this.m = m;
    }

    public BigDecimal getValueByFormula() {
        return getNumeratorByRecursion().divide(getDenominatorByRecursion());
    }

    private BigDecimal getNumeratorByRecursion() {
        return Factorial.recursion(n);
    }

    private BigDecimal getDenominatorByRecursion() {
        return Factorial.recursion(n.subtract(m)).multiply(Factorial.recursion(m));
    }


    public BigDecimal getValue() {
        return getNumerator().divide(getDenominator());
    }

    private BigDecimal getNumerator() {
        return new BigDecimal(Factorial.bigNumber(n.intValue()));
    }

    private BigDecimal getDenominator() {
        return new BigDecimal(Factorial.bigNumber(m.intValue()).multiply(Factorial.bigNumber(n.subtract(m).intValue())));
    }

    public BigDecimal getM() {
        return m;
    }

    public void setM(BigDecimal m) {
        this.m = m;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }
}
