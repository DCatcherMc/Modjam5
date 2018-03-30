package me.dcatcher.demonology;

import me.dcatcher.demonology.util.TabDemonology;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Demonology.MODID, name = Demonology.NAME, version = Demonology.VERSION)
public class Demonology
{
    public static final String MODID = "demonology";
    public static final String NAME = "Demonology";
    public static final String VERSION = "0.01";

    private static Logger logger;

    @Mod.Instance(Demonology.MODID)
    public static Demonology instance;

    public static CreativeTabs tabDemonology = new TabDemonology("demonology");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

}
