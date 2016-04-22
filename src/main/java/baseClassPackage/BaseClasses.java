package baseClassPackage;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
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
	String browser;
	protected static Logger log=Logger.getLogger(BaseClasses.class);
	private static Map<String,WebDriver> driverHolder=new HashMap<String, WebDriver>();
	/**
	 * @param browser
	 * Method used to call the driver according to use
	 */
	public static WebDriver setup(String browser, String user){

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

}