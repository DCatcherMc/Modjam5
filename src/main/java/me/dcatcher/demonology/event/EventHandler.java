package me.dcatcher.demonology.event;

import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.entities.EntitySoul;
import me.dcatcher.demonology.entities.EntityWraith;
import me.dcatcher.demonology.init.ModItems;
import me.dcatcher.demonology.item.ItemEvilEye;
import me.dcatcher.demonology.item.ItemReapersKnife;
import me.dcatcher.demonology.item.ItemSoulFlask;
import me.dcatcher.demonology.util.DefaultSoulHandler;
import me.dcatcher.demonology.util.ISoulHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import javax.swing.*;

@Mod.EventBusSubscriber(modid = Demonology.MODID)
public class EventHandler {

    @SubscribeEvent
    public static void onMobKilled(LivingDeathEvent event) {
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            // get the weapon used
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            Item current = player.inventory.getCurrentItem().getItem();

            float chance = 0.10f;

            if (current instanceof ItemReapersKnife) {
                chance = 0.25f;
            }

            // check to see if we succeed
            if (Demonology.random.nextFloat() < chance) {
                // we succeed!

                // will we spawn a neutral soul or corrup one to form a wraith
                ItemStack evilEye = inventoryContainsItem(player, ItemEvilEye.class);


                float chanceOfWraith = (evilEye == null) ? 0.10f : 0.01f;

                Entity dead = event.getEntity();
                if (Math.random() < chanceOfWraith) {
                    // uh oh, wraith time
                    EntityWraith wraith = new EntityWraith(event.getEntity().world);
                    wraith.setPosition(dead.posX, dead.posY, dead.posZ);
                    event.getEntity().world.spawnEntity(wraith);
                } else {
                    EntitySoul soul = new EntitySoul(event.getEntity().world);
                    soul.setPosition(dead.posX, dead.posY, dead.posZ);
                    event.getEntity().world.spawnEntity(soul);
                }

                // add to the flask if we have one!
                ItemStack soulFlask = inventoryContainsItem(player, ItemSoulFlask.class);
                ISoulHandler ish;
                if (soulFlask != null && (ish = DefaultSoulHandler.getHandler(soulFlask)) != null) {
                    int soulsToAdd;
                    if (event.getEntity() instanceof EntityMob) soulsToAdd = 2 + Demonology.random.nextInt(2);
                    else soulsToAdd =  1 + Demonology.random.nextInt(2);
                    ish.addSouls(soulsToAdd);
                }
            }

        }
    }

    public static ItemStack inventoryContainsItem(EntityPlayer player, Class<? extends Item> item) {
        for (ItemStack istack : player.inventory.mainInventory) {
            if (istack != null && item.isInstance(istack.getItem())) {
                return istack;
            }
        }
        return null;
    }

}
