package company;

import javax.persistence.Entity;

@Entity
public class RegularCustomer extends Customer {
	public int points;
		
	public int countPoints() {
		return points;
	}

	@Override
	public String toString() {
		return "RegularCustomer{" +
				"points=" + points +
				"} " + super.toString();
	}
}
