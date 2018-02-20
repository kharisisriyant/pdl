package company;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.*;

@Entity
public abstract class Customer {
	
	@Id @GeneratedValue
	public Long ID;
	
	public String name;
	
	@Temporal(TemporalType.DATE)
	public Date birth_date;
	
	public int age;
	
	public Location location;

	public Customer() {
		name = "test";
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		try {
			birth_date = format.parse("11/07/1996");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Customer(String name, String birth_date) {
		this.name = name;
		DateFormat format = new SimpleDateFormat("dd/mm/yyyy");
		try {
			this.birth_date = format.parse(birth_date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int countAge() {
		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();
		birthDate.setTime(this.birth_date);
		
		int todayYear = today.get(Calendar.YEAR);
	    int birthDateYear = birthDate.get(Calendar.YEAR);
	    int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
	    int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
	    int todayMonth = today.get(Calendar.MONTH);
	    int birthDateMonth = birthDate.get(Calendar.MONTH);
	    int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
	    int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
	    age = todayYear - birthDateYear;

	    // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
	    if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)){
	        age--;
	    
	    // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
	    } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)){
	        age--;
	    }
	    
	    return age;
	}
}
