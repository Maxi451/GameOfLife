package it.tristana.gameoflife;

import java.io.File;

import it.tristana.commons.helper.PluginDraft;
import it.tristana.commons.interfaces.Reloadable;
import it.tristana.gameoflife.command.GolCommand;
import it.tristana.gameoflife.config.ConfigPlugin;
import it.tristana.gameoflife.config.SettingsPlugin;
import it.tristana.gameoflife.game.GamesManager;
import it.tristana.gameoflife.listener.BlockListener;

public class Main extends PluginDraft implements Reloadable {

	private File folder;
	private SettingsPlugin settings;
	
	private GamesManager gamesManager;
	
	@Override
	public void onEnable() {
		folder = getFolder();
		setupConfigs();
		loadManagers();
		register(new BlockListener());
		registerCommand(this, GolCommand.class, "gol", ConfigPlugin.FILE_NAME);
	}

	@Override
	public void reload() {
		settings.reload();
		gamesManager.reload();
	}
	
	public SettingsPlugin getSettingsPlugin() {
		return settings;
	}
	
	public GamesManager getGamesManager() {
		return gamesManager;
	}
	
	private void setupConfigs() {
		settings = new SettingsPlugin(folder);
	}
	
	private void loadManagers() {
		gamesManager = new GamesManager(this, settings);
	}
}
