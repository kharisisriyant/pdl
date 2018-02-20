package company;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ExecutiveCustomer extends Customer {
    public double discount_rate;
    @ManyToOne
    public Employee consultant;
}
