package com.iheart.selenium.web;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.*; 


public class LiveRadioPage extends Page {
		@FindBy(css="#main > div.directory-live > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
			private WebElement firstLive;
		@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")

			private WebElement firstLiveTextLink;
		
		//Volume
		@FindBy(css=".volume > button:nth-child(1)") public WebElement volumeButton;
		@FindBy(css="#player > div.player-center > div > div > button > i") public WebElement iconMute;
		
		//Filter station
		@FindBy(css=".country-filter > div:nth-child(1) > select:nth-child(2)") public WebElement country;
		@FindBy(css=".market-filter > div:nth-child(1) > select:nth-child(2)") public WebElement city;
		@FindBy(css="div.form-group:nth-child(3) > div:nth-child(1) > select:nth-child(2)") public WebElement genres_dropDown;
		@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)")  public WebElement genres;
		
		//@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)") 
		@FindBy(css="#main > div.directory-live > section > ul > li > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
		    public WebElement firstStation;
		@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)") public WebElement firstStationLabel;
		@FindBy(css=".player-station") public WebElement stationPlaying;
	
		//AFTER SEARCH
		@FindBy(css=".selected > div:nth-child(2) > p:nth-child(1) > a:nth-child(1)") private WebElement firstSearchResult;
		
		//h3 title
		@FindBy(css=".section-header") public WebElement h3_header;
		
		//International
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i") public WebElement firstINT;
		//@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > button > i") public WebElement firstINT_playButton;
		@FindBy(css="#player > div.player-center > div > button.playing.btn-circle.medium.play > i") public WebElement firstINT_stopPlayButton;
		
		//autoPlay
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-text > a") public WebElement liveRadio_firstLink;
		@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > button > i") public WebElement player_stopButton;
		
		@FindBy(css=".player-artist") public WebElement songAfterSkip;
		
		//Volume Bar
	    @FindBy(css=".slider-range-appearance") private WebElement volumeBar;
	    
		
    public void WEB_11743_playLiveWithoutLogin()
    {
    	comeToThisPage_direct();
		makeSureItIsPlaying();
		
		verifyPlayer("Live Radio");
    }
    
    public void WEB_11749_playStationAfterLogin()
    {   login();
    	comeToThisPage_direct();
		makeSureItIsPlaying();
		
		verifyPlayer("Live Radio");
    }
	    
    public void WEB_11756_LivePlayStopScanAfterLogin()
    {
    	login();
    	WEB_11746_PlayStopScan();
    }
    
    public void WEB_11753_favStationAndListenHistoryOnPlayer()
    {
    	
    	comeToThisPage_direct();
		firstLive.click();
		
		
		makeSureItIsPlaying();
		
		addToFavoriteFromPlayer();
		
		//handlePreRoll();
		//is softgate show up?
		if (!isSoftGateShow())
			handleError("Sing up page is not displayed after unauthorized user clicks on Add To Favorite button.", "WEB_11753_favStationAndListenHistoryOnPlayer");
		
		// For logged in user
		faceBookSignUp();
		handlePreRoll();
		
		String player_station = playerStation.getText();
		
		//Add station to favorite
		addToFavoriteFromPlayer();
	    
		//verify that station is saved under Listen Hisotry and My Station page
	    listenHistory.click();
	    if (!driver.getPageSource().contains(player_station))
	    	handleError("Your favorite station is not added to listen history.", "WEB_11753_favStationAndListenHistoryOnPlayer");
    	
	    myStations.click();
	    if (!driver.getPageSource().contains(player_station))
	    	handleError("Your favorite station is not added to My Stations page.", "WEB_11753_favStationAndListenHistoryOnPlayer");
    	
    }
    
    
    public void WEB_11748_ThumbUpAndDownLiveWithoutLogin()
    {
    	comeToThisPage_direct();
		firstLive.click();
		makeSureItIsPlaying();
		
		 if(!isSoftGateShow())
		    handleError("Sign up page is not displayed.", "WEB_11748_ThumbUpAndDownLiveWithoutLogin");
		
		//Verify that thumbUP/Down button is present
		 if (!thumbUp.isDisplayed())
			 errors.append("ThumbUp button is not displayed.");
		 
		 if (!thumbDown.isDisplayed())
			 errors.append("ThumbDown button is not displayed.");
    }
    
