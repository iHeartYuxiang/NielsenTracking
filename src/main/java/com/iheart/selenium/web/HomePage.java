package com.iheart.selenium.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.junit.Assert;

import static org.junit.Assert.*; 

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;



//aka For You Page
public class HomePage extends Page {

	

	@FindBy(css="li.genre:nth-child(13) > div:nth-child(1) > div:nth-child(1)")
			public WebElement comedy;		
	
	
	@FindBy(css="li.genre:nth-child(10) > div:nth-child(1) > div:nth-child(3)") public WebElement sport;
	@FindBy(css="button.idle:nth-child(3)") public WebElement playStation;

	@FindBy(css="button.text:nth-child(1)") public WebElement myStation;
	@FindBy(css=".section-header")   public WebElement stationHeader;
	
	@FindBy(css="li.genre:nth-child(1) > div:nth-child(1) > div:nth-child(1)") public WebElement firstGenra;
	@FindBy(css=".genre-game-footer > button:nth-child(1)") public WebElement getStation;
	//@FindBy(css="li.tabbar:nth-child(1) > a:nth-child(1)") public WebElement forYouLink;
	///@FindBy(css="li.tabbar:nth-child(1) > a:nth-child(1)") public WebElement forYouLink;
	
	@FindBy(css="#hero > div.hero-content > div > div.profile-art-wrapper") public WebElement hero;
	@FindBy(css="a.small") public WebElement heroEnter;
	
	//@FindBy(css=".station-description > p:nth-child(1) > div:nth-child(1) > a:nth-child(3)")  public WebElement heroEnter;
	

	@FindBy(css="#player > div.player-center > div > button.idle.btn-circle.medium.play > i")
		public WebElement playButton;

	//for Z100.com/Popular User Flow
	//@FindBy(css="body > div.pageWrapper > div.nowPlayingWrapper.full > div > a > span.cta > span:nth-child(1)")
	@FindBy(css="body > div.page-wrapper > div.nowplaying-wrapper.full > section > section.listen-live.left > a > div.small-8.cta > span:nth-child(1)")
		private WebElement listenLive;
	
	 public HomePage() {
			
	}
		
	public HomePage(WebDriver driver) {
			super(driver);
	}
	
	//For Most Popular User Flow
	public void  flowAlong()
	{
	    listenLive.click();
		
		makeSureItIsPlaying();
		
		String winHandleBefore = driver.getWindowHandle();
		
		int windowCount = 0;
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);

