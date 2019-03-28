package com.wgb.utils.util.typeconversion;

import com.wgb.utils.util.typeconversion.DifferentTypeConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : innerpeace
 * @date : 2018/11/12 18:32
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DifferentTypeConversionUtilTest {
    /**
     * object类型转int类型测试
     */
    @Test
    public void objectToIntTest() {
        Object o1 = "测试1";
        Object o2 = "ceshier";
        Object o3 = "32.1";
        Object o4 = "32.424.23";
        Object o5 = "测试34.大量端口.dk76.dj、打卡89.8";
        Object o6 = "+43";
        Boolean o7 = true;
        Object o8 = "123.123a123";
        float o9 = 3.45f;
        log.info("测试1结果：{}", DifferentTypeConversionUtil.objectToInt(o1));
        log.info("测试2结果：{}", DifferentTypeConversionUtil.objectToInt(o2));
        log.info("测试3结果：{}", DifferentTypeConversionUtil.objectToInt(o3));
        log.info("测试4结果：{}", DifferentTypeConversionUtil.objectToInt(o4));
        log.info("测试5结果：{}", DifferentTypeConversionUtil.objectToInt(o5));
        log.info("测试6结果：{}", DifferentTypeConversionUtil.objectToInt(o6));
        log.info("测试7结果：{}", DifferentTypeConversionUtil.objectToInt(o7));
        log.info("测试8结果：{}", DifferentTypeConversionUtil.objectToInt(o8));
        log.info("测试9结果：{}",DifferentTypeConversionUtil.objectToInt(o9));
    }

    @Test
    public void formatMoneyTest() {
        double amount = 456.786;
        double v = DifferentTypeConversionUtil.formatMoneyToDouble(amount, 2);
        log.info("formatMoney:{}", v);
        double v1 = DifferentTypeConversionUtil.formatNumber(amount, 2);
        log.info("formatNumber:{}", v1);
        double amount1 = 256.78678;
        String formatMoneyToString = DifferentTypeConversionUtil.formatMoneyToString(amount1,"￥");
        log.info("标准化金额：{}", formatMoneyToString);
        double d0 = 5_451_345.56;
        log.info("doubleToString：{}",DifferentTypeConversionUtil.doubleToString(d0));
        log.info("小写人民币转大写：{}", DifferentTypeConversionUtil.convertToChineseNumber(d0));
    }
}
