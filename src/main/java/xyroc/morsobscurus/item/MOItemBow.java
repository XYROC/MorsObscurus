package xyroc.morsobscurus.item;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import xyroc.morsobscurus.MorsObscurus;

public class MOItemBow extends ItemBow {

	private float damageAmplification;

	public MOItemBow(String name, int durability, float damageAmplification) {
		super();
		this.damageAmplification = damageAmplification;
		setCreativeTab(MorsObscurus.tab);
		setNoRepair();
		setMaxStackSize(1);
	}

	// ItemBow

	public void writeToNBT(ItemStack item, double damage, float damageAmplification) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setFloat("tm_damageAmp", damageAmplification);
		item.setTagInfo("tm_data", nbt);
	}

	/**
	 * 
	 * @param item
	 *            the itemStack
	 * @return returns an double[] with a size of 2. damage: data[0],
	 *         damageAmplification: data[1]
	 */
	public double[] readFromNBT(ItemStack item) {
		if (item.getTagCompound() == null) {
			MorsObscurus.logger.error("Failed to read NBT Data from " + item + ". (null)");
			return null;
		}
		double[] data = new double[2];
		NBTTagCompound nbt = item.getTagCompound().getCompoundTag("tm_data");
		data[1] = damageAmplification = nbt.getFloat("tm_damageAmp");
		return data;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		double[] data = readFromNBT(stack);
		if (data[0] != 0D)
			tooltip.add("Damage Amplification: " + data[0] * 100F + " %");

	}

}
