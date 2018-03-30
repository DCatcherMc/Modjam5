package me.dcatcher.demonology.event;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.entities.EntitySoul;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
                chance = 1.0f;
            }

            // check to see if we succeed
            if (Demonology.random.nextFloat() < chance) {
                // we succeed!
                EntitySoul soul = new EntitySoul(event.getEntity().world);
                Entity dead = event.getEntity();
                soul.setPosition(dead.posX, dead.posY, dead.posZ);
                event.getEntity().world.spawnEntity(soul);
            }

        }
    }

}
