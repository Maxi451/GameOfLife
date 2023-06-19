package it.tristana.gameoflife.game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import it.tristana.commons.helper.CommonsHelper;

public class GamesManager {

	private Map<String, GameBuilder> games;

	public GamesManager() {
		this.games = new HashMap<>();
	}

	public boolean create(String id, Location pos1, Location pos2) {
		Vector pos1vec = pos1.toVector();
		Vector pos2vec = pos2.toVector();
		CommonsHelper.correctExtremities(pos1vec, pos2vec);
		int xDiff = pos2vec.getBlockX() - pos1vec.getBlockX();
		int yDiff = pos2vec.getBlockY() - pos1vec.getBlockY();
		int zDiff = pos2vec.getBlockZ() - pos1vec.getBlockZ();
		GameDirection direction;
		int width;
		int height;
		if (xDiff == 0) {
			direction = GameDirection.X_PLANE;
			width = zDiff;
			height = yDiff;
		} else if (yDiff == 0) {
			direction = GameDirection.Y_PLANE;
			width = xDiff;
			height = zDiff;
		} else if (zDiff == 0) {
			direction = GameDirection.Z_PLANE;
			width = xDiff;
			height = yDiff;
		} else {
			return false;
		}

		games.put(id.toLowerCase(), new GameBuilder(new GameOfLife(width, height), pos1vec.toLocation(pos1.getWorld()), direction));
		return true;
	}

	public GameBuilder getGame(String id) {
		return games.get(id);
	}
}
