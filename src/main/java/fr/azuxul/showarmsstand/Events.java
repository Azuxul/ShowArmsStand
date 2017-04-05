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

        if (!(event.getTarget() instanceof EntityArmorStand) || item == null || !ShowArmsStand.itemSwitchHandWand.equals(item.getItem())) {
            return;
        }

        event.setCanceled(true);

        if (event.getHand() != EnumHand.MAIN_HAND) {
            return;
        }

        EntityArmorStand entityArmorStand = (EntityArmorStand) event.getTarget();

        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        entityArmorStand.writeEntityToNBT(nbtTagCompound);


        if (nbtTagCompound.getBoolean("ShowArms")) {

            ItemStack hand1 = entityArmorStand.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
            ItemStack hand2 = entityArmorStand.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND);

            entityArmorStand.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, hand2);
            entityArmorStand.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, hand1);

        }

    }

}
