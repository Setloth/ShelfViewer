package me.setloth.shelfviewer.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.util.hit.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.OptionalInt;

@Mixin(ChiseledBookshelfBlock.class)
public interface ChiseledBookshelfBlockAccessor {

  @Invoker("getSlotForHitPos")
  OptionalInt invokeGetSlotForHitPos(BlockHitResult hit, BlockState state);

}
