/*
 * Copyright (c) 2017. Azuxul, All Rights Reserved.
 */

package fr.azuxul.showarmsstand;

import fr.azuxul.showarmsstand.item.ShowingArmsArmorStand;
import fr.azuxul.showarmsstand.item.SmallShowingArmsArmorStand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * ShowArmsStand class of ShowArmsStand mod
 *
 * @author Azuxul
 */

@Mod(modid = ShowArmsStand.MODID, version = ShowArmsStand.VERSION, name = ShowArmsStand.NAME)

public class ShowArmsStand {

    public static final String MODID = "showarmsstand";
    public static final String NAME = "ShowArmsStand";
    public static final String VERSION = "1.3.1";
    public static final Item itemShowingArmsArmorStand = new ShowingArmsArmorStand();
    public static final Item itemSmallShowingArmsArmorStand = new SmallShowingArmsArmorStand();
    public static final Item itemSwitchHandWand = new Item().setUnlocalizedName("switchhandwand").setCreativeTab(CreativeTabs.TOOLS);

    @EventHandler
    public static void init(FMLInitializationEvent event) {

        GameRegistry.register(itemShowingArmsArmorStand, new ResourceLocation(ShowArmsStand.MODID + ":showingarmsarmorstand"));
        GameRegistry.register(itemSmallShowingArmsArmorStand, new ResourceLocation(ShowArmsStand.MODID + ":smallshowingarmsarmorstand"));
        GameRegistry.register(itemSwitchHandWand, new ResourceLocation(ShowArmsStand.MODID + ":switchhandwand"));

        GameRegistry.addShapedRecipe(new ItemStack(itemSwitchHandWand), "A A", " A ", "A  ", 'A', Items.STICK);
        GameRegistry.addShapedRecipe(new ItemStack(itemShowingArmsArmorStand), "BAB", 'A', Items.ARMOR_STAND, 'B', Items.STICK);
        GameRegistry.addShapedRecipe(new ItemStack(itemSmallShowingArmsArmorStand), "A", 'A', Items.ARMOR_STAND);
        GameRegistry.addShapedRecipe(new ItemStack(Items.ARMOR_STAND), "A", 'A', itemShowingArmsArmorStand);
        GameRegistry.addShapedRecipe(new ItemStack(Items.ARMOR_STAND, 0, 2), "A", 'A', itemSmallShowingArmsArmorStand);

        if (event.getSide().isClient()) {

            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSmallShowingArmsArmorStand, 0, new ModelResourceLocation(ShowArmsStand.MODID + ":smallshowingarmsarmorstand", "inventory"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemShowingArmsArmorStand, 0, new ModelResourceLocation(ShowArmsStand.MODID + ":showingarmsarmorstand", "inventory"));
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(itemSwitchHandWand, 0, new ModelResourceLocation(ShowArmsStand.MODID + ":switchhandwand", "inventory"));
        }

        MinecraftForge.EVENT_BUS.register(new Events());
    }
}
