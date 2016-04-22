package homePage;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

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
		Assert.assertTrue(loginButton.isDisplayed());
	}

	/**
	 * Method to verify if the Sign in Button is available or not
	 */
	public void verifySinginButton(){
		log.info("Verifying the Sign in button is available or not");
		Assert.assertTrue(driver.findElement(By.cssSelector("#navbar > ul > li:nth-child(2) > a")).isDisplayed());

	}
}
