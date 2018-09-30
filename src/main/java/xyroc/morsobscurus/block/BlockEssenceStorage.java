package xyroc.morsobscurus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyroc.morsobscurus.MorsObscurus;
import xyroc.morsobscurus.Reference;
import xyroc.morsobscurus.block.tile.TileEntityEssenceStorage;
import xyroc.morsobscurus.item.ItemEssenceStorage;

public class BlockEssenceStorage extends BlockContainer {

	public BlockEssenceStorage() {
		super(Material.ROCK);
		setUnlocalizedName("essence_storage");
		setRegistryName("essence_storage");
		setSoundType(SoundType.STONE);
		setCreativeTab(MorsObscurus.tab);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		/*
		 * if(playerIn.getActiveItemStack() == ItemStack.EMPTY) {
		 * 
		 * }
		 */
		playerIn.openGui(MorsObscurus.instance, Reference.GUI_ESSENCE_STORAGE, worldIn, pos.getX(), pos.getY(),
				pos.getZ());
		return true;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return null;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		if (stack.getTagCompound() == null || !stack.getTagCompound().hasKey("tile"))
			return;
		TileEntityEssenceStorage tile = (TileEntityEssenceStorage) worldIn.getTileEntity(pos);
		NBTTagCompound nbt = (NBTTagCompound) stack.getTagCompound().getTag("tile");
		nbt.setInteger("x", pos.getX());
		nbt.setInteger("y", pos.getY());
		nbt.setInteger("z", pos.getZ());
		tile.readFromNBT(nbt);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		ItemStack stack = new ItemStack(MOBlock.essenceStorage);
		TileEntityEssenceStorage tile = (TileEntityEssenceStorage) worldIn.getTileEntity(pos);
		NBTTagCompound nbt = stack.getTagCompound();
		NBTTagCompound tileData = new NBTTagCompound();
		if (nbt == null)
			nbt = new NBTTagCompound();
		tile.writeToNBT(tileData);
		tileData.setInteger("x", 0);
		tileData.setInteger("y", 0);
		tileData.setInteger("z", 0);
		nbt.setTag("tile", tileData);
		stack.setTagCompound(nbt);
		worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack));
		super.breakBlock(worldIn, pos, state);
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
