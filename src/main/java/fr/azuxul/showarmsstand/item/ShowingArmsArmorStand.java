package fr.azuxul.showarmsstand.item;

import fr.azuxul.showarmsstand.MathHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class ShowingArmsArmorStand extends Item {

    public ShowingArmsArmorStand() {

        this.setUnlocalizedName("showingarmsarmorstand");
        this.setCreativeTab(CreativeTabs.DECORATIONS);
        this.setMaxStackSize(16);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (side == EnumFacing.DOWN) {
            return EnumActionResult.FAIL;
        } else {
            boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
            BlockPos blockPos = flag ? pos : pos.offset(side);

            if (!playerIn.canPlayerEdit(blockPos, side, stack)) {
                return EnumActionResult.FAIL;
            } else {
                BlockPos blockPos1 = blockPos.up();
                boolean flag1 = !worldIn.isAirBlock(blockPos) && !worldIn.getBlockState(blockPos).getBlock().isReplaceable(worldIn, blockPos);
                flag1 |= !worldIn.isAirBlock(blockPos1) && !worldIn.getBlockState(blockPos1).getBlock().isReplaceable(worldIn, blockPos1);

                if (flag1) {
                    return EnumActionResult.FAIL;
                } else {
                    double x = (double) blockPos.getX();
                    double y = (double) blockPos.getY();
                    double z = (double) blockPos.getZ();
                    List list = worldIn.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(x, y, z, x + 1.0D, y + 2.0D, z + 1.0D));

                    if (!list.isEmpty()) {

                        return EnumActionResult.FAIL;
                    } else {
                        if (!worldIn.isRemote) {
                            worldIn.setBlockToAir(blockPos);
                            worldIn.setBlockToAir(blockPos1);
                            EntityArmorStand entityarmorstand = new EntityArmorStand(worldIn, x + 0.5D, y, z + 0.5D);
                            float f3 = (float) net.minecraft.util.math.MathHelper.floor((net.minecraft.util.math.MathHelper.wrapDegrees(playerIn.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                            entityarmorstand.setLocationAndAngles(x + 0.5D, y, z + 0.5D, f3, 0.0F);
                            MathHelper.applyRandomRotations(entityarmorstand, worldIn.rand);
                            NBTTagCompound nbttagcompound = stack.getTagCompound();

                            if (nbttagcompound != null && nbttagcompound.hasKey("EntityTag", 10)) {
                                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                                entityarmorstand.writeToNBTOptional(nbttagcompound1);
                                nbttagcompound1.merge(nbttagcompound.getCompoundTag("EntityTag"));
                                entityarmorstand.readFromNBT(nbttagcompound1);
                            }

                            NBTTagCompound nbt = new NBTTagCompound();

                            entityarmorstand.writeToNBTOptional(nbt);
                            nbt.setBoolean("ShowArms", true);
                            entityarmorstand.readFromNBT(nbt);

                            worldIn.spawnEntity(entityarmorstand);

                            stack.stackSize--;
                        }

                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
    }

}
