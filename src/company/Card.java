package company;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Card {
	@Id @GeneratedValue
	public Long number;
	public String type;
	public String issuingBank;
	public int totalAmountOfTransaction;

	@Temporal(TemporalType.DATE)
	public Date lastDateOfTransaction;
	@Temporal(TemporalType.DATE)
	public Date expiryDate;

	@OneToMany(mappedBy = "card")
	public List<Order> orders;
	@ManyToOne
	Customer customer;
	
	public Date getLastDateOfTransaction() {
		return lastDateOfTransaction;
	}
	
	@Override
	public String toString() {
		return "Card [number=" + number + ", type=" + type + ", issuingBank=" + issuingBank
				+ ", totalAmountOfTransaction=" + totalAmountOfTransaction + ", lastDateOfTransaction="
				+ lastDateOfTransaction + ", expiryDate=" + expiryDate + ", orders=" + orders + ", customer=" + customer
				+ "]";
	}

	public int getTotalAmountOfTransaction() {
		return totalAmountOfTransaction;
	}
}
