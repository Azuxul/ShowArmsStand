package fr.azuxul.showarmsstand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Main.MODID, version = Main.VERSION, name = Main.NAME)

public class Main {

    public static final String MODID = "showarmsstand";
    public static final String NAME = "ShowArmsStand";
    public static final String VERSION = "1.2.2";
    public static final Item itemShowingArmsArmorStand = new ShowingArmsArmorStand();
    public static final Item itemSmallShowingArmsArmorStand = new SmallShowingArmsArmorStand();

    @EventHandler
    public static void init(FMLInitializationEvent event) {

        GameRegistry.registerItem(itemShowingArmsArmorStand, "showingarmsarmorstand");
        GameRegistry.registerItem(itemSmallShowingArmsArmorStand, "smallshowingarmsarmorstand");

        GameRegistry.addShapedRecipe(new ItemStack(itemShowingArmsArmorStand), "BAB", 'A', Items.armor_stand, 'B', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(itemSmallShowingArmsArmorStand), "A", 'A', Items.armor_stand);
        GameRegistry.addShapedRecipe(new ItemStack(Items.armor_stand), "A", 'A', itemShowingArmsArmorStand);
        GameRegistry.addShapedRecipe(new ItemStack(Items.armor_stand, 0, 2), "A", 'A', itemSmallShowingArmsArmorStand);

        if (event.getSide().isClient()) {

            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSmallShowingArmsArmorStand, 0, new ModelResourceLocation(Main.MODID + ":smallshowingarmsarmorstand", "inventory"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemShowingArmsArmorStand, 0, new ModelResourceLocation(Main.MODID + ":showingarmsarmorstand", "inventory"));
        }
    }
}
