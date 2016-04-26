package utilPackages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Anilkumar.P
 *
 *This class wraps all the wait related stuffs
 */
public class WaitClass {

	/**
	 * Wrapper wait to verify for an element is visible, if it is found before given time then it 
	 * will come out of loop
	 * 
	 * @param driver
	 * @param time
	 * @param element
	 */
	public static void WaitForElementisDisplay(WebDriver driver, int time,WebElement element){

		int counter=time*2;

		while(counter>0){
			if(element.isDisplayed()){
				return;
			}
			else {
				WaitClass.sleep(500);
				counter--;
			}
		}
		
		throw new RuntimeException("Element is not visible for"+time+"secs");
	}

	/**
	 * Reverse of waitForElementDisplayed
	 * 
	 * @param driver
	 * @param time
	 * @param element
	 */
	public static void WaitForElementisnotVisible(WebDriver driver, int time,WebElement element){

		int counter=time*2;

		while(counter>0){
			if(!element.isDisplayed()){
				return;
			}
			else {
				WaitClass.sleep(500);
				counter--;
			}
		}
		throw new RuntimeException("Element is visible for"+time+"secs");
	}
	
	/**
	 * This method is to verify if the page URL contains the string or not
	 * 
	 * @param driver
	 * @param url
	 * @param time
	 */
	public static void waitForUrl(WebDriver driver,String url, int time){

		int counter=time*2;

		while(counter>0){
			if(driver.getCurrentUrl().contains(url)){
				return;
			}
			else {
				WaitClass.sleep(500);
				counter--;
			}
		}
		throw new RuntimeException("URL is improper for "+time+" secs");
	}


	public static void sleep(int timeOut) {
		try {
			Thread.sleep(timeOut);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
