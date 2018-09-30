package xyroc.morsobscurus.gui.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xyroc.morsobscurus.item.MOItem;

public class SlotVial extends Slot {

	public SlotVial(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return stack.getItem() == MOItem.vial;
	}

}
