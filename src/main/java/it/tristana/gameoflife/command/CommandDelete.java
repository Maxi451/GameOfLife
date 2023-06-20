package it.tristana.gameoflife.command;

import org.bukkit.command.CommandSender;

import it.tristana.commons.helper.CommonsHelper;

public class CommandDelete extends GolSubCommand {

	public CommandDelete(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		CommonsHelper.info(sender, gamesManager.deleteGame(args[1]) ? settings.getCommandDeleteExecuted() : settings.getInvalidGame());
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return settings.getCommandDeleteHelp();
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "<id>";
	}
	
	@Override
	protected int getMinRequiredParameters() {
		return 1;
	}
}
