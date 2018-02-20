package company;

/**
 * Created by anthony on 2/20/18.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Goods implements Serializable {
    @Id @GeneratedValue
    private long id;

    public Goods() {
    }

    

}