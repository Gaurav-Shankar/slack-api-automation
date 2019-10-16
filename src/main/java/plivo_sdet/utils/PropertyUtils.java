package plivo_sdet.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {
	
	Properties properties;

	public Properties returnPropertyValue() {
		properties = new Properties();
		try {
			FileReader fileReader = new FileReader("src/test/resources/project.properties");
			properties.load(fileReader);
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getBaseUrl() {
		properties = new Properties();
		return returnPropertyValue().getProperty("base-url");
	}
	
	public String getContentType() {
		return returnPropertyValue().getProperty("Content-Type");
	}
	
	public String getAuthToken() {
		return returnPropertyValue().getProperty("token");
	}
}
