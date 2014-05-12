package de.lempikbubar.src.blatt04;
public class Triangle extends Simplex
{

    public Triangle(Point... points){
        super(points);
    }

    public boolean validate()
    {
        if(this.get().length == 3){
            for(Point point : this.get()){
                if(point.dim() != 2){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    

}
