package com.wgb.utils.util.email;

import com.wgb.utils.entity.email.AttachmentMail;
import com.wgb.utils.entity.email.EasyMail;
import com.wgb.utils.entity.email.HtmlMail;
import com.wgb.utils.entity.result.Result;
import com.wgb.utils.entity.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;
import java.util.Arrays;

/**
 * 邮件发送工具
 * @author : innerpeace
 * @date : 2018/11/21 10:39
 */
@Slf4j
@Component
public class EmailUtil {
	/**
	 * 发送者
	 */
	@Value("${spring.mail.username}")
	private String deliver;

	/**
	 * 邮件发送
	 */
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * 模板引擎
	 */
	@Autowired
	private TemplateEngine templateEngine;

	/**
	 * html模板
	 */
	public static String temp = "<html><head><style>body{margin:0;padding:0;font-size:14px}.mail{width:680px;padding:10px 30px;background:#fff;border:10px solid #eee}.mail_title{width: 229px;height: 63px;background:url(http://impl.com/images/ielpm_ico.png) no-repeat 0 0;border-bottom:1px dashed #adacac;height:70px}.mail p{margin:20px 30px;line-height:26px}.mail p .a1{color:#1e5494;text-decoration:underline}.mail_fot{border-top:1px dashed #adacac;padding:10px 0 10px 0}.mail_fot p{color:#999;font-size:12px;line-height:18px}</style></head><body><div class=\\\"mail\\\"><div class=\\\"mail_title\\\"></div>\" 此处为测试正文：+ PLACEHOLDER + \"<div class=\\\"mail_fot\\\"><p>本邮件由系统自动发出，请勿直接回复！<br>如有疑问会建议，请联系易联客服，24小时客服电话：010-53500763/764。<br>网址：http://www.impl.com/</p></div></div></body></html>";
	/**
	 * 发送简单邮件
	 * @param easyMail 简单邮件对象
	 * @return 发送时候成功
	 */
	public Result<String> sendSimpleEmail(EasyMail easyMail) {
		long startTimestamp = System.currentTimeMillis();
		log.debug("开始发送邮件 ... ");
		try {
			SimpleMailMessage m = new SimpleMailMessage();
			log.info("发送地址：{}", Arrays.toString(easyMail.getReceiver()));
			log.info("deliver:{}",deliver);
			m.setFrom(deliver);
			m.setTo(easyMail.getReceiver());
			m.setCc(easyMail.getCarbonCopy());
			m.setSubject(easyMail.getSubject());
			m.setText(easyMail.getContent());
			log.info("mail的信息：{}",m.toString());
			mailSender.send(m);
			log.info("邮件发送成功, 耗时 {}毫秒", System.currentTimeMillis() - startTimestamp);
			return new Result<>(ResultEnum.SUCCESS);
		} catch (Exception e) {
			log.error("邮件发送失败: 原因={} \n", e.getMessage());
		}
		return new Result<>(ResultEnum.FAIL);
	}

	/**
	 * 发送html邮件
	 * @param htmlMail 发送信息
	 * @return 返回结果
	 */
	public Result<String> sendHtmlEmail(HtmlMail htmlMail) {
		long startTimestamp = System.currentTimeMillis();
		log.info("Start send email ...");
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			messageHelper.setFrom(deliver);
			messageHelper.setTo(htmlMail.getReceiver());
			messageHelper.setCc(htmlMail.getCarbonCopy());
			messageHelper.setSubject(htmlMail.getSubject());
			messageHelper.setText(htmlMail.getContent(), htmlMail.getIsHtml());
			mailSender.send(message);
			log.info("邮件发送成功, 耗时 {}毫秒", System.currentTimeMillis() - startTimestamp);
			return new Result<>(ResultEnum.SUCCESS);
		}catch (Exception e){
			log.error("邮件发送失败: 原因={} \n", e.getMessage());
		}
		return new Result<>(ResultEnum.FAIL);
	}

	/**
	 * 发送附件邮箱
	 * @param attachmentMail
	 * @return
	 */
	public Result<String> sendAttachmentFileEmail(AttachmentMail attachmentMail) {
		long startTimestamp = System.currentTimeMillis();
		log.info("Start send mail ...");
		try{
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
			messageHelper.setFrom(deliver);
			messageHelper.setTo(attachmentMail.getReceiver());
			messageHelper.setCc(attachmentMail.getCarbonCopy());
			messageHelper.setSubject(attachmentMail.getSubject());
			messageHelper.setText(attachmentMail.getContent(), true);
			messageHelper.addAttachment(attachmentMail.getFileName(), attachmentMail.getFile());
			mailSender.send(message);
			log.info("邮件发送成功, 耗时 {}毫秒", System.currentTimeMillis() - startTimestamp);
			return new Result<>(ResultEnum.SUCCESS);
		}catch (Exception e){
			log.error("邮件发送失败: 原因={} \n", e.getMessage());
		}
		return new Result<>(ResultEnum.FAIL);
	}
}
