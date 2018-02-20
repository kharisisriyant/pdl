package company;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by anthony on 2/20/18.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    public long id;

    @Temporal(TemporalType.DATE)
    public Date orderDate;

    @ManyToMany
    public List<Goods> goods;
    @ManyToOne
    public Employee employee;

	@ManyToOne
    public Branch branch;
    @ManyToOne
    public Customer customer;
    @ManyToOne
    public Card card;

    public Order() {
    }
    
    @Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", goods=" + goods + ", employee=" + employee
				+ ", branch=" + branch + ", customer=" + customer + ", card=" + card + "]";
	}
}
