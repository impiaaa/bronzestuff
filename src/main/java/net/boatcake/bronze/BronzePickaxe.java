package net.boatcake.bronze;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BronzePickaxe extends ItemPickaxe {
	private String repairMaterial;
	private String name;

	public BronzePickaxe(Configuration config, String name,
			ToolMaterial toolMaterial, String repairMaterial) {
		super(toolMaterial);
		this.repairMaterial = repairMaterial;
		this.name = name;
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(this, name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(BronzeStuffMod.modID + ":"
				+ name);
	}

	@Override
	public int getItemEnchantability() {
		return 13;
	}

	@Override
	public boolean getIsRepairable(ItemStack tool, ItemStack item) {
		return (item != null)
				&& (OreDictionary.getOreID(item) == OreDictionary
						.getOreID(repairMaterial));
	}
}
