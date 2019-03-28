package com.wgb.utils.entity.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单邮件发送
 * @author : innerpeace
 * @date : 2018/11/21 10:36
 */

@Slf4j
@Getter
@Builder
@AllArgsConstructor
public class EasyMail {
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
}
