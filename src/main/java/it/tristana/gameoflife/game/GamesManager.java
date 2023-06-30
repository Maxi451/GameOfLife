package it.tristana.gameoflife.game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.World;
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

	public boolean addGame(String name, Location pos1, Location pos2) {
		GameBuilder game = createGame(pos1, pos2);
		if (game == null) {
			return false;
		}

		return true;
	}
	
	public void addGame(String name, GameBuilder game) {
		games.put(parseName(name), game);
	}

	GameBuilder createGame(Location pos1, Location pos2) {
		if (!CommonsHelper.sameWorld(pos1, pos2)) {
			return null;
		}

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
			return null;
		}

		World world = pos1.getWorld();
		return new GameBuilder(new GameOfLife(width, height), pos1vec.toLocation(world), pos2vec.toLocation(world), direction);
	}

	public Map<String, GameBuilder> getGames() {
		return games;
	}

	public boolean deleteGame(String name) {
		return games.remove(parseName(name)) != null;
	}

	public GameBuilder getGame(String name) {
		return games.get(parseName(name));
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

	private static String parseName(String name) {
		return name.toLowerCase();
	}
}
