package my.BPlugin.NMS;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMS {
	
	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace('.', ',').split(",")[3];
	}
	
	public static Class<?> getClass(String path) {
		try {
			return Class.forName("net.minecraft.server." + NMS.getVersion() + "." + path);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object getConnection(Player p) {
		try {
			Method gh = null;
			try {
				gh = p.getClass().getMethod("getHandle");
			} catch (NoSuchMethodException | SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//System.out.println();
			return gh.invoke(p).getClass().getField("playerConnection").get(gh.invoke(p));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
