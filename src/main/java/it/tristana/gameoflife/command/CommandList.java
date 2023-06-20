package it.tristana.gameoflife.command;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bukkit.command.CommandSender;

import it.tristana.commons.helper.CommonsHelper;
import it.tristana.gameoflife.game.GameBuilder;

public class CommandList extends GolSubCommand {

	private static final Comparator<Entry<String, GameBuilder>> COMPARATOR = (e1, e2) -> {
		int cmp = Boolean.compare(e1.getValue().isPaused(), e2.getValue().isPaused());
		if (cmp != 0) {
			return cmp;
		}

		return e1.getKey().compareTo(e2.getKey());
	};

	public CommandList(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		CommonsHelper.info(sender, settings.getCommandListExecuted());
		SortedSet<Entry<String, GameBuilder>> set = new TreeSet<>(COMPARATOR);
		set.addAll(gamesManager.getGames().entrySet());
		set.forEach(entry -> {
			GameBuilder game = entry.getValue();
			CommonsHelper.info(sender, String.format("&6%s &r[%s]: %s", entry.getKey(), CommonsHelper.locationToString(game.getLocation()), game.isPaused() ? settings.getPausedWord() : settings.getRunningWord()));
		});
	}

	@Override
	protected String getHelp() {
		return settings.getCommandListHelp();
	}
}
