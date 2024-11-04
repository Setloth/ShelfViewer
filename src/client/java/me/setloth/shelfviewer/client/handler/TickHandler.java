package me.setloth.shelfviewer.client.handler;

import me.setloth.shelfviewer.client.ShelfViewerClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class TickHandler {

  public static void register() {
    ClientTickEvents.END_CLIENT_TICK.register(TickHandler::onEndClientTick);
  }

  private static void onEndClientTick(MinecraftClient client) {
    if (client.player == null || client.world == null) return;

    HitResult hitResult = client.crosshairTarget;

    if (hitResult == null || hitResult.getType() != HitResult.Type.BLOCK) {
      ShelfViewerClient.itemToRender = ItemStack.EMPTY;
      return;
    }

    BlockHitResult blockHitResult = (BlockHitResult) hitResult;
    BlockPos blockPos = blockHitResult.getBlockPos();
    BlockState blockState = client.world.getBlockState(blockPos);

    BookshelfHandler.handleBlockInteraction(blockHitResult, blockState);
  }


}
