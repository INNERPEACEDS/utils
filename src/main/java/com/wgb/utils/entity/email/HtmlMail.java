package com.wgb.utils.entity.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Html邮件发送
 * @author : innerpeace
 * @date : 2018/11/21 10:36
 */

@Slf4j
@Getter
@AllArgsConstructor
public class HtmlMail {

    public HtmlMail(String[] receiver, String[] carbonCopy, String subject, String content) {
        this.receiver = receiver;
        this.carbonCopy = carbonCopy;
        this.subject = subject;
        this.content = content;
        this.isHtml = true;
    }

    /**
     * 收信人
     */
    private String[] receiver;
    /**
     * 抄送
     */
    private String[] carbonCopy;
    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否html解析
     */
    private Boolean isHtml;
}
