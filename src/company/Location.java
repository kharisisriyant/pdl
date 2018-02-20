package company;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    String address;
    String city;
    String province;
    String post_code;
}