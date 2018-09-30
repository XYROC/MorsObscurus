package xyroc.morsobscurus.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class BigItemStackHelper {
	
	/**
	 * This Method makes it possible to save ItemStacks with a stack-size up to Integer.MAX_VALUE (original maximum is 127)
	 * This is only possible if the saved ItemStack is loaded with BigItemStackHelper.readItemStackFromNBT (the item would disappear otherwise)
	 */
	public static NBTTagCompound writeItemStackToNBT(ItemStack stack, NBTTagCompound nbt) {
		stack.writeToNBT(nbt);
		nbt.setInteger("Count", stack.getCount());
		return nbt;
	}
	
	public static ItemStack readItemStackFromNBT(NBTTagCompound nbt) {
		ItemStack stack = new ItemStack(nbt);
    	stack.setCount(nbt.getInteger("Count"));
    	return stack;
	}

}
