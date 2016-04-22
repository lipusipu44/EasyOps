package samplePackage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilPackages.PropertyValExtractors;

public class TryClass {
	
	
	/**
	 * @param str
	 */
	public static void main(String... str){
		
		/*PropertyValExtractor p=new PropertyValExtractor();
		p.getPropertyFile("abc",  "configuration.properties");
		System.out.println(p.getVal("url"));
		*/
		
	/*	PropertyValExtractor.exeSetup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.google.com");
		try {
			driver.manage().wait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
*/
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		System.out.println(dateFormat.format(date));
		File file=new File(System.getProperty("user.dir")+"//Result//current");
		File fileRename=new File(System.getProperty("user.dir")+"//Result//"+dateFormat.format(date));
		if(file.exists()){
			System.out.println("File exists");
			file.renameTo(fileRename);
			file=new File(System.getProperty("user.dir")+"//Result//current");
			file.mkdir();
		}
		else{
			file.mkdir();
			//File resFile=new File(System.getProperty("user.dir")+"Result//current");
			file.mkdirs();
		}
	}
	
	

}
