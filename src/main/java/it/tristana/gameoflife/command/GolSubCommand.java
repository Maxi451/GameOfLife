package it.tristana.gameoflife.command;

import it.tristana.commons.command.SubCommand;
import it.tristana.gameoflife.Main;
import it.tristana.gameoflife.config.SettingsPlugin;
import it.tristana.gameoflife.game.GamesManager;

public abstract class GolSubCommand extends SubCommand {

	protected final SettingsPlugin settings;
	protected final GamesManager gamesManager;
	
	public GolSubCommand(GolCommand main, String name, String permission) {
		super(main, name, permission);
		Main plugin = main.getPlugin();
		this.settings = plugin.getSettingsPlugin();
		this.gamesManager = plugin.getGamesManager();
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}
}
