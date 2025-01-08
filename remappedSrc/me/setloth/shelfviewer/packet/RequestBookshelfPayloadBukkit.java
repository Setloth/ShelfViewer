package me.setloth.shelfviewer.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record RequestBookshelfPayloadBukkit(int slot) implements CustomPayload {
  public static final Id<RequestBookshelfPayloadBukkit> ID = new Id<>(NetworkingConstants.REQUEST_BOOKSHELF_BUKKIT_PACKET_ID);
  public static final PacketCodec<RegistryByteBuf, RequestBookshelfPayloadBukkit> CODEC = PacketCodec.tuple(
          PacketCodecs.INTEGER,
          RequestBookshelfPayloadBukkit::slot,
          RequestBookshelfPayloadBukkit::new
  );

  @Override
  public Id<? extends CustomPayload> getId() {
    return ID;
  }
}