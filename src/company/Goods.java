package company;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;


/**
 * Created by anthony on 2/20/18.
 */

@Entity
public class Goods implements Serializable {
    @Id
    @GeneratedValue
    public long id;

    public String description;
    public String color;
    public String type;
    @ManyToMany
    public HashMap<Branch, Integer> quantity;
    @ManyToMany
    public List<Goods> consistOf;
    @ManyToMany(mappedBy = "goods")
    public List<Order> orders;

    public Goods() {
    }


}