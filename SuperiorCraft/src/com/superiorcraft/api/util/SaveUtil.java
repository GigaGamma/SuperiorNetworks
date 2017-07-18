package com.superiorcraft.api.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveUtil {
	
	public static PlayerData[] playerData;
	
	public static void save() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("players.data"));
			out.writeObject(playerData);
			out.flush();
			out.close();
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void read() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream("players.data"));
		} catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		try {
			playerData = (PlayerData[]) in.readObject();
			in.close();
		} catch (ClassNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
	}
	
}
