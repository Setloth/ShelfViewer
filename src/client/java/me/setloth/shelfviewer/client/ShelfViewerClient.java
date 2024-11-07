package me.setloth.shelfviewer.client;

import me.setloth.shelfviewer.client.handler.HudRenderer;
import me.setloth.shelfviewer.client.handler.TickHandler;
import me.setloth.shelfviewer.packet.ResponseBookshelfPayload;
import me.setloth.shelfviewer.packet.ResponseBookshelfPayloadBukkit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ShelfViewerClient implements ClientModInitializer {


  public static @NotNull ItemStack itemToRender = ItemStack.EMPTY;

  @Override
  public void onInitializeClient() {

    ClientPlayNetworking.registerGlobalReceiver(ResponseBookshelfPayload.ID, (payload, context) -> context.client().execute(() -> itemToRender = payload.itemStack()));

    ClientPlayNetworking.registerGlobalReceiver(ResponseBookshelfPayloadBukkit.ID, (payload, context) -> context.client().execute(() -> itemToRender = payload.item()));

    TickHandler.register();
    HudRenderer.register();
  }

}


