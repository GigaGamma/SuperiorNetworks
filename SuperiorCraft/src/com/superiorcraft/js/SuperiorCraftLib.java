package com.superiorcraft.js;

import com.jplug.js.lib.Library;

public class SuperiorCraftLib implements Library {

	@Override
	public String getName() {
		return "ScLib";
	}

	@Override
	public String getNamespace() {
		return "superiorcraft";
	}

	@Override
	public Object getPackage() {
		return new SuperiorCraftPackage();
	}

	@Override
	public String getVersion() {
		return "0.0.1-alpha";
	}

}
