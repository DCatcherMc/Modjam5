package me.dcatcher.demonology.event;

import me.dcatcher.demonology.Demonology;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Demonology.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onMobKilled(LivingDeathEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            // get the weapon used
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            Item current = player.inventory.getCurrentItem().getItem();

            float chance = 0.10f;

            if (current instanceof ItemSword) {
                chance = 0.25f;
            }

            // check to see if we succeed
            if (Demonology.random.nextFloat() < chance) {
                // we succeed!
                player.sendMessage(new TextComponentString("SOUL SUMMONED"));
            }

        }
    }

}
