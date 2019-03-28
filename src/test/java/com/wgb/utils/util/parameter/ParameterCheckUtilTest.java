package com.wgb.utils.util.parameter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : innerpeace
 * @date : 2018/11/29 17:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@Data
public class ParameterCheckUtilTest {
	private String a = "1";

	private int b = 1;

	@Test
	public void nullResetTest() {
		ParameterCheckUtilTest p = new ParameterCheckUtilTest();
		String parameter = "2";
		String s = ParameterCheckUtil.nullReset(parameter, p, a);
		log.info("测试值对象的属性值为：{},返回值为：{}" , p.getA(),s);
	}
}
