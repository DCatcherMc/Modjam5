package me.dcatcher.demonology.init;

import me.dcatcher.demonology.block.BlockBasicAltar;
import me.dcatcher.demonology.block.BlockRitualStone;
import me.dcatcher.demonology.block.BlockTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static final BlockBasicAltar blockBasicAltar = new BlockBasicAltar(Material.ROCK, "altar_basic");
    public static final BlockRitualStone blockRitualStone = new BlockRitualStone(Material.ROCK, "ritual_stone");

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(
                blockBasicAltar,
                blockRitualStone
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
                blockBasicAltar.createItemBlock(),
                blockRitualStone.createItemBlock()
        );
    }

    public static void registerModels() {
        blockBasicAltar.registerItemModel(Item.getItemFromBlock(blockBasicAltar));
        blockRitualStone.registerItemModel(Item.getItemFromBlock(blockRitualStone));
    }

    public static void registerTileEntities() {
        GameRegistry.registerTileEntity(((BlockTileEntity<?>)blockBasicAltar).getTileEntityClass(), blockBasicAltar.getRegistryName().toString());
        GameRegistry.registerTileEntity(((BlockTileEntity<?>)blockRitualStone).getTileEntityClass(), blockRitualStone.getRegistryName().toString());
    }

}
