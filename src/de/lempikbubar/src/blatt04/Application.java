package de.lempikbubar.src.blatt04;
public class Application {

	public static void main(String[] args) {

		try {
			
			//Deklariret Koordinaten von Punkten
			double x1 = 0;
			double y1 = 0;

			double x2 = 0;
			double y2 = 0;

			double x3 = 0;
			double y3 = 0;

			//Wenn keine Argumente übergeneb werden, verwendet Zufallskoordinaten
			if ( args.length == 0)
			{
				x1 = random();
				y1 = random();
				
				x2 = random();
				y2 = random();
				
				x3 = random();
				y3 = random();
			}
			//Wenn 6 Koordinaten übergeben werden erfolgt die Zuweisung
			else if ( args.length == 6)
			{
				x1 = Double.valueOf(args[0]);
				y1 = Double.valueOf(args[1]);
				
				x2 = Double.valueOf(args[2]);
				y2 = Double.valueOf(args[3]);
				
				x3 = Double.valueOf(args[4]);
				y3 = Double.valueOf(args[5]);
			}
			else
			{
				throw new IllegalArgumentException();
			}
			
			//Zuordnung von Koordinaten zu den jeweiligen Punkten
			Point p1 = new Point(x1, y1);
			Point p2 = new Point(x2, y2);
			Point p3 = new Point(x3, y3);
			
			//Prüfet ob Dreieck zulässig
			Triangle triangle = new Triangle(p1, p2, p3);
			System.out.println("is valid? " + triangle.validate());

			//Berechnet die jeweiligen Strecken mittels Satz von Euklid
			EuclidDistance euclid = new EuclidDistance();

			System.out.println("Die Distanz zwischen p1("+x1+"|"+y1+") und p2("+x2+"|"+y2+") beträgt: "
					+ euclid.distance(p1, p2));

			System.out.println("Die Distanz zwischen p1("+x1+"|"+y1+") und p3("+x3+"|"+y3+") beträgt: "
					+ euclid.distance(p1, p3));
			
			System.out.println("Die Distanz zwischen p2("+x2+"|"+y2+") und p3("+x3+"|"+y3+") beträgt: "
					+ euclid.distance(p2, p3));

			System.out.println("Der Perimeter beträgt: " + triangle.perimeter());
				
		} catch (DifferentDimensionException e) {
			e.getMessage();		
			
		} catch (IllegalArgumentException e){
			System.out.println("Parameter Falsch!");
		} 
		
	}
	
	//Generiert Zufallszahlen mit den Grenzen von -1000 bis 1000
	public static double random(){
		int grenze = 1000;
		java.util.Random numberGenerator = new java.util.Random();
		double randomNumber = numberGenerator.nextDouble() * grenze;
		if(numberGenerator.nextBoolean()){
			randomNumber *= -1;
		}
		return randomNumber;
	}

}