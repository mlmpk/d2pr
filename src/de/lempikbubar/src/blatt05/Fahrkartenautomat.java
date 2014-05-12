package de.lempikbubar.src.blatt05;

public class Fahrkartenautomat {

	public static int[] euro = { 200, 100, 50, 20, 10, 5, 2, 1 };
	public static int[] alternativeCurrency = { 200, 100, 50, 20, 10, 5, 4, 2, 1 };
	public static int[] changed = null;
	public static int change;
	public static String chosenCurrency;

	/**
	 * Greedy Algorithmus zur Bestimmung von R??ckgeldbetr??gen
	 * @param betrag
	 * @param werte Array
	 * @return
	 */
	public static int[] change(int betrag, int[] werte) {

		changed = new int[werte.length]; // Initialisierung des R??ckgabe Arrays mit dem Wert der angeforderten W??hrung

		for (int i = 0; i < werte.length; i++) {
			if (betrag >= werte[i]) {
				changed[i] = betrag / werte[i];
				betrag = betrag - werte[i] * changed[i];
			}
		}

		return changed;
	}

	public static void main(String[] args) {

		try {
			// Abfrage der Eingabeparameter
			chosenCurrency = args[0];
			change = Integer.valueOf(args[1]);

			//Aufruf der Wechselmethode mit den entsprechenden Parametern
			if (chosenCurrency.equals("Euro")) {
				changed = change(change, euro);
			} else if (chosenCurrency.equals("Alternative")) {
				changed = change(change, alternativeCurrency);
			}

			printResult(); // Ausgabe des Resultats

		} catch (Exception e) {
			System.out.println("Bitte richtige Werte angeben! Ker, lies die Aufgabenstellung !");
		}

	}

	/**
	 * Gibt das Ergebnis des Greedy Algorithmus zur Ausgabe von Wechselgeld wieder zur??ck
	 */
	private static void printResult() {

		System.out.println("Eingegeben: "+change+ " W??hrung: " + chosenCurrency);
		System.out.print("{");

		if (chosenCurrency.equals("Euro")) {

			for (int i = 0; i < euro.length; i++) {
				System.out.print(euro[i]);
				if (i < euro.length - 1)
					System.out.print(",");

			}
			System.out.println("}");

		} else {

			for (int i = 0; i < alternativeCurrency.length; i++) {
				System.out.print(alternativeCurrency[i]);
				if (i < alternativeCurrency.length - 1) {
					System.out.print(",");
				}
			}
			System.out.println("}");

		}

		// Ausgabe des Ergebnisses
		System.out.print("{");
		for (int i = 0; i < changed.length; i++) {
			System.out.print(changed[i]);
			if (i < changed.length - 1)
				System.out.print(",");
		}
		System.out.println("}");
	}
}
