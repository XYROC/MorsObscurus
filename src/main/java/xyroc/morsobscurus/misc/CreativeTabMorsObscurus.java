package xyroc.morsobscurus.misc;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyroc.morsobscurus.block.MOBlock;

public class CreativeTabMorsObscurus extends CreativeTabs{

	public CreativeTabMorsObscurus() {
		super("morsobscurus");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(MOBlock.essenceStorage);
	}

}
