package me.dcatcher.demonology.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Ritual {

    public String[] ritualLayout;
    public int level;
    public int timeToComplete;

    public Ritual(String[] recipe, int level, int timeToComplete) {
        this.level = level;
        this.ritualLayout = recipe;
        this.timeToComplete = timeToComplete;
    }

    public boolean canComplete(World world, BlockPos centre) {
        int l = this.ritualLayout[0].length();
        if (l != ritualLayout.length) return false;

        for (int x = 0; x < ritualLayout.length; x++) {
            for (int z = 0; z < l; z++) {
                Block shouldBe = Ritual.getBlockFromChar(this.ritualLayout[x].charAt(z));
                if (shouldBe == null) continue;

                Block actual = world.getBlockState(centre.add(x, 0, z)).getBlock();
                if (actual != shouldBe) return false;
            }
        }
        return true;
    }

    public static Block getBlockFromChar(char c) {
        switch (c) {
            case 'd':
                return Block.getBlockFromName("diamond_block");
            case 'r':
                // is the ritual stone so can be ignored!
                return null;
            default:
                return null;
        }
    }

}
