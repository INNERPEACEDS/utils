package com.wgb.utils.util.algorithm.leetcode;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author INNERPEACE
 * @date 2020/7/6 15:16
 */
@Slf4j
public class TwoNumbersAdd {

	public static void main(String[] args) {
		ListNode listNode0 = new ListNode("378345678909890989098782");
		ListNode listNode1 = new ListNode("73123445642345678908542");

		log.info("listNode0：{}", listNode0.getAscOrderNumber1());
		log.info("listNode1：{}", listNode1.getAscOrderNumber1());
		TwoNumbersAdd twoNumbersAdd = new TwoNumbersAdd();
		ListNode listNode = twoNumbersAdd.twoNumbersAdd(listNode0, listNode1);
		log.info("result：{}", listNode.getAscOrderNumber1());
	}

	public ListNode twoNumbersAdd(ListNode listNode0, ListNode listNode1) {
//		873   378 + 73 = 451
//		37
		ListNode dummyHead = new ListNode(0);
		ListNode number0 = listNode0, number1 = listNode1, currency = dummyHead;
		int carry = 0;
		boolean flag = true;
		while (number0 != null || number1 != null) {
			int x = (number0 != null) ? number0.getValue() : 0;
			int y = (number1 != null) ? number1.getValue() : 0;
			int sum = 0;
			if (flag) {
				sum = x + y + currency.getValue();
			} else {
				sum = x + y;
			}
			carry = sum / 10;
			if (flag) {
				currency.setValue(sum % 10);
			} else {
				currency.setNext(new ListNode(sum % 10));
				currency = currency.getNext();
			}
			if ( number0 != null) {
				number0 = number0.getNext();
			}
			if (number1 != null) {
				number1 = number1.getNext();
			}
			if (carry > 0) {
				currency.setNext(new ListNode(carry)) ;
				currency = currency.getNext();
				flag = true;
			} else {
				flag = false;
			}
		}
		return dummyHead;
	}
}

/**
 * 存储方式为倒序
 * 例如：456 存储到结构中为：6 -> 5 -> 4
 */
@Getter
@Setter
@Slf4j
class ListNode {
	private int value;
	public ListNode next;

	public ListNode(int value) {
		this.value = value;
	}

	/**
	 * 正序赋值， 例如想将456存储到该结构中，直接输入字符参数“456”
	 * @param values
	 */
	public ListNode(String values) {
		ListNode listNode = null;
		for (int i = values.length() - 1; i > -1; i--) {
			if (i == values.length() - 1) {
				value = Integer.parseInt(values.substring(i));
			} else {
				int value = Integer.parseInt(values.substring(i, i + 1));
				if (i == values.length() - 2) {
					next = new ListNode(value);
					listNode = next;
				} else {
					if (listNode != null) {
						listNode.setNext(new ListNode(value));
						listNode = listNode.getNext();
					} else {
						log.error("listNode为空");
					}
				}
			}
		}
	}

	/**
	 * 按照结构存储方式输出（结构的顺序输出，实际数值的倒序输出）
	 * @return
	 */
	 String getDescOrderNumber() {
		StringBuilder builder = new StringBuilder(String.valueOf(this.value));
		ListNode nextListNode = next;
		if (nextListNode != null) {
			builder.append(nextListNode.getValue());
			while (nextListNode.getNext() != null) {
				nextListNode = nextListNode.getNext();
				builder.append(nextListNode.getValue());
			}
		}
		return builder.toString();
	}



	String getAscOrderNumber0() {
		ArrayList<Integer> linkedDescOrder = getLinkedDescOrder(this);
		return linkedDescOrder.toString();
	}

	/**
	 * 递归倒序输出
	 * @param listNode
	 * @return
	 */
	private ArrayList<Integer> getLinkedDescOrder(ListNode listNode) {
		ArrayList<Integer> integers = new ArrayList<>();
		if (listNode == null) {
			return integers;
		}
		if (listNode.getNext() != null) {
			integers.addAll(getLinkedDescOrder(listNode.getNext()));
		}
		integers.add(listNode.getValue());
		return integers;
	}

	/**
	 * 利用栈的先进后出
	 * @return
	 */
	String getAscOrderNumber1() {
		ArrayList<Integer> list = new ArrayList<>();
		Stack<Integer> stack = new Stack();
		ListNode listNode = this;
		stack.push(listNode.getValue());
		while (listNode.getNext() != null) {
			listNode = listNode.getNext();
			stack.push(listNode.getValue());
		}
		while (!stack.isEmpty()) {
			list.add(stack.pop());
		}
		return list.toString();
	}

}
