package com.iheart.selenium.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

public class WaitUtility {

	private static final String JQUERY_LOAD_SCRIPT = "sources/jQuerify.js";
	private static final String HIJACK_AJAX_SCRIPT = "sources/injectScriptForAjaxCalls.js";

	
	public static void sleep(long milliSecond)
	{
		try{
			Thread.sleep(milliSecond);
		}catch(Exception e)
		{
			
		}
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
	
	public static void waitForAjax(WebDriver driver)
	{    injectJQuery(driver);
		//Check: how many on-going ajax call on this page?
		long ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
	//	System.out.println("Ajax call count:" + ajaxCallCount);
	    while (true) // Handle timeout somewhere
	    {
	        boolean ajaxIsComplete =(Boolean) ((JavascriptExecutor)driver ).executeScript("return jQuery.active == 0");
	        if (ajaxIsComplete)
	            break;
	        sleep(1000);
	    }
	   
	    ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
		System.out.println("Active Ajax call count after waiting:" + ajaxCallCount);
	}

	
	/** dynamically load jQuery */
	public static void injectJQuery(WebDriver driver){
	    String LoadJQuery = "(function(jqueryUrl, callback) {\n" +
	            "if (typeof jqueryUrl != 'string') {" +
	            "jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';\n" +
	            "}\n" +
	            "if (typeof jQuery == 'undefined') {\n" +
	            "var script = document.createElement('script');\n" +
	            "var head = document.getElementsByTagName('head')[0];\n" +
	            "var done = false;\n" +
	            "script.onload = script.onreadystatechange = (function() {\n" +
	            "if (!done && (!this.readyState || this.readyState == 'loaded'\n" +
	            "|| this.readyState == 'complete')) {\n" +
	            "done = true;\n" +
	            "script.onload = script.onreadystatechange = null;\n" +
	            "head.removeChild(script);\n" +
	            "callback();\n" +
	            "}\n" +
	            "});\n" +
	            "script.src = jqueryUrl;\n" +
	            "head.appendChild(script);\n" +
	            "}\n" +
	            "else {\n" +
	            "callback();\n" +
	            "}\n" +
	            "})(arguments[0], arguments[arguments.length - 1]);\n";
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	   // give jQuery time to load asynchronously
	   driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	   js.executeAsyncScript(LoadJQuery);
	    System.out.println("Jquery is loaded.");
	}	
	
	
	public static void hijackAjaxSend(WebDriver driver) throws Exception
    {
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("(function( send) {"+ 
			        		 " XMLHttpRequest.prototype.realSend = XMLHttpRequest.prototype.send;"+
						     // here "this" points to the XMLHttpRequest Object.
						    " var newSend = function(vData) {"
						     + 			"console.log('see request data: ' + vData);}" +
						     "  this.realSend(vData);" +
						    
						     " };" +
						    " XMLHttpRequest.prototype.send = newSend;" +
						   
						    "})(XMLHttpRequest.prototype.send);"
        );
    }
    
	
	public static void hijackHTTPS(WebDriver driver) throws Exception
    {
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        /*
        js.executeAsyncScript("(function($) {"+ 
        		               "var callback = arguments[arguments.length - 1];" +
        		               
	        					"if (window.location.protocol == 'https:'){" +
	        		               " var url = window.location.href; " +
	        					   " console.log('HELLO! ' + window.location.href.substring(window.location.protocol.length));}" +
        		          	
	        				  " callback();"  +	   
						    "})(jQuery);"
        );
        */
        
        
        
        js.executeAsyncScript(  
        		      "var callback = arguments[arguments.length - 1];" +
        		    				
                      " console.log('see argument:' + arguments.length + '/' + arguments[arguments.length - 1]); " +
                  //    "if (window.location.protocol == 'https:'){" +
   					   " console.log('HELLO :' + window.location.href);}" +  //window.location.href.substring(window.location.protocol.length));" +
   					  "   callback(window.location.href);" 		 
		        
        		);
        
        System.out.println("HTTPS hacking code is inserted.");
    }
    
	
	
	// driver
    public static void interceptAjax(WebDriver driver) throws Exception
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        String ajaxURL = "";
     
           ajaxURL = (String)js.executeAsyncScript("(function(open, callback) {" +
        		   		" var ajaxURL;" +
        		   
        		   		"function onStateChange(event) { "+
		        		    "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
		        		    " if (this.readyState === 4 && this.status == 200) {" +
		        		     "console.log('AJAX IS DONE. see response:' + this.responseText);"+
							//" console.log('see event.data/target:' + event.data + '/' + event.target);"+ 
		        		    
		        		    // fires on every readystatechange ever
		        		    // use `this` to determine which XHR object fired the change event
		        		    " setTimeout(function() {" +
		        		    "    console.log('2s wait is done.'); " +
		        		   " }, 2000);" +
		        		 "}}"+
		        		    
		        		 
        		   		"XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" +
        		   		" ajaxURL =  url;" +
        		   		"	    console.log('see ajax calls:' + url  );"+
                    	//"	   console.log('WHY? url contains getTracks?' + url.indexOf('getTracks'));"+
        		   		" if (url.indexOf('getStreamUrl') >=0 ){ " +
                    	"	    this.addEventListener('readystatechange', onStateChange);}" +
        				
                    	"  open.call(this, method, url, async, user, pass);" +
        			
        				"};" +
				"callback();"+
        		"})(XMLHttpRequest.prototype.open,arguments[arguments.length - 1]);" 
        	
           		 );  
        
  }						
    

    public static void interceptAjaxSend(WebDriver driver) throws Exception
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        String ajaxURL = "";
     
           ajaxURL = (String)js.executeAsyncScript("(function(open, callback) {" +
        		   		" var ajaxURL;" +
        		   
        		   		"function onStateChange(event) { "+
		        		    "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
		        		    " if (this.readyState === 4 && this.status == 200) {" +
		        		     "console.log('AJAX IS DONE. see response:' + this.responseText);"+
							//" console.log('see event.data/target:' + event.data + '/' + event.target);"+ 
		        		    
		        		    // fires on every readystatechange ever
		        		    // use `this` to determine which XHR object fired the change event
		        		    " setTimeout(function() {" +
		        		    "    console.log('2s wait is done.'); " +
		        		   " }, 2000);" +
		        		 "}}"+
								        		    
						"XMLHttpRequest.prototype.send = function(data) {" +
							
							"	    console.log('see data:' + data  );"+
							"	send.call(this, data);" +
						"};" + 
						
						
				"callback();"+
        		"})(XMLHttpRequest.prototype.send,arguments[arguments.length - 1]);" 
        	
           		 );  
}						
    
    
    
    public static void interceptHTTPS(WebDriver driver)
    {
    	try{
			  WaitUtility.injectJQuery(driver);
			 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    	
    	try{
			  
			  WaitUtility.hijackHTTPS(driver);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
	
    
  //Check every 500 milliSeconds
  	//.isDisplayed() doesn't work with iheart elements, 
  	public static WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInMilliSecond)
  	{  
  		long times = timeOutInMilliSecond / 500 + 1;    
  		long count = 0;
  		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
  		
  		do{
  			try{
  			   System.out.println(element.getAttribute("outerHTML"));
  			  if (element.isEnabled())
  			      break;
  			}catch(Exception e)
  			{  System.out.println("Not there. try again.");
  			  // e.printStackTrace();
  			   WaitUtility.sleep(500);
  			}
  			
  			count++;
  		}while (count< times);
  		
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		
  		return element;
  	}
  	
    
}
