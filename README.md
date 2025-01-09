# ShelfViewer

What book is that?

Client & Server side mod for Fabric MC 1.21+

For the mod to work, it will need to rely on communication with the server. It is important that you either install the fabric mod on a fabric server, or the [ModificationMaster](https://github.com/Setloth/ModificationMaster) plugin on a Bukkit/Spigot/Paper MC Server. 

My goal with creating this mod was for it to be client side only, for user simplicity. However, Minecraft does not let the client access information about the server-side properties of a chiseled bookshelf. On a singleplayer world with the client-side mod installed, the mod will work as expected because the logical server is included on the client end (you dont connect to a server somewhere else to join a singleplayer world, since, you host the server locally). Hence, the mod must be installed on the server side in order for the client rendering to take effect. Due to this limitation with Minecraft, I still wanted to make the mod more accessible for those that may not be running fabric servers (like myself). So, the inclusion of [Modification Master](https://github.com/Setloth/ModificationMaster) on your Bukkit/Spigot/Paper server will allow any clients with the Shelf Viewer mod to have access to the full functionality of the mod. 

Feel free to create issues with any problems you encounter!
