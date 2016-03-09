package com.iheart.selenium.web;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

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


public abstract class Page {

	//for Explorer menu
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > button > span") 
		public WebElement explorer;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul") 
		public WebElement dropdowns;
	
	//body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li  //The following is firefox version
	
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
		public WebElement option_forYou;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)") 
		public WebElement option_liveRadio;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)") 
		public WebElement option_customRadio;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)") 
		public WebElement option_genres;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)") 
		public WebElement option_podCasts;
	@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
		public WebElement option_perfectFor;
	
	
	//Chrome Version
	
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(1) > a")
		public WebElement option_forYou_chrome;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(2) > a") 
		public WebElement option_liveRadio_chrome;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(3) > a") 
		public WebElement option_customRadio_chrome;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(4) > a") 
		public WebElement option_genres_chrome;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(5) > a") 
		public WebElement option_podCasts_chrome;
	@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(1) > div > div > nav > ul > li:nth-child(6) > a")
		public WebElement option_perfectFor_chrome;
	
	
	//Search
 //  @FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > form > div.form-group.ui-inline-block.search-input > input") 
    @FindBy(css=".input") public WebElement searchBox;
   
  
   @FindBy(css="li.genre:nth-child(1) > div:nth-child(1) > div:nth-child(1)") public WebElement top40;
   @FindBy(css=".genre-game-footer > button:nth-child(1)") public WebElement getStations;
   @FindBy(css=".player-station") public WebElement playerStation;
	
	//In the header
	
	@FindBy(css=".header-right > button:nth-child(1)") public WebElement header_login;
	@FindBy(css="button.short:nth-child(3)") public WebElement header_signUp;
	//inside signup page
	@FindBy(css="button.facebook:nth-child(1)") public WebElement facebookLogin;
	@FindBy(css="button.facebook:nth-child(2)") public WebElement googleLogin;
	
	 //For Signup Page
	//
	//@FindBy(css=".icon-close") public WebElement icon_close;
	@FindBy(css=".dialog-close > div:nth-child(1) > button:nth-child(1)") public WebElement icon_close;
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2") public WebElement signupHeader;
	@FindBy(css="header.dialog-section > div:nth-child(2) > span:nth-child(1) > span:nth-child(1)") public WebElement signupHint; //Have an account? Log In
	
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
	@FindBy(css="div.dropdown-trigger:nth-child(1) > button:nth-child(1)") public WebElement signedAccount;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1)")
	    public WebElement signedAccountDropDown;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1)") 
	  	public WebElement  signedAcctDropDown;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
		public WebElement option_profile;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")
		public WebElement option_myStations;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
		public WebElement  option_listenHistory;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)")
		public WebElement option_friends;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")
		public WebElement option_setting;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
	    public WebElement option_logout;
		
	
	//SignedAccount -> Profile -> Favorite Episode
	@FindBy(css="li.tabbar:nth-child(6) > a:nth-child(1)") private WebElement favoriteEpisodes;
		
	@FindBy(css=".icon-favorite-filled")  public WebElement   icon_favorite_filled ;
	@FindBy(css=".icon-favorite-unfilled")  public WebElement   icon_favorite_unfilled ;
    
	//facebook signup

	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > div > section > ul > li:nth-child(1) > button > span")  public WebElement faceBook;
	
	@FindBy(id="email") public WebElement faceEmail;
	@FindBy(id="pass") public WebElement facePass;
	@FindBy(id="u_0_2") public WebElement faceLogin;
	@FindBy(css="div.dropdown-trigger:nth-child(1) > button:nth-child(1)") public WebElement signedFBacct;
	
	//Google login
	@FindBy(id="Email")  public WebElement googEmail;
	@FindBy(id="Passwd") public WebElement googPass;
	@FindBy(id="signIn") public WebElement googLogin;
	
	//login with email
	//@FindBy(css="body > div:nth-child(1) > div.header > div.header-wrapper > div > div:nth-child(3) > div > button:nth-child(1)") public WebElement loginButton;
	//@FindBy(css=".header-right > button:nth-child(1)") public WebElement loginButton;
	@FindBy(css=".icon-account")	public WebElement loginButton;
	
	@FindBy(css="[name='username'][type='text']")  public WebElement userName;
	@FindBy(css="[name='password'][type='password']")  public WebElement passWord;
	@FindBy(xpath="//*[@id='dialog']/div/div[2]/div[2]/div/form/button")  public WebElement login;
	
	//login wiht G+
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > div > section > ul > li:nth-child(2) > button") public WebElement googleButton;
	
	//GROWLS
	@FindBy(css=".growls") public WebElement growls;
	
	//for doSkip()
	@FindBy(css="#player > div.player-center > div.player-controls > button.btn.text.no-border.xsmall > i") public WebElement icon_skip;
	@FindBy(css=".icon-skip") public WebElement icon_skip2;
	//Moved from liveRadioPage. 
	@FindBy(css="#player > div.player-center > div > button.btn.text.no-border.xsmall > span") public WebElement icon_scan;
	@FindBy(css=".player-song") public WebElement player_song;
	
	//player buttons
	
	@FindBy(id="player") public WebElement player;
	@FindBy(css="button.idle:nth-child(3)") public WebElement button_playing;
	
	@FindBy(css="button.playing:nth-child(3)") public WebElement button_pause;
	
	@FindBy(css=".icon-play") public WebElement icon_play;
	@FindBy(css=".icon-stop") public WebElement icon_stop;
	@FindBy(css=".icon-pause") public WebElement icon_pause;
	@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(2) > span:nth-child(3)") 
		public WebElement listenHistory;
	@FindBy(css="#player > div.player-right.ui-on-dark > button:nth-child(1) > span:nth-child(3)")
	    public WebElement myStations;
	
	
	//@FindBy(css=".icon-more-horizontal") public WebElement icon_more_horizontal;
	@FindBy(css=".more") public WebElement icon_more_horizontal;
	
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


	//Add to favorite
	@FindBy(css=".favorite") protected WebElement favorite;
	@FindBy(css=".icon-favorite-filled") protected WebElement icon_fav_filled;
	
    //AD CONTAINER
	@FindBy(id="imaAdContainer") protected WebElement adContainer;
	
	
	//FACE BOOK Signup info
	public final String FACEBOOKemail = "iheartRadio.tribecca@gmail.com";
	public final String _PASSWORD = "iheart001";

   public static WebDriver driver;
   public static final String screenshot_folder="screenshots";
   public static StringBuffer errors = new StringBuffer(); 
   
   public static String browser = "";
   
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
		//check driver here
	    if (driver == null)  
	    	System.out.println("Driver is null!");
	    else 
	    	System.out.println("Driver is OK!");
	    	
	    int count = 0;
	    
		do{
			waitForElement(loginButton);
			loginButton.click();
			count++;
			
		}while (count < 6 && !driver.getPageSource().contains("Don't have an account?"));
		
		//WaitUtility.sleep(1500);
    	userName.sendKeys(FACEBOOKemail);
	    passWord.sendKeys(_PASSWORD);
	
		login.click();
		
		WaitUtility.sleep(1000);
	}
	
  
   
   private void waitForElement(WebElement element)
   {
	   WebDriverWait wait = new WebDriverWait(driver, 60);
	   wait.until(ExpectedConditions.elementToBeClickable(element));
	  // element.click();
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
		WebElement option =  driver.findElement(By.cssSelector("div.dropdown-trigger:nth-child(1) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")) ;
		System.out.println("checkFavInProfile().option_profile VIA DRIVER:" + option.getText());
		gotoSingedAccountOption(option,  "User");
		
		favoriteEpisodes.click();
		if (!driver.getPageSource().contains(name))
		   errors.append(name + " is not added in profile -> Favorite Episodes.");
	}
	
	
	public void doSkip()
	{
		String songPlaying = player_song.getText();
		System.out.println("It is playing:" + songPlaying);
		
		icon_skip.click();
		WaitUtility.sleep(6000);
		String nextSong = driver.findElement(By.className("player-song")).getText();
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
		WaitUtility.sleep(1000);
		String nextSong = driver.findElement(By.className("player-song")).getText();
		System.out.println("After skip:" + nextSong);
		
		if (nextSong.equalsIgnoreCase(songPlaying))
			errors.append("scan button is not working. ");
	}
	
	public void search(String what)
	{   searchBox.clear();
		WaitUtility.sleep(400);
		searchBox.sendKeys(what);
		List<WebElement> resultRows = driver.findElements(By.className("search-section"));
		System.out.println(resultRows.size() + " rows are suggested.");
		
		if (resultRows == null || resultRows.size() <1)
	    	errors.append("Search doesn't generate the right result.");
		
		searchBox.clear();
		WaitUtility.sleep(400);
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
		String _email = randomEmail_firstPart + "@gmail.com";
		System.out.println("See randomEmail:" + _email);
		WaitUtility.sleep(800);
	    email.sendKeys(_email);
	    password.sendKeys(_PASSWORD);
	    zipCode.sendKeys("10013");
	    new Select(birthYear).selectByVisibleText("1980");
	    gender_female.click();
	    termsAcceptanceDate.click();
	    signUp.click();
	    
	    WaitUtility.sleep(2000);
	    System.out.println("see signed account:" + signedAccount.getText() );
	    signedAccount.click();
	    
	    if (!signedAccount.getText().contains(randomEmail_firstPart))
	    	errors.append("Signup failed.");
	    
	}
	
	public void makeSureItIsPlaying()
	{   
	    try{

		 //   icon_play.isDisplayed();
	        icon_play.getAttribute("class");
		    System.out.println("Music is not playing. About to click.");
	
			icon_play.click();

	    }catch(Exception e)

	    {   System.out.println("Music is playing. ");
	    	return;
	    }
	    
	    handlePreRoll();
	    
	}
	
	//to be done
	public void handlePreRoll()
	{   
		 WaitUtility.sleep(35000);
	}
	
	public void handlePreRoll_obsolete()
	{   
		  int milliSeconds = 0;
		  //Wait for pre-roll if adContainer is detected
	      String duration = driver.findElement(By.cssSelector(".seconds")).getText();
		   System.out.println("duration:" + duration);
		   if (duration!= null && duration.length()>0)
		   {
			   milliSeconds = (Integer.parseInt(duration))*1000;
		       System.out.println("Pre-roll will last (:milliSeconds):" + milliSeconds );
		       WaitUtility.sleep(milliSeconds + 5000);
		   }else
		   {
			   System.out.println("No pre-roll is detected.");
		   }
		
	}
	
	public void handlePreRoll_OLD()
	{
		//Wait for pre-roll if adContainer is detected
		   try {
			   if (adContainer.isDisplayed() && adContainer.isEnabled())
			   {	
				   System.out.println("Pre-roll is taking over the planet. Wait...");
				   String duration = driver.findElement(By.cssSelector(".seconds")).getText();
				   System.out.println("duration:" + duration);
				   int milliSeconds = (Integer.parseInt(duration))*1000;
				   System.out.println("milliSeconds:" + milliSeconds );
				   WaitUtility.sleep(milliSeconds);
			   
			   }
		   }catch(Exception e)
		   {   e.printStackTrace();
			   System.out.println("No pre-roll is detected.");
		   }
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
	public void makeSureItIsNotPlaying()
	{   boolean isPlaying = true;

	    try{

		    icon_play.isDisplayed();
	
		    System.out.println("Music is not playing. Good");

	    }catch(Exception e)

	    {   System.out.println("Music is playing. click to stop it ");
	    	icon_play.click();
		
	    	//Wait for pre-roll
	
	    	WaitUtility.sleep(35000);

	    	return;

	    }
	}
	
	public static String getBrowser()
	{
		return browser;
	}
	
	public static void setBrowser(String _browser)
	{
		browser = _browser;
	}
	
	
	public void gotoExplorerOption(WebElement option, String expectedTitle)
	{   int count = 0;
		Actions action = new Actions(driver);
		do {
			action = action.moveToElement(explorer);
			//action = action.click(explorer);
			WaitUtility.sleep(500);
			try {
			   action.moveToElement(option).click().build().perform();
				
			}catch(Exception e)
			{
				
			}
			WaitUtility.sleep(1500);
			count++;
		}while(count < 5 && !driver.getTitle().contains(expectedTitle));	
		
		if (Page.getBrowser().equalsIgnoreCase("chrome"))
			WaitUtility.sleep(1500);
		else
			WaitUtility.sleep(500);
	}
	
	
	public void gotoSingedAccountOption(WebElement option, String expectedTitle)
	{  // limit try to 5 times
		int count = 0;
		
		Actions action = new Actions(driver);
		do {
				
			action = action.moveToElement(signedAccount);
			WaitUtility.sleep(1500);
			try{
		    	action.moveToElement(option).click().build().perform();
			}catch(Exception e)
			{
				
			}
			
			WaitUtility.sleep(500);
			System.out.println("See title:" + driver.getTitle());
			count++;
		
		}while(count <5 && !driver.getTitle().contains(expectedTitle));	
	}
	
	
	public void doThumbUp(String methodName)
	{  
	    WaitUtility.sleep(1000);
		//Sometimes the thumbUp button is disabled, keep scan(At most 10 times though to avoid hang) until thumbUpiCON is enabled.
		int count = 0; 
		while(!thumbUp_button.isEnabled() && count < 3)
		{	System.out.println("thumbUp button is disabled. Scan now..");
			icon_scan.click();
			count++;
			WaitUtility.sleep(2000);
		}
		//If this is thumbUp before, double-click
		try{
			if (thumbUpDone.isDisplayed())
				thumbUpDone.click(); 
		}catch(Exception e)
		{
			
		}
		thumbUp.click();
		WaitUtility.sleep(500);
		
		//check to make sure that thumpUp Icon is filled
		
	    if (!thumbUpDone.isDisplayed())
			handleError("Favorite icon is not highlighted.", methodName);
		
		
		String response = driver.findElement(By.className("growls")).getText();
		System.out.println("See growls:" + response);
		if (!(response.contains("Glad you like") || response.contains("Thanks for your feedback")))
			handleError("Thump Up is not working properly.", methodName);
	}
	
	public void doFavorite(String methodName)
	{
		 //If the chosen show/song is faved before, double click; 
	    try{
		   if (icon_favorite_filled.isDisplayed())
		   {  
			   icon_favorite_filled.click();
		       //WaitUtility.sleep(1000);
			   WaitUtility.waitForAjax(driver);
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
	
	public boolean isSoftGateShow()
	{   WaitUtility.sleep(2000);
	    try{
	       System.out.println(signupHint.getText());
	    }catch(Exception e)
	    {
	       System.out.println("Soft gate is not shown.");
	       return false;
	    		   
	    }
		return signupHint.getText().contains("Have an account?");
	}
	
	
	public void verifyPlayer(String category)
	{  
		verifyPlayer(category, "verifyPlayer");
	}
	
	
	
	public void verifyPlayer(String category, String testMethod)
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
		    handleError("The " + category + " is not playing.", testMethod);
		
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
		faceBook.click();
		String winHandleBefore = switchWindow();
		
		faceEmail.sendKeys(FACEBOOKemail);
		facePass.sendKeys("iheart001");
		faceLogin.click();
	    
	    WaitUtility.sleep(2000);
	    
	    driver.switchTo().window(winHandleBefore);
	}
	
	
	public void shareOnfaceBook()
	{
		driver.findElement(By.className("facebook")).click();
		String winHandleBefore = switchWindow();
		
		faceEmail.sendKeys(FACEBOOKemail);
		facePass.sendKeys("iheart001");
		faceLogin.click();
	    WaitUtility.sleep(1000);
	    shareOnFacebook.click();
	    driver.switchTo().window(winHandleBefore);
	}
}
