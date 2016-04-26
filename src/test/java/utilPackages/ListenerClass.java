package utilPackages;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import baseClassPackage.BaseClasses;

/**
 * @author Anilkumar.P
 *This class is used to implement TestNg methods
 */
public class ListenerClass  implements ISuiteListener,ITestListener,IInvokedMethodListener{
	Logger log=Logger.getLogger(ListenerClass.class);
	public void onFinish(ISuite arg0) {
		log.info("########"+"Ending the TestSuite:"+arg0.getName()+"#######");
		log.info("##### Ending desktop recording at the beginning of the test suite execution#####");
		stopRecording();
	}

	public void onStart(ISuite arg0) {
		log.info("########"+"Starting the TestSuite:"+arg0.getName()+"#######");
		log.info("########Creating current folder for screenshot and log file holding#######");
		createOutputHolder();
		log.info("##### Starting desktop recording at the beginning of the test suite execution#####");
		startRecording();

	}

	public void onFinish(ITestContext arg0) {
		log.info("########"+"Ending the Test series in the Test Suite:"+arg0.getName()+"#######");
		log.info("########"+"Ending the Browser"+"#######");
		BaseClasses.closeAllDriver();

	}

	public void onStart(ITestContext arg0) {
		log.info("########"+"Starting the Test series in the Test Suite:"+arg0.getName()+"#######");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		log.info("########"+"Starting the Test Method in the Test class:"+arg0.getMethod().getMethodName()+"#######");
	}

	public void onTestSuccess(ITestResult arg0) {
		log.info("########"+"Succeeded the Test Method:"+arg0.getName().trim()+"#######");
		try {
			getscreenshot(BaseClasses.driver,arg0.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		log.info("########"+"Invoked the Test Method:"+arg0.getTestMethod().getMethodName()+"#######");
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		log.info("########"+"Invoking the Test Method:"+arg0.getTestMethod().getMethodName()+"#######");
	}

	/**
	 * This method is to create a folder which will contain screenshots and log files, it will change in each iteration run, so wil be used
	 * with on Suite call method in this listener class
	 */
	public static void createOutputHolder(){
		Date date=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		File file=new File(System.getProperty("user.dir")+"//Result//current");
		File fileRename=new File(System.getProperty("user.dir")+"//Result//"+dateFormat.format(date));
		if(file.exists()){
			file.renameTo(fileRename);
			file=new File(System.getProperty("user.dir")+"//Result//current");
			file.mkdir();
		}
		else{
			file.mkdir();
			file.mkdirs();
		}
	}

	/**
	 * @param driver
	 * @param methodName
	 * @throws Exception
	 * This method id used to take screenshot in the form of methodname.png format
	 */
	public static void getscreenshot(WebDriver driver,String methodName) throws Exception 
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//Result//current//"+methodName+".png"));
	}

	/**
	 * This method is used to start recording of desktop 
	 * using monte
	 */
	public void startRecording(){
		try {
			DesktopRecorder.startRecording();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to call the static method of desktop recording class which uses monte 
	 * class for desktop recording
	 */
	public void stopRecording(){
		try {
			DesktopRecorder.stopRecording();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
