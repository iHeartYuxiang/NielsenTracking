package com.iheart.selenium.web;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import com.iheart.proxyLight.*;

import static org.junit.Assert.*; 


public class LiveRadioPage extends Page {
	
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
		private WebElement firstLive;
	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")
        private WebElement firstLiveTextLink;
	
	//Volume
		@FindBy(css=".volume > button:nth-child(1)") public WebElement volumeButton;
		@FindBy(css="#player > div.player-center > div > div > button > i") public WebElement iconMute;
		
		//Filter station
		@FindBy(css="#main > div > div > div.form-group.ui-inline-block.country-filter > div > select") public WebElement country;
		@FindBy(css="#main > div > div > div.form-group.ui-inline-block.market-filter > div > select") public WebElement city;
		@FindBy(css="#main > div > div > div.form-group.ui-inline-block.genre-filter > div > select") public WebElement genres;
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i") public WebElement firstStation;
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-text > a") public WebElement firstStationLabel;
		@FindBy(css=".player-station") public WebElement stationPlaying;
		
		//AFTER SEARCH
		@FindBy(css=".selected > div:nth-child(2) > p:nth-child(1) > a:nth-child(1)") private WebElement firstSearchResult;
		
		//h3 title
		@FindBy(css=".section-header") public WebElement h3_header;
		
		//International
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i") public WebElement firstINT;
		@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > button > i") public WebElement firstINT_playButton;
		@FindBy(css="#player > div.player-center > div > button.playing.btn-circle.medium.play > i") public WebElement firstINT_stopPlayButton;
		
		//autoPlay
		@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-text > a") public WebElement liveRadio_firstLink;
		@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > button > i") public WebElement player_stopButton;
		
		//Play/stop/resume/scan
		//@FindBy(css="#player > div.player-center > div > button.idle.btn-circle.medium.play > i") public WebElement icon_play;
		//@FindBy(css="#player > div.player-center > div > button.playing.btn-circle.medium.play > i") public WebElement icon_stop;
		
	//	@FindBy(css="#player > div.player-center > div > button.btn.text.no-border.xsmall > span") public WebElement icon_scan;
		//@FindBy(css="#player > div.player-left > div.player-info > a.player-artist.type-secondary.type-xsmall") public WebElement songPlaying;
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
    	//for users not sign in
    	//gotoExplorerOption(option_liveRadio, "Live");
    	comeToThisPage_direct();
		firstLive.click();
		
		try{
		    icon_play.isDisplayed();
		    System.out.println("Music is not playing. About to click.");
			icon_play.click();

	    }catch(Exception e)

	    {   System.out.println("Music is playing. ");
	    	return;
	    }
		
		//Click on Add to Favorite link
		
		addToFavoriteFromPlayer();
		
		handlePreRoll();
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
    	
    	//gotoExplorerOption(option_liveRadio, "Live");
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
    	//gotoExplorerOption(option_liveRadio, "Live");
    	comeToThisPage_direct();
    	filterStation();
    	String chosenStation = firstStation.getText();
    	firstStation.click();
		
		makeSureItIsPlaying();
		//Verify it is playing
	    verifyPlayer("filtered station");
		
		//verify that station is saved under Listen Hisotry and My Station page
	    listenHistory.click();
	    if (!driver.getPageSource().contains(chosenStation))
	    	handleError("Filtered station is not added to listen history.", "WEB_11750_filterLiveStationAfterLogin");
    	
