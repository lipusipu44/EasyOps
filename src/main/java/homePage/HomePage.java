package homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import utilPackages.JavaScriptExec;
import utilPackages.WaitClass;

import baseClassPackage.BasePage;

/**
 * @author Anilkumar.P
 *
 */

public class HomePage extends BasePage{
	/**
	 * This constructor is used to initialize the webdriver in BasePage class, if the user does not want to use page factory
	 * then this will take care of initialization of the driver
	 * 
	 * @param webdriver
	 */
	public HomePage(WebDriver webdriver) {
		super(webdriver);
	}

	Logger log=Logger.getLogger(HomePage.class);
	HomePage homePage;
	
	@FindBy(how=How.CSS,css="#navbar > ul > li:nth-child(1) > a")
	private WebElement loginButton;
	/**
	 * Method to verify Log in Button is available or not
	 */
	public void verifyLoginButton(){
		log.info("Verifying the login button is available or not");
		WaitClass.WaitForElementisDisplay(driver, 5, loginButton);
		Assert.assertTrue(loginButton.isDisplayed());
	}

	/**
	 * Method to verify if the Sign in Button is available or not
	 */
	public void verifySinginButton(){
		log.info("Verifying the Sign in button is available or not");
		Assert.assertTrue(driver.findElement(By.cssSelector("#navbar > ul > li:nth-child(2) > a")).isDisplayed());

	}
	
	@FindBy(how=How.CSS, css="ul#nav-contents> li:nth-child(1) >a")
	private WebElement btn_getProductLink;
	public void verifyProductBtn(){
		log.info("Verifying if Product tab is available or not");
		Assert.assertTrue(btn_getProductLink.getText().contains("Product"), "Assert Failed as its unable to search text in Product Button");
	}
	
	@FindBy(how=How.CSS, using="ul#nav-contents> li:nth-child(2) >a")
	private WebElement btn_getFeatureLink;
	public void verifyFeatureBtn(){
		log.info("Verify the Feature tab in the home page");
		Assert.assertTrue(btn_getFeatureLink.getText().contains("Feature"), "Assert is unable to match the String name of the feature");
	}
	
	@FindBy(how=How.CSS, using="ul#nav-contents> li:nth-child(3) >a")
	private WebElement btn_getIntegrationLink;
	public void verifyIntegrationsBtn(){
		log.info("Verify the Feature tab in the home page");
		Assert.assertTrue(btn_getIntegrationLink.getText().contains("Integrations"), "Assert is unable to match the String name of the Integrations");
	}
	
	@FindBy(how=How.CSS, using="img[alt='Facebook']")
	private WebElement btn_Facebook;
	public void verifyMyntraBtn(){
		log.info("Verify the Myntra tab at the bottom of home page");
		WaitClass.WaitForElementisDisplay(driver, 5, btn_Facebook);
		JavaScriptExec.scrollToElementOnPage(driver, btn_Facebook);
		WaitClass.sleep(5000);
	}
	
	public void navigateBottom(){
		JavaScriptExec.scrolltoBottomofPage(driver);
		WaitClass.sleep(5000);
	}
	
	
}
