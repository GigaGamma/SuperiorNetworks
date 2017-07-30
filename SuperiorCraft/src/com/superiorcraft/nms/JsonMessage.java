package com.superiorcraft.nms;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

public class JsonMessage {
	
	String codetext;
	
	public JsonMessage(String text, String color) {
		codetext = "{\"text\":\"" + text + "\",\"color\":\"" + color + "\"}";
	}
	
	public JsonMessage(String text, String color, String hoverText, String hoverColor, String command) {
		codetext = "{\"text\":\"" + text + "\",\"color\":\"" + color + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + command + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + hoverText + "\",\"color\":\"" + hoverColor + "\"}]}}}";
	}
	
	@Override
	public String toString() {
		return getCodetext();
	}
	
	public String getCodetext() {
		return codetext;
	}

	/*public void setCodetext(String codetext) {
		this.codetext = codetext;
	}*/

	public static PacketPlayOutChat createPacketPlayOutChat(String s){return new PacketPlayOutChat(ChatSerializer.a(s));}

	public static void sendJsonMessage(Player p, String s){
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(createPacketPlayOutChat(s));
	}
	
	public static void sendJsonMessages(Player p, JsonMessage[] s) {
		String f = "[\"\"";
		for (JsonMessage msg : s) {
			f += "," + msg.toString();
		}
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(createPacketPlayOutChat(f + "]"));
	}
	
	public static void broadcastJsonMessage(String s) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			JsonMessage.sendJsonMessage(p, s);
		}
	}
	
	public static void broadcastJsonMessages(JsonMessage[] s) {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			JsonMessage.sendJsonMessages(p, s);
		}
	}
	
	public static void sendMessage(Player player, String message, String url) {
		sendJsonMessage(player, "{text:\"" + message + "\",clickEvent:{action:open_url,value:\"" + url + "\"}}");
	}
	
}
