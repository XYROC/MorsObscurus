package xyroc.morsobscurus.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyroc.morsobscurus.MorsObscurus;
import xyroc.morsobscurus.Reference;
import xyroc.morsobscurus.block.MOBlock;
import xyroc.morsobscurus.item.ItemEssenceStorage;
import xyroc.morsobscurus.item.MOItem;
import xyroc.morsobscurus.item.MOItemSword;

public class EventManager {

	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		MorsObscurus.logger.info("Loading Blocks");
		event.getRegistry().register(MOBlock.looseStoneBrick);
		event.getRegistry().register(MOBlock.essenceStorage);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event) {
		MorsObscurus.logger.info("Loading Items");
		event.getRegistry().register(MOItem.deathEssence);
		event.getRegistry().register(MOItem.vial);
		event.getRegistry().register(MOItem.bloodSword);
		event.getRegistry().register(
				new ItemBlock(MOBlock.looseStoneBrick).setRegistryName(MOBlock.looseStoneBrick.getRegistryName()));
		event.getRegistry()
				.register(new ItemEssenceStorage().setRegistryName(MOBlock.essenceStorage.getRegistryName()));
	}

	@SubscribeEvent
	public void registerRenderers(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(MOItem.deathEssence, 0,
				new ModelResourceLocation(new ResourceLocation(Reference.MODID + ":death_essence"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(MOItem.vial, 0,
				new ModelResourceLocation(new ResourceLocation(Reference.MODID + ":vial"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(MOItem.vial, 1,
				new ModelResourceLocation(new ResourceLocation(Reference.MODID + ":vial"), "inventory"));
		ModelLoader.setCustomModelResourceLocation(MOItem.bloodSword, 0,
				new ModelResourceLocation(new ResourceLocation(Reference.MODID + ":blood_sword"), "inventory"));

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MOBlock.looseStoneBrick), 0,
				new ModelResourceLocation(Reference.MODID + ":loose_stone_brick", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(MOBlock.essenceStorage), 0,
				new ModelResourceLocation(Reference.MODID + ":essence_storage", "inventory"));

	}

	@SubscribeEvent
	public void onLivingDrop(LivingDropsEvent event) {
		Random random = new Random();
		Entity entity = event.getEntity();
		if ((random.nextInt(12) < (5 - event.getLootingLevel() * 2)) && (entity instanceof EntityZombie)
				|| (entity instanceof EntitySkeleton)) {
			event.getDrops().add(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ,
					new ItemStack(MOItem.deathEssence, random.nextInt(+3) + 1 + event.getLootingLevel() * 2)));
		}
	}

}
