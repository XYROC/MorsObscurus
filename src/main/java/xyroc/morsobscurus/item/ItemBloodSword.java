package xyroc.morsobscurus.item;

import net.minecraftforge.common.util.EnumHelper;
import xyroc.morsobscurus.Reference;

public class ItemBloodSword extends MOItemSword {

	public ItemBloodSword() {
		super("blood_sword", EnumHelper.addToolMaterial(Reference.MODID + "blood", 0, 1024, 0, 7, 10), 1F);
	}

}
