package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.lang.Math ;
import java.util.Date;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Long id;


	private String userName;

	private String emailId ;

	private String password ;

	private Date loginDate ;

	private Boolean isLoggedIn ;

	private Boolean isLogged ;

	private String lastName ;

	private String firstName ;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private Double a ;
	private Double b ;
	private Double c ;
	private Double x ;
	private Double y1 ;
	private Double y2 ;
	private Double y3 ;

	protected Customer() {
	}

	public Customer(String emailId,String userName) {
		this.emailId = emailId ;
		this.userName = userName;
		this.a = a;
		this.b = b ;
		this.c = c ;
		this.x = x ;
		this.y1 = a*(x*x)+b*x+c ;
		this.y2 = a*Math.sin(b) ;
		this.y3 = y1/y2 ;
	}

    public Date getLoginDate() {
        return loginDate;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getA() {
		return a;
	}

	public void setA(Double a) {
		this.a = a;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String firstName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public Double getY3() {
		return y3;
	}

	public void setY3(Double y3) {
		this.y3 = y3;
	}

	@Override
	public String toString() {
		return String.format("Customer[id=%d, username='%s', email='%s']", id,
                userName, emailId);
	}

}
