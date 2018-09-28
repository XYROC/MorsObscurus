package xyroc.morsobscurus.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import xyroc.morsobscurus.MorsObscurus;

public class MOItem extends Item {

	public static final Item deathEssence = new ItemDeathEssence();
	public static final Item vial = new ItemVial();

	public MOItem(String name) {
		this(name, true);
	}

	public MOItem(String name, boolean setUnlocalizedName) {
		super();
		setRegistryName(name);
		if (setUnlocalizedName)
			setUnlocalizedName(name);
	}

	public MOItem(String name, int durability) {
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setMaxDamage(durability);
		setNoRepair();
	}

	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		NBTTagCompound nbt = new NBTTagCompound();
		stack.setTagInfo("mo_data", nbt);
	}

	/**
	 * 
	 * @param item
	 *            the item
	 * @return returns the Mors Obscurus NBT-TagCompound of the item
	 */
	public NBTTagCompound getNBTTag(ItemStack item) {
		if (item.getTagCompound() == null) {
			MorsObscurus.logger.error("Failed to read NBT Data from " + item + ". (null)");
			return null;
		}
		NBTTagCompound data = item.getTagCompound().getCompoundTag("mo_data");
		return data;
	}

}
