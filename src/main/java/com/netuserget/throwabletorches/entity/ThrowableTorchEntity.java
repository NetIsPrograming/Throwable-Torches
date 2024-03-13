package com.netuserget.throwabletorches.entity;

import com.netuserget.throwabletorches.ThrowableTorches;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ThrowableTorchEntity extends ThrownItemEntity {

    public ThrowableTorchEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowableTorchEntity(World world, double d, double e, double f) {
        super(ThrowableTorches.THROWABLE_TORCH_ENTITY_ENTITY_TYPE, d, e, f, world);
    }

    public ThrowableTorchEntity(World world, LivingEntity livingEntity) {
        super(ThrowableTorches.THROWABLE_TORCH_ENTITY_ENTITY_TYPE, livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ThrowableTorches.THROWABLE_TORCH_ITEM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 5.0F);

        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.setOnFireFor(3);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        World world = this.getWorld();
        if (world.isClient){
            return;
        }
        this.kill();
        Entity owner = this.getOwner();
        ItemStack stack = Items.TORCH.getDefaultStack();
        BlockItem placeableItem = (BlockItem) Items.TORCH; // surprisingly works
        if (owner == null) {
            // this check is here, so you don't crash while loading, because owner can be and probably is null.
            ActionResult result = this.AutomaticPlace(world, blockHitResult.getBlockPos(), blockHitResult.getSide(), stack, blockHitResult.getSide(), placeableItem);
            if (result == ActionResult.FAIL) {
                this.spawnItem(world, blockHitResult.getBlockPos().offset(blockHitResult.getSide()), new ItemStack(Items.TORCH));
            }
            return;
        }
        if (owner instanceof PlayerEntity playerOwner) {
            ItemPlacementContext ctx = new ItemPlacementContext(world, playerOwner, Hand.MAIN_HAND, stack, blockHitResult);
            ActionResult result = placeableItem.place(ctx);
            if (result == ActionResult.FAIL) {
                this.spawnItem(world, blockHitResult.getBlockPos().offset(blockHitResult.getSide()), new ItemStack(Items.TORCH));
            }
            return;
        }

        ActionResult result = this.AutomaticPlace(world, blockHitResult.getBlockPos(), blockHitResult.getSide(), stack, blockHitResult.getSide(), placeableItem);

        if (result == ActionResult.FAIL) {
            this.spawnItem(world, blockHitResult.getBlockPos().offset(blockHitResult.getSide()), new ItemStack(Items.TORCH));
        }
    }

    private void spawnItem(World world, BlockPos pos, ItemStack stack) {
        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack));
    }

    private ActionResult AutomaticPlace(World world, BlockPos pos, Direction facing, ItemStack stack, Direction side, BlockItem placeItem) {
        AutomaticItemPlacementContext ctx = new AutomaticItemPlacementContext(world, pos, facing, stack, side);
        return placeItem.place(ctx);
    }
}
