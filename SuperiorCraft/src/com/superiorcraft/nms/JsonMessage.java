package com.superiorcraft.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

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

	public static Object createPacketPlayOutChat(String s) {
		Class<?> chatSerial = NMSAdapter.getClass("IChatBaseComponent$ChatSerializer");
		Class<?> chatComponent = NMSAdapter.getClass("IChatBaseComponent");
		Class<?> packetClass = NMSAdapter.getClass("PacketPlayOutChat");
		
		Constructor constructor = null;
		Object text = null;
		Object packetFinal = null;
		Field field = null;
		
		try {
			constructor = packetClass.getConstructor(chatComponent);
			text = chatSerial.getMethod("a", String.class).invoke(chatSerial, s);
			packetFinal = constructor.newInstance(text);
			field = packetFinal.getClass().getDeclaredField("a");
			field.setAccessible(true);
			field.set(packetFinal, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return packetFinal;
	}
	
	public static Object createPacketPlayOutActionBar(String s) {
		Class<?> chatSerial = NMSAdapter.getClass("IChatBaseComponent$ChatSerializer");
		Class<?> chatComponent = NMSAdapter.getClass("IChatBaseComponent");
		Class<?> packetClass = NMSAdapter.getClass("PacketPlayOutChat");
		
		Constructor constructor = null;
		Object text = null;
		Object packetFinal = null;
		Field field = null;
		
		try {
			constructor = packetClass.getConstructor(chatComponent, NMSAdapter.getClass("ChatMessageType"));
			text = chatSerial.getMethod("a", String.class).invoke(chatSerial, s);
			Class<?> classCMT = NMSAdapter.getClass("ChatMessageType");
			Object cf = null;
			for (Object c : classCMT.getEnumConstants()) {
				if (c.toString().contains("GAME_INFO")) {
					cf = c;
					//System.out.println(cf);
				}
			}
			packetFinal = constructor.newInstance(text, cf);
			field = packetFinal.getClass().getDeclaredField("a");
			field.setAccessible(true);
			field.set(packetFinal, text);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return packetFinal;
	}

	public static void sendJsonMessage(Player p, String s){
		NMSAdapter.sendPacket(p, createPacketPlayOutChat(s));
	}
	
	public static void sendActionBar(Player p, String s) {
		NMSAdapter.sendPacket(p, createPacketPlayOutActionBar("{\"text\": \"" + s + "\"}"));
	}
	
	public static void sendJsonMessages(Player p, JsonMessage[] s) {
		String f = "[\"\"";
		for (JsonMessage msg : s) {
			f += "," + msg.toString();
		}
		//((CraftPlayer) p).getHandle().playerConnection.sendPacket(createPacketPlayOutChat(f + "]"));
		NMSAdapter.sendPacket(p, createPacketPlayOutChat(f + "]"));
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
