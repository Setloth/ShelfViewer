package me.setloth.shelfviewer.packet;

import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record ResponseBookshelfPayloadBukkit(ItemStack item) implements CustomPayload {
  public static final CustomPayload.Id<ResponseBookshelfPayloadBukkit> ID = new CustomPayload.Id<>(NetworkingConstants.RESPONSE_BOOKSHELF_BUKKIT_PACKET_ID);

  public static final PacketCodec<RegistryByteBuf, ResponseBookshelfPayloadBukkit> CODEC = PacketCodec.tuple(
          ItemStack.OPTIONAL_PACKET_CODEC,
          ResponseBookshelfPayloadBukkit::item,
          ResponseBookshelfPayloadBukkit::new
  );

  @Override
  public CustomPayload.Id<ResponseBookshelfPayloadBukkit> getId() {
    return ID;
  }
}