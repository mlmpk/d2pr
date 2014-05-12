package de.lempikbubar.src.blatt04;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvexHull {
	
	public List<Point> simpleConvex(Point[] p) {

		List<Point> liste = new ArrayList<>();

		boolean ganzLinks, ganzRechts;
		
		for (int i = 0; i < p.length; i++) {
			
			for (int j = (i + 1); j < p.length; j++) {
				
				ganzLinks = true;
				ganzRechts = true;
				
				// wir brauchen eine Linie, um zu ermitteln
				// ob ein Punkt links davon liegt
				Linie linie = new Linie(p[i], p[j]);

				for (int k = 0; k < p.length; k++) {
				
					if ((k != i) && (k != j)) { // damit der Punkt nicht mit sich selbst verglichen wird
						if (linie.linksVonLinie( p[k] )) // wenn der Punkt links von der linie ist
							ganzLinks = false; // ist er nicht ganz links
						else
							ganzRechts = false;
					}
					if (ganzLinks || ganzRechts) { // wenn der Punkt entweder ganz links oder ganz rechts ist, füge ihn der Liste hinzu
						liste.add(p[i]);
						liste.add(p[j]);
					}
				}

			}
		}
		
		Set<Point> menge = new HashSet<Point>(liste); 
		
		ArrayList<Point> cleanedList = new ArrayList<>(); // Werfe doppelte Punkte aus der Liste
		cleanedList.addAll(menge);
		
		return cleanedList;
	}
	
	
	public static void main(String[] args) {
		
		ConvexHull hull = new ConvexHull();
		
		Point p1 = new Point(1,4);
		Point p2 = new Point(2,2);
		Point p3 = new Point(2,3);
		Point p4 = new Point(2,4);
		Point p5 = new Point(3,2);
		Point p6 = new Point(3,3);
		Point p7 = new Point(3,5);
		Point p8 = new Point(4,4);
		Point p9 = new Point(5,2);
		Point p10 = new Point(5,3);
		Point p11 = new Point(5,4);
		
		Point[] p = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
		List<Point> liste = hull.simpleConvex(p);
		
		for (Point point : liste) {
			System.out.println("("+point.get(0)+"|"+point.get(1)+")");
		}
	}
	
}
