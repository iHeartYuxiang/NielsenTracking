package com.iheart.nielsenTracking;


import com.iheart.selenium.web.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.*;

import com.iheart.proxyLight.*;

public class NielsenTracking extends LiveRadioPage{

	
	 CustomRadioPage customRadioPage;
	
	
		public void playFor5Minutes()
	    {   login();
	    	comeToThisPage_direct();
	    	
	    	WaitUtility.sleep(1000);
	    	driver.findElement(By.cssSelector(".icon-play")).click();
	    	makeSureItIsPlaying();
	    	
	    	//play for 5 minutes 
	    	WaitUtility.sleep(5*60*1000 + 1 * 1000);
	    	
	    	if (!is_IVD_ok_NEW())
	    		errors.append("I, V, D ping doesn't work properly.");
	    	
	    	String quarterlyPing = RequestProcessor.getNielsenRequestMap().get("quarterlyPing");
	    	
	    	/*
	    	String quarterlyPing = "";
	    	
	    	List<String> urls = RequestProcessor.getNielsenRequests();
			for (String url: urls)
			{	 if (isQuarterlyPing(url))
			     {	quarterlyPing = url ;
		        	System.out.println("Quarterly ping: " + url);
		        	break;
		         }
			}
	       */
	    	
			if (!isQuaterlyPingOK(quarterlyPing))
				errors.append("Quarterly ping doesn't work properly.");
			
	    }
		
		public void playPausePlay()
	    {   login();
	    	comeToThisPage_direct();
	    	
	    	WaitUtility.sleep(1000);
	    	//play for 2 minutes and 2 seconds
	    	driver.findElement(By.cssSelector(".icon-play")).click();
	    	makeSureItIsPlaying();
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000); //play for one minute?
	    	//if (!is_IVD_ok())
	    	if (!is_IVD_ok_NEW())
	    		errors.append("I, V, D ping doesn't work properly.");
	    	
	    	
	    	 //stop, then pause for 2 minutes
	    	
	    	//driver.findElement(By.cssSelector(".icon-stop")).click();
	    	driver.findElement(By.cssSelector("button.playing:nth-child(3)")).click();
	    	makeSureItIsNotPlaying();
	        System.out.println("Station shall not be playing now.");
	        
