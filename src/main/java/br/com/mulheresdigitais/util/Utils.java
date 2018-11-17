package br.com.mulheresdigitais.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateFormated(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		return format.format(date);
	}
}
