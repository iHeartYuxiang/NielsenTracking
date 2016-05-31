package com.iheart.selenium.web;

public class CapabilitySetting {
    private String browser;
    private String browserVersion;
    private String osName;
    private String os_version;
    
	public CapabilitySetting(String browser,String browserVersion, String osName, String os_version)
	{
		this.browser = browser;
		this.browserVersion = browserVersion;
		this.osName = osName;
		this.os_version = os_version;
	}
	
	public String getBrowser()
	{
		return browser;
	}
	
	public String getBrowserVersion()
	{
		return browserVersion;
	}
	
	public String getOsName()
	{
		return osName;
	}
	
	public String getOs_version()
	{
		return os_version;
	}
}
