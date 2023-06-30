package it.tristana.gameoflife.game;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import it.tristana.commons.config.Config;
import it.tristana.commons.helper.CommonsHelper;
import it.tristana.gameoflife.Main;

public class SavedGames extends Config {
	
	private static final String WORLD = "world";
	private static final String X = "x";
	private static final String Y = "y";
	private static final String Z = "z";

	private static final String START = "start";
	private static final String END = "end";
	
	private GamesManager manager;
	
	public SavedGames(File folder, GamesManager manager) {
		super(new File(folder, "saved-games.yml"));
		this.manager = manager;
	}

	@Override
	protected void createDefault() {}
	
	@Override
	protected boolean shouldUpdateConfig() {
		return false;
	}
	
	public void saveGames(Map<String, GameBuilder> games) {
		games.forEach((name, game) -> {
			String root = name + ".";
			setLocation(game.getLocation(), root + START);
			setLocation(game.getEndLocation(), root + END);
		});
	}
	
	public Map<String, GameBuilder> loadGames() {
		Map<String, GameBuilder> map = new HashMap<>();
		fileConfiguration.getKeys(false).forEach(name -> {
			try {
				String root = name + ".";
				map.put(name, manager.createGame(getLocation(root + START), getLocation(root + END)));
			} catch (Exception e) {
				CommonsHelper.consoleInfo("&cCan't load game " + name + "!");
				JavaPlugin.getPlugin(Main.class).writeThrowableOnErrorsFile(e);
			}
		});
		return map;
	}
	
	private void setLocation(Location pos, String root) {
		if (!root.endsWith(".")) {
			root += ".";
		}

		set(root + WORLD, pos.getWorld().getName());
		set(root + X, pos.getBlockX());
		set(root + Y, pos.getBlockY());
		set(root + Z, pos.getBlockZ());
	}
	
	private Location getLocation(String root) {
		if (!root.endsWith(".")) {
			root += ".";
		}
		
		World world = Bukkit.getWorld(getString(root + WORLD));
		int x = Integer.parseInt(getString(root + X));
		int y = Integer.parseInt(getString(root + Y));
		int z = Integer.parseInt(getString(root + Z));
		return new Location(world, x, y, z);
	}
}
