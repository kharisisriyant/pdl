package company;

import javax.persistence.*;

@Entity
public class ExecutiveCustomer extends Customer {
	public double discount_rate;	
}
