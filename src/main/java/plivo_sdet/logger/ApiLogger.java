package plivo_sdet.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ApiLogger extends FileAppender {

	private static Logger logger = null;

	static {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
			System.setProperty("current.date.time", dateFormat.format(new Date()));
			PropertyConfigurator.configure("src/test/resources/log4j.properties");
			logger = Logger.getLogger("plivo");
	}

	public static void logInfo(String msg) {
		logger.info(msg);
	}
	
	public static <T> void logInfo(T msg) {
		logger.info(msg);
	}
}
