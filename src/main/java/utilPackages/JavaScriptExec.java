package utilPackages;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Anilkumar.P
 * 
 * The purpose of the class to handle some cases which selenium does not
 * have any in-built methods
 *
 */
public class JavaScriptExec {
	
	public WebDriver driver;
	static Logger log=Logger.getLogger(JavaScriptExec.class);
	
	public JavaScriptExec(WebDriver driver){
		this.driver=driver;
	}
	
	/**
	 * This method is used to scroll down to the element if its not available in the page view and its
	 * somewhere down the page
	 * 
	 * @param driver
	 * @param element
	 */
	public static void scrollToElementOnPage(WebDriver driver,WebElement element){
		log.info("##### Scrolling down to find element######");
		JavascriptExecutor javascriptExecutor=(JavascriptExecutor)driver;
		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
		WaitClass.WaitForElementisDisplay(driver, 5, element);
	}
	
	public static void scrolltoBottomofPage(WebDriver driver){
		log.info("##### Navigating to the bottom of the page");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