	        clearNielsenRequest();
	    	 System.out.println("nielsenRequest shall be cleared:" + getNielsenRequest().size() );
	    	
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000);
	        int newCount = getNielsenRequest().size();
	        System.out.println("newCount after stop:" + newCount);
	        if (newCount > 1  )
	        	errors.append("No ping shall be sent out after audio is paused.");
	       
	        
	        //Scan & play  for 2 minutes and 2 seconds
	        driver.findElement(By.cssSelector("button.text:nth-child(4)")).click();
	    	
	    	System.out.println("Play again. Now it will wait for 2 minutes.");
	    	
	    	//???I suppose here we need to clear request first?
	        clearNielsenRequest();
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000);
	    	
	    	System.out.println("2 minutes has passed.");
	    	//if (!is_IVD_ok())
	    	if (!is_IVD_ok_NEW())
	    		errors.append("I, P, D ping doesn't work properly after stream is restarted.");
	    	
	    	System.out.println("all checking is done.");
			
	    }
		
		
		//opt-out, opt-in
		public void privacyPolicy()
		{  
			
			 optOut();
			
			
			//Launch a new browser? Then play a little
			 
		    login();
			driver.get("http://www.iheart.com/live/country/US/city/new-york-ny-159/");
			 
			makeSureItIsPlaying();
	    	WaitUtility.sleep(2000);
	    	
	    	//play for 2 minutes and 2 seconds
	    	//driver.findElement(By.cssSelector(".icon-play")).click();
	    	driver.findElement(By.cssSelector("button.idle:nth-child(3)")).click();
	    	clearNielsenRequest();
	    	WaitUtility.sleep(1*60*1000 + 1 * 1000);
	        
	        int count = RequestProcessor.getNielsenRequestMap().size();
	    	System.out.println("See nielsen request count:" + RequestProcessor.getNielsenRequestMap().size());
	    	
	    	if (count > 0)
	    		errors.append("No ping shall be sent over to nielsen after user opt out.");
			
		}
		
		
		private void optOut()
		{
			// OPT OUT
			
				driver.findElement(By.cssSelector(".genre-game-footer > div:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)")).click();
				//switch window
				String firstWindow = switchWindow();
				WaitUtility.sleep(10000);
				//opt-out
				
				//Need to scrolldown
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,250)", "");
				driver.findElement(By.cssSelector(".toc > li:nth-child(7) > a:nth-child(1)")).click();
				driver.findElement(By.cssSelector(".small-8 > article:nth-child(2) > p:nth-child(58) > a:nth-child(1)")).click();
				
				switchWindow();
				WaitUtility.sleep(40*1000);//extremely slow to check the status
				try{
					
					driver.findElement(By.id("overlayClose")).click();
				}catch(Exception e)
				{
					
				}
				try {
				   driver.findElement(By.id("selectAllACTIVE")).click();
				
				   driver.findElement(By.cssSelector("#ooSubmitACTIVE > input:nth-child(2)")).click();
				   WaitUtility.sleep(10000);  //SLOW
				}catch(Exception e)
				{
					System.out.println("Opted out already.");
				}
		        
			//Switch back to first window
			goToPreviousWindow(driver, firstWindow);
				
		}
		
		public void switchStation()
		{   WaitUtility.sleep(2000);
			login();
			comeToThisPage_direct();
	    	
	    	WaitUtility.sleep(1000);
	    	//play for 2 minutes , then kill the browser
	    	driver.findElement(By.cssSelector(".icon-play")).click();
	    	makeSureItIsPlaying();
	    	
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000);
		    
		    //scan to next station
	    	driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
	    	clearNielsenRequest();
	    	
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000);
	    	
	    	if (!is_IVD_ok_NEW())
	    		errors.append("I, P, D ping doesn't work properly after stream is restarted.");
	    	
		}
		
		public void browserExit()
		{
			comeToThisPage_direct();
	    	
	    	WaitUtility.sleep(1000);
	    	//play for 2 minutes , then kill the browser
	    	driver.findElement(By.cssSelector(".icon-play")).click();
	    	//Wait for the pre-roll
	    	WaitUtility.sleep(30*1000);
	    	
	    	WaitUtility.sleep(2*60*1000 + 1 * 1000);
	    	
	    	//Verify result: I V D pings + D ping with cr = _11
	    	
	    	if (!is_IVD_ok_NEW())
	    		errors.append("I, P, D ping doesn't work properly.");
	    	
	    	clearNielsenRequest();
	    	
	    	driver.quit();
	    	
	    	List<String> urls = RequestProcessor.getNielsenRequests();
	    	
	    	boolean isPendingOK = false;
	    	
			for (String url: urls)
			{	System.out.println(url);
		         if (isPendingPing(url))
		        	isPendingOK = isPendingPingOK(url);
			}
	    	
		    if (! isPendingOK )
		    	errors.append("No pending ping is sent over.");
		}
		
		public void stationNotMeasured()
		{
			//Play a customer station
			 customRadioPage = PageFactory.initElements(driver, CustomRadioPage.class);
			 customRadioPage.playCustom();
			 clearNielsenRequest();
			 WaitUtility.sleep(5000);
			
			// List<String> urls = RequestProcessor.getNielsenRequests();
			Map<String, String>  urls = RequestProcessor.getNielsenRequestMap();
			if(isImpressionPingOK(urls.get("impressionPing")) )
					errors.append("Customer station shall not be measured.");
		}
		
		public List<String> getNielsenRequest()
		{
			List<String> urls = RequestProcessor.getNielsenRequests();
				for (String url: urls)
				{	System.out.println(url);
			         
				}
			
			return urls;
		}	
		
		public void  clearNielsenRequest()
		{
			 RequestProcessor.clearNielsenRequests();
			 
		}	

		// I, V, D 
		
		/*
		public boolean is_IVD_ok()
		{
			boolean isImpressionOK = false;
	    	boolean isViewOK = false;
	    	boolean isDurationOK = false;
	    	
	    	List<String> urls = RequestProcessor.getNielsenRequests();
			for (String url: urls)
			{	//System.out.println(url);
		        if (isImpressionPing(url)) 
		        {	System.out.println("IMpression ping: " + url);
		        	isImpressionOK = isImpressionPingOK(url);
		        }else if (isViewPing(url))
		        {	isViewOK = isViewPingOK(url);
		        	System.out.println("View ping: " + url);
		        }
		        else if (isDurationPing(url))
		        {	
		        	isDurationOK = isDurationPingOK(url);
		        	System.out.println("Duration ping: " + url);
		        }
			}
	    	
			System.out.println("IVD:" + isImpressionOK + "/" + isViewOK + "/" + isDurationOK);
			
		    return (isImpressionOK && isViewOK && isDurationOK);
		    	
		}
		*/
		
		public boolean is_IVD_ok_NEW()
		{   System.out.println("checking is_IVD_ok_NEW ..");
			boolean isImpressionOK = false;
	    	boolean isViewOK = false;
	    	boolean isDurationOK = false;
	    	
	    	Map<String, String> urls = RequestProcessor.getNielsenRequestMap();
	    	System.out.println("See urls count:" + urls.size());
	    	System.out.println(urls);
	    	
		    isImpressionOK = isImpressionPingOK(urls.get("impressionPing"));
		    isViewOK = isViewPingOK(urls.get("viewPing"));
		    isDurationOK = isDurationPingOK(urls.get("durationPing"));
			System.out.println("IVD:" + isImpressionOK + "/" + isViewOK + "/" + isDurationOK);
			
		    return (isImpressionOK && isViewOK && isDurationOK);
		    	
		}
		
		
		
	
		/*
		 * at=start
		 * ci=..
		 * c18, c19, c20
		 */
		
		public boolean isImpressionPingOK(String request)
		{  System.out.println("isImpressionPingOK:" + request);
		    if (request == null)
		    	return false;
			boolean isOK = false;
		
		    try {
		    	isOK =  (request.substring(request.indexOf("cr="))).contains("_I") 
			    		&&   isC18_19_20_OK(request);
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
		    
		    System.out.println("isImpressionPingOK:" + isOK);
		    return  isOK;
		   
		}
		
		public static boolean isImpressionPing(String url)
		{
			return url.contains("at=start");
		}
		
		public static boolean isViewPing(String url)
		{
			return url.contains("at=view") & url.contains("_V")  ;
		}
		
		public static boolean isDurationPing(String url)
		{
			return url.contains("at=timer");
		}
		
		public static boolean isPendingPing(String url)
		{
			return url.contains("at=timer") && url.contains("_11");
		}
		
		public static boolean isHelloPing(String url)
		{
			return url.contains("cfg?")
					   && url.contains("apid");
		}
		
		public static boolean isGoodByePing(String url)
		{
			return   url.contains("cfg?")
					   && url.contains("uoo=true");
		}
		
		public static boolean isQuarterlyPing(String url)
		{  if (!url.contains("cr=")) return false;
			if((url.substring(url.indexOf("cr="))).contains("_Q"))
			 	System.out.println("RP!! quarterlY?:" + url );
			return url.contains("at=view")
					&&(url.substring(url.indexOf("cr="))).contains("_Q") ;
					
		}
		
		
		
		
		public boolean isViewPingOK(String request)
		{   System.out.println("View ping:" + request);
			if (request == null)
	    	     return false;
		    return  (request.substring(request.indexOf("cr="))).contains("_V")
		    		&& request.contains("at=view")
		    		&&  isC18_19_20_OK(request);
		   
		}
		
		public boolean isDurationPingOK(String request)
		{   if (request == null)
	    			return false;
			return   request.contains("at=timer")
					&&(request.substring(request.indexOf("cr="))).contains("_D") 
					//&&(request.contains("11111"))
		    		&&   isC18_19_20_OK(request);
					
		}
		
		
		public boolean isQuaterlyPingOK(String request)
		{   System.out.println("isQuaterlyPingOK:" + request);
			if (request == null)
	    			return false;
			return   request.contains("at=view")
					&&(request.substring(request.indexOf("cr="))).contains("_Q") &&  isC18_19_20_OK(request);
					//&&(request.contains("11111"))
		    		
					
		}
		
	
		public boolean isPendingPingOK(String request)
		{  
			if (request == null)
		    	return false;
			return   request.contains("at=timer")
					&&(request.substring(request.indexOf("cr="))).contains("_11") 
					&&(request.contains("11111"))
		    		&&  isC18_19_20_OK(request);
					
		}
		
		
		public boolean isHelloPingOK(String request)
		{
			if (request == null)
		    	return false;
		   return  request.startsWith("https")
				   && request.contains("cfg?")
				   && request.contains("apid");
			
		}
		
		public boolean isGoodbyePingOK(String request)
		{
			if (request == null)
		    	return false;
			 return  request.startsWith("https")
					   && request.contains("cfg?")
					   && request.contains("uoo=true");
		}
		
		private boolean isC18_19_20_OK(String request)
		{   if (request == null)
	    		return false;
			String c18 = request.substring(request.indexOf("c18="));
			String c19 = request.substring(request.indexOf("c19="));
			String c20 = request.substring(request.indexOf("c20="));
			System.out.println("c18:" + c18);
			System.out.println("c19:" + c19);
			System.out.println("c20:" + c20);
			
			boolean isOK = (request.substring(request.indexOf("c18="))).contains("clb")
		    		&& (request.substring(request.indexOf("c19="))).contains("stntyp,2")
		    		&& (request.substring(request.indexOf("c20="))).contains("prvd,Clear");
			
			System.out.println("isC18_19_20_OK:" + isOK);
			
			return isOK;  
		}
		
		public void playStationAfterLogin()
	    {   login();
	    	
			comeToThisPage_direct();
			WaitUtility.injectJQuery(driver);
			try {
	           WaitUtility.hijackHTTPS(driver);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			WaitUtility.sleep(3000);
			
	    	driver.findElement(By.xpath("//*[@id='player']/div[2]/div/button[3]/i")).click();
			
			
	    }
		    
		
	
}
