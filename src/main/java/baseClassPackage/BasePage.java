package baseClassPackage;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import bsh.This;

public class BasePage {
	static Logger log=Logger.getLogger(BasePage.class);
	public WebDriver driver;
	
	public BasePage(WebDriver webdriver){
		this.driver = webdriver;
	}
	
	
	/**
	 * Used for Page factory and the page initialization will be called from Test classes. They will pass the
	 * driver and from this class the driver will be passed to the Page classes, if page factory is not used for 
	 * a method then driver will be initialized in this class constrcutor and it will be passed to the page classes
	 * 
	 * @param driver
	 * @param Page
	 * @return
	 */
	public static <T> T getPage (WebDriver driver,Class<T> Page){
		log.info("####### Initializing the Pagefactory Model Base class #####");
		return PageFactory.initElements(driver, Page);
	}

}
