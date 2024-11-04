package me.setloth.shelfviewer.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.math.BlockPos;

public record RequestBookshelfPayload(BlockPos blockPos, int clickedSlot) implements CustomPayload {
  public static final CustomPayload.Id<RequestBookshelfPayload> ID = new CustomPayload.Id<>(NetworkingConstants.REQUEST_BOOKSHELF_PACKET_ID);
  public static final PacketCodec<RegistryByteBuf, RequestBookshelfPayload> CODEC = PacketCodec.tuple(
          BlockPos.PACKET_CODEC, RequestBookshelfPayload::blockPos,
          PacketCodecs.INTEGER, RequestBookshelfPayload::clickedSlot,
          RequestBookshelfPayload::new
  );

  @Override
  public CustomPayload.Id<? extends CustomPayload> getId() {
    return ID;
  }

}
