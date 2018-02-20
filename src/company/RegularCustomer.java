package company;

import javax.persistence.*;

public class RegularCustomer extends Customer {
	public int points;
	
	public RegularCustomer() {
		this.points = 10;
	}
	
	public RegularCustomer(String name, String birth_date, int points) {
		super(name, birth_date);
		this.points = points;
	}
	
	public int countPoints() {
		return points;
	}
}
