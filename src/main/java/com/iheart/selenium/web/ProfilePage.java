package com.iheart.selenium.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;

//import org.apache.log4j.Logger;

public class ProfilePage extends Page {
    @FindBy(css="li.tabbar:nth-child(2) > a:nth-child(1)") private WebElement myStations;
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
      	private WebElement firstStation;
    
   
    @FindBy(css="#main > div:nth-child(2) > div:nth-child(1) > section:nth-child(2) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)")  private WebElement history;
  //  @FindBy(css="li.tabbar:nth-child(3) > a:nth-child(1)") private WebElement history;
    @FindBy(css=".listen-history > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
        private WebElement firstSong;
   // @FindBy(css="li.tabbar:nth-child(5) > a:nth-child(1)") private WebElement favoriteSongs;
    @FindBy(css="section.section-block:nth-child(3) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)") private WebElement favoriteSongs;
    
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement firstFavSong;
   // @FindBy(css="li.tabbar:nth-child(6) > a:nth-child(1)") private WebElement favoriteEpisodes;
    @FindBy(css="section.section-block:nth-child(4) > h3:nth-child(1) > a:nth-child(1) > span:nth-child(1)") private WebElement favoriteEpisodes;
    
    @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement firstFavEpisode;
    
    @FindBy(css="section.section-block:nth-child(1) > h3:nth-child(1)") private WebElement pageHeader;
    
    
    //my stations page
    		
  //  @FindBy(css="li.tile:nth-child(2) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    @FindBy(css="#main > div:nth-child(2) > section > ul > li:nth-child(2) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")
       private WebElement ms_secondStation;
    
    //Listen Hisotry
    @FindBy(css=".listen-history > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
    	private WebElement lh_firstSong;
    
   

	public void WEB_11779_playStations()
	{
		login();
		//gotoSingedAccountOption(option_profile, "User Profile");
		comeToThisPage_direct();
		
	   try{
			firstStation.click();
			
			makeSureItIsPlaying();
			verifyPlayer("station", "WEB_11779_playStations");
	   }catch(Exception e)
	   {
		   
	   }
		System.out.println("Done with station.");
		driver.navigate().refresh();
		comeToThisPage_direct();
		
		try{
			history.click();
			firstSong.click();
			makeSureItIsPlaying();
			
			verifyPlayer("chosen song from Listen Hisotry ", "WEB_11779_playStations");
		 }catch(Exception e)
		   {
			   
		   }		
		System.out.println("Done with Listen Hisotry.");
		
		driver.navigate().refresh();
		comeToThisPage_direct();
		try{
			favoriteSongs.click();
			firstFavSong.click();
			makeSureItIsPlaying();
			verifyPlayer("chosen favorite song", "WEB_11779_playStations");
		 }catch(Exception e)
		   {
			   
		   }	
		System.out.println("Done with favorite song.");
		
		driver.navigate().refresh();
		comeToThisPage_direct();
		
		try{
		    favoriteEpisodes.click();
			firstFavEpisode.click();
			makeSureItIsPlaying();
			verifyPlayer("favorite episode", "WEB_11779_playStations");
		 }catch(Exception e)
		   {
			   
		   }	
		System.out.println("Done with favorite episode.");
		
	}
	
	public void WEB_11780_playStationsInMyStationsPage()
	{
		login();
		gotoMyStationPage_direct();
		//Verify page header
		try{
			if (!pageHeader.getText().equals("My Stations"))
			{	handleError("Clicking on My Stations link didn't lead user to My Stations page." , "WEB_11780_playStationsInMyStationsPage");
	        		 return;
			}
		}catch(Exception e)
		{
			
		}
		
		
		if (!driver.getPageSource().contains("You haven't listened to any stations yet"))
		   waitForElement(ms_secondStation, 5000).click();
		
		try
		{	//Do I need to switch window here?
			driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > form > div.btn-group.ui-divider.new-dialog > button")).click();
			//Happy listening! Got it!
			driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > button")).click();
		}catch(Exception e)
		{
			System.out.println("No fav-related popups");
		}
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
		// gotoSingedAccountOption(option_friends, "User Profile");
		gotoFriendPage_direct();
		
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
		
		
		// gotoSingedAccountOption(option_logout,EXPECTED_TITLE_FORYOU);
		logout();
		//verfiy that no station is playing
		try{
			if( icon_pause.isDisplayed() || icon_stop.isDisplayed()) 
			    handleError("Station is still playing after user logs out.", "WEB_11783_logout");
		}catch(Exception e)
		{
			System.out.println("No station is playing now. Good.");
		}
		
		//verify that login link is present
		/*
		if (!isLoggedOut())
			handleError("Log out failed.", "WEB_11783_logout");
		*/
	}	
	
	
	
	public void logout()
	{  // limit try to 5 times
		int count = 0;
		boolean isOut = false;
		
		Actions action = new Actions(driver);
		do {
			
		    action = action.moveToElement(signedAccount);
			
			try{
		    	action.moveToElement(option_logout).click().build().perform();
			}catch(Exception e)
			{
				
			}
			count++;
		
		}while(count <10 && !isLoggedOut());	
	}
	
	
	private boolean isLoggedOut()
	{   
		boolean isOut = false;
		try{
			loginButton.getTagName();
			System.out.println("User is logged out.");
			isOut = true;
			
		}catch(Exception e)
		{
			isOut = false;
		}
		return isOut;
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
	}
	
	
}
