package com.netuserget.throwabletorches.item;

import com.netuserget.throwabletorches.entity.ThrowableTorchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ThrowableTorchItem extends Item {
    public ThrowableTorchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if (world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 1.0F);
        }
        ThrowableTorchEntity throwableTorch = new ThrowableTorchEntity(world, user);
        throwableTorch.setItem(itemStack);
        throwableTorch.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 0.5F);

        world.spawnEntity(throwableTorch);

        user.incrementStat(Stats.USED.getOrCreateStat(this));

        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return super.use(world, user, hand);
    }
}
