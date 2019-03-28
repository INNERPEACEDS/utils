package com.wgb.utils.entity.email;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.io.File;

/**
 * 该邮件暂无用处，可忽略
 * @author : innerpeace
 * @date : 2018/11/20 16:06
 */
@Slf4j
public class Email {
	/**
	 * 主机
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 用户
	 */
	private String user;

	/**
	 * 密码
	 */
	private String passwd;

	/**
	 * 显示来源
	 */
	private String from;

	/**
	 * 主题
	 */
	private String subject;

	/**
	 * 接收者
	 */
	private String[] to;

	/**
	 * 抄送
	 */
	private String[] cc;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 附件路径
	 */
	private String path;

	/**
	 * 网页email
	 */
	private HtmlEmail email;

	public Email(String user, String passwd) {
		this("smtp." + user.split("@")[1], 465, user, passwd, user, true);
	}

	public Email(String host, String user, String passwd) {
		this(host, 465, user, passwd, user, true);
	}

	public Email(String host, int port, String user, String passwd, String from, boolean isSSL) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.passwd = passwd;
		this.from = from;

		email = new HtmlEmail();
		email.setHostName(this.host);
		email.setSslSmtpPort(this.port + "");
		email.setSslSmtpPort(this.port + "");
		email.setAuthenticator(new DefaultAuthenticator(this.user, this.passwd));
		email.setCharset("utf-8");

		if (isSSL) {
			email.setSSLOnConnect(true);
		}
		try {
			email.setFrom(this.from);
		} catch (EmailException e) {
			log.info("发送者Email异常");
			e.printStackTrace();
		}
	}

	public boolean send(String subject, String content, String receiverTo) {
		return send(subject, content, receiverTo, "", "");
	}

	/**
	 * 发送邮件
	 *
	 * @param subject
	 * @param content
	 * @param receiverTo
	 * @param receiverCc
	 * @param path
	 * @return
	 */
	public boolean send(String subject, String content, String receiverTo, String receiverCc, String path) {
		boolean result = false;
		if (this.host == null || "".equals(this.host)) {
			return result;
		}
		try {
			this.subject = subject;
			this.content = content;
			String[] temp = {receiverTo, receiverCc};
			if (temp[0] != null && !"".equals(temp[0])) {
				this.to = temp[0].split(",");
				for (String t : to) {
					email.addTo(t);
				}
			}
			if (temp[1] != null && !"".equals(temp[1])) {
				this.cc = temp[1].split(",");
				for (String c : cc) {
					email.addCc(c);
				}
			}
			//设置主题
			email.setSubject(this.subject);
			email.setHtmlMsg(buildEmailContent(this.content));
			//如果不支持html，直接显示文本
			email.setTextMsg(this.content);
			if (path != null && !"".equals(path)) {
				EmailAttachment attachment = new EmailAttachment();
				// 本地文件
				attachment.setPath(path);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				String[] filePath = path.split("\\\\" + File.separator);
				String name = filePath[filePath.length - 1];
				attachment.setDescription(name);
				attachment.setName(name);
				try {
					email.attach(attachment);
				} catch (EmailException e) {
					log.info("");
					e.printStackTrace();
				}
			}
			email.send();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static final String  PLACEHOLDER = "{{content}}";
	private static String temp = "<html><head><style>body{margin:0;padding:0;font-size:14px}.mail{width:680px;padding:10px 30px;background:#fff;border:10px solid #eee}.mail_title{width: 229px;height: 63px;background:url(http://impl.com/images/ielpm_ico.png) no-repeat 0 0;border-bottom:1px dashed #adacac;height:70px}.mail p{margin:20px 30px;line-height:26px}.mail p .a1{color:#1e5494;text-decoration:underline}.mail_fot{border-top:1px dashed #adacac;padding:10px 0 10px 0}.mail_fot p{color:#999;font-size:12px;line-height:18px}</style></head><body><div class=\"mail\"><div class=\"mail_title\"></div>" + PLACEHOLDER + "<div class=\"mail_fot\"><p>本邮件由系统自动发出，请勿直接回复！<br>如有疑问会建议，请联系易联客服，24小时客服电话：010-53500763/764。<br>网址：http://www.impl.com/</p></div></div></body></html>";

	/**
	 * 填充邮件模板
	 * @param content
	 * @return
	 */
	private static String buildEmailContent(String content){
		return temp.replace(PLACEHOLDER, content);
	}

//	public static void main(String[] args) throws EmailException {
//		System.out.println("开始发送");
////		int port = 993;
//		int port = 465;
////		String username = "ys.pay@impl.com";
////		String password = "Yishi2015";
//		String username = "yan.long@impl.com";
//		String password = "Yan1234567";
//		String from = username;
//		String host = "smtp."+username.split("@")[1];
//		String receiverTo = "3442354428@qq.com";
//		String receiverCc = "";
//		String subject = "上海易势商户开户信息-----------(测试邮件)";
//		String content = "<p>尊敬的测试公司：</p>\r\n \n"
//				+"<p>我是上海易势商务服务有限公司，以下为贵司的开户重要信息，请注意保密：<br/>\r\n \n"
//				+"商户系统登录地址：https://pay.ielpm.com/ <br/>\r\n"
//				+"商户号：<br/> \r\n"
//				+"登录名：<br/> \r\n"
//				+"登录密码/支付密码：<br/>\r\n"
//				+"如有问题请关注易势公众号（<strong>yishipay</strong>）咨询。<br/>\r\n "
//				+"附件为商户系统使用说明。请查收！<br/>\r\n \n"
//				+"温馨提示：<br/>\r\n"
//				+"为安全起见，新申请的账号，首次登录后请立即修改初始密码。</p><br/>\r\n";
//		//附件
//		String path = "d:"+File.separator;
//		String filePath = path+"File"+File.separator+"新版易势支付-商户系统操作说明 .docx";
//		boolean isSSL = true;
//		Email emailClient = new Email(host, port, username, password, from, isSSL);
//		boolean res = emailClient.send(subject, content, receiverTo, receiverCc, filePath);
//		System.out.println(res);
//		System.out.println("发送完毕");
//	}
}
