package azuxul.showarmsstand;

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
	public static final String VERSION = "1.1";
	
	public static Item ShowingArmsArmorStand;
	public static Item SmallShowingArmsArmorStand;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		ShowingArmsArmorStand = new ShowingArmsArmorStand();
		SmallShowingArmsArmorStand = new SmallShowingArmsArmorStand();
		
		GameRegistry.registerItem(ShowingArmsArmorStand, "showingarmsarmorstand");
		GameRegistry.registerItem(SmallShowingArmsArmorStand, "smallshowingarmsarmorstand");
		
		GameRegistry.addShapedRecipe(new ItemStack(ShowingArmsArmorStand), "BAB", 'A', Items.armor_stand, 'B', Items.stick);
		GameRegistry.addShapedRecipe(new ItemStack(SmallShowingArmsArmorStand), "A", 'A', Items.armor_stand);
		GameRegistry.addShapedRecipe(new ItemStack(Items.armor_stand), "A", 'A', ShowingArmsArmorStand);
		GameRegistry.addShapedRecipe(new ItemStack(Items.armor_stand, 0, 2), "A", 'A', SmallShowingArmsArmorStand);
		
		if(event.getSide().isClient()){
			
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(SmallShowingArmsArmorStand, 0, new ModelResourceLocation(Main.MODID+ ":smallshowingarmsarmorstand", "inventory"));
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(ShowingArmsArmorStand, 0, new ModelResourceLocation(Main.MODID+ ":showingarmsarmorstand", "inventory"));
		}
	}
}
