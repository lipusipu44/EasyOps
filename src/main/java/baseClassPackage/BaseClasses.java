package baseClassPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import utilPackages.PropertyValExtractors;

/**
 * @author Anilkumar.P
 *
 */
public class BaseClasses {
	public static WebDriver driver=null;
	static String browser,remoteWebDriverUrl;
	static Boolean useRemoteWebDriver;
	static PropertyValExtractors p;
	protected static Logger log=Logger.getLogger(BaseClasses.class);
	private static Map<String,WebDriver> driverHolder=new HashMap<String, WebDriver>();
	
	
	/**
	 * @param user
	 * @return
	 * Method to initiate driver with browser preference
	 */
	public static WebDriver setup(String user){
		
		browser=BaseClasses.browserPreference();
		useRemoteWebDriver=isRemoteMachineReq();
		
		if (useRemoteWebDriver) {
			
			log.info("####### Running the test case in remote machine #######");
			remoteWebDriverUrl = remoteUrlLocation();
			DesiredCapabilities capability = null;
			if(browser.equalsIgnoreCase("firefox")||browser.equalsIgnoreCase("ff")){

				capability = DesiredCapabilities.firefox();
				FirefoxProfile profile = new FirefoxProfile(); 
				profile.setAcceptUntrustedCertificates(true);
				/*if(environment.contains("preprod"))
					profile.setAssumeUntrustedCertificateIssuer(false);*/
				capability = DesiredCapabilities.firefox(); 
				capability.setCapability(FirefoxDriver.PROFILE, profile);

			}
			else if(browser.equalsIgnoreCase("internetexplorer")||browser.equalsIgnoreCase("ie")){

				capability = DesiredCapabilities.internetExplorer();
				capability.setJavascriptEnabled(true);
				capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capability.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
				capability.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
				capability.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
				capability.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
				//capability.setCapability(CapabilityType.SUPPORTS_BROWSER_CONNECTION, true);
				capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, true);
				capability.setCapability("ignoreZoomSetting", true);
				capability.setCapability("ignoreProtectedModeSettings" , true);

			}

			else if(browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("gc")){
				//PropertyValExtractors.exeSetup();
				capability = DesiredCapabilities.chrome();
			}

			else if(browser.equalsIgnoreCase("opera")){
				capability = DesiredCapabilities.opera();
			}

			try {
				if (driverHolder.get(user)==null) {
					driver=new RemoteWebDriver(new URL(remoteWebDriverUrl), capability);
					driver.manage().window().maximize();
					driverHolder.put(user, driver);
				} else {
					log.error("Already the driver with the same string exists so closing the execution");
				}

			} catch (MalformedURLException e) {
				log.error("Improper URL:"+remoteWebDriverUrl);
				e.printStackTrace();
			}



		}
		else{
			log.info("######### Not going to remote machine, it will be running on master machine ##########");
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
		p=new PropertyValExtractors();
		p.getPropertyFile("test",  "configuration.properties");
		browser=p.getVal("Browser");
		return browser;

	}

	/**
	 * This method has been designed to verify whether to use remote machine or not, This will return a true or false
	 * Property is mentioned in test/resource folder
	 * @return
	 */
	public static Boolean isRemoteMachineReq(){
		p=new PropertyValExtractors();
		p.getPropertyFile("test",  "configuration.properties");
		useRemoteWebDriver=Boolean.parseBoolean(p.getVal("useRemoteWebDriver"));
		return useRemoteWebDriver;

	}


	/**
	 * This is used to get the remoteURL for the gridification
	 * @return
	 */
	public static String remoteUrlLocation(){
		if(useRemoteWebDriver){
			remoteWebDriverUrl=p.getVal("remoteWebDriverUrl");
			return remoteWebDriverUrl;
		}
		else{
			log.error("Remote Driver is not required");
			throw new RuntimeException("No remote driver required");
		}

	}

	/**
	 * @param driver
	 * @param height
	 * @param width
	 * This is used to set the size of the browser according to use
	 */
	public static void setSize(WebDriver driver,int height, int width){
		log.info("Setting the new size of browser");
		Dimension dimension=new Dimension(width, height);
		driver.manage().window().setSize(dimension);

	}

}