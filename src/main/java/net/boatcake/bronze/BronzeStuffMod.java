package net.boatcake.bronze;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=BronzeStuffMod.modID, name="Bronze stuff")
public class BronzeStuffMod {
	public static final String modID = "bronzestuff";
	@Instance
	public static BronzeStuffMod instance;
	private ItemArmor bronzeHelmet;
	private ItemArmor bronzeChestplate;
	private ItemArmor bronzeLeggings;
	private ItemArmor bronzeBoots;
	@SidedProxy(clientSide="net.boatcake.bronze.ClientProxy", serverSide="net.boatcake.bronze.Proxy")
	public Proxy proxy;
	private ItemTool bronzePickaxe;
	private ItemTool bronzeAxe;
	private ItemSword bronzeSword;
	private ItemTool bronzeShovel;
	private ItemHoe bronzeHoe;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
	    EnumHelper.addToolMaterial("Bronze", 2, 350, 6.0F, 2.0F, 13);
	    EnumArmorMaterial bronzeArmorMaterial = EnumHelper.addArmorMaterial("Bronze", 15, new int[] { 2, 6, 5, 2 }, 9);
	    bronzePickaxe = new BronzePickaxe(4637, config, "bronze_pickaxe", EnumToolMaterial.IRON, 5.0F, "ingotBronze");
	    bronzeAxe = new BronzeAxe(4638, config, "bronze_axe", EnumToolMaterial.IRON, 5.0F, "ingotBronze");
	    bronzeSword = new BronzeSword(4639, config, "bronze_sword", EnumToolMaterial.IRON, 7, "ingotBronze");
	    bronzeShovel = new BronzeShovel(4640, config, "bronze_shovel", EnumToolMaterial.IRON, 5.0F, "ingotBronze");
	    bronzeHoe = new BronzeHoe(4641, config, "bronze_hoe", EnumToolMaterial.IRON, "ingotBronze");
	    bronzeHelmet = new BronzeArmor(4642, config, "bronze_helmet", bronzeArmorMaterial, 0, "ingotBronze");
	    bronzeChestplate = new BronzeArmor(4643, config, "bronze_chestplate", bronzeArmorMaterial, 1, "ingotBronze");
	    bronzeLeggings = new BronzeArmor(4644, config, "bronze_leggings", bronzeArmorMaterial, 2, "ingotBronze");
	    bronzeBoots = new BronzeArmor(4645, config, "bronze_boots", bronzeArmorMaterial, 3, "ingotBronze");
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
	}
}
