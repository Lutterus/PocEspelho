import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Main {
	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\chromedriver.exe");
			WebDriver driver = null;
			driver = null;
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addExtensions(new File("C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\Selenium-IDE_v3.3.0.crx"));
			//use brower with user options
			chromeoptions.addArguments("user-data-dir=C:\\Users\\jfbit\\AppData\\Local\\Google\\Chrome\\User Data");
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeoptions);
			driver = new ChromeDriver(desiredCapabilities);
			
			int a=1;
			while(a==0) {
				System.out.println();driver.getPageSource();
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
