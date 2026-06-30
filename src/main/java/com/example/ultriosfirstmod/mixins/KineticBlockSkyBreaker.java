package com.example.ultriosfirstmod.mixins;

import com.example.ultriosfirstmod.util.IKineticBlockSkyBreaker;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.kinetics.base.BlockBreakingKineticBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBreakingKineticBlockEntity.class)
public abstract class KineticBlockSkyBreaker extends BlockEntity implements IKineticBlockSkyBreaker {

    @Shadow
    protected BlockPos breakingPos;

    @Unique
    public int minecraftModdingAttempt$breakCount;

    public KineticBlockSkyBreaker(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Unique
    public boolean minecraftModdingAttempt$isBreakingSky() {
        return this.breakingPos.getY() >= 320;
    }

//    @Inject(
//            method = "lambda$onBlockBroken$0(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/item/ItemStack;)V",
//            at = @At(value = "HEAD")
//    )
//    public void logShit(Vec3 vec, ItemStack stack, CallbackInfo ci) {
//        LogUtils.getLogger().info(stack.toString());
//    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(target = "com/simibubi/create/content/kinetics/base/BlockBreakingKineticBlockEntity.canBreak (Lnet/minecraft/world/level/block/state/BlockState;F)Z", value = "INVOKE")
    )
    public boolean allowBreakingSky(boolean original) {
        return original || this.minecraftModdingAttempt$isBreakingSky();
    }

//    @ModifyExpressionValue(
//            method = "tick",
//            at = @At(target = "net/minecraft/world/level/block/state/BlockState.getDestroySpeed (Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F", value = "INVOKE")
//    )
//    public float makeSkyTough(float original) {
//        if (!this.minecraftModdingAttempt$isBreakingSky()) return original;
//        return 10;
//    }

    @Inject(
            method = "onBlockBroken",
            at = @At("RETURN")
    )
    public void skybreakLogic(BlockState stateToBreak, CallbackInfo ci) {
        if (!this.minecraftModdingAttempt$isBreakingSky()) return;
        if (this.getLevel() == null) return;
        String itemName = "ae2:sky_dust";
        if (!BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName))) return;
        this.minecraftModdingAttempt$breakCount += 1;
        if (this.minecraftModdingAttempt$breakCount < 8) return;
        Item toDrop = BuiltInRegistries.ITEM.get(ResourceLocation.parse(itemName));
        double x = 0.5d + this.breakingPos.getX();
        double y = 0.5d + this.breakingPos.getY();
        double z = 0.5d + this.breakingPos.getZ();
        ItemStack newStack = new ItemStack(toDrop);
        ItemEntity itementity = new ItemEntity(this.getLevel(), x,y,z, newStack);
        itementity.lifespan = 20 * 3;
        itementity.setNoPickUpDelay();
        itementity.setDeltaMovement(new Vec3(0, 0.2, 0));
        itementity.setNoGravity(true);
        this.getLevel().addFreshEntity(itementity);
        this.minecraftModdingAttempt$breakCount = 0;
    }
}
