package de.lempikbubar.src.blatt04;
public class Point
{
    private double[] values;
    private int d = 0;
    
    public Point(double... values) throws IllegalArgumentException
    {
        d = values.length;
        this.values = new double[d];
        for(int i = 0; i < d; i++)
        {
            this.values[i] = values[i];
        }
    }
    
    public double get(int i)
    {
        return values[i];
    }

    public int dim()
    {
        return d;
    }
}
