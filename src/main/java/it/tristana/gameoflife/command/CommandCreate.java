package it.tristana.gameoflife.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.tristana.commons.helper.CommonsHelper;

public class CommandCreate extends GolSubCommand {

	public CommandCreate(GolCommand main, String name, String permission) {
		super(main, name, permission);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		World world;
		if (args.length >= 8) {
			world = Bukkit.getWorld(args[7]);
			if (world == null) {
				CommonsHelper.info(sender, settings.getInvalidWorld());
				return;
			}
		} else if (sender instanceof Player player) {
			world = player.getWorld();
		} else {
			CommonsHelper.info(sender, getHelpMessage());
			return;
		}

		int x1, y1, z1, x2, y2, z2;
		try {
			x1 = Integer.parseInt(args[2]);
			y1 = Integer.parseInt(args[3]);
			z1 = Integer.parseInt(args[4]);
			x2 = Integer.parseInt(args[5]);
			y2 = Integer.parseInt(args[6]);
			z2 = Integer.parseInt(args[7]);
		} catch (NumberFormatException e) {
			CommonsHelper.info(sender, settings.getInvalidNumber());
			return;
		}

		CommonsHelper.info(
				sender,
				gamesManager.create(
						args[1],
						new Location(world, x1, y1, z1),
						new Location(world, x2, y2, z2))
				? settings.getCommandCreateExecuted()
						: settings.getGameAlreadyExists());
	}

	@Override
	protected List<String> onTab(CommandSender sender, String[] args) {
		if (!(sender instanceof Player player)) {
			return null;
		}

		Block target = player.getTargetBlock(null, 5);
		Location pos = target == null ? player.getLocation() : target.getLocation();
		String value = switch (args.length) {
		case 2: // x1
		case 5: // x2
			yield Integer.toString(pos.getBlockX());
		case 3: // y1
		case 6: // y2
			yield Integer.toString(pos.getBlockY());
		case 4: // z1
		case 7: // z2
			yield Integer.toString(pos.getBlockZ());
		case 8: // world
			yield pos.getWorld().getName();
		default:
			yield null;
		};

		if (value == null) {
			return null;
		}

		return Arrays.asList(value);
	}

	@Override
	protected String getHelp() {
		return settings.getCommandCreateHelp();
	}

	@Override
	protected String getAdditionalHelpParameters() {
		return "<id> <x1> <y1> <z1> <x2> <y2> <z2> [world]";
	}

	@Override
	protected int getMinRequiredParameters() {
		return 7;
	}
}
