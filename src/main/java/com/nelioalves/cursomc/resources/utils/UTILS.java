package com.nelioalves.cursomc.resources.utils;

import java.math.BigDecimal;
import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class UTILS {

	public static String formatCEP(String value) {

		String pattern = "#####-###";

		MaskFormatter mf;
		try {
			mf = new MaskFormatter(pattern);
			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException ex) {
			return value;
		}
	}

	public static String formatFhone(String value) {

		String patternCEL = "#####-####";
		String patternFHONE = "####-####";

		MaskFormatter mf;
		try {

			if (value.length() < 7) {
				mf = new MaskFormatter(patternFHONE);
			} else {
				mf = new MaskFormatter(patternCEL);
			}

			mf.setValueContainsLiteralCharacters(false);
			return mf.valueToString(value);
		} catch (ParseException ex) {
			return value;
		}
	}

	public static BigDecimal round(double value) {

		BigDecimal bd = new BigDecimal(value);
		// bd.setScale(2, BigDecimal.ROUND_HALF_UP); bd.setScale does not change bd
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println(bd);

		return bd;

	}

	public UTILS() {
		// TODO Auto-generated constructor stub
	}

}
