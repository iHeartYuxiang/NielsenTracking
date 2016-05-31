package com.iheart.selenium.web;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*; 

public class CustomRadioPage  extends Page {
		
		//Play/stop/resume/scan
	/*
		@FindBy(css="#player > div.player-center > div > button.idle.btn-circle.medium.play > i") private WebElement icon_play;
		@FindBy(css="#player > div.player-center > div > button.playing.btn-circle.medium.play > i") private WebElement icon_stop;
		@FindBy(css="#player > div.player-center > div > button.btn.text.no-border.xsmall > span") private WebElement icon_scan;
		*/
		@FindBy(css="#player > div.player-left > div.player-info > a.player-artist.type-secondary.type-xsmall") private WebElement songPlaying;
		@FindBy(css=".player-artist") public WebElement songAfterSkip;
	    
	    //Skip Limit
	     @FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)") 
	         private WebElement firstCustomRadio;
	   // @FindBy(css="#player > div.player-center > div.player-controls > button.btn.text.no-border.xsmall > i") private WebElement icon_skip;
	    
	    //Custome Radio playing button
	    @FindBy(css="button.playing:nth-child(3)") private WebElement customePlayButton;
	    
	  //logged in: Custom ThumpUp
	  	@FindBy(css="#player > div.player-center > div.player-controls > button:nth-child(2) > i") private WebElement customThumpUp;
	  	
	  	@FindBy(css="#player > div.player-left > div.player-info > a.player-artist.type-secondary.type-xsmall") private WebElement currentSong;
	  	
	  	//@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")
	  	@FindBy(css="#main > div.directory-custom > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i")	
	  	   private WebElement customFirstLinkPlayButton;
	  	
	  	 	
	  	//@FindBy(css="#main > div > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i") private WebElement customFirstLinkPlayButton;
	    @FindBy(css="button.text:nth-child(4)") private WebElement customSkipButton;
	  	@FindBy(css=".favorite") private WebElement  customFavorite; 
	  	@FindBy(css="li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(2) > a:nth-child(1)") private WebElement firstArtist;
	    
	  	public void WEB_11758_browseCustomBeforeLogin()
		{   
			playCustomRadio();
		
			//Verify that the soft gate shows up
			String hint = driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > div.type-xsmall.type-secondary > span > span")).getText();
			if (!hint.contains("Have an account?"))
				handleError("Sign up gate is not displayed.", "WEB_11758_browseCustomBeforeLogin");
			
			//Close signup page
			icon_close.click();
			
			comeToThisPage_direct();
			
			
			
			//Select a genra and play
			new Select(driver.findElement(By.name("genre"))).selectByIndex(4);
		
			playCustomRadio();
			
			hint = driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > div.type-xsmall.type-secondary > span > span")).getText();
			if (!hint.contains("Have an account?"))
				handleError("Sign up gate is not displayed.", "WEB_11758_browseCustomBeforeLogin");
		}
	  	
	  	
	  	public void WEB_11761_filterAndPlayCustomAfterLogin()
		{   login();
		    //gotoExplorerOption(option_customRadio, "Popular");
		    comeToThisPage_direct();
		  
		
			new Select(driver.findElement(By.name("genre"))).selectByIndex(5); 
			WaitUtility.sleep(1000);
			customFirstLinkPlayButton.click();
			
		}
	  	
	  	
	  	public void WEB_11762_skipLimit()
		{   login();
			playCustomRadioAfterLogin();
			
			
			for (int i = 0; i < 6; i++)
			{
				icon_skip.click();
				WaitUtility.sleep(1000);
			}
			
			icon_skip.click();
			String _growls = growls.getText();
			System.out.println("See growls:" + _growls);
			if (!_growls.contains("reached your skip limit"))
				handleError("Skip limit message is not displayed.","WEB_11762_skipLimit" );
			
			
		}
		
		public void WEB_11760_playCustom()
		{   login();
			playCustomRadioAfterLogin();
		
			String classes = customePlayButton.getAttribute("class");
			System.out.println("See classes:" + classes);
			if (!classes.contains("playing"))
				handleError("custom radio is not playing.", "WEB_11760_playCustom");
		}
		
		public void WEB_11763_thumpUpCustom()
		{   login();
			playCustomRadioAfterLogin();
			
			//Get the current song playing
			
			String songPlaying = "";
			try{
		       songPlaying = currentSong.getText();
			}catch(Exception e)
			{
				System.out.println("Hit the commercial. no song to thump up.");
				return;
			}
			System.out.println("It is playing:" + songPlaying);
			
			doThumbUp("WEB_11763_thumpUpCustom");
			
			
		}
		
		public void WEB_11764_addCustomStationToFavorite()
		{   
			login();
			
			comeToThisPage_direct();
		
			firstArtist.click();
			//clickOnTopPlayButton();
			
			doFavorite("WEB_11764_addCustomStationToFavorite");
			
			
		}
		
		public void WEB_11765_CustomStationPlayStop()
		{   
			login();
			playCustomRadioAfterLogin();
						
			//icon_pause.click();
			driver.findElement(By.cssSelector("button.playing")).click();
			icon_play.click();
			
			
		}
		
		
		public void WEB_11772_browsePodcasts()
		{   
			
			//gotoExplorerOption(option_podCasts, "Popular");
			podcasts.click();
			
			driver.findElement(By.cssSelector("ul.station-tiles:nth-child(3) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
			clickOnTopPlayButton();
			makeSureItIsPlaying();
			
			if (!isSoftGateShow())
				handleError("Sign Up page is not displayed." , "WEB_11772_browsePodcasts");
			
			driver.navigate().back();
			
			//filter by topic
			new Select(driver.findElement(By.name("category"))).selectByIndex(5);
			WaitUtility.sleep(1000);
			driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(2) > button:nth-child(2)")).click();
			
			if (!isSoftGateShow())
				handleError("Sign Up page is not displayed for filtered podCast station." , "WEB_11772_browsePodcasts");
		}
		
		
		private void playCustomRadio()
		{   
			
			//gotoExplorerOption(option_customRadio, "Popular");
			comeToThisPage_direct();
			
			customFirstLinkPlayButton.click();
			
			//makeSureItIsPlaying();
		}
	
		private void playCustomRadioAfterLogin()
		{   
			//gotoExplorerOption(option_customRadio);
			comeToThisPage_direct();
			customFirstLinkPlayButton.click();
			//Sometimes the above play button didn't get clicked . 
			clickOnTopPlayButton();
			
			
			makeSureItIsPlaying();
		}
		
		public void huntAjax() throws Exception
		{   login();
		    
			comeToThisPage_direct();
			WaitUtility.injectJQuery(driver);
			
			customFirstLinkPlayButton.click();
			//Sometimes the above play button didn't get clicked . 
			clickOnTopPlayButton();
			
			makeSureItIsPlaying();
			//WaitUtility.interceptAjax(driver);
			WaitUtility.interceptAjaxSend(driver);
			WaitUtility.sleep(120*000);
		}
		
		
		public void playCustom()
		{
			login();
			playCustomRadioAfterLogin();
		}
		

		
		
		public void comeToThisPage()
		{  
		    	comeToThisPage_direct();
		}
		
		public void comeToThisPage_direct()
		{  
			String currentURL = driver.getCurrentUrl();
			System.out.println("SEE current url:"  + currentURL);
		    String part1 = currentURL.split("//")[0];
		    String part2  = currentURL.split("//")[1].split("/")[0];
		    
		    String newURL = part1 + "//" + part2 + "/artist/" ;
			System.out.println("SEE new url:"  + newURL );
			
			driver.get(newURL);
			 //FGDF
			WaitUtility.sleep(3000);
			
		}


}
