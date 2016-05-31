package com.iheart.nielsenTracking;


import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.iheart.proxyLight.RequestProcessor;
import com.iheart.proxyLight.Response;
import com.iheart.selenium.web.CustomRadioPage;
import com.iheart.selenium.web.HomePage;
import com.iheart.selenium.web.Page;
import com.iheart.selenium.web.Utils;
import com.iheart.selenium.web.WaitUtility;


public class HuntAjaxTest {

	WebDriver driver;
	 static Proxy proxy; 
	 Response response;
		
	 
	 HomePage homePage;
	 NielsenTracking liveRadioPage;
	 CustomRadioPage customRadioPage;
		
	//static String browser = "firefox";
    final static String browser = "chrome";
	 
	final String URL = "http://www.iheart.com";
	
	@Rule public TestName name = new TestName();
	/*
	@BeforeClass
	public static void startUpProxy(){
		
		 proxy = new Proxy();
		 proxy.initProxy(browser);
	}
	*/
	
	@Before
    public void init() {
	  driver = Utils.launchBrowser(URL, browser);

	  Page.setDriver (driver);
	 
	  proxy = new Proxy();
	  proxy.initProxy(browser);
	 
	  WaitUtility.sleep(8000); 
    
      homePage = PageFactory.initElements(driver, HomePage.class);
      customRadioPage = PageFactory.initElements(driver, CustomRadioPage.class);
     
      Page.getErrors().delete(0, Page.getErrors().length());
      RequestProcessor.clearNielsenRequests();
    }
	
	@Test
	 public void testAjaxHunting() throws Exception
	 {  
	 	System.out.println("test method:" +  name.getMethodName() );
	 	try{
	 		customRadioPage.huntAjax();
	 	   //  proxy.proxy.stop();
	 		 
	 	}catch(Exception e)
	 	{  
	 		handleException(e);
	 	}
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	 
	
	@After
	   public void tearDown() throws Exception{
	   	//proxy.proxy.stop();
	   	if (Page.getErrors().length() > 0)
				 fail(Page.getErrors().toString());
	   	try{
	      // 	driver.quit(); 
	   	}catch(Exception e)
	   	{   
	   		System.out.println("Excepton closing driver. Why the hack so?");
	   		e.printStackTrace();
	   	}
	       
	   	proxy.proxy.stop();
	   	
	   }
	
	
	
	 private void handleException(Exception e)
	   {   Page.getErrors().append("Exception is thrown.");
	       e.printStackTrace();
	       try{
	   	   Page.takeScreenshot(driver, name.getMethodName());
	       }catch(Exception eX)
	       {
	       	eX.printStackTrace();
	       }
	   }
	
	 
	 
}
