package me.setloth.shelfviewer;

import me.setloth.shelfviewer.packet.RequestBookshelfPayload;
import me.setloth.shelfviewer.packet.RequestBookshelfPayloadBukkit;
import me.setloth.shelfviewer.packet.ResponseBookshelfPayload;
import me.setloth.shelfviewer.packet.ResponseBookshelfPayloadBukkit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChiseledBookshelfBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ShelfViewer implements ModInitializer {


  @Override
  public void onInitialize() {
    PayloadTypeRegistry.playC2S().register(RequestBookshelfPayload.ID, RequestBookshelfPayload.CODEC);
    PayloadTypeRegistry.playS2C().register(ResponseBookshelfPayload.ID, ResponseBookshelfPayload.CODEC);

    PayloadTypeRegistry.playC2S().register(RequestBookshelfPayloadBukkit.ID, RequestBookshelfPayloadBukkit.CODEC);
    PayloadTypeRegistry.playS2C().register(ResponseBookshelfPayloadBukkit.ID, ResponseBookshelfPayloadBukkit.CODEC);

    ServerPlayNetworking.registerGlobalReceiver(RequestBookshelfPayload.ID, this::handleRequestBookshelfPayload);
  }

  private void handleRequestBookshelfPayload(RequestBookshelfPayload payload, ServerPlayNetworking.Context context) {
    context.server().execute(() -> {
      BlockPos pos = payload.blockPos();
      ServerPlayerEntity player = context.player();
      World world = player.getWorld();

      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (!(blockEntity instanceof ChiseledBookshelfBlockEntity bookshelf)) {
        return;
      }

      int slot = payload.clickedSlot();
      ItemStack stack = bookshelf.getStack(slot);

      if (stack == null || stack.isEmpty()) {
        return;
      }

      CustomPayload responsePayload = new ResponseBookshelfPayload(stack);
      ServerPlayNetworking.send(player, responsePayload);
    });
  }

}
