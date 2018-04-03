package me.dcatcher.demonology.util;

import me.dcatcher.demonology.entities.EntityDemonicEye;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class RitualExecutor {

    public static List<Ritual> rituals = new ArrayList<>();

    public static final Ritual summonEye = new Ritual("summonEye",
            new String[] {
                    " d ",
                    "drd",
                    " d "
            }, 0, 10
    );


    public static void registerRituals() {
        RitualExecutor.rituals.add(summonEye);
    }

    public static void executeRitual(Ritual r, World world, BlockPos pos, ISoulHandler ish, EntityPlayer player) {
        r.destroyBlocks(world, pos);
        ish.addSouls(-r.soulCost);

        switch(r.name) {
            case "summonEye":
                EntityDemonicEye eye = new EntityDemonicEye(world, pos.getX(), pos.getY() + 5, pos.getZ());
                eye.addPlayerToInfo((EntityPlayerMP) player);
                world.spawnEntity(eye);
                break;
            default:
                // do nothing
        }
    }

}
