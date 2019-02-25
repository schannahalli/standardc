package hello;

public class ComputeObj  {


    private final Double x ;
    private final Double a ;
    private final Double b ;
    private final Double c ;
    private final Double y1 ;
    private final Double y2 ;

    ComputeObj(Double x,Double a,Double b,Double c,Double y1, Double y2){
        this.a = a;
        this.b = b ;
        this.c = c ;
        this.x = x ;
        this.y1 = y1 ;
        this.y2 = y2 ;
    }

    public Double getX() {
        return x;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public Double getY1() {
        return y1;
    }

    public Double getY2() {
        return y2;
    }
}
