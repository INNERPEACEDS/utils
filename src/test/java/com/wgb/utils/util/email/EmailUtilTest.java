package com.wgb.utils.util.email;

import com.wgb.utils.entity.email.AttachmentMail;
import com.wgb.utils.entity.email.EasyMail;
import com.wgb.utils.entity.email.HtmlMail;
import com.wgb.utils.entity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;

/**
 * 发送邮件工具类测试
 * @author : innerpeace
 * @date : 2018/11/21 11:20
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailUtilTest {
	/**
	 * 邮件工具类对象
	 */
	@Autowired
	EmailUtil emailUtil;

	/**
	 * 发送简单邮件测试
	 */
	@Test
	public void sendSimpleEmail() {
		String[] re = {"2013138148@qq.com"};
		String[] cc = {};
		String subject = "测试邮件";
		//String content = "测试信息";
		//测试使用简单邮件发送html文件，发送结果原样输出，不解析html
		String content = emailUtil.temp;
		EasyMail easyMail = new EasyMail(re,cc,subject,content);
		emailUtil.sendSimpleEmail(easyMail);
	}

	/**
	 * 发送html类型邮件测试
	 */
	@Test
	public void sendHtmlEmail() {
		String[] re = {"2431440826@qq.com"};
		String[] cc = {};
		String subject = "测试邮件";
		String content = emailUtil.temp;

		HtmlMail htmlMail = new HtmlMail(re,cc,subject,content);
		emailUtil.sendHtmlEmail(htmlMail);
	}

	/**
	 * 发送附件邮件测试
	 */
	@Test
	public void sendAttachmentFileEmail() {
		String[] re = {"2431440826@qq.com"};
		String[] cc = {};
		String subject = "测试邮件";
		String content = emailUtil.temp;

		String fileName = "附件.png";
		// 获取执行该类的类路径
		URL resource = Result.class.getClass().getResource("/");
		// resource:file:/D:/project/wgb/utils/target/test-classes/
		log.info("resource:{}", resource);
		// resource.getPath():/D:/project/wgb/utils/target/test-classes/
		log.info("resource.getPath():{}",resource.getPath());

		String s = resource.getPath() + "file/attachment/附件 .png";
		String replace = s.replace("test-", "");
		// target路径：/D:/project/wgb/utils/target/classes/file/attachment/附件 .png
		log.info("target路径：{}",replace);
		File file = new File(replace);
		AttachmentMail attachmentMail = new AttachmentMail(re, cc, subject,content,fileName,file);
		emailUtil.sendAttachmentFileEmail(attachmentMail);

	}
}


