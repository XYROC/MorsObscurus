package xyroc.morsobscurus.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemDeathEssence extends MOItem {

	public ItemDeathEssence() {
		super("death_essence");
		setMaxStackSize(64);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.GRAY + "" + TextFormatting.ITALIC + ""
				+ "A dark essence found in the corpses of several undead monsters.");
	}

}
