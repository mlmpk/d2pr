package de.lempikbubar.src.blatt04;
public abstract class Simplex
{
    private Point[] points;    

    // Contructor
    public Simplex(Point... points)
    {
        int d = points[0].dim();
        this.points = new Point[d+1];
        
        if(points.length == d+1)
        {
            for(int i = 0; i < d+1; i++)
            {
                this.points[i] = points[i];
            }
        }
        else
        {
            for(int i = 0; i < d; i++)
            {
                this.points[i] = points[i];
            }
        }
    }
    
    /**
     * Die Methode validate sollte von spezialisierten Klassen  implementiert werden,
     * um entsprechende Validierungen bereitzustellen
     * @return
     */
    public abstract boolean validate();
    
    /**
     * Gibt den Punkt bzw. das Punkte Array zurück
     * @return
     */
    public Point[] get(){
        return points;
    };

    /**
     * Gibt die Summe der Seitenlängen des Simplex zurück
     * @return Perimeter als double
     * @throws DifferentDimensionException
     */
    public double perimeter() throws DifferentDimensionException{
    	
    	double perimeter = 0.0;
    
    	EuclidDistance euclid = new EuclidDistance();
    	
    	// Betrachte alle angegebenen Punkte,
    	// berechne die Distanz der Strecken von p(1) bis p(länge-1) und summiere in perimeter
    	for(int i = 0; i < points.length-1;i++){
    		perimeter += euclid.distance(points[i], points[i+1]);
    	}
    	// abschließend berechne noch Abstand zwischen erstem und letztem Punkt und füge es zu perimeter hinzu
    	perimeter += euclid.distance(points[0], points[points.length-1]);
    	
	    return perimeter;
    }
    
}
