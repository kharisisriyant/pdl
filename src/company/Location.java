package company;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    String address;
    String city;
    String province;
    String post_code;

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", post_code='" + post_code + '\'' +
                '}';
    }
}