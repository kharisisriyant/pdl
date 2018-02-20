package company;

import javax.persistence.*;

@Entity
public class ExecutiveCustomer extends Customer {
	private double discount_rate;

	public ExecutiveCustomer() {
		this.discount_rate = 5.0;
	}
	
	public ExecutiveCustomer(String name, String birth_date, double discount_rate) {
		super(name, birth_date);
		this.discount_rate= discount_rate;
	}

	
}
