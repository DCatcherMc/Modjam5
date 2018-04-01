package me.dcatcher.demonology.tileentities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBasicAltar extends TileEntity implements ITickable {

    public ItemStackHandler iStackHandler;
    public InventoryHelper invHelper;
    public int itemCount = 0;

    public boolean isCrafting = false;
    public EntityItem craftingItem = null;
    public long worldTimeCraftStarted = 0;
    public long craftingTime = 0;


    public TileEntityBasicAltar() {
        iStackHandler = new ItemStackHandler(7);
        invHelper = new InventoryHelper();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("items", iStackHandler.serializeNBT());
        compound.setInteger("itemCount", itemCount);
        compound.setBoolean("isCrafting", isCrafting);
        compound.setLong("worldTimeCraftStarted", worldTimeCraftStarted);
        compound.setLong("craftingTime", craftingTime);

        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey("items")) {
            iStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
        }
        if (compound.hasKey("itemCount")) {
            itemCount = compound.getInteger("itemCount");
        }

        if (compound.hasKey("isCrafting")) isCrafting = compound.getBoolean("isCrafting");
        if (compound.hasKey("worldTimeCraftStarted")) worldTimeCraftStarted = compound.getLong("worldTimeCraftStarted");
        if (compound.hasKey("craftingTime")) craftingTime = compound.getLong("craftingTime");
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        NBTTagCompound tagCom = pkt.getNbtCompound();
        this.readFromNBT(tagCom);
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, -1, getUpdateTag());
    }

    @Override
    public void markDirty() {
        super.markDirty();
        this.getWorld().markBlockRangeForRenderUpdate(pos, pos);
        this.world.notifyBlockUpdate(this.pos, this.world.getBlockState(this.pos), this.world.getBlockState(this.pos),3);
        this.getWorld().scheduleBlockUpdate(pos,this.getBlockType(),0,0);
    }

    public Item[] getContents() {
        Item[] contents = new Item[this.itemCount];
        for (int i = 0; i < this.itemCount; i++) {
            contents[i] = this.iStackHandler.getStackInSlot(i).getItem();
        }
        return contents;
    }

    public void clear() {
        this.itemCount = 0;
        this.markDirty();
    }

    public void startCrafting(ItemStack result, World world, int craftingTime) {
        System.out.println("Is crafting");
        isCrafting = true;
        worldTimeCraftStarted = world.getTotalWorldTime();
        this.iStackHandler.setStackInSlot(6, result);
        this.craftingTime = craftingTime;
    }

    @Override
    public void update() {
        if (!isCrafting) return;
        if (world.isRemote) return;

        if (this.world.getTotalWorldTime() - worldTimeCraftStarted > this.craftingTime) {
            // we have finished crafting!
            world.createExplosion(craftingItem, pos.getX() + 0.5, pos.getY()+ 1, pos.getZ() + 0.5, 0, true);
            world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), this.iStackHandler.getStackInSlot(6)));
            this.craftingTime = 0;
            this.isCrafting = false;
            this.clear();
        }
    }

}
