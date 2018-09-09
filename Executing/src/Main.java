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
	private WebDriver driver;
	private String login = "null";
	private String senha = "null";

	public static void main(String[] args) {

	}

	public void doIt() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\joao\\Downloads\\testes\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String id;
		String value;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("C:\\Users\\joao\\Downloads\\testes\\Untitled Project2.side"));
			String line;
			int i = 0;
			while ((line = reader.readLine()) != null) {
				// line = line.replace("\"", "�");
				System.out.println("linha: " + line);

				if (line.contains("url")) {
					int statIndex = line.indexOf("h");
					int endIndex = line.indexOf(",") - 1;
					id = line.substring(statIndex, endIndex);
					changePage(id);
					System.out.println("encontrou url: " + id);

				} else if (line.contains("\"command\": \"click\",")) {
					line = reader.readLine();
					int statIndex = line.indexOf("id=") + 3;
					int endIndex = line.indexOf(",") - 1;
					id = line.substring(statIndex, endIndex);
					click(id);
					System.out.println("clickou em: " + id);

				} else if (line.contains("\"command\": \"type\",")) {
					reader.mark(i);
					line = reader.readLine();
					while (line.contains("\"command\":") == false) {
						line = reader.readLine();
					}
					if (line.contains("login")) {
						reader.reset();
						line = reader.readLine();
						int semi = line.indexOf("id=");
						int statIndex = semi + 3;
						int endIndex = line.indexOf(",") - 1;
						id = line.substring(statIndex, endIndex);
						System.out.println("USOU LOGIIIIIIIIIIIIIIIIIIIIIIIIIIIIIN");
						type(id, login);

					} else if (line.contains("senha")) {
						reader.reset();
						line = reader.readLine();
						int semi = line.indexOf("id=");
						int statIndex = semi + 3;
						int endIndex = line.indexOf(",") - 1;
						id = line.substring(statIndex, endIndex);
						System.out.println("USOU SENHAAAAAAAAAAAAAAAAAAAAAA");
						type(id, senha);

					} else {
						reader.reset();
						line = reader.readLine();
						int semi = line.indexOf("id=");
						int statIndex = semi + 3;
						int endIndex = line.indexOf(",") - 1;
						id = line.substring(statIndex, endIndex);
						System.out.println("clickou em: " + id);

						for (int j = 0; j < 200; j++) {
							if (line.contains("\"value\":")) {
								System.out.println("line ta assim " + line);
								break;
							} else {
								line = reader.readLine();
							}
						}
						System.out.println("line ta assim " + line);
						statIndex = line.indexOf("\"value\": \"") + 10;
						int tam = line.length();
						tam = tam - 1;
						value = line.substring(statIndex, tam);
						value.replace("\"", "");
						System.out.println("digitou : " + value);
						type(id, value);
					}

				} else if (!line.contains("\"comment\": \"\",")) {
					line = reader.readLine();
					if (line.contains("\"command\": \"mouseOver\",")) {
						line = reader.readLine();
						int statIndex = line.indexOf("id=") + 3;
						int endIndex = line.indexOf(",") - 1;
						id = line.substring(statIndex, endIndex);
						moveMouse(id);
						System.out.println("moveu mouse sobre: " + id);
					}

				}
				i++;
			}
			reader.close();
			System.out.println("concluido");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void type(String id, String value) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		System.out.println("metodo de escrever esta escrevendo:   " + value);
		System.out.println("no campo : " + id);
		driver.findElement(By.id(id)).sendKeys(value);
	}

	private void changePage(String url) {
		driver.get(url);
	}

	private void click(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		WebElement element = driver.findElement(By.id(id));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();

		System.out.println("metodo de clicar, estaclicando em :   " + id);
		// driver.findElement(By.id(id)).click();
	}

	private void moveMouse(String id) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
		WebElement element = driver.findElement(By.id(id));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).moveToElement(element);
	}
}
