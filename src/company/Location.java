package company;

import javax.persistence.*;

@Embeddable
public class Location {
	String address;
	String city;
	String province;
	String post_code;
}