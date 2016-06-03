package baseClassPackage;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilPackages.PropertyValExtractors;

/**
 * @author Anilkumar.P
 *
 */
public class BaseClasses {
	public static WebDriver driver=null;
	static String browser;
	protected static Logger log=Logger.getLogger(BaseClasses.class);
	private static Map<String,WebDriver> driverHolder=new HashMap<String, WebDriver>();
	
	/**
	 * @param user
	 * @return
	 * Method to initiate driver with browser preference
	 */
	public static WebDriver setup(String user){
		browser=BaseClasses.browserPreference();
		if(browser.equalsIgnoreCase("firefox")||browser.equalsIgnoreCase("ff")){
			if(driverHolder.get(user)==null){
				log.info("Firefox Driver is selected");
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				driverHolder.put(user, driver);

			}
			else {
				log.error("Already the driver with the same string exists so closing the execution");
			}
		}
		else if (browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("gc")) {
			if(driverHolder.get(user)==null){
				log.info("Chrome Driver is selected");
				PropertyValExtractors.exeSetup();
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driverHolder.put(user, driver);

			}
			else {
				log.error("Already the driver with the same string exists so closing the execution");
			}
		}
		else {
			if(driverHolder.get(user)==null){
				log.info("Default driver as Firefox Driver is selected");
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				driverHolder.put(user, driver);
			}
			else {
				log.error("Already the driver with the same string exists so closing the execution");
			}
		}
		return driver;

	}

	public static void closeAllDriver() {
		log.info("###### Closing all the browser at the end of the execution #####");
		for (String key : driverHolder.keySet()) {
			driverHolder.get(key).close();
			driverHolder.get(key).quit();
		}
	}

	public static void closeDriver(String user) {

		for (String key : driverHolder.keySet()) {
			if(key.equalsIgnoreCase(user)){
				log.info("###### Closing "+user+" browser at the end of the execution #####");
				driverHolder.get(key).close();
				driverHolder.get(key).quit();

			}
			else {
				log.error("The given name of the user is not found so can't close");
				System.exit(0);
			}
		}
	}

	/**
	 * This mehthod is used for browser preference, it will fetch the data directly from config.
	 * property file in test/resource folder
	 * @return
	 */
	public static String browserPreference(){
		PropertyValExtractors p=new PropertyValExtractors();
		p.getPropertyFile("test",  "configuration.properties");
		browser=p.getVal("Browser");
		return browser;

	}
	
	public static void setSize(WebDriver driver,int height, int width){
		log.info("Setting the new size of browser");
		Dimension dimension=new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		
	}

}