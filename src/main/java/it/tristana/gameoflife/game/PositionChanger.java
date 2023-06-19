package it.tristana.gameoflife.game;

import org.bukkit.util.Vector;

@FunctionalInterface
public interface PositionChanger {

	Vector toVector(Vector start, int i, int ii);
}
