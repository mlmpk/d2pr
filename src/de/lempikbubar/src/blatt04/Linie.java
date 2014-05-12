package de.lempikbubar.src.blatt04;
public class Linie {

	private Point a;
	private Point b;
	private boolean steigungUndefiniert;
	private double steigung;

	public Linie(Point p1, Point p2) {
		a = p1;
		b = p2;

		if (a.get(0) == b.get(0)) // wenn die x-Werte in beiden Punkten Punkten
							      // gleich ist, dann ist die Steigung
								  // undefiniert
			steigungUndefiniert = true;
		else {
			if (b.get(1) == a.get(1)) // wenn die y-Werte der beiden Punkte
										// übereinstimmt, ist die Steigung 0
				steigung = 0;
			else
				steigung = (b.get(1) - a.get(1)) / (b.get(0) - a.get(0)); // Berechnung der Steigung p2(y)-p1(y) / p2(x) - p1(x)
			steigungUndefiniert = false;
		}

	}

	public boolean linksVonLinie(Point punkt) {
		if (this.steigungUndefiniert) {
			if ( punkt.get(0) < a.get(0) )
				return true; // wenn die x-Koordinate des Punktes kleiner ist
								// als der der Linie, liegt der Punkt links der
								// Linie
			else {
				if (punkt.get(0) == a.get(0)) { // ansonsten wenn die  x-Koordinaten übereinstimmen, prüfe
					// ob die y- Koordinaten entsprechend
					if (((punkt.get(1) > a.get(1)) && (punkt.get(1) < b.get(1)))  ||  ((punkt.get(1) > b.get(1)) && (punkt.get(1) < a.get(1))))
					{
						return true;
					}
					else
					{
						return false;
					}
				} 
				else
				{
					return false;
				}
			}
		}

		return false;
	}

}
