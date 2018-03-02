package com.bigeek.reactive.constants;

import com.bigeek.reactive.domain.Person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Constant class with some person instances used to initialize DB and testing.
 */
public class Persons {

    private Persons() {
        super();
    }

    public static final Person PERSON_1 = Person.builder()
            .personId(1)
            .name("Álvaro")
            .lastName("Martin")
            .birthDate(Date.from(LocalDate.now()
                    .minusYears(35).withDayOfMonth(22).withMonth(4).atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .build();
    public static final Person PERSON_2 = Person.builder()
            .personId(2)
            .name("Pedro")
            .lastName("Gómez")
            .birthDate(Date.from(LocalDate.now()
                    .minusYears(52).withDayOfMonth(16).withMonth(9).atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .build();
    public static final Person PERSON_3 = Person.builder()
            .personId(3)
            .name("Sara")
            .lastName("Varas")
            .birthDate(Date.from(LocalDate.now()
                    .minusYears(28).withDayOfMonth(1).withMonth(11).atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .build();
    public static final Person PERSON_4 = Person.builder()
            .personId(4)
            .name("Julia")
            .lastName("Gutierrez")
            .birthDate(Date.from(LocalDate.now()
                    .minusYears(40).withDayOfMonth(30).withMonth(8).atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .build();
    public static final Person PERSON_5 = Person.builder()
            .personId(5)
            .name("David")
            .lastName("Martin")
            .birthDate(Date.from(LocalDate.now()
                    .minusYears(20).atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant()))
            .build();
}
