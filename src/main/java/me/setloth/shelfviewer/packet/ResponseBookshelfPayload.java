package me.setloth.shelfviewer.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.item.ItemStack;

public record ResponseBookshelfPayload(ItemStack itemStack) implements CustomPayload {
  public static final CustomPayload.Id<ResponseBookshelfPayload> ID = new CustomPayload.Id<>(NetworkingConstants.RESPONSE_BOOKSHELF_PACKET_ID);
  public static final PacketCodec<RegistryByteBuf, ResponseBookshelfPayload> CODEC = PacketCodec.tuple(
          ItemStack.PACKET_CODEC,
          ResponseBookshelfPayload::itemStack,
          ResponseBookshelfPayload::new
  );

  @Override
  public CustomPayload.Id<? extends CustomPayload> getId() {
    return ID;
  }
}