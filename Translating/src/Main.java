
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Main {
	private static ArrayList<String> login = new ArrayList<String>();
	private static ArrayList<String> senha = new ArrayList<String>();
	private static String result = "";
	private static String caminho = "C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\azul.side";
	private static Writer w;

	public static void main(String[] args) {
		
		try {
			File statText = new File("C:\\Projects\\pocEspelho\\pocEspelho\\fonte\\statsTest.txt");
			FileOutputStream is = new FileOutputStream(statText);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			w = new BufferedWriter(osw);
			reading();
			w.close();

		} catch (IOException e) {
			System.err.println("Problem writing to the file statsTest.txt");
		}

	}

	private static String checkValue(String s) {
		String aux = s;
		aux.replace(".", "");
		aux.replace("-", "");
		aux = s.replaceAll("[^a-zA-Z0-9]+","");
		for (String string : login) {
			if (string.contentEquals(aux)) {
				return "LOGIN";
			}
		}

		for (String string : senha) {
			if (string.contentEquals(aux)) {
				return "PASSWORD";
			}
		}

		return s;
	}

	private static void type(String id, String value, String idXpath) throws IOException {
		result = "type";
		w.write(result);
		((BufferedWriter) w).newLine();

		w.write(id);
		((BufferedWriter) w).newLine();
		
		w.write(idXpath);
		((BufferedWriter) w).newLine();

		result = checkValue(value);
		if (result == "LOGIN" | result == "PASSWORD") {
			w.write(result);
			((BufferedWriter) w).newLine();
		} else {
			w.write(result);
			((BufferedWriter) w).newLine();
		}

	}

	private static void changePage(String url) throws IOException {
		// driver.get(url);
		result = "url";
		w.write(result);
		((BufferedWriter) w).newLine();

		w.write(url);
		((BufferedWriter) w).newLine();
	}

	private static void click(String id, String idXpath) throws IOException {
		result = "click";
		w.write(result);
		((BufferedWriter) w).newLine();

		w.write(id);
		((BufferedWriter) w).newLine();

		w.write(idXpath);
		((BufferedWriter) w).newLine();
	}

	private static void moveMouse(String id, String xpath) throws IOException {
		result = "moveMouse";
		w.write(result);
		((BufferedWriter) w).newLine();

		w.write(id);
		((BufferedWriter) w).newLine();
		
		w.write(xpath);
		((BufferedWriter) w).newLine();
	}

	public static void reading() throws IOException {
		String id;
		String value;
		String idXpath;
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(caminho));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println("linha: " + line);
				// ["xpath=//input[@id='_smilesloginportlet_WAR_smilesloginportlet_password']"
				if (line.contains("url")) {
					int statIndex = line.indexOf("h");
					int endIndex = line.indexOf(",") - 1;
					id = line.substring(statIndex, endIndex);
					changePage(id);

				} else if (line.contains("\"command\": \"click\",")) {
					line = reader.readLine();
					System.out.println("linha: " + line);
					int statIndex = 0;
					int endIndex = 0;;
					if(line.contains("id=")) {
						statIndex = line.indexOf("id=") + 3;
						endIndex = line.indexOf(",") - 1;
					}else {
						statIndex = line.indexOf("\"target\": \"") + 11;
						endIndex = line.indexOf(",") - 1;
					}
					
					id = line.substring(statIndex, endIndex);
					for (int j = 0; j < 10; j++) {
						if (line.contains("xpath:position")) {
							System.out.println("linha: " + line);
							break;
						} else {
							line = reader.readLine();
							System.out.println("linha: " + line);
						}
					}
					statIndex = line.indexOf("[") + 8;
					endIndex = line.indexOf(",") - 1;
					idXpath = line.substring(statIndex, endIndex);
					click(id, idXpath);

				} else if (line.contains("\"command\": \"type\",")) {
					line = reader.readLine();
					System.out.println("linha: " + line);
					int statIndex = 0;
					int endIndex = 0;;
					if(line.contains("id=")) {
						statIndex = line.indexOf("id=") + 3;
						endIndex = line.indexOf(",") - 1;
					}else {
						statIndex = line.indexOf("\"target\": \"") + 11;
						endIndex = line.indexOf(",") - 1;
					}

					id = line.substring(statIndex, endIndex);
					for (int j = 0; j < 10; j++) {
						if (line.contains("xpath:position")) {
							System.out.println("linha: " + line);
							break;
						} else {
							line = reader.readLine();
							System.out.println("linha: " + line);
						}
					}
					statIndex = line.indexOf("[") + 8;
					endIndex = line.indexOf(",") - 1;
					idXpath = line.substring(statIndex, endIndex);

					for (int j = 0; j < 200; j++) {
						if (line.contains("\"value\":")) {
							System.out.println("linha: " + line);
							break;
						} else {
							line = reader.readLine();
							System.out.println("linha: " + line);
						}
					}
					statIndex = line.indexOf("\"value\": \"") + 10;
					int tam = line.length();
					tam = tam - 1;
					value = line.substring(statIndex, tam);
					value.replace("\"", "");
					type(id, value, idXpath);

				} else if (line.contains("\"command\": \"mouseOver\",")) {
					line = reader.readLine();
					System.out.println("linha: " + line);
					int statIndex = 0;
					int endIndex = 0;;
					if(line.contains("id=")) {
						statIndex = line.indexOf("id=") + 3;
						endIndex = line.indexOf(",") - 1;
					}else {
						statIndex = line.indexOf("\"target\": \"") + 11;
						endIndex = line.indexOf(",") - 1;
					}
					
					id = line.substring(statIndex, endIndex);
					for (int j = 0; j < 10; j++) {
						if (line.contains("xpath:position")) {
							System.out.println("linha: " + line);
							break;
						} else {
							line = reader.readLine();
							System.out.println("linha: " + line);
						}
					}
					statIndex = line.indexOf("[") + 8;
					endIndex = line.indexOf(",") - 1;
					idXpath = line.substring(statIndex, endIndex);
					moveMouse(id, idXpath);
				}
			}
			reader.close();
			System.out.println("concluido");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
