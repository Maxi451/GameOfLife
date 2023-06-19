package it.tristana.gameoflife.command;

import org.bukkit.command.CommandSender;

import it.tristana.commons.command.SubCommand;

public class CommandPause extends SubCommand {

	public CommandPause(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length == 1) {
			
		}
	}

	@Override
	protected boolean requiresPlayer() {
		return false;
	}

	@Override
	protected String getHelp() {
		return "pauses";
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
