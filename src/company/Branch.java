package company;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by anthony on 2/20/18.
 */

@Entity
public class Branch {
    @Id
    @GeneratedValue
    public long id;

    public String name;
    public Integer area;
    public Boolean isHeadquarter;
    @Embedded
    public Location location;
    @ManyToMany(mappedBy = "quantity")
    public HashMap<Goods, Integer> stockpile;
    @OneToMany(mappedBy = "branch")
    public List<Employee> employeeList;
    @OneToMany(mappedBy = "branch")
    public List<Order> orders;
	@Override
	public String toString() {
	
	return "Branch [id=" + id + ", name=" + name + ", area=" + area + ", isHeadquarter=" + isHeadquarter
				+ ", location=" + location + ", stockpile=" + stockpile + ", employeeList=" + employeeList + ", orders="
				+ orders + "]";
	}

    
    
}
