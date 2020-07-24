package com.wgb.utils.test.high.concurrency;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 清单15.7 Michael-Scott非阻塞队列算法中的插入（Michael与Scott，1996）
 * @author INNERPEACE
 * @date 2020/7/21
 */
@ThreadSafe
public class LinkedQueue <E> {
	private static class Node<E> {
		final E item;
		final AtomicReference<Node<E>> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = new AtomicReference<Node<E>>(next);
		}
	}

	private final Node<E> dummy = new Node<E>(null, null);
	private final AtomicReference<Node<E>> head = new AtomicReference<Node<E>>(dummy);
	private final AtomicReference<Node<E>> tail = new AtomicReference<Node<E>>(dummy);

	public boolean put(E item) {
		Node<E> newNode = new Node<E>(item, null);
		while (true) {
			Node<E> curTail = tail.get();
			Node<E> tailNext = curTail.next.get();
			if (curTail == tail.get()) {
				if (tailNext != null) { // A
					// Queue in intermediate state, advance tail
					tail.compareAndSet(curTail, tailNext); // B
				} else {
					// In quiescent state, try inserting new node
					if (curTail.next.compareAndSet(null, newNode)) { // C
						// Insertion succeeded, try advancing tail
						tail.compareAndSet(curTail, newNode); // D
						return true;
					}
				}
			}
		}
	}
}
