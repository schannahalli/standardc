package hello;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Compute {
    @Id
    @GeneratedValue
    private Long id;
    private Double a;
    private Double b;
    private Double c;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private Double x;
    private Double y1 ;
    private Double y2;
    private Double ratio;

    private Long userId;

    Compute(){

    }

    Compute(Double a,Double b,Double c,Double x){
        this.a=a;
        this.b=b;
        this.c=c;
        this.x=x;
        this.y1=0D;
        this.y2=0D;


    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;

    public Long getId() {
        return id;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY1() {
        return y1;
    }

    public void setY1(Double y1) {
        this.y1 = y1;
    }

    public Double getY2() {
        return y2;
    }

    public void setY2(Double y2) {
        this.y2 = y2;
    }
}
