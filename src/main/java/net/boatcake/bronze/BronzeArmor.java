package net.boatcake.bronze;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BronzeArmor extends ItemArmor {
	private String repairMaterial;
	private String name;
	private String armorName;

	public BronzeArmor(Configuration config, String name,
			ArmorMaterial armorMaterial, int armorType, String repairMaterial) {
		super(armorMaterial, BronzeStuffMod.proxy.addArmor(name), armorType);
		this.repairMaterial = repairMaterial;
		this.name = name;
		this.armorName = "bronze";
		setMaxDamage(armorMaterial.getDurability(armorType));
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.tabCombat);
		GameRegistry.registerItem(this, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(BronzeStuffMod.modID + ":"
				+ name);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot,
			String type) {
		int suffix = this.armorType == 2 ? 2 : 1;
		return BronzeStuffMod.modID + ":textures/armor/" + this.armorName + "_"
				+ suffix + ".png";
	}

	@Override
	public boolean getIsRepairable(ItemStack armor, ItemStack item) {
		return (item != null)
				&& (OreDictionary.getOreID(item) == OreDictionary
						.getOreID(repairMaterial));
	}
}
