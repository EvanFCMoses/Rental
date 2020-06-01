package com.evan.rental

import java.math.BigDecimal;

class Tool {

	enum ToolCode {
		LADW,
		CHNS,
		JAKR
	}


	private ToolCode toolCode;
	private String brand;
	private String toolType;

	public Tool(ToolCode toolCode, String brand, String toolType) {
		this.toolCode = toolCode;
		this.brand = brand;
		this.toolType = toolType;
	}

	public ToolCode getToolCode() {
		return this.getToolCode;
	}

	//TODO remove setters?
	public void setToolCode(ToolCode toolCode) {
		this.toolCode = toolCode;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getToolType() {
		return this.toolType;
	}
}