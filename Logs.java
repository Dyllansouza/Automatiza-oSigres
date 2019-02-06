import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logs {
	public static java.util.logging.Logger Logger() {
		Logger logger = Logger.getLogger("MyLog");
		FileHandler fh;

		try {

			// This block configure the logger with handler and formatter

			fh = new FileHandler("C:/Automatizacao/Log/LOG_" + Logs.Data() + ".txt");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			// the following statement is used to log any messages

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return logger;

	}

	public static String Data() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Calendar data = Calendar.getInstance();
		return sdf.format(data.getTime());
	}

}
