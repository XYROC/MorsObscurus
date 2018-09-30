package xyroc.morsobscurus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyroc.morsobscurus.MorsObscurus;
import xyroc.morsobscurus.block.MOBlock;

public class ItemEssenceStorage extends ItemBlock{

	public ItemEssenceStorage() {
		super(MOBlock.essenceStorage);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + ""
				+ "A crate made to store massive amounts of essence vials.");
	}
	
	

}
