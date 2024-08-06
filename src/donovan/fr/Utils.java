package donovan.fr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
	public static Scanner scanner = new Scanner(System.in);

	public static LocalDate stringToLocalDate(String dateString) {
		return stringToLocalDate(dateString, "dd/MM/yyyy");
	}

	public static LocalDate stringToLocalDate(String dateString, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		formatter = formatter.withLocale( Locale.FRANCE );
		LocalDate date = LocalDate.parse(dateString, formatter);
		return date;
	}

	public static LocalDate userWriteADate(String question) {
//		boolean isDateGoodFormat = false;
		LocalDate localDate = null;
		do {
			try {
				localDate = Utils.stringToLocalDate(Utils.questionScanner(question));
				return localDate;
			} catch (Exception e) {
				System.err.println("la date a ete mal entré.");
			}
		} while (true);
//		return localDate;
	}
	
	public static String questionScanner(String question) {
		System.out.println(question);
		return scanner.nextLine();
	}
	
	public static Long questionScannerLong(String question) {
		System.out.println(question);
		String input = scanner.nextLine();
		return Long.parseLong(input);
	}

	public static Float questionScannerFloat(String question) {
		System.out.println(question);
		String input = scanner.nextLine();
		return Float.parseFloat(input);
	}
}
