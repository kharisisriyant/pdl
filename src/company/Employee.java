package company;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anthony on 2/20/18.
 */

@Entity
public class Employee {
    @Id
    @GeneratedValue
    public long id;

    public String name;
    public List<String> phoneNumber;
    @Temporal(TemporalType.DATE)
    public Date startingDate;
    public Integer baseSalary;
    @Temporal(TemporalType.DATE)
    public Date birthDate;
    @Embedded
    public Location location;

    @OneToMany(mappedBy = "employee")
    public List<Order> orders;
    @OneToMany(mappedBy = "consultant")
    public List<ExecutiveCustomer> executiveCustomers;
    @ManyToOne
    public Branch branch;

    @Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", startingDate="
				+ startingDate + ", baseSalary=" + baseSalary + ", birthDate=" + birthDate + ", location=" + location
				+ ", orders=" + orders + ", executiveCustomers=" + executiveCustomers + "]";
	}

	public int countAge() {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        birthDate.setTime(this.birthDate);

        int todayYear = today.get(Calendar.YEAR);
        int birthDateYear = birthDate.get(Calendar.YEAR);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int birthDateDayOfYear = birthDate.get(Calendar.DAY_OF_YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int birthDateMonth = birthDate.get(Calendar.MONTH);
        int todayDayOfMonth = today.get(Calendar.DAY_OF_MONTH);
        int birthDateDayOfMonth = birthDate.get(Calendar.DAY_OF_MONTH);
        int age = todayYear - birthDateYear;

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDateDayOfYear - todayDayOfYear > 3) || (birthDateMonth > todayMonth)) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDateMonth == todayMonth) && (birthDateDayOfMonth > todayDayOfMonth)) {
            age--;
        }

        return age;
    }

}
