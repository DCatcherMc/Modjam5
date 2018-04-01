package me.dcatcher.demonology.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.dcatcher.demonology.Demonology;
import me.dcatcher.demonology.util.DefaultSoulHandler;
import me.dcatcher.demonology.util.ISoulHandler;
import me.dcatcher.demonology.util.SoulProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class ItemSoulFlask extends ItemBase {

    public ItemSoulFlask(String name) {
        super(name);
        this.setMaxStackSize(1);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new SoulProvider();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        ISoulHandler ish = DefaultSoulHandler.getHandler(stack);
        if (ish != null) {
            int souls = ish.getSouls();
            tooltip.add(ChatFormatting.DARK_GRAY + "" + ChatFormatting.ITALIC + "Souls stored: " + souls);
        }
    }

    @Nullable
    @Override
    public NBTTagCompound getNBTShareTag(ItemStack stack) {
        NBTTagCompound nbt = stack.serializeNBT();
        return nbt;
    }
}
