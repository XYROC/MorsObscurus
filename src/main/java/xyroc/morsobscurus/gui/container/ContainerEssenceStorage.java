package xyroc.morsobscurus.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import xyroc.morsobscurus.block.tile.TileEntityEssenceStorage;

public class ContainerEssenceStorage extends Container{

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}
	
	public ContainerEssenceStorage(IInventory playerInv, TileEntityEssenceStorage tile) {
		int i = -18;
		int j;
		int k;

		int index = 0;
		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 4; ++k) {
				addSlotToContainer(new SlotVial(tile, index++, 46 + k * 22, 16 + j * 19));
			}
		}

		for (j = 0; j < 3; ++j) {
			for (k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(playerInv, k + j * 9 + 9, 8 + k * 18, 102 + j * 18 + i));
			}
		}

		for (j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 160 + i));
		}
	}

	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 12)
            {
                if (!this.mergeItemStack(itemstack1, 12, 45, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 12, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
	
	

}
