package inputmanager;

import java.util.Scanner;

public class InputManager {
	
	/*
	 * Get an integer and validate
	 */
	public static int getNumber(String text) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		int number = 0;
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				number = sc.nextInt();
				valid = true;
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.next();
			}
		} while (!valid);
		return number;
	}
	/*
	 * Get an integer validate with condition
	 */
	public static int getNumberBiggerCondition(String text, int nCondition) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		int number = 0;
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				number = sc.nextInt();
				valid = true;
				if (number <= nCondition) {
					valid = false;
					System.out.println("[!] El numero introducido tiene que ser mayor que " + nCondition);
				}			
				
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.next();
			}
		} while (!valid);
		return number;
	}
	/*
	 * Get an integer validate in a range
	 */
	public static int getNumberRange(String text, int nLower, int nMax) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		int number = 0;
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				number = sc.nextInt();
				valid = true;
				if (number < nLower || number > nMax) {
					valid = false;
					System.out.println("[!] El numero introducido tiene que ser mayor o igual que " + nLower + " y menor o igual que " + nMax);
				}			
				
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.nextLine();
			}
		} while (!valid);
		return number;
	}
	
	/*
	 * Get a String and validate
	 */
	
	public static String getString(String text) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String word = "";
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				word = sc.nextLine();
				valid = true;
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.next();
			}
		} while (!valid);
		return word;
	}
	/*
	 * Get a String Not Empty and validate
	 */
	public static String getStringNotEmpty(String text) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String word = "";
		
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				word = sc.nextLine();
				valid = true;
				if (word.equals("")) {
					valid = false;
					System.out.println("[!] No puede estar vacio");
				}
				
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.next();
			}
		} while (!valid);
		return word;
	}
	/*
	 * Get a String Not Empty, only characters and validate
	 */
	public static String getStringNEOC(String text) {
		Scanner sc = new Scanner(System.in);
		boolean valid = false;
		String word = "";
		
		do {
			try {
				System.out.print("[>] Dime " + text + ": ");
				word = sc.nextLine();
				System.out.print(word.equals("") || !word.matches("[a-zA-Z.\u00F1\u00D1 ]*") ? "[!] No puede estar vacio ni contener simbolos\n": "" );
				valid = word.equals("") ||!word.matches("[a-zA-Z.\u00F1\u00D1 ]*");
				
			} catch (Exception e) {
				System.out.println("[!] Tipo de dato incorrecto");
				sc.next();
			}
		} while (!valid);
		return word;
	}
	/*
	 * Press ENTER
	 */
	
	public static void pressEnter() {
        Scanner sc = new Scanner(System.in);
	    sc.nextLine();
	}
	/*
	 * Clean console with System.out.println
	 */
	public static void limpiarConsola() {
		for (int i = 0; i < 50; ++i) System.out.println(); 
	}
	
	
}
