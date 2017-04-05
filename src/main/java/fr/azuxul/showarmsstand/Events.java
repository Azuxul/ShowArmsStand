package fr.azuxul.showarmsstand;

import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Events listener
 *
 * @author Azuxul
 * @version 1.0
 */
public class Events {

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {

        ItemStack item = ((EntityPlayer) event.getEntity()).getHeldItemMainhand();

        if (!(event.getTarget() instanceof EntityArmorStand)) {
            return;
        }

        EntityArmorStand entityArmorStand = (EntityArmorStand) event.getTarget();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        entityArmorStand.writeEntityToNBT(nbtTagCompound);

        if (item == null) {

            if (entityArmorStand.getHeldItemMainhand() == null && entityArmorStand.getHeldItemOffhand() != null && !hasDisabledSlots(nbtTagCompound, EntityEquipmentSlot.MAINHAND, true, true, false)) {

                ((EntityPlayer) event.getEntity()).setHeldItem(event.getHand(), entityArmorStand.getHeldItemOffhand());
                entityArmorStand.setHeldItem(EnumHand.OFF_HAND, null);
            }


        } else if (ShowArmsStand.itemSwitchHandWand.equals(item.getItem()) && !hasDisabledSlots(nbtTagCompound, EntityEquipmentSlot.MAINHAND, true, true, true) && !hasDisabledSlots(nbtTagCompound, EntityEquipmentSlot.OFFHAND, true, true, true)) {

            event.setCanceled(true);

            if (event.getHand() != EnumHand.MAIN_HAND) {
                return;
            }

            if (nbtTagCompound.getBoolean("ShowArms")) {

                ItemStack hand1 = entityArmorStand.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
                ItemStack hand2 = entityArmorStand.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

                entityArmorStand.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, hand2);
                entityArmorStand.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, hand1);

            }
        }

    }

    private boolean hasDisabledSlots(NBTTagCompound nbtTagCompound, EntityEquipmentSlot slot, boolean remove, boolean replace, boolean place) {

        if (nbtTagCompound.hasKey("DisabledSlots")) {
            int disabledSlots = nbtTagCompound.getInteger("DisabledSlots");

            if ((disabledSlots & 1 << slot.getIndex()) != 0 && remove)
                return true;

            if ((disabledSlots & 1 << slot.getIndex() + 8) != 0 && replace)
                return true;

            if ((disabledSlots & 1 << slot.getIndex() + 16) != 0 && place)
                return true;
        }

        return false;
    }

}
