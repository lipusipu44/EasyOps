package loginEasyops;

import homePage.HomePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import baseClassPackage.BaseClasses;



public class EasyOpsLoginTest{
	HomePage homePage;
	BaseClasses baseClass;
	WebDriver driver,driver2;
	@BeforeClass
	public void setup(){
		driver=BaseClasses.setup("gc", "User1");
		driver.get("http://www.easyops.in/");
		//driver2=BaseClasses.setup("gc", "User2");
		//driver2.get("http://www.easyops.in/");
	}

	@Test
	public void checkLoginBtnAvl(){
		//homePage=new HomePage(driver);
		homePage=HomePage.getPage(driver, HomePage.class);
		homePage.verifyLoginButton();
		homePage.verifySinginButton();
	}
}
