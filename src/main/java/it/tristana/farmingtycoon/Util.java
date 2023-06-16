package it.tristana.farmingtycoon;

import org.bukkit.Location;
import org.bukkit.Material;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.EditSessionBuilder;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.World;

public class Util {

	private Util() {}

	public static void pasteBlocks(Material[][][] types, Location pos) throws WorldEditException {
		if (types.length == 0 || types[0].length == 0) {
			return;
		}

		World world = new BukkitWorld(pos.getWorld());
		int x = pos.getBlockX();
		int y = pos.getBlockY();
		int z = pos.getBlockZ();
		EditSessionBuilder builder = WorldEdit.getInstance().newEditSessionBuilder().world(world);
		try (EditSession editSession = builder.build()) {
			BlockArrayClipboard a = new BlockArrayClipboard(new CuboidRegion(world, BlockVector3.at(x, y, z), BlockVector3.at(x + types.length, y + types[0].length, z + types[0][0].length)));
			for (int i = 0; i < types.length; i ++) {
				for (int ii = 0; ii < types[i].length; ii ++) {
					for (int iii = 0; iii < types[ii].length; iii ++) {
						a.setBlock(BlockVector3.at(x + i, y, z + ii), BukkitAdapter.asBlockType(types[i][ii][ii]).getDefaultState());
					}
				}
			}
			Operations.complete(a.commit());
		}
	}
}
