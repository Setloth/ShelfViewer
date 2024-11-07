package me.setloth.shelfviewer.client.handler;

import me.setloth.shelfviewer.client.ShelfViewerClient;
import me.setloth.shelfviewer.mixin.client.ChiseledBookshelfBlockAccessor;
import me.setloth.shelfviewer.packet.RequestBookshelfPayload;
import me.setloth.shelfviewer.packet.RequestBookshelfPayloadBukkit;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChiseledBookshelfBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class BookshelfHandler {

  private static final AtomicReference<BlockPos> lastBlockPos = new AtomicReference<>();
  private static final AtomicInteger lastSlot = new AtomicInteger(-1);

  public static void handleBlockInteraction(BlockHitResult hitResult, BlockState blockState) {
    Block block = blockState.getBlock();
    BlockPos blockPos = hitResult.getBlockPos();

    if (block instanceof ChiseledBookshelfBlock bookshelfBlock) {
      ChiseledBookshelfBlockAccessor accessorBlock = (ChiseledBookshelfBlockAccessor) bookshelfBlock;
      OptionalInt optSlot = accessorBlock.invokeGetSlotForHitPos(hitResult, blockState);

      int ls = lastSlot.get();
      BlockPos lbp = lastBlockPos.get();

      int slot = optSlot.orElse(BookshelfHandler.compareBlockPos(lbp, blockPos) ? ls : -1);
      if (slot == -1 || !isSlotOccupied(blockState, slot)) {
        ShelfViewerClient.itemToRender = ItemStack.EMPTY;
        return;
      }

      lastSlot.set(slot);
      lastBlockPos.set(blockPos);

      RequestBookshelfPayload payload = new RequestBookshelfPayload(blockPos, slot);
      RequestBookshelfPayloadBukkit payloadBukkit = new RequestBookshelfPayloadBukkit(slot);

      System.out.println("Sending request to server...");
      ClientPlayNetworking.send(payload);
      System.out.println("Sending request to bukkit...");
      ClientPlayNetworking.send(payloadBukkit);


    } else {
      ShelfViewerClient.itemToRender = ItemStack.EMPTY;
    }
  }

  private static boolean isSlotOccupied(BlockState blockState, int slot) {
    List<BooleanProperty> props = ChiseledBookshelfBlock.SLOT_OCCUPIED_PROPERTIES;
    return blockState.get(props.get(slot));
  }

  private static boolean compareBlockPos(BlockPos bp1, BlockPos bp2) {
    if (bp1 == null || bp2 == null) return false;
    int bp1X = bp1.getX();
    int bp1Y = bp1.getY();
    int bp1Z = bp1.getZ();

    int bp2X = bp2.getX();
    int bp2Y = bp2.getY();
    int bp2Z = bp2.getZ();

    return (bp1X == bp2X) && (bp1Y == bp2Y) && (bp1Z == bp2Z);
  }
}
