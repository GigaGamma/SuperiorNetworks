package com.superiorcraft.api.util.json;

public class JsonGroup {
	
	private Object obj;
	
	public JsonGroup(Object... obj) {
		setObj(obj);
	}
	
	public String getJsonConverted() {
		return JsonUtil.toJson(getObj());
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	};
	
}
