package com.superiorcraft.web;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.superiorcraft.SuperiorCraft;
import com.superiorcraft.api.util.DatabaseUtil;
import com.superiorcraft.api.util.ServerUtil;
import com.superiorcraft.api.util.json.JsonGroup;
import com.superiorcraft.web.views.Login;

import spark.Spark;

public class SuperiorWeb {
	
	public static DatabaseUtil db;
	
	public static void main(String[] args) {
		//stopServer();
		startServer();
	}
	
	public static void startServer() {
		db = new DatabaseUtil("C:/Users/augus/git/SuperiorCraft/SuperiorCraft/resources/sweb.db");
		System.out.println("Starting...");
		Spark.setPort(9382);
		Spark.staticFileLocation("/assets");
		
		Spark.get(Path.INDEX, Login.request());
		Spark.post(Path.INDEX, Login.form());
		Spark.get(Path.CONSOLE, WebConsole.request());
		
		Spark.get("/api/status", (req, res) -> {
			res.type("application/json");
			return new JsonGroup(ServerUtil.getPlayers().size(), ServerUtil.getPlayerNames()).getJsonConverted();
		});
		Spark.get("*", (req, res) -> {
			stopServer();
			return req.body();
		});
		System.out.println("Started Spark Server On Port " + Spark.port());
	}
	
	public static void stopServer() {
		System.out.println("Stopping server");
		System.exit(0);
		Spark.stop();
	}
	
}
