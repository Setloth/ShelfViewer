package me.setloth.shelfviewer.packet;

import net.minecraft.util.Identifier;

public class NetworkingConstants {
  public static final Identifier REQUEST_BOOKSHELF_PACKET_ID = Identifier.of("shelfviewer", "request_bookshelf");

  public static final Identifier RESPONSE_BOOKSHELF_PACKET_ID = Identifier.of("shelfviewer", "response_bookshelf");

  public static final Identifier REQUEST_BOOKSHELF_BUKKIT_PACKET_ID = Identifier.of("shelfviewer", "request_bookshelf_bukkit");

  public static final Identifier RESPONSE_BOOKSHELF_BUKKIT_PACKET_ID = Identifier.of("shelfviewer", "response_bookshelf_bukkit");
}
