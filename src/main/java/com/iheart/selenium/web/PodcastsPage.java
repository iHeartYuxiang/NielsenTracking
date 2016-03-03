package com.iheart.selenium.web;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

//import org.apache.log4j.Logger;

public class PodcastsPage  extends Page{
	
	@FindBy(css="#main > div > div:nth-child(2) > section:nth-child(1) > ul > li:nth-child(2) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
	    private WebElement secondPod;
	
	
	
	//@FindBy(css="#main > div > div.filters > div > div > select") private WebElement topics; //This works fine for Firefox
	@FindBy(name="category") private WebElement topics; //topics for podcasts
	
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)") 
	    private WebElement firstPod;
	//CSS PATH IN CHROME
	//#main > div > div:nth-child(2) > section:nth-child(1) > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i
	//@FindBy(css="ul.station-tiles:nth-child(3) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
	@FindBy(css="ul.station-tiles:nth-child(3) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
		private WebElement  firstPodName;
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
		private WebElement  firstPodNameAfterFilter;
	
	//favorite
	@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > ul > li.station-name > button > i")
		private WebElement favorite;
	
				
	//@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(3) > div > div > button > span")
	//	private WebElement loggedInUser; //use signedAccount in the Page.java
	
	//thumpUp/Down
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(2) > i")
	   	private WebElement thumpUp;
	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(1) > i")
	    private WebElement thumpDown;
	//alternate way to access them
	@FindBy(css=".icon-thumb-up-unfilled") private WebElement thumpUp2;
	@FindBy(css="icon-thumb-down-unfilled") private WebElement thumpDown2;
	
	@FindBy(css=".icon-thumb-up-filled") private WebElement thumpUp_filled;
	@FindBy(css="icon-thumb-down-filled") private WebElement thumpDown_filled;
	
	
	
	
	
	public void WEB_11772_browsePodcasts()
	{
		//gotoExplorerOption(option_podCasts,"Popular");
		comeToThisPage_direct();
		
		secondPod.click();
		
		//Verify sign-up gate shows up
		if(!signupHeader.getText().equalsIgnoreCase("Sign Up"))
			handleError("Sign up gate is not displayed." , "WEB_11772_browsePodcasts");
		
		driver.navigate().back();
		new Select(topics).selectByIndex(3);
		WaitUtility.waitForAjax(driver);
	   // firstPod.click();
		driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
	    makeSureItIsPlaying();
		
	  //Verify sign-up gate shows up
  		if(!signupHeader.getText().equalsIgnoreCase("Sign Up"))
  			handleError("Sign up gate is not displayed for filtered topic.", "WEB_11772_browsePodcasts");
	}
	
	
	public void WEB_11773_playPodAfterLogin()
	{
		login();
		
		
		//  gotoExplorerOption(option_podCasts,"Popular");
		comeToThisPage_direct();
		
		/*
	  
		Actions action = new Actions(driver);
		
		 action.moveToElement(explorer).click().perform();
		 driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div[1]/div/div/nav/ul/li[5]/a")).click();
		WaitUtility.sleep(500);
		*/
		
				
		secondPod.click();
		makeSureItIsPlaying();
		//verify the player is playing
		try
		{
			if (icon_play.isDisplayed())
				handleError("Player is not playing.", "WEB_11773_playPodAfterLogin");
		}catch(Exception e)
		{
			
		}
		
	}
	
	public void WEB_11774_filterPodAfterLogin()
	{
		login();
		
		comeToThisPage_direct();
		
		
		new Select(topics).selectByIndex(2);
		WaitUtility.sleep(1500);
		//Need to remember this station name
		String chosenStation = firstPodNameAfterFilter.getText();
		
		System.out.println("See chosenStation:" + chosenStation);
		
		playAstation();
	   
	    makeSureItIsPlayingWithNoWait();
		
	    gotoSingedAccountOption(option_myStations, "User Profile");
	    myStations.click();
		
		if (!driver.getPageSource().contains(chosenStation))
			handleError("The chosen podCast is not added to my stations.", "WEB_11774_filterPodAfterLogin");
		gotoSingedAccountOption(option_listenHistory, "User Profile");
		listenHistory.click();
		
		if (!driver.getPageSource().contains(chosenStation))
			handleError("The chosen podCast is not added to my listen history.", "WEB_11774_filterPodAfterLogin");
		
	}
	
	
	public void WEB_11775_addShowToFavorite()
	{
		login();
		comeToThisPage_direct();
		
		
		
		//Need to remember this station name
		String chosenStation = firstPodName.getText();
		System.out.println("See chosenStation:" + chosenStation);
	    firstPod.click();
	    
	    doFavorite("WEB_11775_addShowToFavorite");
	    /*
	    favorite.click();
		
		if (!favorite.getAttribute("class").equals("icon-favorite-filled"))
			handleError("Favorite icon is not highlighted.", "WEB_11775_addShowToFavorite");
	    
	    */
		checkFavInProfile(chosenStation);
		
	}
	
	public void WEB_11776_thumbUp()
	{
		login();
		WaitUtility.waitForAjax(driver);
		//gotoExplorerOption(option_podCasts,"Popular");
		comeToThisPage_direct();
		
		//Need to remember this station name
	   // WaitUtility.sleep(1000);
		
	
		String chosenStation = firstPodName.getText();
		System.out.println("See chosenStation:" + chosenStation);
		  
	    firstPod.click();
	    WaitUtility.waitForAjax(driver);
	     makeSureItIsPlaying();
	    
	    doThumbUp("WEB_11776_thumbUp");
		doSkip();
			
	}
	
	
	public void WEB_11777_skipLimitless()
	{
		login();
		comeToThisPage_direct();
		
		
	    firstPod.click();
	    
	    makeSureItIsPlaying();
		
		checkSkipLimitless();
	}
	
	//Seems that this is 
	public void WEB_11778_pauseResume()
	{
		login();
		
		comeToThisPage_direct();
		
	    firstPod.click();
		//driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
		makeSureItIsPlaying();
	   
		//Verify it is playing
	   try{
		   if (icon_play.isDisplayed())
			   handleError("Player is not playing.", "WEB_11778_pauseResume");;
	   }catch(Exception e)
	   {
           
	   }  
	   
	   WaitUtility.sleep(500);
	    icon_pause.click();
	    WaitUtility.sleep(1000);
	    
	    try{
		   if (icon_stop.isDisplayed())
			   handleError("Player is not paused.", "WEB_11778_pauseResume");;
	   }catch(Exception e)
	   {
           
	   } 
	    
	   
	    icon_play.click();
	    try{
		   if (icon_play.isDisplayed())
			   handleError("Player is not playing.", "WEB_11778_pauseResume");;
	   }catch(Exception e)
	   {
           
	   }  
		
	    /*
		if (!button_pause.getAttribute("class").contains("playing"))
           handleError("Player is not playing.", "WEB_11778_pauseResume");
	    button_pause.click();
	    if (!button_playing.getAttribute("class").contains("idle"))
	           handleError("Player is not paused.", "WEB_11778_pauseResume");
	    button_playing.click();
	    if (!button_pause.getAttribute("class").contains("playing"))
	           handleError("Player is not playing.","WEB_11778_pauseResume");
		*/
		
	}
	
	private void checkSkipLimitless()
	{
		for (int i = 0; i < 6; i++)
		{
			icon_skip.click();
			//thumpDown.click();
			WaitUtility.sleep(1000);
		}
		
		icon_skip.click();
		WaitUtility.sleep(1000);
		try{
		   System.out.println("growl:"+ growls.getText());
		   if ( growls.getText().length()> 1) handleError("There shall be no limit for episode skipping.", "checkSkipLimitless");
		}catch(Exception e)
		{
			
		}
		
		
	}
	

	public void comeToThisPage()
	{   /*
		if (isChrome)
			gotoExplorerOption(option_podCasts_xpath,"Popular");
		else	
		    gotoExplorerOption(option_podCasts,"Popular");
		*/
	 //   if (!driver.getTitle().contains("Popular"))
	    	comeToThisPage_direct();
	}
	
	private void comeToThisPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/show/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		WaitUtility.waitForAjax(driver);
	}


}
