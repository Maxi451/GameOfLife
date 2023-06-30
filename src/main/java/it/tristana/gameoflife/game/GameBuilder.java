package it.tristana.gameoflife.game;

import org.bukkit.Location;

import it.tristana.commons.interfaces.Tickable;

public class GameBuilder implements Tickable {

	private final GameOfLife game;
	private final Location start;
	private final Location end;
	private final GameDirection direction;
	
	private boolean isPaused;

	public GameBuilder(GameOfLife game, Location start, Location end, GameDirection direction) {
		this.game = game;
		this.start = start;
		this.end = end;
		this.direction = direction;
	}

	@Override
	public void runTick() {
		if (isPaused) {
			return;
		}

		game.runTick();
		direction.build(start, game.getMatrix(), game.getPreviousMatrix());
	}
	
	public Location getLocation() {
		return start.clone();
	}
	
	public Location getEndLocation() {
		return end.clone();
	}
	
	public boolean togglePause() {
		isPaused = !isPaused;
		return isPaused;
	}
	
	public boolean isPaused() {
		return isPaused;
	}
}
