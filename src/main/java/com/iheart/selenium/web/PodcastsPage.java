package com.iheart.selenium.web;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

//import org.apache.log4j.Logger;

public class PodcastsPage  extends Page{
	
	@FindBy(css="#main > div > div:nth-child(2) > section:nth-child(1) > ul > li:nth-child(2) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
	    private WebElement secondPod;
	
	
	
	@FindBy(name="category") private WebElement topics; //topics for podcasts
	@FindBy(css="#main > div.directory-shows > div:nth-child(2) > section:nth-child(1) > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
           private WebElement firstPod;
	@FindBy(css="ul.station-tiles:nth-child(3) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
	 private WebElement firstPod_alternate;
	
	
	@FindBy(css="ul.station-tiles:nth-child(3) > li:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
		private WebElement  firstPodName;
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
		private WebElement  firstPodNameAfterFilter;
	
	//favorite
	@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > ul > li.station-name > button > i")
		private WebElement favorite;
	
	//15/30 second rewind
	@FindBy(css="#player > div.player-center > div.player-controls > button.btn.medium.btn-seek.btn-rewind > i.icon-rewind-border") 
		private WebElement icon15;
	//@FindBy(css=".icon-15")  private WebElement icon15;
	@FindBy(css="#player > div.player-center > div.player-controls > button.btn.medium.btn-seek.btn-forward > i.icon-fastforward-border")
	 private WebElement icon30;
	
	
	//@FindBy(css=".player-duration-position")  private WebElement playerDurationPosition;
	@FindBy(css="#player > div.player-center > div.player-duration > span.type-xsmall.type-secondary.player-duration-position")
		private WebElement playerDurationPosition;
	@FindBy(css=".player-duration-duration")  private WebElement playerDuration;
	
	public void WEB_11772_browsePodcasts()
	{
		comeToThisPage_direct();
		
		secondPod.click();
		
		//Verify sign-up gate shows up
		if(!signupHeader.getText().equalsIgnoreCase("Sign Up"))
			handleError("Sign up gate is not displayed." , "WEB_11772_browsePodcasts");
		
		driver.navigate().back();
		new Select(topics).selectByIndex(3);
	   // firstPod.click();
		driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
		 try{
		       driver.findElement(By.cssSelector("button.idle:nth-child(1)")).click();
	    }catch(Exception e)
	    {
	    	
	    }
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
			
		secondPod.click();
		clickOnTopPlayButton();
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
		//Need to remember this station name
		String chosenStation = waitForElement(firstPodNameAfterFilter, 5000).getText();
		//String chosenStation = firstPodNameAfterFilter.getText();
		
		System.out.println("See chosenStation:" + chosenStation);
		
		playAstation();
	   
	    makeSureItIsPlayingWithNoWait();
		
	   // gotoSingedAccountOption(option_myStations, "User Profile");
	    gotoSingedAccountOption_direct("Profile");
	   
	   try{
	    myStations.click(); 
	   }catch(Exception e)
	   {
	   	
	   }

	   gotoMyListenHistoryPage_direct();
	}
	
	
	public void WEB_11775_addShowToFavorite()
	{
		login();
		comeToThisPage_direct();
		
		
		
		//Need to remember this station name
		String chosenStation = firstPodName.getText();
		System.out.println("See chosenStation:" + chosenStation);
	    firstPod.click();
	    clickOnTopPlayButton();
	    handlePreRoll();
	    
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
		comeToThisPage_direct();
		
	
		String chosenStation = firstPodName.getText();
		System.out.println("See chosenStation:" + chosenStation);
		
		try{
	       firstPod.click();
		}catch(Exception e)
		{
			firstPod_alternate.click();
		}
	    try{
	       driver.findElement(By.cssSelector("button.idle:nth-child(1)")).click();
	    }catch(Exception e)
	    {
	    	
	    }
	    //WaitUtility.waitForAjax(driver);
	     makeSureItIsPlaying();
	    
	    doThumbUp("WEB_11776_thumbUp");
		//doSkip();
			
	}
	
	
	public void WEB_11777_skipLimitless()
	{
		login();
		comeToThisPage_direct();
		
		
	    firstPod.click();
	    try{
		       driver.findElement(By.cssSelector("button.idle:nth-child(1)")).click();
	    }catch(Exception e)
	    {
	    	
	    }
	    makeSureItIsPlaying();
		
		checkSkipLimitless();
	}
	
	//Seems that this is 
	public void WEB_11778_pauseResume()
	{
		login();
		
		comeToThisPage_direct();
		
	    firstPod.click();
	    try{
	       driver.findElement(By.cssSelector("button.idle:nth-child(1)")).click();
	    }catch(Exception e)
	    {
	    	
	    }
		//driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
		makeSureItIsPlaying();
	   
		//Verify it is playing
	   try{
		   if (icon_play.isDisplayed())
			   handleError("Player is not playing.", "WEB_11778_pauseResume");;
	   }catch(Exception e)
	   {
           
	   }  
	   
	    icon_pause.click();
	    
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
		}
		
		icon_skip.click();
		try{
		   System.out.println("growl:"+ growls.getText());
		   if ( growls.getText().length()> 1) handleError("There shall be no limit for episode skipping.", "checkSkipLimitless");
		}catch(Exception e)
		{
			
		}
		
		
	}
	
	
	public void WEB_42777_Rewind_15_seconds()
	{
		//login();
		
		comeToThisPage_direct();
		login();
			
		WaitUtility.sleep(5000);
		
		waitForElement(secondPod, 8000).click();
		
		handlePreRoll();
	
		//Get duration and the current positio
		int episodeDuration = convertToSecond( playerDuration.getText());
		int episodePlayed = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("song played/ Duration:" + episodePlayed + "/" + episodeDuration );
		
		if (episodePlayed < 15 )
			WaitUtility.sleep(17 * 1000 );
		
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("song played after waiting:"+ episodePlayed);
		
		try{
		   icon15.click();
		   WaitUtility.sleep(1000);
		}catch(Exception e)
		{
			WaitUtility.sleep(1000);
			icon15.click();
			WaitUtility.sleep(1000);
		}
		
		int episodePlayed_new = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("NOW WHAT:song played:" + episodePlayed_new );
		
		int difference = episodePlayed - episodePlayed_new ;
		System.out.println("difference:" + difference );
		
		//allow one second difference
		if ( difference >= 16 )
				handleError("Rewind too much.", "WEB_42777_Rewind_15_seconds");
		else if ( difference < 14 )  //Tolearte one second difference
			handleError("Rewind too little.", "WEB_42777_Rewind_15_seconds");
		
		//Now for the case where episode only played less than 15 seconds
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		while(episodePlayed > 15)
		{
			icon15.click();
			WaitUtility.sleep(500);
		}
	
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		System.out.println("After rewinding: " + episodePlayed);
		
		icon15.click();
		WaitUtility.sleep(500);
		
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		if ( episodePlayed > 1 ) //allow 1 second difference
			handleError("Podcast is not rewound to the beginning.", "WEB_42777_Rewind_15_seconds");
		
		
	}
	
	public void WEB_42777_fastForward_30_seconds()
	{
		//login();
		
		comeToThisPage_direct();
		login();
			
		WaitUtility.sleep(5000);
		
		waitForElement(secondPod, 8000).click();
		
		handlePreRoll();
		
	
		//Get duration and the current position
		int episodeDuration = convertToSecond( playerDuration.getText());
		int episodePlayed = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("song played/ Duration:" + episodePlayed + "/" + episodeDuration );
		
	
	    icon30.click();
	    WaitUtility.sleep(500);
	
		
		int episodePlayed_new = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("NOW WHAT:song played:" + episodePlayed_new );
		
		int difference = episodePlayed_new - episodePlayed;
		System.out.println("difference:" + difference );
		
		//allow one second difference
		if ( difference > 31 )
				handleError("Fast forward too much.", "WEB_42777_fastForward_30_seconds");
		else if ( difference < 29 )  //Tolearte one second difference
			handleError("Fast forward too little.", "WEB_42777_fastForward_30_seconds");
		
		
		//Now to see whether or not it will go to the very end
		episodeDuration = convertToSecond( playerDuration.getText());
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		while ((episodeDuration - episodePlayed) > 30)
		{
			icon30.click();
			WaitUtility.sleep(500);
			episodeDuration = convertToSecond( playerDuration.getText());
			episodePlayed = convertToSecond( playerDurationPosition.getText());
			System.out.println("WHILE LOOP:" + episodePlayed + "/" + episodeDuration);
		}
		
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		System.out.println("after fast forward: " + episodePlayed + "/" + episodeDuration);
		
		icon30.click();
		WaitUtility.sleep(200);
		
		episodePlayed = convertToSecond( playerDurationPosition.getText());
		System.out.println("after FINAL fast forward: " + episodePlayed + "/" + episodeDuration);
		
		difference = episodeDuration - episodePlayed;
		
		if ( difference > 1 )
			handleError("Fast forward didn't go to the end.", "WEB_42777_fastForward_30_seconds");
	
	}
	
	
	
	public void WEB_42777_15_30_refresh()
	{
		//login();
		
		comeToThisPage_direct();
		login();
			
		WaitUtility.sleep(5000);
		
		waitForElement(secondPod, 8000).click();
		
		handlePreRoll();
	
		//Get duration and the current position
		int episodeDuration = convertToSecond( playerDuration.getText());
		int episodePlayed = convertToSecond( playerDurationPosition.getText());
		
		System.out.println("song played/ Duration:" + episodePlayed + "/" + episodeDuration );
		
		driver.navigate().refresh();
		
		
		//verify that icon15 and icon30 are disabled
		if (!icon15.isEnabled() ||!icon30.isEnabled())
				handleError("icon 15 and icon 30 are not disabled after refresh.", "WEB_42777_15_30_refresh");
		
	}
	
	
	
	
	 //duration format: 4:46
    private int convertToSecond(String duration)
    {   System.out.println("see duration text:" + duration);
    	int minutes = Integer.parseInt(duration.split(":")[0]);
    	int seconds = Integer.parseInt(duration.split(":")[1]);
    	
    	int total = (minutes * 60  ) +  seconds ;
    	//System.out.println("See songduration in seconds:" + total );
    	return total;
    }

	public void comeToThisPage()
	{   
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
	}


}
