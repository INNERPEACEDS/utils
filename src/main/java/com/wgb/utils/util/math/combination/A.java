package com.wgb.utils.util.math.combination;

import com.wgb.utils.util.math.factorial.Factorial;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 有序排列计算的值（A（n, m））
 * @author INNERPEACE
 * @date 2019/1/13 12:18
 **/
public class A {
    private BigDecimal m;
    private BigDecimal n;

    A(int m, int n) {
        this.m = new BigDecimal(m);
        this.n = new BigDecimal(n);
    }

    A(String m, String n) {
        this.m = new BigDecimal(m);
        this.n = new BigDecimal(n);
    }

    A(BigDecimal m, BigDecimal n) {
        this.m = m;
        this.n = n;
    }

    public BigDecimal getValueByFormula() {
        return getNumeratorByRecursion().divide(getDenominatorByRecursion());
    }

    private BigDecimal getNumeratorByRecursion() {
        return Factorial.recursion(n);
    }

    private BigDecimal getDenominatorByRecursion() {
        return Factorial.recursion(n.subtract(m));
    }

    public BigDecimal getValue() {
        return new BigDecimal(getNumerator().divide(getDenominator()));
    }

    private BigInteger getNumerator() {
        return Factorial.bigNumber(n.intValue());
    }

    private BigInteger getDenominator() {
        return Factorial.bigNumber(n.subtract(m).intValue());
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
