package eksaqPack;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoEksaq {
	public static String url = "https://uat.eksaqonline.com";
	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void openURL() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		htmlreporter=new ExtentHtmlReporter("extent.html");
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
	}
	
	@Test(priority = 0)
	public void login() {
		test=extent.createTest("This is a test automation using TestNG by Hariharamanikandan K - testing Eksaq website");
		//System.out.println("opened");
		//driver.findElement(By.xpath("(//*[@href='https://uat.eksaqonline.com/index.php/about-us/'])[2]")).click();
		WebElement login= driver.findElement(By.xpath("(//*[@href='https://uat.eksaqonline.com/index.php/user-account'])[2]"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*Actions mouseact=new Actions(driver);
		mouseact.contextClick(login).build().perform();*/
		JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();", login);
        test.pass("clicked login");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement uTBox= driver.findElement(By.id("wdm_username"));
		//uTBox.click();
		uTBox.sendKeys("testorguat1@mailinator.com");
		test.pass("sent Uname");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.id("wdm_password")).sendKeys("Abcd@123");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("sent Pword");
		driver.findElement(By.name("wdm_login")).click();
		test.pass("logged in");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@Test(priority = 1)
	public void selectCourse() throws InterruptedException {
		driver.findElement(By.xpath("(//*[@href='https://uat.eksaqonline.com/index.php/all-programs'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("entered courses page");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[@href='https://uat.eksaqonline.com/index.php/product/rca-nagaram/'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("clicked on a course");
	}
	
	@Test(priority = 2)
	public void addingSandT() {
		driver.findElement(By.name("height_option")).sendKeys("1");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("entered number of students");
		driver.findElement(By.name("width_option")).sendKeys("1");
		test.pass("entered number of teachers");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.name("add-to-cart")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("Added to the cart");
	}
	
	@Test(priority = 3)
	public void cartActions() {
		driver.findElement(By.xpath("//*[@class='button wc-forward']")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("clicked the cart");
		JavascriptExecutor scroll=(JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,300)");
		scroll.executeScript("window.scrollBy(0,300)");
		System.out.println("scrolled down");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement cOut=driver.findElement(By.xpath("(//*[@href='https://uat.eksaqonline.com/index.php/checkout/'])[3]"));
		cOut.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("entered billing/checkout page");
	}
	
	@Test(priority = 4)
	public void billing() {
		WebElement fname= driver.findElement(By.id("billing_first_name"));
		fname.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the fname");
		fname.sendKeys("abcd");
		WebElement lname= driver.findElement(By.id("billing_last_name"));
		lname.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the lname");
		lname.sendKeys("xyz");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement add1=driver.findElement(By.id("billing_address_1"));
		add1.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the add1");
		add1.sendKeys("123 street");
		System.out.println("entered the add1");
		WebElement add2=driver.findElement(By.id("billing_address_2"));
		add2.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the add1");
		add2.sendKeys("qwerty");
		System.out.println("entered the add2");
		WebElement city=driver.findElement(By.id("billing_city"));
		city.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the city");
		city.sendKeys("Chennai");
		System.out.println("entered the city");
		WebElement state=driver.findElement(By.id("billing_state"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Select drop=new Select(state);
		drop.selectByIndex(24);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Selected state");
		WebElement zipc=driver.findElement(By.id("billing_postcode"));
		zipc.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the zipcode");
		zipc.sendKeys("600028");
		System.out.println("entered the zipcode");
		JavascriptExecutor scroll=(JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,200)");
		WebElement phn=driver.findElement(By.id("billing_phone"));
		phn.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the phn");
		phn.sendKeys("9876543210");
		System.out.println("entered the phn");
		WebElement eml=driver.findElement(By.id("billing_email"));
		eml.clear();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("cleared the emailid");
		eml.sendKeys("abcd@gmail.com");
		System.out.println("entered the emailid");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("entered the required billing details");
		scroll.executeScript("window.scrollBy(0,300)");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("scrolled down to payment method");
	}
	
	@Test(priority = 5)
	public void payment() throws InterruptedException {
		String cartPage=driver.getTitle();
		System.out.println("\n"+cartPage+"\n");
		WebElement payRButton=driver.findElement(By.id("payment_method_billdesk"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*payRButton.click();
		Actions mouseact=new Actions(driver);
		mouseact.contextClick(payRButton).build().perform();*/
		JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click();", payRButton);
        test.pass("selected the payment method");
		JavascriptExecutor scroll=(JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,200)");
		System.out.println("scrolled down to place order");
		WebElement pay=driver.findElement(By.id("place_order"));
		js.executeScript("arguments[0].click();", pay);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		test.pass("clicked the place order");
		Thread.sleep(6000);
//		String checkTitle=driver.getTitle();
		String paymentPage=driver.getTitle();
		System.out.println("\n"+paymentPage+"\n");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (paymentPage==cartPage) {
			Thread.sleep(6000);
			if(paymentPage=="BillDesk - All Your Payments. Single Location") {
				System.out.println("Payment page reached successfully!");
				test.pass("Payment page reached successfully!");
				//System.out.println("1."+paymentPage);
			}
			else {
				System.out.println("Try again!");
				test.pass("Try again!");
			}
		}
		/*else if (paymentPage!="BillDesk - All Your Payments. Single Location") {
			System.out.println("2."+paymentPage);
		}*/
		else {
			System.out.println("Payment page reached successfully!");
			test.pass("Payment page reached successfully!");
			//System.out.println("2."+paymentPage);
		}
		extent.flush();
		Thread.sleep(2000);
		//BillDesk - All Your Payments. Single Location
	}
	
	@AfterTest
	public void closing() {
		driver.quit();
	}

}
