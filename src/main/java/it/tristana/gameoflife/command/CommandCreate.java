package it.tristana.gameoflife.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import it.tristana.commons.command.SubCommand;

public class CommandCreate extends SubCommand {

	public CommandCreate(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

	}
	
	@Override
	protected List<String> onTab(CommandSender sender, String[] args) {
		return null;
	}

	@Override
	protected String getHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "<x1> <y1> <z1> <x2> <y2> <z2> [world]";
	}

	@Override
	protected int getMinRequiredParameters() {
		return 6;
	}
}
