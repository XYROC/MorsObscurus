package xyroc.morsobscurus.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import xyroc.morsobscurus.item.ItemVial;
import xyroc.morsobscurus.item.MOItem;
import xyroc.morsobscurus.util.BigItemStackHelper;

public class TileEntityEssenceStorage extends TileEntity implements IInventory {

	private final int size = 12;

	private NonNullList<ItemStack> storage = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
	// TileEntityFurnace

	public TileEntityEssenceStorage() {
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < storage.size(); ++i)
        {
            ItemStack itemstack = storage.get(i);

            if (!itemstack.isEmpty())
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                BigItemStackHelper.writeItemStackToNBT(itemstack, nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        if (!nbttaglist.hasNoTags())
        {
        	compound.setTag("Items", nbttaglist);
        }

		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.storage = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot") & 255;

            if (j >= 0 && j < storage.size())
            {
                storage.set(j, BigItemStackHelper.readItemStackFromNBT(nbttagcompound));
            }
        }
	}

	@Override
	public String getName() {
		return "Essence Storage";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		return size;
	}

	public boolean isEmpty() {
		for (ItemStack itemstack : this.storage) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return storage.get(index);
	}

	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.storage, index, count);
	}

	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.storage, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		storage.set(index, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 1024;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {

	}

	@Override
	public void closeInventory(EntityPlayer player) {

	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return stack.getItem() == MOItem.vial;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {

	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {

	}

}