		    windowCount ++;
		    if (windowCount == 2) break;

		}
		
		//Here, need to modify URL per env and  reload page
	   if (!Page.getURL().startsWith("http://www."))
		   driver.get(modifyURLPerRunningEnv());
		//Pre-roll gets kicked here some times. Handle it 
	   handlePreRoll();
		signUp();
	}
	
	private String modifyURLPerRunningEnv()
	{
		String currentURL = driver.getCurrentUrl();
		//What env it is on? 
	    String runningEnvURL = Page.getURL();
	    String part1 = runningEnvURL.split(".iheart.")[0];
	    System.out.println("part 1:" + part1);
	    String part2  = currentURL.split(".iheart.")[1];
	    System.out.println("part 2:" + part2);
	    
	    String newURL = part1  + ".iheart."+  part2  ;
		System.out.println("SEE new url adjusted for running env:"  + newURL );
		return newURL;
		
	    
	}
	
	public void WEB_14440_playerUponFirstLaunch()
	{  
		top40.click();
		getStations.click();
		
		if(!player.isDisplayed())
			 handleError("Player doesn't exist upon first launch.", "WEB_14440_playerUponFirstLaunch" );
	}
	
	public void WEB_21226_playDefaultByLocation()
	{  
		top40.click();
		getStations.click();
		
		
		String stationName = playerStation.getText();
		
		System.out.println("stationName:" + stationName );
		if(!stationName.contains("106.7"))
			 handleError("Default Station is not based on location.", "WEB_21226_playDefaultByLocation" );
		
	}
	
	
	public void WEB_11734_startUp()
	{   
		comedy.click();
		waitForElement(driver.findElement(By.cssSelector(".genre-game-footer > button:nth-child(1)")),5000).click();
		
		WaitUtility.sleep(5000);
		
	    playStation.click();
		
		driver.quit();
		driver = null;
		driver = Utils.launchBrowser("http://www.iheart.com", Page.getBrowser());
		
		driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div.genres-wrapper.wrapper > ul > li:nth-child(10) > div > div.genre-img")).click();
		driver.findElement(By.cssSelector(".genre-game-footer > button:nth-child(1)")).click();
		
		//playStation.click();
		WaitUtility.sleep(6000);
		driver.findElement(By.cssSelector("#player > div.player-center > div > button.idle.btn-circle.medium.play > i")).click();
		
		//waitForElement(driver.findElement(By.cssSelector("#player > div.player-center > div > button.idle.btn-circle.medium.play > i")), 5000).click();
		driver.quit();
	}
	
	
	public void WEB_11759_Hero() throws Exception
	{
		
		firstGenra.click();
		//WaitUtility.waitForAjax(driver);
		getStation.click();
		//WaitUtility.waitForAjax(driver);
		
		// assert that player shall appear
	   System.out.println("See text:"+ myStation.getText());
	   
		if(!myStation.getText().contains("Stations"))
			 handleError("Didn't reach My Stations page.", "WEB_11759_Hero" );
		
		//Check for you link
		//check Hero 
		/*
		try {
			forYouLink.getText();
			System.out.println("For You link is here.");
		}catch(Exception e)
		{   handleError("For You link is missing.", "WEB_11759_11790_Hero" );
		}
		*/
		try {
			hero.getText();
		}catch(Exception e)
		{   
			handleError("Hero is missing.", "WEB_11759_Hero");
		}
		
		heroEnter.click();
		
		//Verify that a seperate window is launched
		String winHandleBefore = driver.getWindowHandle();
		
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
	    //Check page title of the newly launched window
		System.out.println(driver.getTitle()); 
		
	

	}
	
	public void WEB_11790_Hero() throws Exception
	{
		
		firstGenra.click();
		getStation.click();
	   
	   if (!stationHeader.getText().contains("Stations Just For You"))
			 handleError("Didn't reach My Stations page.", "WEB_11790_Hero" );
		
	   makeSureItIsPlaying();
	   
		
		try {
		  	System.out.println("see hero:" + hero.getText());
		}catch(Exception e)
		{   
			handleError("Hero is missing.", "WEB_11790_Hero");
		}
		
		
	
	    if(!isSoftGateShow())
	    	errors.append("Sign up page is not displayed for unauthorized user.");
	    	
	}
	
	
	
	public void WEB_11735_exploreMenu() throws Exception
	{
		 verifyLink(forYou, EXPECTED_TITLE_FORYOU);
		 verifyLink(liveRadio, EXPECTED_TITLE_LIVERADIO);
		 verifyLink(customRadio, EXPECTED_TITLE_CUSTOMRADIO);
		 verifyLink(genres, EXPECTED_TITLE_GENRES);
		 verifyLink(perfectFor, EXPECTED_TITLE_PERFECTFOR);
		 verifyLink(podcasts, EXPECTED_TITLE_PODCASTS);
	}
	
	private void verifyLink(WebElement option, String expectedTitle)
	{    option.click();
	     String _option =  option.getText() ;
		 System.out.println("See option/ title: "+ _option +"/"+ driver.getTitle());
		 if(!driver.getTitle().contains(expectedTitle))
			 handleError(_option +" link is not working.", "WEB_11735_explorerMenu" );
			 
	}
	
	
	public void WEB_11784_signUp()
	{
		firstGenra.click();
		//WaitUtility.waitForAjax(driver);
		
		getStation.click();
		//WaitUtility.waitForAjax(driver);
		playButton.click();
		makeSureItIsPlaying();
	  	signUp();
	   
	}
	
	
	public void WEB_11736_signUp()
	{  
		int count = 0;
		boolean clickAgain = true;
        do
        {
        	try{
			  driver.findElement(By.cssSelector(".icon-account")).click();
			   clickAgain  = false;
	        }catch(Exception e) 
	        {
	            clickAgain = true; 
	            WaitUtility.sleep(1000);
	        }	
            count++;
            
        	
	     } while (count < 3 && clickAgain)	;
	        	        
		
		signUpLink.click();
		signUp();
	}
	
	public void WEB_11738_FACEBOOKsignUp()
	{   loginButton.click();
	    faceBookSignUp();
		
	}
	
	
	public void WEB_11741_searchAfterLogin()
	{   login();
		search("Pool Party");
		
		search("Elvis Duran");
		
		search("WHTZ");
	
		search("97.1");
		
		search("Bruno Mars");
		
		search("Umbrella");
	}
	
	
	
	public void WEB_8823_faceBooksignUp()
	{     
		waitForElement(firstGenra, 5000).click();
		
		waitForElement(getStation, 5000).click();
	    try{
		  waitForElement(playButton, 5000).click();
	    }catch(Exception e)
	    {
	    	WaitUtility.sleep(1000);
	    	playButton.click();
	    }
		makeSureItIsPlaying();
		
		
		faceBookSignUp();
	  //  String signedAcct = driver.findElement(By.cssSelector("div.dropdown-trigger:nth-child(1) > button:nth-child(1)")).getText();
	    System.out.println("see account:" + signedFBacct.getText());
	    
	    try{
	    	signedFBacct.click();
	    }catch(Exception e)
	    {
	    	errors.append("Facebook Signup failed.");
	    }
	}
	
	
	
	public void WEB_11737_loginWithEmail()
	{
		loginButton.click();
		userName.sendKeys(FACEBOOKemail);
		passWord.sendKeys(_PASSWORD);
		login.click();
		
		
	    System.out.println("see account:" + signedFBacct.getText());
	    
	    try{
	    	signedFBacct.click();
	    }catch(Exception e)
	    {
	    	errors.append("login with email failed.");
	    }
	}
	
	public void WEB_11739_loginWithGoog()
	{   
		/*
		int count = 0;
		do{
			loginButton.click();
			WaitUtility.sleep(1000);
			count++;
		}while (count < 5 && !driver.getPageSource().contains("Don't have an account?"));
		*/
		waitForElement(loginButton, 10000).click();
		
		waitForElement(driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2")), 10000);
		googleButton.click();
		//Need to switch Windows here
		String winHandleBefore = switchWindow();
		googEmail.sendKeys(FACEBOOKemail);
		//NEXT BUTTON IS ADDED
		try{
			driver.findElement(By.id("next")).click();
		}catch(Exception e)
		{
			
		}
		googPass.sendKeys(_PASSWORD);
		googLogin.click();
		
		 driver.switchTo().window(winHandleBefore);
		 System.out.println("see account:" + waitForElement(signedFBacct, 5000).getText());
	   // System.out.println("see account:" + signedFBacct.getText());
	    
	    try{
	    	signedFBacct.click();
	    }catch(Exception e)
	    {
	    	errors.append("login with G+ failed.");
	    }
	    
	    
	}
	
	public void WEB_11740_search()
	{
		searchBox.sendKeys("SUN");
		
		List<WebElement> resultRows = driver.findElements(By.className("search-section"));
		System.out.println(resultRows.size() + " rows are suggested.");
	//	boolean failed = true;
		//if (failed)
		if (resultRows == null || resultRows.size() <1)
	    	errors.append("No suggestion is found.");
	    
	}
	
	
	public void WEB_11742_searchWihoutLogin()
	{   
		search("Pool Party");
		
		search("Elvis Duran");
		
		search("WHTZ");
	
		search("97.1");
		
		search("Bruno Mars");
		
		search("Umbrella");
	}
	
	public void WEB_11741_search()
	{   login();
		search("Pool Party");
	    
	}
	
	
	public void WEB_11902_GeographySearch(String city)
	{   System.out.println("Check user city:" + city);
		searchBox.sendKeys("97.1"); 
		
		String topHit = driver.findElement(By.cssSelector(".selected > div:nth-child(2) > p:nth-child(2)")).getText();
		
		System.out.println("userCity/See displayed top hit:" + city + "/" + topHit);
		if(!topHit.contains(city))
			errors.append("Returned top hit is not based on user's geo.");
	}
	
	
	public void comeToThisPage()
	{  //DO NOTHING
	}		
	
}
