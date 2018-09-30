package xyroc.morsobscurus.item;

import java.util.List;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import xyroc.morsobscurus.MorsObscurus;

public class MOItemSword extends ItemSword {

	private float damageAmplification;

	public MOItemSword(String name, ToolMaterial toolMaterial, float damageAmplification) {
		super(toolMaterial);
		this.damageAmplification = damageAmplification;
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MorsObscurus.tab);
		setNoRepair();
		setMaxStackSize(1);
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> map = HashMultimap.<String, AttributeModifier>create();
		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			map.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER,
					"Weapon modifier", (double) (3.0 + getAttackDamage()) * (damageAmplification + 1F), 0));
			
			map.put(SharedMonsterAttributes.ATTACK_SPEED.getName(),
					new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", -2.4000000953674316D, 0));
		}
		return map;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(TextFormatting.BLUE + "Damage Amplification: " + damageAmplification * 100F + " %");
	}

	public float getDamageAmplification() {
		return damageAmplification;
	}

}
