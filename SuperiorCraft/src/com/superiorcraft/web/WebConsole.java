package com.superiorcraft.web;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Route;
import spark.template.velocity.VelocityTemplateEngine;

public class WebConsole {
	
	public static Route request() {
		System.out.println("A");
		return (req, res) -> {
			System.out.println("B");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name", "samplename");
			//return "A";
			return new VelocityTemplateEngine().render(new ModelAndView(params, "templates/webconsole.html"));
		};
	}
	
}
