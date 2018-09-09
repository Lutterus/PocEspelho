
import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	private static WebDriver driver;
	private static String login = "";
	private static String senha = "";
	private static int waitTime = 5;

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\statsTest.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("linha: " + line);

				if (line.contentEquals("url")) {
					line = reader.readLine();
					System.out.println("linha: " + line);
					changePage(line);

				} else if (line.contentEquals("click")) {
					String id;
					line = reader.readLine();
					System.out.println("linha: " + line);
					id = line;
					line = reader.readLine();
					System.out.println("linha: " + line);
					click(id, line);

				} else if (line.contentEquals("type")) {
					String id, value, xpath;
					line = reader.readLine();
					id = line;
					System.out.println("linha: " + line);
					line = reader.readLine();
					xpath = line;
					System.out.println("linha: " + line);
					line = reader.readLine();
					if (line.contentEquals("LOGIN") | line.contentEquals("PASSWORD")) {
						if(line.contentEquals("LOGIN")) {
							complexType(id, "LOGIN", xpath);
						}else {
							complexType(id, "PASSWORD", xpath);
						}
						
					} else {
						value = line;
						System.out.println("linha: " + line);
						simpleType(id, value, xpath);
					}
				} else if (line.contentEquals("moveMouse")) {
					String id, xpath;
					line = reader.readLine();
					System.out.println("linha: " + line);
					id = line;
					xpath = reader.readLine();					
					System.out.println("linha: " + line);
					moveMouse(id, xpath);
				}
			}
			reader.close();
			System.out.println("concluido");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void complexType(String id, String type, String xpath) {
		try {
			if(type.contentEquals("LOGIN")) {
				WebDriverWait wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
				driver.findElement(By.id(id)).sendKeys(login);
			}else {
				WebDriverWait wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
				driver.findElement(By.id(id)).sendKeys(senha);
			}
		}catch (Exception e) {
			if(type.contentEquals("LOGIN")) {
				WebDriverWait wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				driver.findElement(By.id(id)).sendKeys(login);
			}else {
				WebDriverWait wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				driver.findElement(By.xpath(xpath)).sendKeys(senha);
			}
		}
	}

	private static void simpleType(String id, String value, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			driver.findElement(By.id(id)).sendKeys(value);
		}catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(id)));
			driver.findElement(By.id(id)).sendKeys(value);
		}
		
	}

	private static void changePage(String url) {
		driver.get(url);
	}

	private static void click(String id, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			WebElement element = driver.findElement(By.id(id));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();
		}catch (Exception e) {
			try {
				System.out.println("nao conseguiu");
				WebDriverWait wait = new WebDriverWait(driver, waitTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				WebElement element = driver.findElement(By.xpath(xpath));
				Actions actions = new Actions(driver);
				actions.moveToElement(element).click().build().perform();
			}catch (Exception e2) {
				System.out.println("nao conseguiu de novo");
			}
			
		}
		
	}

	private static void moveMouse(String id, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
			WebElement element = driver.findElement(By.id(id));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).moveToElement(element);
		}catch (Exception e) {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement element = driver.findElement(By.xpath(xpath));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).moveToElement(element);
		}
		
	}
}
