package it.tristana.gameoflife.command;

import it.tristana.commons.command.MainCommand;
import it.tristana.commons.config.SettingsDefaultCommands;
import it.tristana.gameoflife.Main;

public class GolCommand extends MainCommand<Main> {

	public GolCommand(Main plugin, SettingsDefaultCommands settings, String command) {
		super(plugin, settings, command);
		String adminPerms = getAdminPerms();
		registerSubCommand(new CommandCreate(this, "create", adminPerms));
		registerSubCommand(new CommandPause(this, "pause", adminPerms));
	}
	
	@Override
	protected String getAdminPerms() {
		return "gameoflife.admin";
	}
}