	    myStations.click();
	    if (!driver.getPageSource().contains(chosenStation))
	    	handleError("Filtered station is not added to My Stations page.", "WEB_11750_filterLiveStationAfterLogin");
    	
    }
	    
    public void WEB_11757_liveShare()
	{
		login();
		search("Elvis Duran");
		firstSearchResult.click();
	
		WaitUtility.waitForAjax(driver);
		addToFavoriteFromPlayer();
	
		//Verify that we are on share page now
		System.out.println("Share page title: " + sharePageTitle.getText());
	
		
		if (!sharePageTitle.getText().trim().equals("Share"))
			handleError("Share button is not working.", "WEB_11757_liveShare");
		
		shareOnfaceBook();
	}    
    
    private void addToFavoriteFromPlayer()
    {
    	Actions builder = new Actions(driver);
		builder = builder.moveToElement(icon_more_horizontal);
			
		try{
			builder.moveToElement(shareButton).click().build().perform();
		}catch(Exception e)
		{
			
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
		//gotoExplorerOption(option_liveRadio, "Live");
	    comeToThisPage_direct();
		
		filterStation();
		String chosenStation = firstStationLabel.getText();
		System.out.println("chosen station:" + chosenStation);
		firstStation.click();
		WaitUtility.sleep(1000);
		
		String playingStation = stationPlaying.getText();
		System.out.println("station PLAYING:" + playingStation);
		
		makeSureItIsPlaying();
		
		
		if (!chosenStation.equalsIgnoreCase(playingStation))
			handleError("Filter is not working.", "WEB_11744_filterStation");
	}
	
	private void filterStation()
	{
		new Select(country).selectByVisibleText("Mexico");
		WaitUtility.sleep(2000);
		//new Select(city).selectByIndex(3);
		new Select(driver.findElement(By.name("city"))).selectByIndex(1);
		WaitUtility.sleep(1000);
		new Select(genres).deselectByIndex(2);
	}

	public void WEB_11745_International()
	{  
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		
		int count = 0;
		
		do{
		  new Select(city).selectByVisibleText("All Stations, INT");
		  WaitUtility.sleep(500);
		  count++;
		}while(!h3_header.getText().contains("All Stations, INT") && count < 5);
		
		WaitUtility.waitForAjax(driver);
		
		firstINT.click();
		WaitUtility.waitForAjax(driver);
		firstINT_playButton.click();
		WaitUtility.waitForAjax(driver);
		
		//Verify that it is indeed playing
		if (!firstINT_stopPlayButton.getAttribute("class").equalsIgnoreCase("icon-stop"))
			handleError("The chosen station is not playing.", "WEB_11745_International");
	}
	
	public void WEB_11796_LiveStationAutoPlay(){
		//gotoExplorerOption(option_liveRadio, "Live");
		
		comeToThisPage_direct();
		
		firstLiveTextLink.click();
		WaitUtility.sleep(1000);
		System.out.println("SEE new url:"  + driver.getCurrentUrl()+ "?autoplay=true" );
		driver.get(driver.getCurrentUrl()+ "?autoplay=true");
		WaitUtility.sleep(1000);
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
		
		icon_stop.click();
		
		icon_play.click();
		//here, get the song that is playing
		String songTitle = player_song.getText();
		System.out.println("song playing:" + songTitle);
		icon_scan.click();
		WaitUtility.sleep(1000);
		String songAfter = player_song.getText();
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
		}while (driver.getTitle().contains("Popular") && count < 5);	
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
		
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		firstLive.click();
		makeSureItIsNotPlaying();
		
		doFavorite("WEB_11755_favorite");
		
	}
	
	
	public void WEB_11754_thumpDown()
	{   login();
		//gotoExplorerOption(option_liveRadio, "Live");
		comeToThisPage_direct();
		firstLive.click();
		WaitUtility.waitForAjax(driver);
		makeSureItIsPlaying();
		
		
		
		//Keep scan if the thumbDown icon is disabled
		
		int count = 0;
		while (!thumbDown.isEnabled() && count <3)
		{	
			icon_scan.click();
			count++;
			WaitUtility.sleep(2000);
		}
		
		thumbDown.click();
		WaitUtility.sleep(200);
		//String hint = growls.getText();
		String response = driver.findElement(By.className("growls")).getText();
		System.out.println("See growls:" + response);
		//if (!response.contains("Thanks for your feedback"))
		//	handleError("Thump Down is not working properly.", "WEB_11754_thumpDown");
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
		driver.findElement(By.cssSelector("section.section-block:nth-child(7) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
		
		//play top country station
		driver.findElement(By.cssSelector("#main > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
	    //Verify: 0. user is brought to the station page; and it is playing & sign up page shows up
		String subTitle = driver.findElement(By.cssSelector("section.section-block:nth-child(3) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).getText();
		if (!subTitle.contains("Similar"))
			errors.append("Clicking on top country station doesn't bring user to station page. ");
		verifyPlayer("Country station", "WEB_11785_playStationFromGenreProfile");
		WaitUtility.sleep(10000);//for some reason, it takes long time for the pre-roll to show
		if (!isSoftGateShow())
			handleError("Sign up page is not showing up. ", "WEB_11785_playStationFromGenreProfile");
		
		
		//top country artist	
		driver.navigate().back();
	    driver.findElement(By.cssSelector("#main > div:nth-child(1) > div:nth-child(1) > section:nth-child(2) > ul:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
		//verify: it is not playing & signup page shows up
	    
	    if (!icon_play.isDisplayed())  
	       errors.append("Custom station is playing for unauthorized user.");
	    
	    if (!isSoftGateShow())
			handleError("Sign up page is not showing up. ", "WEB_11785_playStationFromGenreProfile");
		 
	}
	
	
	public void comeToThisPage()
	{  comeToThisPage_direct();
	}		
	
	public void comeToThisPage_direct()
	{   String currentURL = driver.getCurrentUrl();
	//	System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/live/country/US/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		WaitUtility.sleep(2000);
	}
	
	public void gotoGenrePage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/genre/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		//WaitUtility.sleep(1000);
		WaitUtility.waitForAjax(driver);
	}
	
	public void gotoGenrePage()
	{  	
		gotoExplorerOption(option_genres,"Genres");
	
		if (!driver.getTitle().contains("Genres"))
			gotoGenrePage_direct();
	}

    
}
