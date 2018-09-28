package xyroc.morsobscurus.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLooseStoneBrick extends BlockFalling {

	public BlockLooseStoneBrick() {
		super(Material.ROCK);
		setUnlocalizedName("loose_stone_brick");
		setRegistryName("loose_stone_brick");
		setSoundType(blockSoundType.STONE);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		fallInstantly = false;
	}

	// BlockGravel BlockSand

	@Override
	public void onLanded(World worldIn, Entity entityIn) {
		checkFallable(worldIn, new BlockPos(entityIn.getPosition().getX(), entityIn.getPosition().getY() - 1,
				entityIn.getPosition().getZ()));
		super.onLanded(worldIn, entityIn);
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0) {
			int i = 32;

			if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32))) {
				if (!worldIn.isRemote) {
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double) pos.getX() + 0.5D,
							(double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntity(entityfallingblock);
				}
			} else {
				IBlockState state = worldIn.getBlockState(pos);
				worldIn.setBlockToAir(pos);
				BlockPos blockpos;

				for (blockpos = pos
						.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos)))
								&& blockpos.getY() > 0; blockpos = blockpos.down()) {
					;
				}

				if (blockpos.getY() > 0) {
					worldIn.setBlockState(blockpos.up(), state); // Forge: Fix loss of state information during world
																	// gen.
				}
			}
		}
	}

	@Override
	protected void onStartFalling(EntityFallingBlock fallingEntity) {
		if (!fallingEntity.world.isRemote)
			return;
		fallingEntity.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, fallingEntity.posX, fallingEntity.posY+1,
				fallingEntity.posZ, 0, 0, 0, 0);
		fallingEntity.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, fallingEntity.posX - 1, fallingEntity.posY+1,
				fallingEntity.posZ, 0, 0, 0, 0);
		fallingEntity.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, fallingEntity.posX + 1, fallingEntity.posY+1,
				fallingEntity.posZ, 0, 0, 0, 0);
		fallingEntity.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, fallingEntity.posX, fallingEntity.posY+1,
				fallingEntity.posZ - 1, 0, 0, 0, 0);
		fallingEntity.world.spawnParticle(EnumParticleTypes.BLOCK_DUST, fallingEntity.posX, fallingEntity.posY+1,
				fallingEntity.posZ + 1, 0, 0, 0, 0);

	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
	}

	/**
	 * How many world ticks before ticking
	 */
	public int tickRate(World worldIn) {
		return 2;
	}

	public static boolean canFallInto(World worldIn, BlockPos pos) {
		if (worldIn.isAirBlock(pos))
			return true;
		Block block = worldIn.getBlockState(pos).getBlock();
		Material material = block.getMaterial(worldIn.getBlockState(pos));
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER
				|| material == Material.LAVA;
	}

	@Override
	public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_) {
		worldIn.setBlockState(pos, MOBlock.looseStoneBrick.getDefaultState(), 3);
		if (!worldIn.isRemote)
			return;
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getY()+1, pos.getZ(), 0, 0.5, 0, 0);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX() - 1, pos.getY()+1, pos.getZ(), 0, 0.5, 0, 0);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX() + 1, pos.getY()+1, pos.getZ(), 0, 0.5, 0, 0);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getY()+1, pos.getZ() - 1, 0, 0.5, 0, 0);
		worldIn.spawnParticle(EnumParticleTypes.BLOCK_DUST, pos.getX(), pos.getY()+1, pos.getZ() + 1, 0, 0.5, 0, 0);
	}

	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
		return MapColor.STONE;
	}

	// BlockGravel

}
