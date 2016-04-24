package loginEasyops;

import homePage.HomePage;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilPackages.PropertyValExtractors;

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
		homePage=HomePage.getPage(driver, HomePage.class);
		driver2=BaseClasses.setup("User2");
		driver2.get(url);
		homePage2=HomePage.getPage(driver2, HomePage.class);
	}

	@Test(groups={"Sanity"})
	public void checkLoginBtnAvl(){
		homePage.verifyLoginButton();
		homePage.verifySinginButton();
	}

	@Test(groups={"Sanity"})
	public void checkProductBtnAvl(){
		homePage2.verifyProductBtn();
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
