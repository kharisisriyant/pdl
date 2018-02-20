package company;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            RegularCustomer r = new RegularCustomer();
            r.name = "Orang " + i;
            r.birth_date = generateRandomDate();
            Location l = new Location();
            l.address = "Jalan " + i + " nomor " + i;
            l.city = "Bandung";
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
            System.out.println(p);
        }

        // Close the database connection:
        em.close();
        emf.close();
    }
    
    static Date generateRandomDate() {
        Random  rnd;
        Date    dt;
        long    ms;

        // Get a new random instance, seeded from the clock
        rnd = new Random();

        // Get an Epoch value roughly between 1940 and 2010
        // -946771200000L = January 1, 1940
        // Add up to 70 years to it (using modulus on the next long)
        ms = -946771200000L + (Math.abs(rnd.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));

        // Construct a date
        dt = new Date(ms);
        return dt;
    }
}