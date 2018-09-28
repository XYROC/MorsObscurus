package xyroc.morsobscurus.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import xyroc.morsobscurus.block.tile.TileEntityEssenceStorage;

public class BlockEssenceStorage extends BlockContainer{

	public BlockEssenceStorage() {
		super(Material.ROCK);
		setUnlocalizedName("essence_storage");
		setRegistryName("essence_storage");
		setSoundType(SoundType.STONE);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityEssenceStorage();
	}

}
