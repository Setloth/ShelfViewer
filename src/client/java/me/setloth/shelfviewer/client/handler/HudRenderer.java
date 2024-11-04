package me.setloth.shelfviewer.client.handler;

import me.setloth.shelfviewer.client.ShelfViewerClient;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class HudRenderer {
  private static final MinecraftClient client = MinecraftClient.getInstance();

  public static void register() {
    HudRenderCallback.EVENT.register(HudRenderer::onRenderHud);
  }

  private static void onRenderHud(DrawContext drawContext, RenderTickCounter tickDelta) {
    if (ShelfViewerClient.itemToRender.isEmpty()) return;

    TextRenderer textRenderer = client.textRenderer;
    drawContext.drawItemTooltip(textRenderer, ShelfViewerClient.itemToRender, drawContext.getScaledWindowWidth() / 2, drawContext.getScaledWindowHeight() / 2);
  }
}
