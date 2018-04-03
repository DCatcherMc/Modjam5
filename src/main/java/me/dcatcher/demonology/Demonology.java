package me.dcatcher.demonology;

import me.dcatcher.demonology.init.ModBlocks;
import me.dcatcher.demonology.init.ModItems;
import me.dcatcher.demonology.proxy.IProxy;
import me.dcatcher.demonology.util.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod(modid = Demonology.MODID, name = Demonology.NAME, version = Demonology.VERSION)
public class Demonology
{
    public static final String MODID = "demonology";
    public static final String NAME = "Demonology";
    public static final String VERSION = "0.01";

    private static Logger logger;

    public static Random random;

    @Mod.Instance(Demonology.MODID)
    public static Demonology instance;

    public static List<AltarRecipe> altarRecipes = new ArrayList<>();

    @SidedProxy(clientSide = "me.dcatcher.demonology.proxy.ClientProxy",
            serverSide = "me.dcatcher.demonology.proxy.CommonProxy")
    public static IProxy proxy;

    @CapabilityInject(ISoulHandler.class)
    public static final Capability<ISoulHandler> CAPABILITY_SOUL = null;

    public static CreativeTabs tabDemonology = new TabDemonology("demonology");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        random = new Random();
        proxy.preInit(event);
        CapabilityManager.INSTANCE.register(ISoulHandler.class, new SoulStorage(), new FactorySoulHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModBlocks.registerTileEntities();
        proxy.init(event);
        Demonology.addRecipes();

        // loot tables
        LootTableList.register(new ResourceLocation(MODID, "loot_demonic_eye"));
    }

    private static void addRecipes() {
        // 3 stone + 3 glass = 1 soul flask
        altarRecipes.add(new AltarRecipe(new Item[]{
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("glass"),
                ItemBlock.getByNameOrId("glass"),
                ItemBlock.getByNameOrId("glass")
        }, ModItems.itemSoulFlask, 1));

        // 2 iron ingots + 1 stone + 1 stick = 1 Reaper's Knife
        altarRecipes.add(new AltarRecipe(new Item[]{
                Item.getByNameOrId("iron_ingot"),
                Item.getByNameOrId("iron_ingot"),
                ItemBlock.getByNameOrId("stone"),
                Item.getByNameOrId("stick")
        }, ModItems.itemReapersKnife, 1));

        // 2 iron ingots + 1 stone + 1 stick = 1 Reaper's Knife
        altarRecipes.add(new AltarRecipe(new Item[]{
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone")
        }, ModItems.itemDemonicRod, 3));

        altarRecipes.add(new AltarRecipe(new Item[]{
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone")
        }, ModItems.itemDemonicRod, 1));

        altarRecipes.add(new AltarRecipe(new Item[]{
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone"),
                ItemBlock.getByNameOrId("stone")
        }, ModItems.itemDemonicRod, 2));

        altarRecipes.add(new AltarRecipe(new Item[]{
                ModItems.itemDemonicRod,
                ModItems.itemDemonicRod,
                ModItems.itemDemonicRod,
                Item.getByNameOrId("diamond"),
                Item.getByNameOrId("diamond"),
                ModItems.itemEvilEye
        }, ModItems.itemDemonicPulsator, 1));

        RitualExecutor.registerRituals();
    }
}
