package it.tristana.gameoflife.game;

import it.tristana.commons.interfaces.Tickable;

public class GameOfLife implements Tickable {

	private boolean[][] matrix;

	public GameOfLife(int width, int height) {
		this.matrix = new boolean[width][height];
		randomizeField();
	}

	@Override
	public void runTick() {
		boolean[][] nextGen = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < nextGen.length; i ++) {
			for (int ii = 0; ii < nextGen[i].length; ii ++) {
				int neighbors = getNeighbors(matrix, i, ii);
				nextGen[i][ii] = neighbors == 2 || neighbors == 3;
			}
		}
		this.matrix = nextGen;
	}

	public boolean[][] getMatrix() {
		return matrix;
	}

	private void randomizeField() {
		for (int i = 0; i < matrix.length; i ++) {
			for (int ii = 0; ii < matrix[i].length; ii ++) {
				matrix[i][ii] = Math.random() > 0.5;
			}
		}
	}

	private static int getNeighbors(boolean[][] matrix, int i, int ii) {
		int neighbors = 0;
		if (i > 0) {
			if (ii > 0 && matrix[i - 1][ii - 1]) {
				neighbors ++;
			}
			if (ii < matrix[0].length - 1 && matrix[i - 1][ii + 1]) {
				neighbors ++;
			}
			if (matrix[i - 1][ii]) {
				neighbors ++;
			}
		}

		if (i < matrix.length - 1) {
			if (ii > 0 && matrix[i + 1][ii - 1]) {
				neighbors ++;
			}
			if (ii < matrix[0].length - 1 && matrix[i + 1][ii + 1]) {
				neighbors ++;
			}
			if (matrix[i + 1][ii]) {
				neighbors ++;
			}
		}

		if (ii > 0 && matrix[i][ii - 1]) {
			neighbors ++;
		}

		if (ii < matrix[0].length - 1 && matrix[i][ii + 1]) {
			neighbors ++;
		}

		return neighbors;
	}
}
