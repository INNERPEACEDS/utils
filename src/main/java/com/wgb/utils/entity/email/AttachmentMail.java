package com.wgb.utils.entity.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import java.io.File;

/**
 * 附件邮件发送
 * @author : innerpeace
 * @date : 2018/11/21 10:36
 */
@Slf4j
@Getter
@AllArgsConstructor
public class AttachmentMail {
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
     * 附件名称
     */
    private String fileName;
    /**
     * 附件
     */
    private File file;
}
