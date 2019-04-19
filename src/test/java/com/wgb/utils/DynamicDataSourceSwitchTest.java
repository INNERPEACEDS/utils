package com.wgb.utils;

import com.wgb.utils.common.mybatis.DynamicDataSource;
import com.wgb.utils.common.mybatis.DynamicDataSourceSwitch;
import com.wgb.utils.dao.oracle.BookRecordMapper;
import com.wgb.utils.entity.oracle.BookRecord;
import com.wgb.utils.service.query.page.PageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author INNERPEACE
 * @date 2019/3/29 18:37
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceSwitchTest {

	@Autowired
	PageService pageService;
	@Autowired
	BookRecordMapper bookRecordMapper;

	@Test
	public void dynamicDataSourceSwitchTest() {
		log.info("初始查询：{}", bookRecordMapper.selectByPrimaryKey("31"));
		DynamicDataSourceSwitch.setRouteKey("slave1");
		/*BookRecord bookRecord = new BookRecord();
		bookRecord.setId("2");
		pageService.queryBookRecordByDTO();*/
		BookRecord bookRecord = bookRecordMapper.selectByPrimaryKey("31");
		log.info("从结点1结果：{}", bookRecord.toString());
		// DynamicDataSourceSwitch.removeRouteKey("");
		DynamicDataSourceSwitch.setRouteKey("slave2");
		log.info("从节点2结果：{}",bookRecordMapper.selectByPrimaryKey("31") );

		DynamicDataSourceSwitch.setRouteKey("master");
		log.info("主节点结果：{}",bookRecordMapper.selectByPrimaryKey("31") );
	}
}
