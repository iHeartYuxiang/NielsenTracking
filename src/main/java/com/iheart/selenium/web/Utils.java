package com.iheart.selenium.web;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;




public class Utils {
	public static final String browserStack_USERNAME = "robrowe";
	public static final String browserStack_AUTOMATE_KEY = "SGtW65fVhR9zqp7KpVUo";
	public static final String browserStack_URL = "http://" + browserStack_USERNAME + ":" + browserStack_AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";


	public static WebDriver  createWebDriver() 
	
	{
	
	return createWebDriver("firefox");
	
	}
	
	
	public static WebDriver  createWebDriver(String browser) 
	
	{   WebDriver driver;
	
	    if (browser.equalsIgnoreCase("firefox"))
	    { 
	        driver = new FirefoxDriver();
	
	    }else if (browser.equalsIgnoreCase("chrome"))
	    {   //Set actual path to the driver file
	
	      System.setProperty("webdriver.chrome.driver", "C:\\Users\\1111128\\workspace\\drivers\\chromedriver.exe");
	   //   System.setProperty("webdriver.chrome.driver", "/Users/1111128/Documents/workspace/drivers/chromedriver.exe");	
	
	      ChromeOptions options = new ChromeOptions();
	      options.addArguments("test-type");
	      options.addArguments("--start-maximized");
	     
	      driver = new ChromeDriver(options);
	
	      }else if (browser.equalsIgnoreCase("ie"))
	      {    //Set actual path to the driver file
	
	      System.setProperty("webdriver.ie.driver","C:\\Users\\1111128\\workspace\\drivers\\IEDriverServer.exe");
	
	      
	      driver = new InternetExplorerDriver();
	
	      }else 
	
	      {
	
	      System.out.println("Unknown browser.");
	
	      return null;
	
	      }
	
	     
	
	      return driver;
	
	  }
	
	
public static WebDriver  createWebDriverWithProxy(String browser) 
	
	{   WebDriver driver;
	
	    if (browser.equalsIgnoreCase("firefox"))
	    { 
	    	//added for nielsen stuff:
	    	 FirefoxProfile profile = new FirefoxProfile();

		    /**
		     * Get the native browser to use our proxy
		     */
	    	 
	    	/* 
		    profile.setPreference("network.proxy.type", 1);
		    profile.setPreference("network.proxy.http", "localhost");
		    profile.setPreference("network.proxy.https", "localhost");
		    profile.setPreference("network.proxy.http_port", 5368);  // localProxyPort = 5368;
		    profile.setPreference("network.proxy.https_port", 5378);  // localProxyPort = 5368;
	     
	
		    driver = new FirefoxDriver(profile);
	        */
	    	 
	    	 String PROXY = "localhost:5368";

	    	 org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
	    	 proxy.setHttpProxy(PROXY)
	    	      .setFtpProxy(PROXY)
	    	      .setSslProxy(PROXY);
	    	 DesiredCapabilities cap = new DesiredCapabilities();
	    	 cap.setCapability(CapabilityType.PROXY, proxy);
	    	  driver = new FirefoxDriver(cap);
	    	
	    	
	    	//The following is original
	      //  driver = new FirefoxDriver();
	
	    }else if (browser.equalsIgnoreCase("chrome"))
	    {   //Set actual path to the driver file
	
	    //  System.setProperty("webdriver.chrome.driver", "C:\\Users\\1111128\\workspace\\drivers\\chromedriver.exe");
	    	System.setProperty("webdriver.chrome.driver", "/Users/1111128/git/drivers/chromedriver");
	    /*
	      DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	      capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://user:password@proxy.com:8080"));
	       driver = new ChromeDriver(capabilities);  
	      */
	    	 String PROXY = "localhost:5369";

	    	org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy(); 
	    	proxy.setHttpProxy(PROXY);
	    	proxy.setSslProxy(PROXY); 
	    	proxy.setFtpProxy(PROXY); 
	    	proxy.setSocksUsername("SSSLL277"); 
	    	proxy.setSocksPassword("password"); 

	    	DesiredCapabilities dc = DesiredCapabilities.chrome();
	    	dc.setCapability(CapabilityType.PROXY, proxy); 
	    	dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
	    	
	    	//Options
	    	ChromeOptions options = new ChromeOptions();
	        options.addArguments("test-type");
	        options.addArguments("--start-maximized");
    	
	    	dc.setCapability(ChromeOptions.CAPABILITY, options);
	    	
	    	driver = new ChromeDriver(dc);
	    	
	    	
	    /*	
	      ChromeOptions options = new ChromeOptions();
	      options.addArguments("test-type");
	      options.addArguments("--start-maximized");
	      options.addArguments("--proxy-server=http://localhost:5369 ");
	      options.addArguments("--proxy-server=https://localhost:5369 ");
	      driver = new ChromeDriver(options);
	     */
	      }else if (browser.equalsIgnoreCase("ie"))
	      {    //Set actual path to the driver file
	
	      System.setProperty("webdriver.ie.driver","C:\\Users\\1111128\\workspace\\drivers\\IEDriverServer.exe");
	
	      
	      driver = new InternetExplorerDriver();
	
	      }else 
	
	      {
	
	      System.out.println("Unknown browser.");
	
	      return null;
	
	      }
	
	     
	
	      return driver;
	
	  }
	
	
	
	public static WebDriver  createRemoteDriver(String browser,String browserVersion, String osName, String os_version) 
	{  
		WebDriver driver;
		
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", browser);
	    caps.setCapability("browser_version", browserVersion);
	    caps.setCapability("os", osName);
	    caps.setCapability("os_version", os_version);
	    caps.setCapability("browserstack.debug", "true");
	   
	    try{
	       driver = new RemoteWebDriver(new URL(browserStack_URL), caps);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	    driver.manage().window().maximize();
	
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	    return driver;
	
	  }
	
	
	
	
	public static void waitForPageToLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	
	        public Boolean apply(WebDriver driver) {
	
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	
	        }
	
	      };
	
	    Wait<WebDriver> wait = new WebDriverWait(driver,1000);
	
	      try {
	
	              wait.until(expectation);
	
	      } catch(Throwable error) {
	
	              System.out.println("Timeout waiting for Page Load Request to complete.");
	
	      }
	
	} 
	
	public static WebDriver launchBrowserWithProxy(String url, String browser)
	{       Page.setBrowser(browser);
			WebDriver driver = createWebDriverWithProxy(browser);
			
			driver.get(url);
			//WaitUtility.waitForAjax(driver);
			
			 driver.manage().window().maximize();
				
		      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
	
	}
	
	
	public static WebDriver launchBrowser(String url, String browser)
	{       Page.setBrowser(browser);
			WebDriver driver = createWebDriver(browser);
			
			driver.get(url);
		//	WaitUtility.waitForAjax(driver);
			
			 driver.manage().window().maximize();
				
		      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
	
	}
	
	
	public static int getRandomInt()
	{
		Random randomGenerator = new Random();
	  
	    int randomInt = randomGenerator.nextInt(999999);
	     
	    return randomInt;
	    
	}
	
	public static Map<String, String> getLocationByIp(WebDriver driver)
	{    Map<String, String> geoInfo = new HashMap<String, String>();
		 driver.navigate().to("http://www.iplocation.net");
		 String country = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		 String state = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(3)")).getText();
		 String city = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(4)")).getText();
		 
		 geoInfo.put("country", country);
		 geoInfo.put("state", state);
		 geoInfo.put("city", city);
		 
		 
		 
		 return geoInfo;
	}
	

}