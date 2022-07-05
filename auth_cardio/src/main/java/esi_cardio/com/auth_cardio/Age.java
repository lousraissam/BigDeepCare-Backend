package esi_cardio.com.auth_cardio;

import java.time.*;
import java.util.Date;

public class Age {
    public static int getAge(Date dateNaissance){
        Instant instant =  dateNaissance.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        //diff btw givenDate & currentDate
        Period period = Period.between(givenDate, LocalDate.now());
        return period.getYears();
    }
}