    public void WEB_11750_filterLiveStationAfterLogin()
    {
    	login();
    	comeToThisPage_direct();
    	filterStation();
    	String chosenStation = firstStation.getText();
    	firstStation.click();
		
		makeSureItIsPlaying();
		//Verify it is playing
	    verifyPlayer("filtered station");
	   
	    try{	
	       listenHistory.click();
	    }catch(Exception e)
	    {
	       //Click on More ...., then ListenHistory
	    	Actions action = new Actions(driver);
	    													
	    	WebElement we = driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hide > div:nth-child(1) > button > i"));
	    													    
	    		try{
	    	  action.moveToElement(we).moveToElement(driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options > div > nav > ul > li:nth-child(4) > a"))).click().build().perform();
	    	}catch(Exception eX)
	    	{
	    		
	    	}
	    }
	    if (!driver.getPageSource().contains(chosenStation))
	    	handleError("Filtered station is not added to listen history.", "WEB_11750_filterLiveStationAfterLogin");

    	/*
	    try{
	        myStations.click();
	    }catch(Exception e)
	    {
	    	 //Click on More ...., then ListenHistory
	    	Actions action = new Actions(driver);
	    	WebElement we = driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options > button > i"));
	    	try{
	    		action.moveToElement(we).moveToElement(driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options > div > nav > ul > li:nth-child(3) > a"))).click().build().perform();
		    }catch(Exception eX)
	    	{
	    		
	    	}
	    }
	    if (!driver.getPageSource().contains(chosenStation))
	    	handleError("Filtered station is not added to My Stations page.", "WEB_11750_filterLiveStationAfterLogin");
    	*/
    }
	    
    public void WEB_11757_liveShare()
	{
		login();
		search("Elvis Duran");
		firstSearchResult.click();
		
	    makeSureItIsPlaying();
		
		shareFromPlayer();
		//Verify that we are on share page now
		System.out.println("Share page title: " + sharePageTitle.getText());
	
		
		if (!sharePageTitle.getText().trim().equals("Share"))
			handleError("Share button is not working.", "WEB_11757_liveShare");
		
		shareOnfaceBook();
	}    
    
    private void addToFavoriteFromPlayer_obsolete()
    {
    	Actions builder = new Actions(driver);
		builder = builder.moveToElement(icon_more_horizontal);
			
		try{
			builder.moveToElement(shareButton).click().build().perform();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    private void addToFavoriteFromPlayer()
    {
    	if (Page.getBrowser().equals("chrome"))
    	{
    		try{
    		   driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hidden-sm > button")).click();
    		}catch(Exception e)
    		{   
    			driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hidden-sm > button")).click();
    		}
    		
    	    driver.findElement(By.cssSelector("#player > div.player-left > div.dropdown-trigger.align-right.align-bottom.now-playing-options.hidden-sm > div > nav > ul > li:nth-child(1) > a")).click();
    	}else
    	{
    		driver.findElement(By.cssSelector(".align-bottom > button:nth-child(1)")).click();
    		//driver.findElement(By.cssSelector(".now-playing-options > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
    		driver.findElement(By.cssSelector(".align-bottom > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
    
    	}
    }	
    
    private void shareFromPlayer()
    {
    	int count = 0;
		boolean shared = false;
		WebElement we;
		Actions action = new Actions(driver);
		do {
			we = driver.findElement(By.cssSelector(".align-bottom > button:nth-child(1)"));
		    action = action.moveToElement(we);
			try{
		    	//action.moveToElement(option_logout).click().build().perform();
				 action.moveToElement(driver.findElement(By.cssSelector(".align-bottom > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)"))).click().build().perform();
				 
			}catch(Exception e)
			{
			   e.printStackTrace();
			}
			
			count++;
		}while(count <5 && !isShared());	
    	
    	
    }
       
    private boolean isShared()
    {
    	try{
    		return (driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2")).getText()).equals("Share");
    	}catch(Exception e)
    	{
    		return false;
    	}
    }
    
    
	public void WEB_14441_mutePlayer()
	{   
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		volumeButton.click();
		if (!iconMute.getAttribute("class").equalsIgnoreCase("icon-mute"))
			handleError("Player is not muted.", "WEB_14441_mutePlayer");
	    
	}
	
	public void WEB_11744_filterStation()
	{   
		
	    comeToThisPage_direct();
		
		filterStation();
		String chosenStation = firstStationLabel.getText();
		System.out.println("chosen station:" + chosenStation);
		firstStation.click();
		//give it one second in case the machine is slow
		//No way to remove this sleep cos we have to wait for the new statin info is loaded! Otherwise it will grab the old station name!
		WaitUtility.sleep(1000); //DO NOT REMOEVE this line!
		String playingStation = waitForElement(stationPlaying, 8000).getText();
		System.out.println("station PLAYING:" + playingStation);
		
		makeSureItIsPlaying();
		
		if (!chosenStation.equalsIgnoreCase(playingStation))
			handleError("Filter is not working.", "WEB_11744_filterStation");
	}
	
	private void filterStation()
	{   
		new Select(country).selectByVisibleText("Mexico");
		WaitUtility.sleep(1000);
		new Select(driver.findElement(By.name("city"))).selectByIndex(1);
		WaitUtility.sleep(1000);
	    new Select(genres_dropDown).selectByIndex(2);  
	}		  

	public void WEB_11745_International()
	{  
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		
		int count = 0;
		
		do{
		  new Select(city).selectByVisibleText("All Cities");
		  count++;
		}while(!h3_header.getText().contains("All Stations, INT") && count < 5);
		
	
		
		//firstINT.click();
	//	firstINT_playButton.click();
		driver.findElement(By.cssSelector("#main > div.directory-live > section > ul > li:nth-child(2) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")).click();
		makeSureItIsPlaying();
		
		//Verify that it is indeed playing
		if (!firstINT_stopPlayButton.getAttribute("class").equalsIgnoreCase("icon-stop"))
			handleError("The chosen station is not playing.", "WEB_11745_International");
	}
	
	public void WEB_11796_LiveStationAutoPlay(){
		//gotoExplorerOption(option_liveRadio, "Live");
		
		comeToThisPage_direct();
		
		firstLiveTextLink.click();
		System.out.println("SEE new url:"  + driver.getCurrentUrl()+ "?autoplay=true" );
		driver.get(driver.getCurrentUrl()+ "?autoplay=true");
		//verify that the player is indeed playing
		verifyPlayer("Auto Play", "WEB_11796_LiveStationAutoPlay");
		
		/*
		if (!player_stopButton.getAttribute("class").equalsIgnoreCase("icon-stop"))
			handleError("Auto play is not working.", "WEB_11796_LiveStationAutoPlay");
		*/	
	}
	
	public void WEB_11746_PlayStopScan(){
		//gotoExplorerOption(option_liveRadio, "Live");
		
		comeToThisPage_direct();
		
		icon_play.click();
		
		makeSureItIsPlaying();
		
		try {
		 icon_stop.click();
		}catch(Exception e)
		{
			WaitUtility.sleep (20*1000);
			icon_stop.click();
		}
		
		icon_play.click();
		//here, get the song that is playing
		String songTitle = player_song.getText();
		System.out.println("song playing:" + songTitle);
		icon_scan.click();
		String songAfter = "";
		try{
		  songAfter =player_song.getText();
		}catch(Exception e)
		{
			System.out.println("Hit the commercial.. ");
			return;
		}
		System.out.println("after skip:"  + songAfter);
		if (songTitle.equalsIgnoreCase(songAfter))
			handleError("Scan button is not working.", "WEB_11746_PlayStopScan" );
	}
	
	
	//For logged in cases
	public void WEB_11752_thumpUp()
	{   login();
		
		//gotoExplorerOption(option_liveRadio, "Live");
	    comeToThisPage_direct();
	
	    int count = 0; 
		do {
			firstLive.click();
			WaitUtility.sleep(300);
			count++;
		}while (driver.getTitle().contains("Top Radio") && count < 5);	
		makeSureItIsPlaying();
		
		doThumbUp("WEB_11752_thumpUp");
	}
	
	public void WEB_11751_INT()
	{   
		login();
		
		WEB_11745_International();
	}
	
	public void WEB_11755_favorite()
	{  //fOR FAVORITE, make sure that the player is not playing to circuit pre-roll
		login();
		
		comeToThisPage_direct();
		
		driver.findElement(By.cssSelector("#main > div.directory-live > section > ul > li:nth-child(1) > div > div.station-text > a")).click();
		
		doFavorite("WEB_11755_favorite");
		
	}
	
	
	public void WEB_11754_thumpDown()
	{   login();
		
		comeToThisPage_direct();
		
		waitForElement(firstLive, 2000).click();
		
		
		makeSureItIsPlaying();
		
		doThumbDown("WEB_11754_thumpDown");
		
	}
	
	public void WEB_11747_adjustVolume()
	{   login();
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		firstLive.click();
		makeSureItIsPlaying();
		int widthOfVolumeBar = volumeBar.getSize().getWidth();
		System.out.println("See bar width:" + widthOfVolumeBar) ;
		Actions slideAction = new Actions(driver);
		slideAction.clickAndHold(volumeBar);
		slideAction.moveByOffset(widthOfVolumeBar, 0).build().perform();
		//Need to add acceptance criteria
		
	}
	
	
	public void WEB_11785_playStationFromGenreProfile(){
		
		//gotoExplorerOption(option_genres, "Genres");
		gotoGenrePage_direct();
		
		//click on country genre
		if (Page.getBrowser().equals("chrome"))
		{	scrollDown();
		    driver.findElement(By.cssSelector("#main > div.directory-genre > section:nth-child(7) > h3 > a > span:nth-child(1)")).click();
		}else								
		    driver.findElement(By.cssSelector("section.section-block:nth-child(7) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
		
		//play top country station
		driver.findElement(By.cssSelector("#main > div:nth-child(2) > div:nth-child(1) > section:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
		          		//Verify: 0. user is brought to the station page; and it is playing & sign up page shows up
		String subTitle = driver.findElement(By.cssSelector("section.section-block:nth-child(3) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).getText();
		if (!subTitle.contains("Similar"))
			errors.append("Clicking on top country station doesn't bring user to station page. ");
		verifyPlayer("Country station", "WEB_11785_playStationFromGenreProfile");
		if (!isSoftGateShow())
			handleError("Sign up page is not showing up. ", "WEB_11785_playStationFromGenreProfile");
		
		
		 
	}
	
	
	public void comeToThisPage()
	{  comeToThisPage_direct();
	}		
	
	public void comeToThisPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/live/country/US/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
	}
	
	public void gotoGenrePage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/genre/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
	}
	
	
	public void gotoGenrePage_direct_NEW()
	{    
		driver.findElement(By.cssSelector(".header-menu-main > li:nth-child(2) > a:nth-child(1)")).click();
		
	}
	
	public void gotoGenrePage()
	{  	
		genres.click();
	
		if (!driver.getTitle().contains("Genres"))
			gotoGenrePage_direct();
	}    



	

}
