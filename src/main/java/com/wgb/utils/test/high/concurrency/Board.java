package com.wgb.utils.test.high.concurrency;

/**
 * @author INNERPEACE
 * @date 2020/5/6 18:02
 */
public class Board {

	public void commitNewValues() {

	}

	public Board getSubBoard(int count) {
		return new Board();
	}

	public boolean hasConverged() {
		return false;
	}

	public int getMaxX() {
		return 2;
	}

	public int getMaxY() {
		return 2;
	}

	public void setNewValue(int x, int y, int max) {

	}

	public void waitForConvergence() {

	}
}
