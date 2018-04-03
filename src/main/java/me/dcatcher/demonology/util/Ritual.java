package me.dcatcher.demonology.util;

import me.dcatcher.demonology.item.ItemSoulFlask;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class Ritual {

    public String name;
    public String[] ritualLayout;
    public int level;
    public int soulCost;

    public Ritual(String name, String[] recipe, int level, int soulCost) {
        this.name = name;
        this.ritualLayout = recipe;
        this.level = level;
        this.soulCost = soulCost;
    }

    public ISoulHandler canComplete(World world, BlockPos centre) {
        int l = this.ritualLayout[0].length();
        if (l != ritualLayout.length) return null;

        // check soulCost success
        List<EntityItem> items = world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(centre.add(-3, -3, -3), centre.add(3, 3, 3)));

        if (items.size() == 0) return null;

        boolean willExecute = false;
        ISoulHandler ish = null;

        for (EntityItem item : items) {
            if (item.getItem().getItem() instanceof ItemSoulFlask) {
                // now we're talking
                ish = DefaultSoulHandler.getHandler(item.getItem());
                if (ish != null) {
                    willExecute = ish.getSouls() >= this.soulCost;
                }
            }
        }

        if (!willExecute) return null;

        for (int x = 0; x < ritualLayout.length; x++) {
            for (int z = 0; z < l; z++) {
                Block shouldBe = Ritual.getBlockFromChar(this.ritualLayout[x].charAt(z));
                if (shouldBe == null) continue;

                Block actual = world.getBlockState(centre.add(x-1, 0, z-1)).getBlock();
                if (actual != shouldBe) return null;
            }
        }
        return ish;
    }

    public void destroyBlocks(World world, BlockPos centre) {
        int l = this.ritualLayout[0].length();
        for (int x = 0; x < ritualLayout.length; x++) {
            for (int z = 0; z < l; z++) {
                Block shouldBe = Ritual.getBlockFromChar(this.ritualLayout[x].charAt(z));
                if (shouldBe == null) continue;
                BlockPos pos = centre.add(x-1, 0, z-1);
                world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 0, true);
                world.setBlockToAir(pos);
            }
        }
    }

    public static Block getBlockFromChar(char c) {
        switch (c) {
            case 'd':
                return Block.getBlockFromName("diamond_block");
            case 'r':
                // is the ritual stone so can be ignored!
                return null;
            case 'R':
                return Block.getBlockFromName("redstone_block");
            default:
                return null;
        }
    }
}
