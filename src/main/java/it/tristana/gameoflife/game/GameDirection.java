package it.tristana.gameoflife.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;

enum GameDirection {

	X_PLANE((vec, i, ii) -> new Vector(vec.getBlockX(), vec.getBlockY() + i, vec.getBlockZ() + ii)),
	Y_PLANE((vec, i, ii) -> new Vector(vec.getBlockX() + i, vec.getBlockY(), vec.getBlockZ() + ii)),
	Z_PLANE((vec, i, ii) -> new Vector(vec.getBlockX() + ii, vec.getBlockY() + i, vec.getBlockZ()));

	private final PositionChanger builder;

	GameDirection(PositionChanger builder) {
		this.builder = builder;
	}

	public void build(Location start, boolean[][] matrix, boolean[][] previousMatrix) {
		World world = start.getWorld();
		Vector vec = start.toVector();
		for (int i = 0; i < matrix.length; i ++) {
			for (int ii = 0; ii < matrix[0].length; ii ++) {
				if (previousMatrix != null && matrix[i][ii] == previousMatrix[i][ii]) {
					continue;
				}

				Vector res = builder.toVector(vec, i, ii);
				world.getBlockAt(res.getBlockX(), res.getBlockY(), res.getBlockZ()).setType(matrix[i][ii] ? Material.GREEN_WOOL : Material.BLACK_WOOL);
			}
		}
	}
}
