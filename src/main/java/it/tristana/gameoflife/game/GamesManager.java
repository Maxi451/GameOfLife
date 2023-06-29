package it.tristana.gameoflife.game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import it.tristana.commons.arena.Clock;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.gameoflife.Main;
import it.tristana.gameoflife.config.SettingsPlugin;

public class GamesManager implements Reloadable {

	private Main plugin;
	private SettingsPlugin settings;
	private Map<String, GameBuilder> games;
	private Clock clock;

	public GamesManager(Main plugin, SettingsPlugin settings) {
		this.plugin = plugin;
		this.settings = settings;
		this.games = new HashMap<>();
		this.clock = new Clock();
		this.clock.add(() -> games.values().stream().filter(this::hasPlayersNearby).forEach(GameBuilder::runTick));
		reload();
	}

	@Override
	public void reload() {
		clock.cancel();
		clock.schedule(plugin, settings.getGamesTickInterval());
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

		games.put(parseId(id), new GameBuilder(new GameOfLife(width, height), pos1vec.toLocation(pos1.getWorld()), direction));
		return true;
	}

	public Map<String, GameBuilder> getGames() {
		return games;
	}

	public boolean deleteGame(String id) {
		return games.remove(parseId(id)) != null;
	}

	public GameBuilder getGame(String id) {
		return games.get(parseId(id));
	}

	private boolean hasPlayersNearby(GameBuilder game) {
		Location pos = game.getLocation();
		Iterable<Player> players = pos.getWorld().getPlayers();
		for (Player player : players) {
			if (player.getLocation().distanceSquared(pos) < Math.pow(settings.getMaxDistanceToRun(), 2)) {
				return true;
			}
		}

		return false;
	}

	private static String parseId(String id) {
		return id.toLowerCase();
	}
}
