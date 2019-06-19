package com.wgb.utils;

import com.wgb.utils.util.typeconversion.DifferentTypeConversionUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

/**
 * @author INNERPEACE
 * @date 2018/12/18 13:53
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOther {
    @Test
    public void exceptionTest() throws Exception {
        String str = "测试";
        String str1 = null;
        String end = null;
        try {
            end = catchExceptionMethod(str1);
            log.info("无异常继续执行，返回end的值为：{}", end);
        } catch (Exception e) {
            log.info("结束语：{}",end, e);
        }
        log.info("主函数执行完毕");
    }

    public static String catchExceptionMethod(String str) throws Exception{
		/*String.valueOf(str.length());
		System.out.println("继续运行");
		return "fanhi";*/
        /*finally一定执行；如果中断（抛出异常或者return）异常则在执行完finally之后将不再执行，不中断，将继续执行后面的语句*/
        String logInfo = "---------------------------------log-------------------------------------";
        try {
            try {
                String.valueOf(str.length());
            } catch (Exception e) {
                log.info(logInfo, e);
                log.error("内部exception", e);
                // throw new Exception("抛出内部yichang");
                return "zhi";
            } finally {
                log.info("内部finally");
            }
            log.info("内部继续执行");
        } catch (Exception e) {
            log.error("外部exception", e);
            // throw new Exception("抛出外部yichang");
        } finally {
            log.info("外围finally");
        }
        log.info("异常执行完后继续执行");
       /* try {

            String.valueOf(str.length());
        } catch (Exception e) {
            log.info(logInfo, e);
            throw new Exception("yichang");
            // return "zhi";
        } finally {
            System.out.println("测试finally");
        }*/
        return "zhixing";
    }

    @Test
    public void utilTypeconversionDifferentTypeConverdionUtilArray2ArrayListTest() {
        String[] str = {"0", "1", "2", "3", "4"};
        ArrayList<Object> arrayList = DifferentTypeConversionUtil.array2ArrayList(str);
        int i = 0;
        for ( Object str1 : arrayList) {
            log.info("数据{}：{}", ++i, str1);
        }
    }

    @Test
    public void stringTest() {
        String id = "";

        log.info("长度：{}", id.substring(0,1)+"****************"+id.substring(id.length()-1, id.length()));

    }
}
