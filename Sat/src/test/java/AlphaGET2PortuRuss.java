
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlphaGET2PortuRuss {

	public static WebDriver driver;
	public static AlphaGET2PortuRuss b;
	public static WebElement loader;
	public int pages;
	public static  JavascriptExecutor js;
	public static WebDriverWait wait;
	public static int module;
	public static int modulen;
	public static int moduleCount;

	
	public static String spath;

public void inputs() throws InterruptedException {
	boolean n=true;
	b=new AlphaGET2PortuRuss();
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the Details to Go....");
		
		int mod=1;
		int scr=1;
		
		do {
		System.out.println("Enter the server name VS VP CS or Online: ");
		String server=sc.nextLine();
		if(server.equalsIgnoreCase("VS")) {
			System.out.println("Enter the URL ID: ");
			String id=sc.nextLine();
			int uId=Integer.parseInt(id);
			System.out.println("Enter the course name: ");
			String m=sc.nextLine();
			spath=System.getProperty("user.dir")+"\\screenshots\\VS\\"+m;
			System.out.println("Enter the module to launch: ");
			String i=sc.nextLine();
			 mod=Integer.parseInt(i);
			System.out.println("Enter the screen to go: ");
			String s=sc.nextLine();	
			 scr=Integer.parseInt(s);
			b.base();
			b.VenuStaging(uId, m);
		}else if(server.equalsIgnoreCase("VP")) {
			System.out.println("Enter the URL ID: ");
			String id=sc.nextLine();
			int uId=Integer.parseInt(id);
			System.out.println("Enter the course name: ");
			String m=sc.nextLine();	
			spath=System.getProperty("user.dir")+"\\screenshots\\VP\\"+m;
			System.out.println("Enter the module to launch: ");
			String i=sc.nextLine();
			 mod=Integer.parseInt(i);
			System.out.println("Enter the screen to go: ");
			String s=sc.nextLine();	
			 scr=Integer.parseInt(s);
			b.base();
			b.VenuProd(uId,m);
			
		}else if(server.equalsIgnoreCase("CS")) {
			System.out.println("Enter the Course name: ");
			String m=sc.nextLine();	
			spath=System.getProperty("user.dir")+"\\screenshots\\CS\\"+m;
			System.out.println("Enter the module to launch: ");
			String id=sc.nextLine();
			 mod=Integer.parseInt(id);
			System.out.println("Enter the screen to go: ");
			String s=sc.nextLine();	
			 scr=Integer.parseInt(s);
			b.base();
			b.ConvergenceLogin(m);
		}else if(server.equalsIgnoreCase("Online")) {
			System.out.println("Enter the onlink link: ");
			String m=sc.nextLine();	
			System.out.println("Enter the course name: ");
			String a=sc.nextLine();	
			spath=System.getProperty("user.dir")+"\\screenshots\\Online\\"+a;

			System.out.println("Enter the module to launch: ");
			String id=sc.nextLine();
			 mod=Integer.parseInt(id);
			System.out.println("Enter the screen to go: ");
			String s=sc.nextLine();	
			 scr=Integer.parseInt(s);
			b.base();
			LaunchOnline(m);
		}else{
			System.out.println("Enterd server name is incorrect please chese again!!!!");
			n=false;
		}
		
		}while(n==true);
		
		
	Thread.sleep(4000);
	
	int mc=b.modCount();
	
	
	b.LaunchModule(mod, scr);
	b.Loader();
	//int pc=b.pagecount();
	//Thread.sleep(40000);
	System.out.println("Module COunt :"+mc );
	
	
	
}


   
	public static void main(String[] args) throws InterruptedException, IOException {
	b=new AlphaGET2PortuRuss();
		
		b.inputs();
		//System.out.println("Module COunt :"+pc );
		System.out.println("currentscreen()"+currentscreen());
				System.out.println("totalscreens()"+totalscreens());
		b.pageLoopCompleteCourse();
      
	}
	
	public void	VenuStaging(int URLID, String Cname) throws InterruptedException {
		driver.get("https://wabtecdev:wabtecdev@staging45.wabtecuniversity.com/lms/course/view.php?id="+URLID);
		WebElement element = driver.findElement(By.id("login-myBtn"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		
		driver.findElement(By.id("login_username")).sendKeys("Lisho_origin");
		driver.findElement(By.id("password")).sendKeys("changeME2021");
		driver.findElement(By.id("submitbutton")).click();
		//Lisho_origin
		//changeME2021
		driver.findElement(By.xpath("//*[@id='support']/div[1]/input[3]")).click();
		driver.findElement(By.xpath("//*[@id=\"course-view\"]/div[2]/div[1]/div[3]/div/div[3]/form[2]/button")).click();
		

		try {
		driver.findElement(By.linkText(Cname)).click();
		} catch (Exception e) {
			Scanner sc=new Scanner(System.in);
			System.out.println("Please enter the course name again: ");
			String m=sc.nextLine();
			driver.findElement(By.linkText(m)).click();
		}
		
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		Thread.sleep(5000);
		driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[3]")).click();
		
		Thread.sleep(5000);
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		try {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[contains(@class,'BookmarkPop_close')]")).click();
		}catch (Exception e) {
			
		}
	}
	
	
	public void ConvergenceLogin(String Cname) throws InterruptedException {
			
			driver.get("https://www.wabteclearning.com/MyLogin");
			driver.findElement(By.id("ctl00_cph_content_Username")).sendKeys("bharath.s@originlearning.com");
			driver.findElement(By.id("ctl00_cph_content_Password")).sendKeys("Pa55word");
			driver.findElement(By.id("ctl00_cph_content_btnLogin")).click();
			
			try {
			driver.findElement(By.xpath("//*[@id=\"ctl00_ctl00_cph_header_right_rtsMain\"]/div/ul/li[2]/a")).click();
			driver.findElement(By.xpath("//*[@id=\"ttp\"]/div[6]/div[2]/div[1]/div[4]/input")).sendKeys(Cname);
						Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"ctGridScroll\"]/div[2]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div")).click();
						} catch (Exception e) 
			{
				
							Scanner sc=new Scanner(System.in);
							System.out.println("Please enter the course name again: ");
							String m=sc.nextLine();			
				driver.findElement(By.xpath("//*[@id=\"ctl00_ctl00_cph_header_right_rtsMain\"]/div/ul/li[2]/a")).click();
				driver.findElement(By.xpath("//*[@id=\"ttp\"]/div[6]/div[2]/div[1]/div[4]/input")).sendKeys(Cname);
										Thread.sleep(5000);
				driver.findElement(By.xpath("//*[@id=\"ctGridScroll\"]/div[2]/div[2]/div[2]/div[2]/div[1]/div[3]/div[1]/div")).click();
				
				
			}
		
		
			driver.switchTo().frame(driver.findElement(By.id("ctl00_cph_body_ifrmContent")));
			driver.switchTo().frame(driver.findElement(By.id("ifrmContent")));
			driver.findElement(By.cssSelector("div.introVideo > div > div.continue_btn.modulecontinue_clkbtn")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().defaultContent();
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		    try {
				Thread.sleep(5000);
				driver.findElement(By.xpath("//*[contains(@class,'BookmarkPop_close')]")).click();
				}catch (Exception e) {
					
				}
		
		
		}
	
	public static void screenshotfull(String path) throws IOException {


		// screenshots
		// ----screenshot naming---
		Date d = new Date();
		String date = d.toString();
		String screenshotName = date.replace(":", "_").replace(" ", "_");

		// Entire screen screenshot
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File(path +".jpg"));
	}
	
	public static int totalscreens() {
		
		String s=driver.findElement(By.xpath("//*[@class='pgNum mobile_hdr_none']")).getText();
		int slash=s.indexOf("/");
		
		String check=s.substring(slash+1).trim();
		int count=Integer.parseInt(check);
		
		return count;
		
		
	}
    public static int currentscreen() {
		
		String s=driver.findElement(By.xpath("//*[@class='pgNum mobile_hdr_none']")).getText();
		int slash=s.indexOf("/");
		int colon=s.indexOf(":");
		String check=s.substring(colon+1, slash-1).trim();
		int count=Integer.parseInt(check);
		
		return count;
		
		
	}
	
	public void base() throws InterruptedException {

		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		driver = new ChromeDriver(options); 
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//settings-ui")).sendKeys(Keys.ENTER);
		
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			WebDriverWait wait=new WebDriverWait(driver, 120);
			driver.manage().window().maximize();
		}
	
	public void VenuProd(int URLID, String Cname) throws InterruptedException {
		driver.get("https://www.wabtecuniversity.com/lms/course/view.php?id="+URLID);
		driver.findElement(By.id("login-myBtn")).click();
		driver.findElement(By.id("login_username")).sendKeys("lisho_origin");
		driver.findElement(By.id("password")).sendKeys("lisho@1234");
		driver.findElement(By.id("submitbutton")).click();
		driver.findElement(By.className("btn-primary")).click();
		
		try {
			driver.findElement(By.linkText(Cname)).click();
			} catch (Exception e) {
				Scanner sc=new Scanner(System.in);
				System.out.println("Please enter the course name again: ");
				String m=sc.nextLine();
				driver.findElement(By.linkText(m)).click();
			}
		//click continue button
		//driver.findElement(By.xpath("//*[@id='m2909']/button")).click();
			String winHandleBefore = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			Thread.sleep(5000);
			driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[3]")).click();
			
			Thread.sleep(5000);
			for(String winHandle : driver.getWindowHandles()){
			    driver.switchTo().window(winHandle);
			}
			try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[contains(@class,'BookmarkPop_close')]")).click();
			}catch (Exception e) {
				
			}

	}

	public void LaunchOnline(String onlineLink) throws InterruptedException{
		//driver.get("https://originlearning.in/Wabtec_HTML5/2021/v228_DE_Basic/index.html");
		driver.get(onlineLink);
		//driver.manage().window().
		
		//driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/div[3]")).click();
		driver.findElement(By.xpath("//*[@class='continue_btn modulecontinue_clkbtn']")).click();
		String winHandleBefore = driver.getWindowHandle();
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
	}
	
	
	public void LaunchModule(int mod, int page) throws InterruptedException{

		
		moduleCount=b.modCount();
		module=mod;
		for(int i=1;i<=mod-3;i++) {
			
			driver.findElement(By.className("srch_right")).findElement(By.id("rightMenuBtn")).click(); 
			 
			}
		//Thread.sleep(1000);
		driver.findElement(By.className("srch_right")).findElement(By.className("module_"+mod)).click();
		Thread.sleep(10000);
		b.Loader();
		if(page>7){
			
			
			driver.findElement(By.xpath("//*[@class='menu_icon main_menu_click']")).click();
		
	
	boolean c=false;
	int i=5;
	do {
	WebElement scroll=driver.findElement(By.xpath("//*[@id='mCSB_6_scrollbar_vertical']/div"));
	 Actions act = new Actions(driver);               //Object of <em>Actions</em> class
	 WebElement source=driver.findElement(By.xpath("//*[@id='mCSB_6_dragger_vertical']"));
	act.moveToElement(scroll).dragAndDropBy(source, 0,i).build().perform(); //Page Down  
	
	c=driver.findElement(By.xpath("//*[@id=\"mCSB_6_container\"]/div/li["+page+"]")).isDisplayed();
	i=i+10;
	System.out.println("scroll "+c+  "  i "+i);
	}while(c==false);	
			
		driver.findElement(By.xpath("//*[@id=\"mCSB_6_container\"]/div/li["+page+"]")).click();
		}else if((page==1)||(page==0)){
		
			
		}else if((page<8)&&(page>1)) {
			
			
			driver.findElement(By.xpath("//*[@class='menu_icon main_menu_click']")).click();
		
		driver.findElement(By.xpath("//*[@id=\"mCSB_6_container\"]/div/li["+page+"]")).click();
		}
			
	}
	public int pagecount() throws InterruptedException {

		
		driver.findElement(By.xpath("//*[@class='menu_icon main_menu_click']")).click();
			WebElement pagecoun=driver.findElement(By.className("menu_items_div"));
			int pagecount = pagecoun.findElements(By.tagName("li")).size();
	
/*	boolean c=false;
	int i=5;
	do {
	WebElement scroll=driver.findElement(By.xpath("//*[@id='mCSB_6_scrollbar_vertical']/div"));
	 Actions act = new Actions(driver);               //Object of <em>Actions</em> class
	 WebElement source=driver.findElement(By.xpath("//*[@id='mCSB_6_dragger_vertical']"));
	act.moveToElement(scroll).dragAndDropBy(source, 0,i).build().perform(); //Page Down  
	
	c=driver.findElement(By.xpath("//*[@id=\"mCSB_6_container\"]/div/li[15]")).isDisplayed();
	i=i+10;
	System.out.println("scroll "+c+  "  i "+i);
	}while(c==false);	
			
		driver.findElement(By.xpath("//*[@id=\"mCSB_6_container\"]/div/li[15]")).click();	
			*/
			return pagecount;
		
		
		
	}
	public int modCount() {
		/*String Modulecounts=driver.findElement(By.className("srch_right")).findElement(By.xpath("//div/div[1]/div[3]")).getText();
		 char First=Modulecounts.charAt(0);
		//.findElement(By.xpath("//*[@class='inner_container index_inner_container srch_right']"))
			int modulesTotal=Character.getNumericValue(First); */
		
		int modulesTotal=0;
		try {
		
		modulesTotal=driver.findElements(By.xpath("//*[@class='owl-item']")).size();
	
		}catch(Exception e) {
		}
		
		//=mcount.findElements(By.tagName("div")).size();
		
		return modulesTotal+3;
		
	}
	public void Loader()
	{
	   
	    WebDriverWait wait=new WebDriverWait(driver, 120);
	    loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));
		wait.until(ExpectedConditions.invisibilityOf(loader));
	    
	    
	}
	public void VideoScreens() throws InterruptedException {
		//driver.manage().timeouts().pageLoadTimeout(100,TimeUnit.SECONDS);
		//driver.manage().timeouts().setScriptTimeout(100,TimeUnit.SECONDS);
		js = (JavascriptExecutor) driver;
		Thread.sleep(1000);
		 js.executeScript("document.getElementById('vidPlayer').play()");
			 Thread.sleep(2500);
			//Pause the video 
			 js.executeScript("document.getElementById('vidPlayer').pause()");
			// Perform next
			Thread.sleep(1000);
	}
	public void Dropdown() throws InterruptedException {
		//Thread.sleep(1000);
		// CYU 1st attempt
		int dropcount=0;
		boolean m=true;
 for (int i=1;m==true;i++) {
		
	 
	try {		
		
		//wait.withTimeout(10, TimeUnit.SECONDS);
		WebElement Dropdown=driver.findElement(By.xpath("//*[@id='dropdown_"+i+"']"));
	
		Dropdown.click();
		//Thread.sleep(1000);
		
		Dropdown.findElement(By.id("option_1")).click();
		
		m=driver.findElement(By.className("dropArrow")).isDisplayed();
		//Thread.sleep(1000);
		dropcount=i;
		//System.out.println("Element in the page is"+driver.findElements(By.id("option_1")).size()+"---"+driver.findElements(By.className("dropArrow")).size());
		}catch (Exception e) {
			m=false;
		}
		 
}
//click submmit
 driver.findElement(By.id("submit")).click();
		//Thread.sleep(500);
		//close the popup
		driver.findElement(By.xpath("//*[@id=\"page\"]/div/div[2]/div/div/div/h3/div/a/img")).click();
		// CYU 2st attempt
		
		 m=true; 
		 for (int i=1;i<=dropcount;i++) {
				
					
				//wait.withTimeout(4, TimeUnit.SECONDS);
				WebElement Dropdown=driver.findElement(By.xpath("//*[@id='dropdown_"+i+"']"));
			
				Dropdown.click();
				//Thread.sleep(1000);
				Dropdown.findElement(By.id("option_1")).click();
				m=driver.findElement(By.className("dropArrow")).isDisplayed();
				
				//Thread.sleep(1000);
				//System.out.println("Element in the page is"+driver.findElements(By.id("option_1")).size()+"---"+driver.findElements(By.className("dropArrow")).size());
				
				
				 
		}        
		 driver.findElement(By.id("submit")).click();
		//Thread.sleep(500);
		    driver.findElement(By.xpath("//*[@id=\"page\"]/div/div[2]/div/div/div/h3/div/a/img")).click();
		  
		    //Thread.sleep(500);
		
		 //driver.findElement(By.xpath("//*[@id=\"page\"]/div/div[1]/div[4]/a[3]")).click();
		 driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[3]")).click();
		 wait=new WebDriverWait(driver, 120);
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			wait.until(ExpectedConditions.invisibilityOf(loader));
		 js = (JavascriptExecutor) driver;
		 js .executeScript("document.getElementById('vidPlayer').play()");
		 Thread.sleep(3000);
		//Pause the video 
		 js .executeScript("document.getElementById('vidPlayer').pause()");
		 //Thread.sleep(2000);
		 // clickin return Button
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			wait.until(ExpectedConditions.invisibilityOf(loader));
		// driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div[1]/a")).click();
		 driver.findElement(By.className("sub_header")).findElement(By.className("returnTxt")).click();
		 b.Loader();
		// CYU 3st attempt
		// Thread.sleep(2000);
		 m=true; 
		 for (int i=1;i<=dropcount;i++) {
				
					
					
					WebElement Dropdown=driver.findElement(By.xpath("//*[@id='dropdown_"+i+"']"));
				
				Dropdown.click();
				//Thread.sleep(1000);
				Dropdown.findElement(By.id("option_1")).click();
				m=driver.findElement(By.className("dropArrow")).isDisplayed();
				//Thread.sleep(1000);
				//System.out.println("Element in the page is"+driver.findElements(By.id("option_1")).size()+"---"+driver.findElements(By.className("dropArrow")).size());
				
				
				 
		}
		 driver.findElement(By.id("submit")).click();
		 
			//Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id=\"page\"]/div/div[2]/div/div/div/h3/div/a/img")).click();
			//Thread.sleep(1000);

	}
	public void MultipleChoice() throws InterruptedException {
		
		//Thread.sleep(1000);

		WebElement multi=driver.findElement(By.id("optbox_1"));
		
		//wait.withTimeout(10, TimeUnit.SECONDS);
		multi.click();
		// Thread.sleep(1000);
		
		driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
		//	Thread.sleep(1000);
			
			
			driver.findElement(By.className("pop_close")).click();
			//Thread.sleep(2000);
			boolean Display=b.ElementPresent(By.xpath("//*[@class=\"rightBtn fright right_arw_btn\"]"));
			System.out.println(Display);
				if (Display!=true) {
				 
			
			
				driver.findElement(By.id("optbox_1")).click();
				// Thread.sleep(1000);
			
			
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
				//Thread.sleep(1000);
				
				
				driver.findElement(By.className("pop_close")).click();
				//Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[3]")).click();
			WebDriverWait wait=new WebDriverWait(driver, 120);
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			wait.until(ExpectedConditions.invisibilityOf(loader));
			js = (JavascriptExecutor) driver;
			Thread.sleep(1000);
			 js .executeScript("document.getElementById('vidPlayer').play()");
			 Thread.sleep(3000);
			//Pause the video 
			 js .executeScript("document.getElementById('vidPlayer').pause()");
			// Thread.sleep(1000);
			 // clickin return Button
			 driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div[1]/a")).click();
			 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
				wait.until(ExpectedConditions.invisibilityOf(loader));
			 driver.findElement(By.id("optbox_1")).click();
			 //Thread.sleep(1000);
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			 //Thread.sleep(1000);
			 driver.findElement(By.className("pop_close")).click();
			 //Thread.sleep(1000);
						 
			}
			
	}
	public void hotspot() throws InterruptedException {
		
		driver.findElement(By.id("spot_1")).click();
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			driver.findElement(By.className("pop_close")).click();
		
		if(b.ElementPresent(By.className("spotActive"))!=true) {
		driver.findElement(By.id("spot_1")).click();
		
		driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
	
		driver.findElement(By.className("pop_close")).click();
		
		driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[3]")).click();
		WebDriverWait wait=new WebDriverWait(driver, 120);
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			wait.until(ExpectedConditions.invisibilityOf(loader));
		js = (JavascriptExecutor) driver;
		
		 js .executeScript("document.getElementById('vidPlayer').play()");
		 Thread.sleep(3000);
		//Pause the video 
		 js .executeScript("document.getElementById('vidPlayer').pause()");
		// Thread.sleep(1000);
		 // clickin return Button
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			
		 driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div[1]/a")).click();
		// Thread.sleep(1000);
			wait.until(ExpectedConditions.invisibilityOf(loader));
		 driver.findElement(By.id("spot_1")).click();
		 //Thread.sleep(1000);
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			//Thread.sleep(1000);
			driver.findElement(By.className("pop_close")).click();
			//Thread.sleep(1000);
		}
		
	}
	public void Sequence() throws InterruptedException {
		
		
		Actions sequence=new Actions(driver);
		WebElement from =driver.findElement(By.id("item1"));
		WebElement to =driver.findElement(By.id("item4"));
		sequence.dragAndDrop(from, to).build().perform();
		 from =driver.findElement(By.id("item2"));
		to =driver.findElement(By.id("item1"));
		sequence.dragAndDrop(from, to).build().perform();
		//driver.findElement(By.id("item1")).click();
		 //Thread.sleep(500);
		
		driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			//Thread.sleep(500);
			//System.out.println("excuted sc 1st attempt submited");
			driver.findElement(By.className("pop_close")).click();
			//Thread.sleep(500);
		try {
			from =driver.findElement(By.id("item1"));
		to =driver.findElement(By.id("item4"));
			sequence.dragAndDrop(from, to).build().perform();
		 Thread.sleep(1000);
		 from =driver.findElement(By.id("item2"));
			to =driver.findElement(By.id("item1"));
			sequence.dragAndDrop(from, to).build().perform();
		
		driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			//Thread.sleep(1000);
			
			driver.findElement(By.className("pop_close")).click();
			
			//Thread.sleep(3000);
			
			driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[3]")).click();
			WebDriverWait wait=new WebDriverWait(driver, 120);
			 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
				wait.until(ExpectedConditions.invisibilityOf(loader));
			js = (JavascriptExecutor) driver;
			//Thread.sleep(1000);
			 js .executeScript("document.getElementById('vidPlayer').play()");
			 Thread.sleep(3000);
			//Pause the video 
			 js .executeScript("document.getElementById('vidPlayer').pause()");
			 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
				wait.until(ExpectedConditions.invisibilityOf(loader));
			 //Thread.sleep(1000);
			 // clickin return Button
			 driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div[1]/a")).click();
			 Thread.sleep(2000);
			 from =driver.findElement(By.id("item1"));
				to =driver.findElement(By.id("item4"));
					sequence.dragAndDrop(from, to).build().perform();
					 from =driver.findElement(By.id("item2"));
						to =driver.findElement(By.id("item1"));
						sequence.dragAndDrop(from, to).build().perform();
			 //Thread.sleep(1000);
			
			 driver.findElement(By.xpath("//*[contains(@class,'text-center button_bdr_top')]/a[1]")).click();
			 //Thread.sleep(1000);
			 driver.findElement(By.className("pop_close")).click();
			// Thread.sleep(1000);
		}catch (Exception e) {
			
		}
	}
	public void Execute() throws InterruptedException, IOException {
		WebDriverWait wait=new WebDriverWait(driver, 120);
		b=new AlphaGET2PortuRuss();
		//Thread.sleep(1000);
		 loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
			wait.until(ExpectedConditions.invisibilityOf(loader));
		String title=driver.findElement(By.xpath("/html/body/div[1]/div[12]/div[1]/div/div[2]/div/h1")).getText().trim();
		//boolean video=b.ElementPresent(By.id("vidPlayer"));
boolean cyu=b.ElementPresent(By.xpath("//*[@class='rightBtn fright right_arw_btn deActiveBtn arw_btn_disabled']"));
		//	if (title.equals("Check Your Understanding"))
 if (cyu==true){
				driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		/*
			try {
				b.MultipleChoice();
				
				
			}catch (Exception m) {
				try {
					b.hotspot();
				
					
				}catch (Exception h) {
					try {
						b.Sequence();
						
						
					}catch (Exception s) {
						try{
							b.Dropdown();
																							
						}catch (Exception repeat) {
							System.out.println("checking");
						}
					}
				
			}
		}
		*/
				if(b.ElementPresent(By.id("optbox_1"))==true){
					b.MultipleChoice();
					//System.out.println("MultipleChoice");
				}else if(b.ElementPresent(By.id("spot_1"))==true) {
					b.hotspot();
					//System.out.println("Spot");
				}else if(b.ElementPresent(By.id("item1"))==true) {
					b.Sequence();
					//System.out.println("Sequence");
				}else if(b.ElementPresent(By.xpath("//*[@id='dropdown_1']"))==true) {
					b.Dropdown();
					//System.out.println("dropdown");
				}else {
				//	System.out.println("checking");
				}
				
				b.screenshotfull(spath+"CYU\\Module"+modulen+"\\");
			}else {

				b.VideoScreens();
				
			}
			}
	
	public void pageLoopCompleteCourse() throws InterruptedException, IOException {
		AlphaGET2PortuRuss b=new AlphaGET2PortuRuss();
		//int mod=b.modCount();
		WebDriverWait wait=new WebDriverWait(driver, 120);
		
		
		
		for (int i=module;i<=moduleCount;i++) {
			System.out.println("Let's Start Module "+i+" Completed");
			//for (int j=1;j<=screencount[i-1];j++)
			int total = totalscreens();
			int act= currentscreen();
			
			if(i==moduleCount){
				total=total-1;				
			}
			while(act<=total) {
				
				try{
					modulen=i;
					b.Execute();
					loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));  //id=preloader
					wait.until(ExpectedConditions.invisibilityOf(loader));
					AlphaGET2PortuRuss.screenshotfull(spath+"All\\module"+i+"\\Mod "+ i +" Screen "+currentscreen());
					//System.out.println("Try blocak");
					
					System.out.println(act+"   "+total);
					
						driver.findElement(By.id("nextBtn")).click();
					act=act+1;
					
				}catch(Exception e)
				{
					driver.navigate().refresh();
					
					//driver.findElement(By.linkText("Yes")).click();
					driver.findElement(By.xpath("//*[contains(@class,'BookmarkPop_clk')]")).click();
					System.out.println("This screen"+ currentscreen() +"have such error try to reloading ");
					b.Execute();
					//wait(Timeout.ofSeconds(20));
					loader=driver.findElement(By.xpath("//*[@id=\"preloader\"]"));
					wait.until(ExpectedConditions.invisibilityOf(loader));
					AlphaGET2PortuRuss.screenshotfull(spath+"All\\module"+i+"\\Mod "+ i +" Screen "+currentscreen());
					total = totalscreens();
					act=currentscreen();
					System.out.println(act+"   "+total);
					driver.findElement(By.id("nextBtn")).click();
					act=act+1;
				}
				
				b.Loader();
			}
			
			System.out.println("Module "+i+" successfully Completed");
			
		}
		
	}
    public boolean ElementPresent(By by) {
	driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
	try {
		//wait.withTimeout(2, TimeUnit.SECONDS);  //*[@class="text-center button_bdr_top button_bottom"]/a[2] //*[contains(@class,'text-center button_bdr_top button_bottom')]/a[2]
		driver.findElement(by);
		return true;
		
	}catch (Exception e) {
	
	return false;
	}
}


}