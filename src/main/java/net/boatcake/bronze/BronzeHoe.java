package net.boatcake.bronze;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class BronzeHoe extends ItemHoe {
	private String repairMaterial;
	private String name;

	public BronzeHoe(int defaultID, Configuration config, String name,
			EnumToolMaterial toolMaterial, String repairMaterial) {
		super(config.getItem("IDs", name, defaultID).getInt(defaultID),
				toolMaterial);
		this.repairMaterial = repairMaterial;
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(this, name);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(BronzeStuffMod.modID + ":"
				+ name);
	}

	public int getItemEnchantability() {
		return 13;
	}

	public boolean getIsRepairable(ItemStack tool, ItemStack item) {
		return (item != null)
				&& (OreDictionary.getOreID(item) == OreDictionary
						.getOreID(repairMaterial));
	}
}
