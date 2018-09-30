package xyroc.morsobscurus.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import xyroc.morsobscurus.Reference;
import xyroc.morsobscurus.gui.container.ContainerEssenceStorage;

public class GuiEssenceStorage extends GuiContainer {
	
	ResourceLocation background = new ResourceLocation(Reference.MODID+":textures/gui/essence_storage.png");

	public GuiEssenceStorage(ContainerEssenceStorage inventorySlotsIn) {
		super(inventorySlotsIn);

	}

	@Override
	public void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		mc.renderEngine.bindTexture(background);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

}
