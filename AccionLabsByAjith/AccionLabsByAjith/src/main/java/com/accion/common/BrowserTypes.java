package com.accion.common;

/**
 * 
 * @author Ajith A S
 * 
 */
public enum BrowserTypes {
	FIREFOX("firefox"), CHROME("chrome"), IE("ie"), SAFARI("safari"), REMOTE("remote"), HTMLUNIT(
			"htmlunit"), CHROME_MOBILE ("chrome)");

	private final String browser;

	BrowserTypes(String browser) {
		this.browser = browser;
	}

	public String getBrowser() {
		return browser;
	}
}