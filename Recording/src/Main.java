import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Main {
	public static void main(String[] args) {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\joao\\Downloads\\testes\\chromedriver.exe");
			WebDriver driver = null;
			driver = null;
			ChromeOptions chromeoptions = new ChromeOptions();
			chromeoptions.addExtensions(new File("C:\\Users\\joao\\Downloads\\testes\\Selenium-IDE_v3.3.0.crx"));
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
