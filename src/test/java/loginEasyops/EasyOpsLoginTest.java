package loginEasyops;

import homePage.HomePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilPackages.PropertyValExtractors;
import utilPackages.WaitClass;

import baseClassPackage.BaseClasses;



public class EasyOpsLoginTest{
	HomePage homePage,homePage2;
	WebDriver driver,driver2;
	
	@BeforeClass(alwaysRun=true)
	public void setup(){
		driver=BaseClasses.setup( "User1");
		PropertyValExtractors p=new PropertyValExtractors();
		p.getPropertyFile("test", "configuration.properties");
		String url=p.getVal("url");
		driver.get(url);
		WaitClass.waitForUrl(driver, url, 10);
		homePage=HomePage.getPage(driver, HomePage.class);
		driver2=BaseClasses.setup("User2");
		driver2.get(url);
		WaitClass.waitForUrl(driver2, url, 10);
		//BaseClasses.setSize(driver2, 446, 619);
		homePage2=HomePage.getPage(driver2, HomePage.class);
	}

	@Test(groups={"Sanity"},description="Check for LoginBtn is available or not")
	public void checkLoginBtnAvl(){
		homePage.verifyLoginButton();
		homePage.verifySinginButton();
		homePage.verifyMyntraBtn();
	}

	@Test(groups={"Sanity"},description="Check for Product button is available or not")
	public void checkProductBtnAvl(){
		homePage2.verifyProductBtn();
		homePage2.navigateBottom();
	}

	@Test()
	public void checkFeatureBtnAvl(){
		homePage.verifyFeatureBtn();
	}

	@Test
	public void checkIntegrationAvl(){
		homePage2.verifyIntegrationsBtn();
	}
}
