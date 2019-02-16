package com.sample.test;

import java.text.DecimalFormat;

/**
 * Number to Word Program
 *
 */
public class NumberToWord {
	private static final String[] tens = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy",
			" eighty", " ninety" };
	private static final String[] numbers = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private NumberToWord() {
	}

	private static String convertBelowThousand(int input) {

		String num;

		if (input % 100 < 20) {
			num = numbers[input % 100];
			input /= 100;
		} else {
			num = numbers[input % 10];
			input /= 10;

			num = tens[input % 10] + num;
			input /= 10;
		}
		if (input == 0)
			return num;

		return numbers[input] + " hundred" + num;
	}

	public static String convert(long number) {

		String output = "";

		try {

			// 0 to 999 999 999
			if (number == 0) {
				return "zero";
			}

			String snumber = Long.toString(number);

			// pad with "0"
			String mask = "000000000000";
			DecimalFormat df = new DecimalFormat(mask);
			snumber = df.format(number);

			// XXXnnnnnnnnn
			int billions = Integer.parseInt(snumber.substring(0, 3));
			// nnnXXXnnnnnn
			int millions = Integer.parseInt(snumber.substring(3, 6));
			// nnnnnnXXXnnn
			int hundredThousands = Integer.parseInt(snumber.substring(6, 9));
			// nnnnnnnnnXXX
			int thousands = Integer.parseInt(snumber.substring(9, 12));
			

			String tradBillions;
			switch (billions) {
			case 0:
				tradBillions = "";
				break;
			case 1:
				tradBillions = convertBelowThousand(billions) + " billion ";
				break;
			default:
				tradBillions = convertBelowThousand(billions) + " billion ";
			}
			String result = tradBillions;

			String tradMillions;
			switch (millions) {
			case 0:
				tradMillions = "";
				break;
			case 1:
				tradMillions = convertBelowThousand(millions) + " million ";
				break;
			default:
				tradMillions = convertBelowThousand(millions) + " million ";
			}
			result = result + tradMillions;
			
			

			String tradHundredThousands;
			switch (hundredThousands) {
			case 0:
				tradHundredThousands = "";
				break;
			case 1:
				tradHundredThousands = "one thousand ";
				break;
			default:
				tradHundredThousands = convertBelowThousand(hundredThousands) + " thousand ";
			}
			result = result + tradHundredThousands;

			String tradThousand;
			tradThousand = convertBelowThousand(thousands);
			result = result + tradThousand;

			// remove extra spaces!
			output = result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
		} catch (NullPointerException e) {
			System.out.println("Please eneter Value");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;

	}

	/**
	 * testing
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {

		System.out.println("0 =  " + NumberToWord.convert(0));
		System.out.println("1 =  " + NumberToWord.convert(1));
		System.out.println("13 =  " + NumberToWord.convert(13));		
		System.out.println("115 =  " + NumberToWord.convert(115));
		System.out.println("300 =  " + NumberToWord.convert(300));
		System.out.println("539 =  " + NumberToWord.convert(539));
		System.out.println("1232 = " + NumberToWord.convert(1232));
		System.out.println("1000000 =  " + NumberToWord.convert(1000000));
		System.out.println("999999999 =  " + NumberToWord.convert(999999999));
		// System.out.println("0 = " + NumberToWord.convert((Long) null));

	}
}