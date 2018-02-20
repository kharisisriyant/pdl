package company;

import java.util.Date;

import javax.persistence.*;

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

	public Date getLastDateOfTransaction() {
		return lastDateOfTransaction;
	}
	
	public int getTotalAmountOfTransaction() {
		return totalAmountOfTransaction;
	}
	
	@ManyToOne Customer customer;
}
