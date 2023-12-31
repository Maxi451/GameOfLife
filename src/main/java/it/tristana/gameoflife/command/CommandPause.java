package it.tristana.gameoflife.command;

import org.bukkit.command.CommandSender;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.gameoflife.game.GameBuilder;

public class CommandPause extends GolSubCommand {

	public CommandPause(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		GameBuilder game = gamesManager.getGame(args[1]);
		if (game == null) {
			CommonsHelper.info(sender, settings.getInvalidGame());
			return;
		}
		
		CommonsHelper.info(sender, game.togglePause() ? settings.getCommandPausePaused() : settings.getCommandPauseResumed());
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return settings.getCommandPauseHelp();
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
