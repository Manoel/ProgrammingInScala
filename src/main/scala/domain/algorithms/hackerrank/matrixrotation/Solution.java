package domain.algorithms.hackerrank.matrixrotation;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int M = input.nextInt();

		int N = input.nextInt();

		int R = input.nextInt();

		int[][] matrix = new int[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				matrix[i][j] = input.nextInt();
			}
		}

		rotate(matrix, R);

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}

		input.close();
	}

	private static void rotate(int[][] matrix, int num) {
		Cell tL = new Cell(0, 0);
		Cell bL = new Cell(matrix.length - 1, 0);
		Cell bR = new Cell(matrix.length - 1, matrix[0].length - 1);
		Cell tR = new Cell(0, matrix[0].length - 1);

		RotationCorners rc = new RotationCorners(tL, bL, bR, tR);
		int decrease = 0;
		int r;
		int c;
		do {
			r = matrix.length - decrease;
			c = matrix[0].length - decrease;
            
            int rotate = num % rc.getTotal();
            
            while (rotate-- > 0) {
                rotate(matrix, rc, decrease);    
            }
            
            decrease++;	
            
		} while ((rc = rc.next()).isAllowed(r, c, decrease));
	}

	private static void rotate(int[][] matrix, RotationCorners rc, int decrease) {
		int r = matrix.length - decrease;

		int c = matrix[0].length - decrease;

		Cell start = rc.getTopLeft();

		Cell next;

		int temp1 = matrix[start.getRow()][start.getColumn()];

		int temp2;

		while ((next = start.down()).isInMatrix(r, c, decrease)) {
			temp2 = matrix[next.getRow()][next.getColumn()];
			matrix[next.getRow()][next.getColumn()] = temp1;
			temp1 = temp2;
			start = next;
		}

		while ((next = start.right()).isInMatrix(r, c, decrease)) {
			temp2 = matrix[next.getRow()][next.getColumn()];
			matrix[next.getRow()][next.getColumn()] = temp1;
			temp1 = temp2;
			start = next;
		}

		while ((next = start.up()).isInMatrix(r, c, decrease)) {
			temp2 = matrix[next.getRow()][next.getColumn()];
			matrix[next.getRow()][next.getColumn()] = temp1;
			temp1 = temp2;
			start = next;
		}

		while ((next = start.left()).isInMatrix(r, c, decrease)) {
			temp2 = matrix[next.getRow()][next.getColumn()];
			matrix[next.getRow()][next.getColumn()] = temp1;
			temp1 = temp2;
			start = next;
		}
	}
}

class Cell {
	int row;

	int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public boolean isInMatrix(int r, int c, int s) {
		return row >= s && row < r && column >= s && column < c;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public Cell down() {
		return new Cell(row + 1, column);
	}

	public Cell up() {
		return new Cell(row - 1, column);
	}

	public Cell right() {
		return new Cell(row, column + 1);
	}

	public Cell left() {
		return new Cell(row, column - 1);
	}

	@Override
	public String toString() {
		return "Cell(" + row + ", " + column + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Cell)) {
			return false;
		}

		Cell other = (Cell) o;

		return row == other.row && column == other.column;
	}
}

class RotationCorners {

	private Cell topLeft;

	private Cell bottomLeft;

	private Cell bottomRight;

	private Cell topRight;

	public RotationCorners(Cell topLeft, Cell bottomLeft, Cell bottomRight,
			Cell topRight) {
		this.topLeft = topLeft;
		this.bottomLeft = bottomLeft;
		this.bottomRight = bottomRight;
		this.topRight = topRight;
	}
    
    public int getTotal() {
        int left = bottomLeft.row - topLeft.row + 1;
        int bottom = bottomRight.column - bottomLeft.column + 1;
        int right = bottomRight.row - topRight.row + 1;
        int top = topRight.column - topLeft.column + 1;
        return left + bottom + right + top - 4;
    }

	Cell getTopLeft() {
		return topLeft;
	}

	Cell getBottomLeft() {
		return bottomLeft;
	}

	Cell getBottomRight() {
		return bottomRight;
	}

	Cell getTopRight() {
		return topRight;
	}

	public boolean isAllowed(int r, int c, int s) {
		return topLeft.row < bottomLeft.row && topRight.row < bottomRight.row
				&& topLeft.isInMatrix(r, c, s)
				&& bottomLeft.isInMatrix(r, c, s)
				&& bottomRight.isInMatrix(r, c, s)
				&& topRight.isInMatrix(r, c, s);
	}

	public RotationCorners next() {
		Cell tL = new Cell(topLeft.row + 1, topLeft.column + 1);
		Cell bL = new Cell(bottomLeft.row - 1, bottomLeft.column + 1);
		Cell bR = new Cell(bottomRight.row - 1, bottomRight.column - 1);
		Cell tR = new Cell(topRight.row + 1, topRight.column - 1);

		return new RotationCorners(tL, bL, bR, tR);
	}
}