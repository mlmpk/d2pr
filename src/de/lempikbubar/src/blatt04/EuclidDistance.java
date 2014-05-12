package de.lempikbubar.src.blatt04;
public class EuclidDistance implements Distance {

	/**
	 * Gibt den Euklidischen Abstand zwischen zwei Punkten zurück
	 */
	public double distance(Point p1, Point p2) throws DifferentDimensionException {

		// Berechnung erfolgt nur, wenn beide Punkte dieselbe Dimension aufweisen
		if (p1.dim() != p2.dim())
			throw new DifferentDimensionException();

		int dimension = p1.dim();   // da die Dimension gleich sind, nehme die von p1
		double diffSquared = 0;

		// Wende an, für alle Koordinaten, der angegebenen Punkte
		// Euklidischer Abstand:
		// Die Summe der Quadrate der Differenzen der einzelnen Koordinaten
		for (int i = 0; i < dimension; i++) {
			diffSquared += Math.pow(p1.get(i) - p2.get(i), 2);
		}

		// und davon die Wurzel
		double distance = Math.sqrt(diffSquared);

		return distance;

	}

}
