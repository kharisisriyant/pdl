package company;

import javax.persistence.*;

public class RegularCustomer extends Customer {
	public int points;
		
	public int countPoints() {
		return points;
	}
}
