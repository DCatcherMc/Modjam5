package me.dcatcher.demonology.init;

import me.dcatcher.demonology.block.BlockBasicAltar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {

    public static final BlockBasicAltar blockBasicAltar = new BlockBasicAltar(Material.ROCK, "altar_basic");

    public static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.registerAll(
            blockBasicAltar
        );
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(
            blockBasicAltar.createItemBlock()
        );
    }

    public static void registerModels() {
        blockBasicAltar.registerItemModel(Item.getItemFromBlock(blockBasicAltar));
    }


}
