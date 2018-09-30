package xyroc.morsobscurus.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyroc.morsobscurus.MorsObscurus;

public class ItemVial extends MOItem {

	public ItemVial() {
		super("vial",false);
		setMaxStackSize(512);
		setMaxDamage(0);
		setNoRepair();
		setHasSubtypes(true);
		addPropertyOverride(new ResourceLocation("content"), new IItemPropertyGetter() {
			
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				return stack.getItemDamage();
			}

		});
		//ItemElytra
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(getCreativeTab() != tab) return;
		ItemStack vialEmpty = new ItemStack(this);
		vialEmpty.setItemDamage(0);

		ItemStack vialDeathEssence = new ItemStack(this);
		vialDeathEssence.setItemDamage(1);

		items.add(vialEmpty);
		items.add(vialDeathEssence);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		switch (stack.getItemDamage()) {
		case 1:
			return "vial_death_essence";
		default:
			return "vial_empty";
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getItemDamage() == 1;
	}

}
