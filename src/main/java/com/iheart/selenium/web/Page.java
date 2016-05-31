package com.iheart.selenium.web;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.runner.Description;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.JavascriptExecutor;


public abstract class Page {

	
	@FindBy(css=".header-menu-main > li:nth-child(1) > a:nth-child(1)")   public WebElement forYou;
	@FindBy(css=".header-menu-main > li:nth-child(2) > a:nth-child(1)")   public WebElement liveRadio;
	@FindBy(css=".header-menu-main > li:nth-child(3) > a:nth-child(1)")    public WebElement customRadio;
	@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)") 	public WebElement genres;
	@FindBy(css=".header-menu-main > li:nth-child(5) > a:nth-child(1)") 	public WebElement podcasts;
	@FindBy(css=".header-menu-main > li:nth-child(6) > a:nth-child(1)")    public WebElement perfectFor;
	
	
	//Search
 //  @FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > form > div.form-group.ui-inline-block.search-input > input") 
    @FindBy(css=".input") public WebElement searchBox;
   
  
   @FindBy(css="li.genre:nth-child(1) > div:nth-child(1) > div:nth-child(1)") public WebElement top40;
   @FindBy(css=".genre-game-footer > button:nth-child(1)") public WebElement getStations;
   @FindBy(css=".player-station") public WebElement playerStation;
	
	//In the header

   
	@FindBy(css="button.facebook:nth-child(1)") public WebElement facebookLogin;
	@FindBy(css="button.facebook:nth-child(2)") public WebElement googleLogin;
	
	 //For Signup Page
	
	@FindBy(css=".dialog-close > div:nth-child(1) > button:nth-child(1)") public WebElement icon_close;
	 @FindBy(css="header.dialog-section > div:nth-child(2) > span:nth-child(1) > a:nth-child(3)")  public WebElement signUpLink;
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2") public WebElement signupHeader;
	@FindBy(css=".dialog-title")   public WebElement signupHint; //Have an account? Log In
	          
	@FindBy(css="[name='userName'][type='text']")  public WebElement email;
	@FindBy(css="[name='password'][type='password']")  public WebElement password;
	
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > form > div:nth-child(3) > section > input[type='text']")
		public WebElement zipCode;
	
	@FindBy(name="birthYear") public WebElement birthYear; 
	
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > form > div:nth-child(4) > div > label:nth-child(1) > span.input-radio > input[type='radio']")
			public WebElement gender_female;		
	
	@FindBy(name="termsAcceptanceDate") public WebElement termsAcceptanceDate;
	
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > form > button") public WebElement signUp;
	
	
	
	//SignedAccount DROP-DOWN
	@FindBy(css="#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > div > div:nth-child(1) > button > span") public WebElement signedAccount;

	
	@FindBy(css="//*[@id='page-view-container']/div/div[1]/div[2]/div/div[2]/div/button/span")  public WebElement signedAccount_chrome;
	
	
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
		public WebElement option_profile;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")
		public WebElement option_myStations;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
		public WebElement  option_listenHistory;
		
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)")
		public WebElement  option_favoriteSongs;	
		
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")
		public WebElement option_friends;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
		public WebElement option_setting;
				 
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(7) > a:nth-child(1)")
	    public WebElement option_logout;
	
	          
	@FindBy(css="#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > div > button > span")  public WebElement option_logout_chrome;
	
    public final static String EXPECTED_TITLE_FORYOU = "Listen to Free Radio Stations";
    public final static String EXPECTED_TITLE_LIVERADIO = "Listen to Top Radio Stations";
    public final static String EXPECTED_TITLE_CUSTOMRADIO = "Create Free Music Stations";
    public final static String EXPECTED_TITLE_GENRES = "Find Radio & Custom";
    public final static String EXPECTED_TITLE_PODCASTS = "Listen to the Best Podcasts";
    public final static String EXPECTED_TITLE_PERFECTFOR = "Listen to Music Pefect for";
    public final static String EXPECTED_TITLE_LOGOUT = "Listen to Free Radio Stations";
    
    
	
	
	//SignedAccount -> Profile -> Favorite Episode
	@FindBy(css="li.tabbar:nth-child(6) > a:nth-child(1)") private WebElement favoriteEpisodes;
		
	@FindBy(css="#hero > div.hero-content > div > div.profile-info > div > ul > li.station-name > button > i")  public WebElement   top_favorite_icon ;
	@FindBy(css=".icon-favorite-filled")  public WebElement   icon_favorite_filled ;
	@FindBy(css=".icon-favorite-unfilled")  public WebElement   icon_favorite_unfilled ;
    
	//facebook signup
	
    @FindBy(css="button.facebook:nth-child(1)")  public WebElement faceBook;
	
	@FindBy(id="email") public WebElement faceEmail;
	@FindBy(id="pass") public WebElement facePass;
	@FindBy(id="u_0_2") public WebElement faceLogin;

	//@FindBy(css="div.dropdown-trigger:nth-child(2) > button:nth-child(1)") public WebElement signedFBacct;
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(1) > button:nth-child(1)")   public WebElement signedFBacct;

	//Google login
	@FindBy(id="Email")  public WebElement googEmail;
	@FindBy(id="Passwd") public WebElement googPass;
	@FindBy(id="signIn") public WebElement googLogin;
	
	//login with email
	@FindBy(css=".icon-account")  public WebElement loginButton;
		
	
	@FindBy(css="[name='username'][type='text']")  public WebElement userName;
	@FindBy(css="[name='password'][type='password']")  public WebElement passWord;
	@FindBy(xpath="//*[@id='dialog']/div/div[2]/div[2]/div/form/button")  public WebElement login;
	
	//login wiht G+
	//@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > section > ul > li:nth-child(2) > button") public WebElement googleButton;
	@FindBy(css=".google-plus") public WebElement googleButton;
	
	//GROWLS
	@FindBy(css=".growls") public WebElement growls;
	
	//for doSkip()
	@FindBy(css="#player > div.player-center > div.player-controls > button.btn.text.no-border.xsmall > i") public WebElement icon_skip;
	@FindBy(css=".icon-skip") public WebElement icon_skip2;
	//Moved from liveRadioPage. 
	@FindBy(css="#player > div.player-center > div > button.btn.text.no-border.xsmall > span") public WebElement icon_scan;
	
	@FindBy(css="#player > div.player-left > div.player-info > a.player-song")  public WebElement player_song;
	
	//player buttons
	
	@FindBy(id="player") public WebElement player;
	@FindBy(xpath="//*[@id='player']/div[2]/div/button[3]")   public WebElement playButton;
	@FindBy(css="button.idle:nth-child(3)") public WebElement button_playing;
	
	@FindBy(css="button.playing:nth-child(3)") public WebElement button_pause;
	
	@FindBy(css=".icon-play") public WebElement icon_play;
    @FindBy(css="button.idle:nth-child(3)")  public WebElement icon_play_inPlayer;
    
    @FindBy(css="#player > div.player-center > div > button.playing.btn-circle.medium.play > i") public WebElement icon_stop_in_player;
   
	@FindBy(css=".icon-stop") public WebElement icon_stop;
	@FindBy(css=".icon-pause") public WebElement icon_pause;
	
		//@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(2) > span:nth-child(3)") 
	@FindBy(css="#player > div.player-right > button:nth-child(2) > span:nth-child(1)")	
	   public WebElement listenHistory;
	//@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(1) > span:nth-child(3)")
	@FindBy(css="#player > div.player-right > button:nth-child(1) > span:nth-child(3)")
	    public WebElement myStations;
	
	
	 @FindBy(css=".now-playing-options > button:nth-child(1)") public WebElement icon_more_horizontal;
   
	@FindBy(css="div.align-left:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)") public WebElement shareButton;
	@FindBy(css="div.align-left:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)") public WebElement addToFavorite;
	@FindBy(css=".dialog-title") public WebElement sharePageTitle;
	@FindBy(css="button._42ft:nth-child(2)") public WebElement shareOnFacebook;
	
	//Common for live radios and custom radios
	//thumbUp
	@FindBy(css="button.medium:nth-child(2)") protected WebElement thumbUp_button;
	
	
	@FindBy(css=".icon-thumb-up-unfilled") protected WebElement thumbUp;
	@FindBy(css=".icon-thumb-up-filled") protected WebElement thumbUpDone;
	 
    @FindBy(css="button.medium:nth-child(1)") protected WebElement thumbDown;
    @FindBy(css=".icon-thumb-down-filled") protected WebElement thumbDownDone;
	

	//Add to favorite
	@FindBy(css=".favorite") protected WebElement favorite;
	@FindBy(css=".icon-favorite-filled") protected WebElement icon_fav_filled;
	
    //AD CONTAINER
	@FindBy(id="imaAdContainer") protected WebElement adContainer;
	
	
	//FACE BOOK Signup info
	//public final String FACEBOOKemail = "iheartRadio.tribecca@gmail.com";
	public final String FACEBOOKemail = "iheartRocks999@gmail.com";
	public final String _PASSWORD = "iheart001";

   public static WebDriver driver;
   public static final String screenshot_folder="screenshots";
   public static StringBuffer errors = new StringBuffer(); 
   
   public static String browser = "";
   
   public static String URL = "";
   
   public Page()
   {
	   
   }
	
   public Page(WebDriver _driver)    
   {
	   driver = _driver;
   }
   
   
   public abstract void comeToThisPage();

   public static void takeScreenshot_PORGRESS(WebDriver driver, String testMethod) throws Exception 
   {      
		  // Creating new directory in Java, if it doesn't exists
	       File directory = new File(screenshot_folder);
	       
	       if (!directory.exists()) 
	       {
	           System.out.println("Directory not exists, creating now");
	
	           boolean success = directory.mkdir();
	           if (success) {
	               System.out.printf("Successfully created new directory : %s%n", screenshot_folder);
	           } else {
	               System.out.printf("Failed to create new directory: %s%n", screenshot_folder);
	           }
	       }
	       
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   			Date date = new Date();
   			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
	       System.out.println("See screenshotName:" + screenshotName);
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File( screenshotName));
       
           System.out.println("Screenshot is taken.");
   }
   
   
   public static void takeScreenshot(WebDriver driver, String testMethod) throws Exception 
   {      
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   			Date date = new Date();
   			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
	       System.out.println("See screenshotName:" + screenshotName);
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
           FileUtils.copyFile(scrFile, new File(screenshotName));
           System.out.println("Screenshot is taken.");
   }
   
   
   
   public static void takeScreenshotInCloud(WebDriver driver, String testMethod) throws Exception 
   {      
	       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
   		   Date date = new Date();
	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
	       System.out.println("See screenshotName:" + screenshotName);
	       
	       driver = new Augmenter().augment(driver);
	       
           File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           FileUtils.copyFile(scrFile, new File(screenshotName));
           System.out.println("Screenshot is taken.");
   }
   
   
   public static void setDriver(WebDriver _driver)
   {
	   driver = _driver;
   }
   
   public static WebDriver getDriver()
   {
	   return driver;
   }
   
   public void login()
	{   
		int count = 0;    
		do{
			
			try{
			   loginButton.click();
			   
			}catch(Exception e)
			{
				
			}
			count++;
		}while (count< 12 && !driver.getPageSource().contains("Don't have an account?"));
		
		
    	userName.sendKeys(FACEBOOKemail);
	    passWord.sendKeys(_PASSWORD);
	
		login.click();
		WaitUtility.sleep(2000);
	}
	
   
   private List<WebElement> getMyStations()
   {
	   return  driver.findElements(By.className("a-block title"));	
   }
   
   public String  switchWindow()
	{
		//Switch to new tab where the sign-up is
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}	
		return winHandleBefore;
	}
	
	public void goToPreviousWindow(WebDriver  currentDriver, String winHandleBefore)
	{
	    currentDriver.close();
		//Switch back to original browser (first window)
	    driver.switchTo().window(winHandleBefore);
	}
	
	public String getCurrentDateString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String getCurrentDateInMilli()
	{
		Date date = new Date();
		return date.getTime() + "";
	}
   
	//For Podcasts and Perfect For
	public void checkFavInProfile(String name)
	{
		System.out.println("checkFavInProfile().option_profile:" + option_profile.getText() );
															
		WebElement option =  driver.findElement(By.cssSelector("div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")) ;
		System.out.println("checkFavInProfile().option_profile VIA DRIVER:" + option.getText());
		gotoSingedAccountOption(option,  "User");
		
	//	favoriteEpisodes.click();
		if (!driver.getPageSource().contains(name))
		   errors.append(name + " is not added in profile -> Favorite Episodes.");
	}
	
	
	public void doSkip()
	{
		String songPlaying = player_song.getText();
		System.out.println("It is playing:" + songPlaying);
		
		icon_skip.click();
		String nextSong = waitForElement(driver.findElement(By.className("player-song")), 3000).getText();
		System.out.println("After skip:" + nextSong);
		
		
		//Some times different episodes have the same title, such as 'ABC News - Headlines and Top Stories', so need to address this case
		if (nextSong.equalsIgnoreCase(songPlaying) &&!nextSong.equals("ABC News Update"))
			errors.append("skip button is not working for Podcasts. ");
	}
	
	
	//For Podcasts and Perfect For
	public void doScan()
	{
		String songPlaying = player_song.getText();
		System.out.println("It is playing:" + songPlaying);
		
		icon_scan.click();
		String nextSong = driver.findElement(By.className("player-song")).getText();
		System.out.println("After skip:" + nextSong);
		
		if (nextSong.equalsIgnoreCase(songPlaying))
			errors.append("scan button is not working. ");
	}
	
	public void search(String what)
	{   searchBox.clear();
		searchBox.sendKeys(what);
		List<WebElement> resultRows = driver.findElements(By.className("search-section"));
		System.out.println(resultRows.size() + " rows are suggested.");
		
		if (resultRows == null || resultRows.size() <1)
	    	errors.append("Search doesn't generate the right result.");
		
		searchBox.clear();
	}
	
	public void handleError(String msg, String methodName) 
	{
		errors.append(msg);
		try{
		   takeScreenshot( driver,  methodName);
		}catch(Exception e)
		{
			System.out.println("Exception is thrown taking screenshot.");
		}
	}
	
	public void doubleClick(WebElement element)
	{
		Actions action = new Actions(driver);
		//Double click
		action.doubleClick(element).perform();

	}
	
	
	public static StringBuffer getErrors()
	{
		return errors;
	}
	
	public void signUp()
	{  
		//Signup 
		//tweak email so that we won't get user already singed up error 
		String  randomEmail_firstPart = getCurrentDateInMilli();
		//String _email = randomEmail_firstPart + "@gmail.com";
		String _email = randomEmail_firstPart + "@mailinator.com";
		System.out.println("See randomEmail:" + _email);
		
	    waitForElement(email, 10000).sendKeys(_email);
	    password.sendKeys(_PASSWORD);
	    zipCode.sendKeys("10013");
	    new Select(birthYear).selectByIndex(20);
	    
	    waitForElement(gender_female, 1000).click();
	   // termsAcceptanceDate.click();
	    signUp.click();
	    
	    System.out.println("see signed account:" + signedAccount.getText() );
	    signedAccount.click();
	    
	    if (!signedAccount.getText().contains(randomEmail_firstPart))
	    	errors.append("Signup failed.");
	    
	}
	
	
	
	public void makeSureItIsPlaying()
	{   
	    try{
	    	//icon_play_inPlayer.isDisplayed();//isDisplayed() is not working against iheart elements
	    	icon_play_inPlayer.getAttribute("outerHTML");
		    System.out.println("Music is not playing. About to click.");
	        if (icon_play_inPlayer.isEnabled())
		       icon_play_inPlayer.click();
	        else     //CLick on play button in hero
	        {  
	        	try{
	        	   driver.findElement(By.cssSelector("#hero > div.hero-content > div > div.profile-info > div > button > i")).click();
	        	}catch(Exception e) {}   
	        }	
	    }catch(Exception e)
	    {   
	    	System.out.println("Music is playing. ");
	    	return;
	    }
	    
	    handlePreRoll();
	    
	}
	
	//to be done
	public void handlePreRoll()
	{   
		 WaitUtility.sleep(48000);
		 
	}
	
	
	
	
	public void makeSureItIsPlayingWithNoWait()
	{   
	    try{

		    icon_play.isDisplayed();
		    System.out.println("Music is not playing. About to click.");
			icon_play.click();
	    }catch(Exception e)

	    {   System.out.println("Music is playing. ");
	    	return;
	    }
	    System.out.println("OUT of makeSureItIsPlayingWithNoWait()");
	}
	
	//to by-pass the pre-roll
	public void makeSureItIsNotPlaying_old()
	{   boolean isPlaying = true;

	    try{

		   // icon_play.isDisplayed();
	    	icon_play_inPlayer.isDisplayed();
		    System.out.println("Music is not playing. Good");

	    }catch(Exception e)

	    {   System.out.println("Music is playing. click to stop it ");
	    	icon_play_inPlayer.click();
		
	    	//Wait for pre-roll
	
	    	handlePreRoll();

	    	return;
	    	

	    }
	}
	
	
	//to by-pass the pre-roll
		public void makeSureItIsNotPlaying()
		{   boolean isPlaying = false;

		    try{
		    	icon_stop_in_player.getAttribute("class");
                System.out.println("Is playing.. click to stop.");
                isPlaying = true;
		    }catch(Exception e)
		    {  
		    	isPlaying = false;

		    }
		    
		    if (isPlaying)
		    	icon_stop_in_player.click();
		    
		}
	
	
	public static String getBrowser()
	{
		return browser;
	}
	
	public static void setBrowser(String _browser)
	{
		browser = _browser;
	}
	
	
	public void gotoSingedAccountOption(WebElement option, String expectedTitle)
	{  // limit try to 5 times
		int count = 0;
		
		Actions action = new Actions(driver);
		do {
		    action = action.moveToElement(signedAccount);
			
			try{
		    	action.moveToElement(option).click().build().perform();
			}catch(Exception e)
			{
				
			}
			
			System.out.println("See title:" + driver.getTitle());
			count++;
		
		}while(count <5 && !driver.getTitle().contains(expectedTitle));	
	}
	
	
	public void doThumbUp(String methodName)
	{  	int count = 0; 
		
		//Try a little bit more
		while(isThumbUpDisabled() && count < 3)
		{	System.out.println("thumbUp button is disabled. Scan now..");
		    try{
			   icon_scan.click();
		    }catch(Exception e)
		    {   
		    	
		    }
			count++;
		}
		
		//if it is still disabled, return 
		if(isThumbUpDisabled()) return;
		
		//If this is thumbUp before, double-click
		if (isThumbUpDone())
		{	
			return;
		}
		try{
		   thumbUp.click();
		} catch(Exception e)
		{
			System.out.println("Hit the commercial time. return now.");
			return;
		}
		

		//ThumbUp/Down spec has changed per web-5588. Drop this part so that I can modify it after I come back from vacation

	    /*
		if (!isThumbUpDone())
			handleError("Favorite icon is not highlighted.", methodName);
		*/
		//COMMENT OFF FOR now since the rule has changed. Will implement new rule after vacation

		/*
		String response = driver.findElement(By.className("growls")).getText();
		System.out.println("See growls:" + response);
		if (!(response.contains("Glad you like") || response.contains("Thanks for your feedback")))
			handleError("Thump Up is not working properly.", methodName);
		*/	
	}
	
	private boolean isThumbUpDone()
	{
		
		boolean isDone = false;
		try{
			String innerHTML = thumbUp_button.getAttribute("innerHTML");
			
			System.out.println("Is thumpUp done? see innerhtml:" + innerHTML );
			  
		   isDone = innerHTML.contains("-filled");
		   
		}catch(Exception e)
		{
			
		}
		return isDone;
	}
	
	
	private boolean isThumbDownDone()
	{
		
		boolean isDone = false;
		try{
			String innerHTML = thumbDown.getAttribute("innerHTML");
			
			System.out.println("Is thumpDown done? see innerhtml:" + innerHTML );
			  
		   isDone = innerHTML.contains("-filled");
		   
		}catch(Exception e)
		{
			
		}
		return isDone;
	}
	
	
	private boolean isThumbUpDisabled()
	{
		
		boolean isDisabled = false;
		try{
			//System.out.println("Is thumpUp html:" + thumbUp_button.getAttribute("innerHTML") );
			String outerHTML = thumbUp_button.getAttribute("outerHTML");
			
			System.out.println("Is thumpUp outerhtml:" + outerHTML );
			
			
			//System.out.println("Is thumpUp disabled:" + thumbUp_button.getAttribute("disabled") );
		   
		   isDisabled = outerHTML.contains("disabled");
		   
		}catch(Exception e)
		{
			
		}
		return isDisabled;
	}
	
	public void doThumbDown(String methodName)
	{  	int count = 0; 
		
		//Try a little bit more
		while(isThumbDownDisabled() && count < 3)
		{	System.out.println("thumbDown button is disabled. Scan now..");
		    try{
			  icon_scan.click();
			  count++;
		    }catch(Exception e)
		    {
		    	
		    }
			WaitUtility.sleep(2000); //Do not remove this .sleep()
		}
		
		//if it is still disabled, return 
		if(isThumbDownDisabled()) return;
		
		//If this is thumbUp before, double-click
		
		
		if (isThumbDownDone())
		{	
			return;
		}
		try{
		   thumbDown.click();
		} catch(Exception e)
		{
			System.out.println("Hit the commercial time. return now.");
			return;
		}
		
		
		//check to make sure that thumpUp Icon is filled
	  
		//ThumbUp/Down spec has changed per web-5588. Drop this part so that I can modify it after I come back from vacation

	   /*
		if (!isThumbDownDone())
			handleError("Favorite icon is not highlighted.", methodName);
		*/
		//COMMENT OFF FOR now since the rule has changed. Will implement new rule after vacation

		/*
		String response = driver.findElement(By.className("growls")).getText();
		System.out.println("See growls:" + response);
		if (! response.contains("Thanks for your feedback"))
			handleError("Thump Down is not working properly.", methodName);
<<<<<<< HEAD
		*/

	}
	
	public boolean isThumbDownDisabled()
	{
		boolean isDisabled = false;
		try{
		   
		   String outerHTML = waitForElement(thumbDown, 10000).getAttribute("outerHTML");
			
			System.out.println("Is thumpDown outerhtml:" + outerHTML );
			
		   
		   isDisabled = outerHTML.contains("disabled");
		   
		}catch(Exception e)
		{
			
		}
		return isDisabled;
	}
	
	
	public void doFavorite(String methodName)
	{
		 //If the chosen show/song is faved before, double click; 
		boolean isFavoredAlready = false;
		System.out.println("See class:" + top_favorite_icon.getAttribute("class"));
		if ((top_favorite_icon.getAttribute("class")).contains("-filled"))
			isFavoredAlready = true;
		
	    if (isFavoredAlready)
	    {  
	    	try{
	        	top_favorite_icon.click();
	    	}catch(Exception e)
	    	{     
	    		   top_favorite_icon.click();
	    	}
	      
	    }
	    
	    
	   top_favorite_icon.click();
	   System.out.println("See class again:" + top_favorite_icon.getAttribute("class"));
	   if (top_favorite_icon.getAttribute("class").contains("-unfilled"))
	   {
		   handleError("Favorite icon is not highlighted.", methodName);
	   }
		
	   //Check that growls show up
	   String _growls = growls.getText();
		System.out.println("See growls:" + _growls);
	
		if (!_growls.contains("Favorite"))
		   handleError("Add to Favorite failed.", methodName);
	   
	}
	
	public void doFavorite_OLD(String methodName)
	{
		 //If the chosen show/song is faved before, double click; 
		boolean isFavoredAlready = false;
	    try{
		   icon_favorite_filled.getAttribute("class");
		   isFavoredAlready = true;
	    }catch(Exception e)
	    {
	    	
	    }
		
	    try{
		   if (isFavoredAlready)
		   {  
			   icon_favorite_filled.click();
		   }
	    }catch(Exception e)
	    {
	    	System.out.println("Exception clicking on filled heart.");
	    }
	   
	   if (!icon_favorite_unfilled.isDisplayed())
	   {
		   handleError("unFavorite  is not working.", methodName);
	   }
	   
	   icon_favorite_unfilled.click();
		
	   if (!icon_favorite_filled.isDisplayed())
	   {
		   handleError("Favorite icon is not highlighted.", methodName);
	   }
		
	   //Check that growls show up
	   String _growls = growls.getText();
		System.out.println("See growls:" + _growls);
	
		if (!_growls.contains("Favorite"))
		   handleError("Add to Favorite failed.", methodName);
	   
	}
	
	public boolean isSoftGateShow_old()
	{  
	    try{
	       signupHint.getText();
	    }catch(Exception e)
	    { // e.printStackTrace();
	       System.out.println("Soft gate is not shown. Give it more time");
	      
	       try{
	    	   System.out.println(signupHint.getText());
	    	   
	       }catch(Exception ex)
	       {
	    	   return false;
	       }
	       
	    }
	    return signupHint.getText().contains("Sign Up");
	}
	
 
       public boolean isSoftGateShow()
   	{   	
	    WebElement signupButton = null;
		
		signupButton = waitForElement(driver.findElement(By.cssSelector("button.btn:nth-child(7)")), 48000);
	
	  
		//return signupHint.getText().contains("Have an account?");
		return signupButton.getText().contains("Sign Up");
	}

	
	
	public void verifyPlayer(String category)
	{  
		verifyPlayer(category, "verifyPlayer");
	}
	
	
	public void verifyPlayer_old(String category, String testMethod)
	{   boolean isPlaying = false;
	    try{
	    	if (icon_pause.isDisplayed())
	    	{	isPlaying = true;
	    		System.out.println(category + " is  playing. Good" );
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
		    handleError("The " + category + " is not playing.", testMethod);
		
	}
	
	public void verifyPlayer(String category, String testMethod)
	{   
	    
		if (!isPlaying())
		    handleError("The " + category + " is not playing.", testMethod);
		
	}
	
	private boolean isPlaying()
	{
		String outHTML = playButton.getAttribute("outerHTML");
		return (!outHTML.contains("icon-play"));
	}
	
	
	public void playAstation()
	{
		List<WebElement> stations = driver.findElements(By.className("icon-play"));
		System.out.println("stations count:" + stations.size());
		
	    for (WebElement station: stations)
	    {
	    	station.click();
	    	break;
	    }
	   
	}
	
	
	public void faceBookSignUp()
	{   
		try{
		   waitForElement(faceBook, 18000).click();
		}catch(Exception e)
		{
			faceBook.click();
		}
        //Give it some time for the window to popup		
		WaitUtility.sleep(1000);  
		String winHandleBefore = switchWindow();
		
		faceEmail.sendKeys(FACEBOOKemail);
		
		facePass.sendKeys("iheart001");
		faceLogin.click();
	    
	    
	    driver.switchTo().window(winHandleBefore);
	}
	
	public void waitForSignUp()
	{
		int count = 0;
		while (count < 6)
		{	
			if (!isSignUpShown()) 
			{	
				WaitUtility.sleep(6*1000);
				count++;
				System.out.println("Waited for signup:" + count + " time(s)");
			}else 
				break;
		}	
	}
	
	private boolean isSignUpShown()
	{
		try{
			driver.findElement(By.cssSelector("#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2")).getText();
		    return true;
		}catch(Exception e)
		{   
			return false;
		}
	}
	
	public void shareOnfaceBook()
	{
		driver.findElement(By.className("facebook")).click();
		String winHandleBefore = switchWindow();
		
		faceEmail.sendKeys(FACEBOOKemail);
		facePass.sendKeys("iheart001");
		faceLogin.click();
	    shareOnFacebook.click();
	    driver.switchTo().window(winHandleBefore);
	}
	
	public void clickOnTopPlayButton()
	{
		 try{
		       driver.findElement(By.cssSelector("button.idle:nth-child(1)")).click();
		    }catch(Exception e)
		    {
		    	
		    }
	}
	
	
	public void scrollDown()
	{
		scrollDown(250);
	}
	
	
	public void scrollDown(int offset)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		String script = "window.scrollBy(0," + offset + ")";
		System.out.println("See script:" + script);
		//jse.executeScript("window.scrollBy(0,_offset)", "");
		jse.executeScript(script, "");
	}
	
	public void gotoSingedAccountOption_direct(String option)
	{   String newURL ="";
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	   if (option.equalsIgnoreCase("Profile"))
		   newURL = part1 + "//" + part2 + "/my/stations/" ;
	   else  if (option.equalsIgnoreCase("My Station"))
		   newURL = part1 + "//" + part2 + "/my/stations/" ;
	   else  if (option.equalsIgnoreCase("Listen History"))
		   newURL = part1 + "//" + part2 + "/my/history/" ;
	   if (option.equalsIgnoreCase("Friends"))
		   newURL = part1 + "//" + part2 + "/my/friends/" ;
	   else  if (option.equalsIgnoreCase("Settings"))
		   newURL = part1 + "//" + part2 + "/my/settings/" ;
	  
	   
	   System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		 
	}
	
	//Need to optimize the code to reduce the redundency
	
	public static void gotoMyStationPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/my/stations/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
	}
	
	public static void gotoMyListenHistoryPage_direct(){
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/my/history/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
	}
	

	public static void gotoFriendPage_direct(){
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "my/friends/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
		
	}
	
	
	public static void setURL(String url)
	{
		URL = url;
	}
	
	public static String getURL()
	{
		return URL;
	}
	
	
	public static WebElement waitForElement(WebElement element, long timeOutInMilliSecond)
	{  
		return WaitUtility.waitForElement(driver, element, timeOutInMilliSecond);
	}
		
	
}
