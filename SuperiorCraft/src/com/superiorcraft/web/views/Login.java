package com.superiorcraft.web.views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.superiorcraft.web.SuperiorWeb;

import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class Login {
	
	public static Route request() {
		return (req, res) -> {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", "samplename");
			return new VelocityTemplateEngine().render(new ModelAndView(params, "templates/index.html"));
		};
	}
	
	public static Route form() {
		return (req, res) -> {
			ResultSet rs = SuperiorWeb.db.execQuery("SELECT * FROM users;");
			boolean pass = false;
			try {
				while (rs.next()) {
					System.out.println(rs.getString("username"));
					System.out.println(rs.getString("password"));
					if(rs.getString("username").equals(req.queryParams("username")) && rs.getString("password").equals(req.queryParams("password"))) {
						pass = true;
					}
				}
				rs.close();
			} catch (SQLException e) {e.printStackTrace();}
			if (!pass) {
				return "TEST";
			}
			HashMap<String, Object> params = new HashMap<String, Object>();
			System.out.println(req.queryParams("username"));
			params.put("username", req.queryParams("username"));
			return new VelocityTemplateEngine().render(new ModelAndView(params, "templates/login.html"));
		};
	}
	
}
