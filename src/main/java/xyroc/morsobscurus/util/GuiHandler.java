package xyroc.morsobscurus.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import xyroc.morsobscurus.Reference;
import xyroc.morsobscurus.block.tile.TileEntityEssenceStorage;
import xyroc.morsobscurus.gui.GuiEssenceStorage;
import xyroc.morsobscurus.gui.container.ContainerEssenceStorage;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Reference.GUI_ESSENCE_STORAGE:
			return new ContainerEssenceStorage(player.inventory,
					(TileEntityEssenceStorage) world.getTileEntity(new BlockPos(x, y, z)));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case Reference.GUI_ESSENCE_STORAGE:
			return new GuiEssenceStorage(new ContainerEssenceStorage(player.inventory,
					(TileEntityEssenceStorage) world.getTileEntity(new BlockPos(x, y, z))));
		default:
			return null;
		}
	}

}
