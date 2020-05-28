package com.wgb.utils.test;

import java.util.List;

/**
 * @author INNERPEACE
 * @date 2019/11/14 10:52
 */
public class MessageList {
	private long createTime;

	private List<String> messages;

	private MessageList() {}

	private static class MessageListFactory {
		private static MessageList messageList = new MessageList();
	}

	public static MessageList getInstance() {
		return MessageListFactory.messageList;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getCreateTime() {
		return createTime;
	}
}
