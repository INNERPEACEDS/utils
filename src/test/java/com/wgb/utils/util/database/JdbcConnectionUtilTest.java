package com.wgb.utils.util.database;

import com.wgb.utils.entity.oracle.BookRecord;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author INNERPEACE
 * @date 2018/12/18 13:54
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcConnectionUtilTest {
    /**
     * 增删改测试
     */
    @Test
    public void updateTest() {
        String sql = "insert into bookRecord values(?, ?, ?)";
        List params = new ArrayList();
        params.add("33");
        params.add("测试30");
        params.add("jdbc连接数据库测试");
        boolean b = JdbcConnectionUtil.operateUpdate(sql, params);
        if (b) {
            log.info("插入数据成功");
        } else {
            log.info("插入失败");
        }
    }

    /**
     * 查询测试
     */
    @Test
    public void queryTest() {
        String sql = "select * from bookRecord where id != ?";
        List<Object> params = new ArrayList();
        params.add("33");
        // params.add("测试30");
        // params.add("jdbc连接数据库测试");
        List<BookRecord> ts = JdbcConnectionUtil.operateQuery(sql, params, BookRecord.class);
        if (ts != null && ts.size() > 0) {
            int i = 1;
            for (BookRecord bookRecord : ts) {
                log.info("BookRecord[{}]:{}", i++, bookRecord.toString());
            }
        } else {
            log.info("数据为空");
        }
    }
}
