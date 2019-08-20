package com.wgb.utils.util.algorithm.queen;


/**
 * @author INNERPEACE
 */
public class NQueen {
	private static final String TYPE_CHAR = "0";
	private static final String TYPE_INT = "1";
	private static int n = 0;
	/**
	 * 一数字占4个字节
	 */
	private int[][] board;
	/**
	 * 一字符占两个字节
	 */
	private char[][] cBoard;
	private int cnt;

	public NQueen() {
		n = 8;
		this.board = new int[n][n];
		this.cBoard = new char[n][n];
	}

	public NQueen(int queenValue) {
		n = queenValue;
		this.board = new int[n][n];
		this.cBoard = new char[n][n];
	}

	private void memset(char[][] cBoard, char defaultValue, int size) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cBoard[i][j] = defaultValue;
			}
		}
	}
	public void solve() {
		char defaultValue = '0';
		memset(cBoard, defaultValue, n);
		solveMethod(0);
		// print();
	}

	private void solveMethod(int row) {
		int i;
		for (i = 0; i < n; i++) {
			cBoard[row][i] = '1';
			// 是否满足要求（满足一下条件输出：1.同一行和列中有且只能出现一次1；2.主对角线和副对角线有且只能出现一次1.)
			if (check(row, i)) {
				// 构建数据满足要求，输出结果
				if (row == n - 1) {
					print("0");
				} else {
					solveMethod(row + 1);
				}
			}
			// 1.满足要求数据结果后回退构建其他满足数据要求；2.不满足要求后回退构建其他满足数据要求。
			cBoard[row][i] = '0';
		}
	}

	private boolean check(int row, int col) {
		int i, j;
		// 之前行中相同列中不能出现1，如果出现，返回false
		for (i = 0; i < row; i++) {
			if (cBoard[i][col] == '1') {
				return false;
			}
		}
		// 之前行中对角线不能出现1，如果出现，返回false（cannot be diagonal）
		// 主对角线
		i = row -1;
		j = col - 1;
		while (i >= 0 && j >= 0) {
			if (cBoard[i][j] == '1') {
				return false;
			}
			i--;
			j--;
		}
		// 副对角线
		i = row - 1;
		j = col + 1;
		while (i >= 0 && j < n) {
			if (cBoard[i][j] == '1') {
				return false;
			}
			i--;
			j++;
		}
		return true;
	}

	private void print(String type) {
		if (TYPE_CHAR.equals(type)) {
			print(cBoard);
		} else if (TYPE_INT.equals(type)) {
			print(board);
		} else {
			System.out.println("选择类型错误!!!");
		}
	}

	private void print(int[][] oBoard) {
		System.out.println("chessboard:" + ++cnt);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(oBoard[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private void print(char[][] oBoard) {
		System.out.println("chessboard:" + ++cnt);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(oBoard[i][j] + "\t");
			}
			System.out.println();
		}
	}



}
