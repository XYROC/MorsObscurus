package xyroc.morsobscurus.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import xyroc.morsobscurus.MorsObscurus;
import xyroc.morsobscurus.Reference;
import xyroc.morsobscurus.block.MOBlock;
import xyroc.morsobscurus.item.MOItem;

public class ClientProxy extends ServerProxy{
	
	@Override
	public void load() {
		MorsObscurus.logger.info("Loading Client Proxy");
	}

}
