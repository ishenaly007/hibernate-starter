package com.abit8.hibernatestarter;

import com.abit8.hibernatestarter.converter.BirthdayConverter;
import com.abit8.hibernatestarter.entity.Birthday;
import com.abit8.hibernatestarter.entity.Role;
import com.abit8.hibernatestarter.entity.User;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAttributeConverter(new BirthdayConverter(), true);
        //configuration.addAnnotatedClass(User.class);
        //configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = User.builder()
                    .username("Ishenaly00@gmail.com")
                    .firstname("Ishenaly")
                    .lastname("Narkozuev")
                    .birthDate(new Birthday(LocalDate.of(2007, 2, 3)))
                    .role(Role.ADMIN)
                    .build();
            //session.save(user);
            //session.saveOrUpdate(user);
            session.delete(user);
            session.getTransaction().commit();
        }
    }
}