package net.boatcake.bronze;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = BronzeStuffMod.modID, name = "Bronze stuff")
public class BronzeStuffMod {
	public static final String modID = "bronzestuff";

	@Instance
	public static BronzeStuffMod instance;

	@SidedProxy(clientSide = "net.boatcake.bronze.ClientProxy", serverSide = "net.boatcake.bronze.Proxy")
	public static Proxy proxy;

	private ItemArmor bronzeHelmet;
	private ItemArmor bronzeChestplate;
	private ItemArmor bronzeLeggings;
	private ItemArmor bronzeBoots;
	private ItemTool bronzePickaxe;
	private ItemTool bronzeAxe;
	private ItemSword bronzeSword;
	private ItemTool bronzeShovel;
	private ItemHoe bronzeHoe;
	private Item bronzeHorseArmor;

	private boolean enableLoot;
	private boolean enableRecipes;
	private boolean enableTools;
	private boolean enableArmor;

	private enum MetalMod {
		IC2, METALLURGY
	};

	private MetalMod modBalance;

	private final String categoryEnablers = "enablers";

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(
				e.getSuggestedConfigurationFile());
		enableLoot = config.get(categoryEnablers, "Enable loot", true)
				.getBoolean(true);
		enableRecipes = config.get(categoryEnablers, "Enable crafting recipes",
				true).getBoolean(true);
		enableTools = config.get(categoryEnablers, "Enable tools", true)
				.getBoolean(true);
		enableArmor = config.get(categoryEnablers, "Enable armor", true)
				.getBoolean(true);

		for (MetalMod m : MetalMod.values()) {
			if (config.get("balance", m.name(), m == MetalMod.IC2).getBoolean(
					m == MetalMod.IC2)) {
				modBalance = m;
			}
		}

		EnumArmorMaterial bronzeArmorMaterial;
		switch (modBalance) {
		case IC2:
			EnumHelper.addToolMaterial("Bronze", 2, 350, 5.0F, 2.0F, 13);
			bronzeArmorMaterial = EnumHelper.addArmorMaterial("Bronze", 15,
					new int[] { 2, 6, 5, 2 }, 9);
			break;
		case METALLURGY:
			EnumHelper.addToolMaterial("Bronze", 2, 250, 6.0F, 1.0F, 9);
			bronzeArmorMaterial = EnumHelper.addArmorMaterial("Bronze", 13,
					new int[] { 2, 4, 3, 3 }, 9);
			break;
		default:
			bronzeArmorMaterial = null;
			break;
		}
		if (enableTools) {
			bronzePickaxe = new BronzePickaxe(4637, config, "bronze_pickaxe",
					EnumToolMaterial.IRON, "ingotBronze");
			bronzeAxe = new BronzeAxe(4638, config, "bronze_axe",
					EnumToolMaterial.IRON, "ingotBronze");
			bronzeSword = new BronzeSword(4639, config, "bronze_sword",
					EnumToolMaterial.IRON, "ingotBronze");
			bronzeShovel = new BronzeShovel(4640, config, "bronze_shovel",
					EnumToolMaterial.IRON, "ingotBronze");
			bronzeHoe = new BronzeHoe(4641, config, "bronze_hoe",
					EnumToolMaterial.IRON, "ingotBronze");
		}
		if (enableArmor) {
			bronzeHelmet = new BronzeArmor(4642, config, "bronze_helmet",
					bronzeArmorMaterial, 0, "ingotBronze");
			bronzeChestplate = new BronzeArmor(4643, config,
					"bronze_chestplate", bronzeArmorMaterial, 1, "ingotBronze");
			bronzeLeggings = new BronzeArmor(4644, config, "bronze_leggings",
					bronzeArmorMaterial, 2, "ingotBronze");
			bronzeBoots = new BronzeArmor(4645, config, "bronze_boots",
					bronzeArmorMaterial, 3, "ingotBronze");
		}
//		bronzeHorseArmor = new Item(config.getItem("IDs", "bronze_horse_armor",
//				4646).getInt(4646)).setUnlocalizedName("bronze_horse_armor")
//				.setMaxStackSize(1).setCreativeTab(CreativeTabs.tabMisc)
//				.setTextureName("bronze_horse_armor");
		if (config.hasChanged()) {
			config.save();
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		if (enableRecipes) {
			if (enableTools) {
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzePickaxe,
						"III", " S ", " S ", 'I', "ingotBronze", 'S',
						"stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeAxe, "II",
						"IS", " S", 'I', "ingotBronze", 'S', "stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeAxe, "II",
						"SI", "S ", 'I', "ingotBronze", 'S', "stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeSword, "I",
						"I", "S", 'I', "ingotBronze", 'S', "stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeShovel, "I",
						"S", "S", 'I', "ingotBronze", 'S', "stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeHoe, "II",
						" S", " S", 'I', "ingotBronze", 'S', "stickWood"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeHoe, "II",
						"S ", "S ", 'I', "ingotBronze", 'S', "stickWood"));
			}
			if (enableArmor) {
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeHelmet, "III",
						"I I", 'I', "ingotBronze"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeChestplate,
						"I I", "III", "III", 'I', "ingotBronze"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeLeggings,
						"III", "I I", "I I", 'I', "ingotBronze"));
				GameRegistry.addRecipe(new ShapedOreRecipe(bronzeBoots, "I I",
						"I I", 'I', "ingotBronze"));
			}
		}

		if (enableLoot) {
			if (enableTools) {
				ItemStack pickaxe = new ItemStack(bronzePickaxe);
				ItemStack sword = new ItemStack(bronzeSword);
				// ItemStack horseArmor = new ItemStack(bronzeHorseArmor);
				ChestGenHooks.addItem(ChestGenHooks.MINESHAFT_CORRIDOR,
						new WeightedRandomChestContent(pickaxe, 1, 1, 1));
				ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,
						new WeightedRandomChestContent(pickaxe, 1, 1, 1));
				WeightedRandomChestContent[] bronzeTools = {
						new WeightedRandomChestContent(pickaxe, 1, 1, 3),
						new WeightedRandomChestContent(sword, 1, 1, 3) };
				for (WeightedRandomChestContent item : bronzeTools) {
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,
							item);
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST,
							item);
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST,
							item);
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR,
							item);
					ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
							item);
				}
			}
			if (enableArmor) {
				ItemStack helmet = new ItemStack(bronzeHelmet);
				ItemStack chestplate = new ItemStack(bronzeChestplate);
				ItemStack leggings = new ItemStack(bronzeLeggings);
				ItemStack boots = new ItemStack(bronzeBoots);
				WeightedRandomChestContent[] bronzeArmor = {
						new WeightedRandomChestContent(helmet, 1, 1, 3),
						new WeightedRandomChestContent(chestplate, 1, 1, 3),
						new WeightedRandomChestContent(leggings, 1, 1, 3),
						new WeightedRandomChestContent(boots, 1, 1, 3) };
				for (WeightedRandomChestContent item : bronzeArmor) {
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CROSSING,
							item);
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_DESERT_CHEST,
							item);
					ChestGenHooks.addItem(ChestGenHooks.PYRAMID_JUNGLE_CHEST,
							item);
					ChestGenHooks.addItem(ChestGenHooks.STRONGHOLD_CORRIDOR,
							item);
					ChestGenHooks.addItem(ChestGenHooks.VILLAGE_BLACKSMITH,
							item);
				}
			}
		}
	}
}
