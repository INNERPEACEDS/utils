package com.wgb.utils.util.enumhelper;

import com.wgb.utils.entity.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : innerpeace
 * @date : 2018/11/14 15:33
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class EnumHelperUtilTest {
    @Test
    public void getEnumByTypeCode() {
        ResultEnum getCode = EnumHelperUtil.getEnumByIntegerTypeCode(ResultEnum.class, "getOrdinal", null);
        log.info("获取的枚举对象为：{}", getCode);

    }
}
