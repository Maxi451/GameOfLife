package it.tristana.gameoflife;

import it.tristana.commons.helper.PluginDraft;
import it.tristana.gameoflife.command.GolCommand;
import it.tristana.gameoflife.config.ConfigPlugin;

public class Main extends PluginDraft {

	@Override
	public void onEnable() {
		registerCommand(this, GolCommand.class, "gol", ConfigPlugin.FILE_NAME);
	}
	
	@Override
	public void onDisable() {
		
	}
}
