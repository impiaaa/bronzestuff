package net.boatcake.bronze;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;

public class BronzeArmor extends ItemArmor {
	private String repairMaterial;
	private String armorName;

	public BronzeArmor(int defaultID, Configuration config, String name,
			EnumArmorMaterial armorMaterial, int armorType,
			String repairMaterial) {
		super(config.getItem("IDs", name, defaultID).getInt(defaultID),
				armorMaterial, BronzeStuffMod.proxy.addArmor(name),
				armorType);
		this.repairMaterial = repairMaterial;
		this.armorName = name;
		setMaxDamage(armorMaterial.getDurability(armorType));
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(this, name);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(BronzeStuffMod.modID + ":"
				+ armorName);
	}

	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		int suffix = this.armorType == 2 ? 2 : 1;
		return BronzeStuffMod.modID + ":textures/armor/" + this.armorName + "_"
				+ suffix + ".png";
	}

	public boolean getIsRepairable(ItemStack armor, ItemStack item) {
		return (item != null)
				&& (OreDictionary.getOreID(item) == OreDictionary
						.getOreID(repairMaterial));
	}
}
