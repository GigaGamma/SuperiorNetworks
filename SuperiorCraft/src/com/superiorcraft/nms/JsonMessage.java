package com.superiorcraft.nms;

import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;

public class JsonMessage {
	
	public static PacketPlayOutChat createPacketPlayOutChat(String s){return new PacketPlayOutChat(ChatSerializer.a(s));}

	public static void sendJsonMessage(Player p, String s){( (CraftPlayer)p ).getHandle().playerConnection.sendPacket( createPacketPlayOutChat(s) );}

	public static void sendMessage(Player player, String message, String url) {
		sendJsonMessage(player, "{text:\"" + message + "\",clickEvent:{action:open_url,value:\"" + url + "\"}}");
	}
	
}
