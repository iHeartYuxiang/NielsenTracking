package com.iheart.selenium.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

import java.util.List;

//import org.apache.log4j.Logger;

public class ProfilePage extends Page {
    @FindBy(css="li.tabbar:nth-child(2) > a:nth-child(1)") private WebElement myStations;
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
      	private WebElement firstStation;
    @FindBy(css="li.tabbar:nth-child(3) > a:nth-child(1)") private WebElement history;
    @FindBy(css=".listen-history > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
        private WebElement firstSong;
    @FindBy(css="li.tabbar:nth-child(5) > a:nth-child(1)") private WebElement favoriteSongs;
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement firstFavSong;
    @FindBy(css="li.tabbar:nth-child(6) > a:nth-child(1)") private WebElement favoriteEpisodes;
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement firstFavEpisode;
    
    @FindBy(css="section.section-block:nth-child(1) > h3:nth-child(1)") private WebElement pageHeader;
    
    
    //my stations page
    		
    @FindBy(css="li.tile:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement ms_secondStation;
    
    //Listen Hisotry
    @FindBy(css=".listen-history > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement lh_firstSong;
    
   

	public void WEB_11779_playStations()
	{
		login();
		//gotoSingedAccountOption(option_profile, "User Profile");
		comeToThisPage_direct();
		
		myStations.click();
		WaitUtility.waitForAjax(driver);
		firstStation.click();
		makeSureItIsPlaying();
		verifyPlayer("station");
		
		driver.navigate().refresh();
		//gotoSingedAccountOption(option_profile, "User Profile");
		comeToThisPage_direct();
		history.click();
		WaitUtility.waitForAjax(driver);
		firstSong.click();
		makeSureItIsPlaying();
		//makeSureItIsPlayingWithNoWait();
		verifyPlayer("chosen song from Listen Hisotry ");
		
		driver.navigate().refresh();
	//	gotoSingedAccountOption(option_profile, "User Profile");
		comeToThisPage_direct();
		favoriteSongs.click();
		WaitUtility.waitForAjax(driver);
	    try{
		   firstFavSong.click();
	    }catch(Exception e)
	    {
	    	driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)")).click();
	    }
		
		makeSureItIsPlaying();
		//makeSureItIsPlayingWithNoWait();
		verifyPlayer("chosen favorite song");
		
		driver.navigate().refresh();
		//gotoSingedAccountOption(option_profile, "User Profile");
		comeToThisPage_direct();
		
		favoriteEpisodes.click();
		
		WaitUtility.waitForAjax(driver);
		firstFavEpisode.click();
		makeSureItIsPlaying();
		//makeSureItIsPlayingWithNoWait();
		verifyPlayer("favorite episode");
		
	}
	
	public void WEB_11780_playStationsInMyStationsPage()
	{
		login();
		//gotoSingedAccountOption(option_myStations, "User Profile");
		comeToThisPage_direct();
		
		//Verify page header
		if (!pageHeader.getText().equals("My Stations"))
		{	handleError("Clicking on My Stations link didn't lead user to My Stations page." , "WEB_11780_playStationsInMyStationsPage");
            return;
		}
		ms_secondStation.click();
		makeSureItIsPlaying();
		
		//Verify it is playing:
		verifyPlayer("station");
		
	}	
	
	
	public void WEB_11781_playStationsInListenHistoryPage()
	{
		login();
		//gotoSingedAccountOption(option_listenHistory, "User Profile");
		gotoMyListenHistoryPage_direct();
		//Verify page header
		if (!pageHeader.getText().equals("Listen History"))
		{	handleError("Clicking on Listen History link didn't lead user to the right page.","WEB_11781_playStationsInListenHistoryPage" );
            return;
		}
		
		lh_firstSong.click();
		makeSureItIsPlaying();
		
		//Verify it is playing:
		verifyPlayer("song");
	}	
	
	public void WEB_11782_friendPage()
	{
		login();
		gotoSingedAccountOption(option_friends, "User Profile");
		//Verify page header
		String _pageHeader ="";
		boolean friendsExist = true;
		
		try{
			_pageHeader = pageHeader.getText();
		}catch(Exception e)
		{   friendsExist = false;
			System.out.println("You currently do not have any Facebook friends listening to iHeartRadio.");
		}
				
		
		if (friendsExist && !pageHeader.getText().equals("Friends"))
		{	handleError("Clicking on Friends link didn't lead user to the right page." , "WEB_11782_friendPage");
            return;
		}
		
		//Need to add some friends to the test account in FB to see the expected behavior.
	}	
	
	public void WEB_11783_logout()
	{
		login();
		
		gotoSingedAccountOption(option_logout, "Home");
		
		//verfiy that no station is playing
		try{
			if( icon_pause.isDisplayed() || icon_stop.isDisplayed()) 
			    handleError("Station is still playing after user logs out.", "WEB_11783_logout");
		}catch(Exception e)
		{
			System.out.println("No station is playing now. Good.");
		}
		
		//verify that login link is present
		try{
			header_login.getText();
			System.out.println("Log In button is displayed. Good.");
		}catch(Exception e)
		{
			handleError("Log out failed.", "WEB_11783_logout");
		}
		
	}	
	
	private void verifyPlayer_OBSOLETE(String category)
	{   boolean isPlaying = false;
	    try{
	    	if (icon_pause.isDisplayed())
	    	{	isPlaying = true;
	    		System.out.println("It is  playing. Good" );
	    	}
	    }catch(Exception e)
	    {   
	    }
	    
	    if (!isPlaying)
	    {	
		    try{
		    	if (icon_stop.isDisplayed())
		    	{	isPlaying = true;
		    		System.out.println("It is  playing. Good" );
		    	}
		    }catch(Exception e)
		    {   
		    }
	    }
	    
		if (!isPlaying)
		    handleError("The " + category + " is not playing.", "verifyPlayer");
		
	}
	
	
	public void comeToThisPage()
	{ /*
		try {
			if (isChrome)
				gotoSingedAccountOption(option_profile_xpath,"User Profile");
			else	
				gotoSingedAccountOption(option_profile,"User Profile");
		}catch(Exception e)
		{
		
		}
		
		*/
		//if (!driver.getTitle().contains("User"))
	    	comeToThisPage_direct();
	}
	
	
	public void gotoMyStationPage()
	{  try{
				gotoSingedAccountOption(option_myStations,"User Profile");
		}catch(Exception e)
		{
			
		}
			
		if(!driver.getTitle().contains("User"))
			gotoMyStationPage_direct();
	}
	
	public void gotoMyListenHistoryPage()
	{
		try{
				
				gotoSingedAccountOption(option_listenHistory,"User Profile");
		}catch(Exception e)
		{
			
		}
		
		if (!driver.getTitle().contains("User"))
			gotoMyListenHistoryPage_direct();
	}
	
	private void comeToThisPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/my/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		//WaitUtility.sleep(1000);
		WaitUtility.waitForAjax(driver);
	}
	
	
	public void gotoMyStationPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/my/stations/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		//WaitUtility.sleep(1000);
		WaitUtility.waitForAjax(driver);
	}
	
	private void gotoMyListenHistoryPage_direct(){
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/my/history/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		//WaitUtility.sleep(1000);
		WaitUtility.waitForAjax(driver);
	}
	

	
}
