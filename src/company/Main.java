package company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
    static EntityManager em = emf.createEntityManager();
    static Random rnd = new Random();
    public static void main(String[] args) {
        populateRegCustomer();
        populateExecutiveCustomer();
        populateBranch();
        populateCard();
        populateEmployee();
        populateGoods();
        populateOrder();

        //1
        TypedQuery<Object[]> query = em.createQuery(
                "SELECT COUNT(p), p.location.city FROM RegularCustomer p WHERE p.location.province = :province GROUP BY p.location.city", Object[].class);
        List<Object[]> res1 = query.setParameter("province", "Jawa Barat").getResultList();
        for (Object[] r : res1) {
            System.out.println("Count: " + r[0] + ", City: " + r[1]);
        }
        em.close();
        emf.close();
    }
    
    static Date generateRandomDate(long yearStart, long yearCount) {
        Date    dt;
        long    ms;

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        //default yearStart = 0; yearCount = 70L
        ms = -946771200000L + yearStart + (Math.abs(rnd.nextLong()) % (yearCount * 365 * 24 * 60 * 60 * 1000));
        
        // Construct a date
        dt = new Date(ms);
        return dt;
    }

    static void populateRegCustomer() {
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            RegularCustomer r = new RegularCustomer();
            r.name = "Orang " + i;
            r.birth_date = generateRandomDate(1950, 50);
            Location l = new Location();
            l.address = "Jalan " + i + " nomor " + i;
            l.city = i % 2 == 0 ? "Bandung" : "Bogor";
            l.post_code = "4051" + (i % 10);
            l.province = "Jawa Barat";
            r.location = l;

            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<RegularCustomer> query =
                em.createQuery("SELECT p FROM RegularCustomer p", RegularCustomer.class);
        List<RegularCustomer> results = query.getResultList();
        for (RegularCustomer p : results) {
        	p.countAge();
            System.out.println(p);
        }
    }
    
    static void populateExecutiveCustomer() {
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            ExecutiveCustomer r = new ExecutiveCustomer();
            r.name = "Orang " + (i+10);
            r.birth_date = generateRandomDate(0, 70L);
            Location l = new Location();
            l.address = "Jalan " + (i+10) + " nomor " + (i+10);
            l.city = "Bandung";
            l.post_code = "4051" + (i % 10);
            l.province = "Jawa Barat";
            r.location = l;
            r.discount_rate = 5.0;
            
            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<ExecutiveCustomer> query =
                em.createQuery("SELECT p FROM ExecutiveCustomer p", ExecutiveCustomer.class);
        List<ExecutiveCustomer> results = query.getResultList();
        for (ExecutiveCustomer p : results) {
        	p.countAge();
            System.out.println(p);
        }
    }

    static void populateBranch() {
        em.getTransaction().begin();
        for (int i = 0; i < 5; i++) {
        	Branch r = new Branch();
            r.name = "Branch " + (i+10);
            r.area = 250;
            r.isHeadquarter = i == 0;
            Location l = new Location();
            l.address = "Jalan " + (i+10) + " nomor " + (i+10);
            l.city = "Bandung";
            l.post_code = "4051" + (i % 10);
            l.province = "Jawa Barat";
            r.location = l;
            
            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<Branch> query =
                em.createQuery("SELECT p FROM Branch p", Branch.class);
        List<Branch> results = query.getResultList();
        for (Branch p : results) {
            System.out.println(p);
        }
    }
    
    static void populateCard() {
        em.getTransaction().begin();
        for (int i = 0; i < 15; i++) {
            Card r = new Card();
            if (i % 2 == 0) {
                r.type = "debit";
            } else {
                r.type = "credit";
            }
            switch (i % 3) {
                case 0:
                    r.issuingBank = "BNI";
                    break;
                case 1:
                    r.issuingBank = "BCA";
                    break;
                case 2:
                    r.issuingBank = "BRI";
                    break;
            }
            r.expiryDate = generateRandomDate(75L, 10L);

            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<Card> query =
                em.createQuery("SELECT p FROM Card p", Card.class);
        List<Card> results = query.getResultList();
        for (Card p : results) {
            System.out.println(p);
        }

    }
    
    static void populateEmployee() {
        em.getTransaction().begin();
        for (int i = 0; i < 5; i++) {
            Employee r = new Employee();
            r.name = "Orang " + (i + 10);
            r.baseSalary = randomNumber(5000000, 3000000);
            r.startingDate = generateRandomDate(30L, 40L);
            r.birthDate = generateRandomDate(0, 70L);
            Location l = new Location();
            l.address = "Jalan " + (i + 10) + " nomor " + (i + 10);
            l.city = "Bandung";
            l.post_code = "4051" + (i % 10);
            l.province = "Jawa Barat";
            r.location = l;
            r.countAge();

            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<Employee> query =
                em.createQuery("SELECT p FROM Employee p", Employee.class);
        List<Employee> results = query.getResultList();
        for (Employee p : results) {
            System.out.println(p);
        }

        // Close the database connection:

    }
    
    static int randomNumber(int maximum, int minimum) {
    	Random rn = new Random();
    	int n = maximum - minimum + 1;
    	int i = rn.nextInt() % n;
    	return minimum + i;
    }
    
    static void populateGoods() {
        em.getTransaction().begin();
        for (int i = 0; i < 30; i++) {
        	Goods r = new Goods();
            r.name = "Barang " + (i+1);
            r.description = "Barang untuk keperluan " + (i*i*i*i);
            switch (i%4) {
	            case 0: r.color = "Blue"; break;
	            case 1: r.color = "Black"; break;
	            case 2: r.color = "Red"; break;
	            case 3: r.color = "White"; break;
            }
            switch (i%3) {
	            case 0: r.type = "utility"; break;
	            case 1: r.type = "electronics"; break;
	            case 2: r.type = "games"; break;
            }
            
            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<Goods> query =
                em.createQuery("SELECT p FROM Goods p", Goods.class);
        List<Goods> results = query.getResultList();
        for (Goods p : results) {
            System.out.println(p);
        }
    }
    
    static void populateOrder() {
        em.getTransaction().begin();
        for (int i = 0; i < 60; i++) {
            Order r = new Order();

            r.orderDate = generateRandomDate(70L, 10L);

            em.persist(r);
        }
        em.getTransaction().commit();

        TypedQuery<Order> query =
                em.createQuery("SELECT p FROM Order p", Order.class);
        List<Order> results = query.getResultList();
        for (Order p : results) {
            System.out.println(p);
        }
    }
}