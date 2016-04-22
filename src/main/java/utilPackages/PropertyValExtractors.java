package utilPackages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Anilkumar.P
 *
 */

//Class is developed to extract the value from property files
public class PropertyValExtractors {
	Properties property=new Properties();
	String env,filename,propName;
	FileInputStream freader;


	/**
	 * @param env
	 * @param filename
	 * Method used to get property file location
	 */
	public void getPropertyFile(String env, String filename){
		this.env=env;
		this.filename=filename;
		if(env.equalsIgnoreCase("test")){
			File propFile=new File(System.getProperty("user.dir")+"/src/test/resources/testingPropertyFiles");

			try {
				for(File dfile:propFile.listFiles()){
					if(dfile.getName().equalsIgnoreCase(filename))
						freader=new FileInputStream(dfile);
					property.load(freader);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		else if(env.equalsIgnoreCase("src")){
			File propFile=new File(System.getProperty("user.dir")+"/src/main/resources/propertyFilePackage");
			try {
				for(File dfile:propFile.listFiles()){
					if(dfile.getName().equalsIgnoreCase(filename))
						freader=new FileInputStream(dfile);
					property.load(freader);
				}
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			File propFile=new File(System.getProperty("user.dir")+"/src/test/resources/testingPropertyFiles");
			try {
				for(File dfile:propFile.listFiles()){
					if(dfile.getName().equalsIgnoreCase(filename))
						freader=new FileInputStream(dfile);
					property.load(freader);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	

		}

	}

	/**
	 * @param propName
	 * @return
	 * Method to return the asked property value
	 */
	public String getVal(String propName){
		return property.getProperty(propName);
	}

	/**
	 * Method to call the chromedriver exec, static method
	 */
	public static void exeSetup(){
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/executableFiles/chromedriver.exe");
	}
}
